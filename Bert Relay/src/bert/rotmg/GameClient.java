package bert.rotmg;

import java.util.ArrayList;

import bert.engine.Canvas;
import bert.engine.Engine;
import bert.rotmg.constants.Characters;
import bert.rotmg.constants.NetworkConstants;
import bert.rotmg.model.MapInfo;
import bert.rotmg.model.MoveRecord;
import bert.rotmg.model.MoveRecords;
import bert.rotmg.model.ObjectData;
import bert.rotmg.model.ObjectStatusData;
import bert.rotmg.model.PlayerData;
import bert.rotmg.model.Server;
import bert.rotmg.model.WorldPosData;
import bert.rotmg.net.Client;
import bert.rotmg.net.ClientListener;
import bert.rotmg.net.Reconnect;
import bert.rotmg.net.packets.PacketHandler;
import bert.rotmg.net.packets.impl.CreatePacket;
import bert.rotmg.net.packets.impl.FailurePacket;
import bert.rotmg.net.packets.impl.GotoAckPacket;
import bert.rotmg.net.packets.impl.GotoPacket;
import bert.rotmg.net.packets.impl.HelloPacket;
import bert.rotmg.net.packets.impl.LoadPacket;
import bert.rotmg.net.packets.impl.MapInfoPacket;
import bert.rotmg.net.packets.impl.MovePacket;
import bert.rotmg.net.packets.impl.NewTickPacket;
import bert.rotmg.net.packets.impl.PingPacket;
import bert.rotmg.net.packets.impl.PongPacket;
import bert.rotmg.net.packets.impl.SuccessPacket;
import bert.rotmg.net.packets.impl.UpdateAckPacket;
import bert.rotmg.net.packets.impl.UpdatePacket;

public class GameClient implements PacketHandler, ClientListener, Canvas {
	
	private Client client;
	
	private Engine engine;

	private Thread engineThread;
	
	private MapInfo mapInfo;
	
	private Server server;
	
	private UpdateAckPacket updateAck;
	
	private PongPacket pong;
	
	private long connectTime;
	
	private int objectId;
	
	private WorldPosData position;
	
	private int lastUpdate;
	
	private WorldPosData target;
	
	private boolean hasTarget;
	
	private double moveDir;
	
	private MoveRecords moveRecords = new MoveRecords();
	
	private Reconnect reconnect;
	
	public final String alias;

	public AccountData accountData;
	
	public PlayerData playerData;
	
	public GameClient(final String alias, AccountData accountData, Server server) throws Exception {
		this.server = server;
		this.client = new Client(server.toReconnect());
		this.client.addPacketHandler(this);
		this.client.addClientListener(this);
		this.engine = new Engine(this, 30);
		this.alias = alias;
		this.accountData = accountData;
		new Thread(this.client).start();
		(this.engineThread = new Thread(this.engine)).start();
	}
	
	@Override
	public void connected(Reconnect reconnect) {
		
		this.reconnect = reconnect;
		
		this.connectTime = System.currentTimeMillis();
		
		this.updateAck = new UpdateAckPacket();
		this.pong = new PongPacket();
		
		HelloPacket hp = new HelloPacket();
		hp.build = NetworkConstants.BUILD;
		hp.gameId = reconnect.getGameId();
		hp.guid = accountData.getGUID();
		hp.password = accountData.getPassword();
		hp.secret = "";
		hp.keyTime = 0;
		hp.entryTag = "";
		hp.gameNet = "rotmg";
		hp.gameNetUserId = "";
		hp.playPlatform = "rotmg";
		hp.platformToken = "";
		hp.userToken = "";
		this.client.sendPacket(hp);
	}

	@Override
	public void ready() {
		this.log("Connected!");
		this.engine.setWorking(true);
	}

	@Override
	public void disconnect(int exitCode) {
		this.log("Disconnected! Exit Code: " + exitCode);
		this.hasTarget = false;
		this.moveRecords.lastClearTime = 0;
		this.moveRecords.records.clear();
		this.engine.setWorking(false);
		if (exitCode < 3) {
			this.reconnect();
		} else {
			this.engineThread.interrupt();
		}
	}

	@Override
	public void onFailure(FailurePacket failure) {
		this.log(String.format("Failure %d | %s", failure.id, failure.description));
		if (failure.id == 0 && failure.description.equalsIgnoreCase("account is under maintenance")) {
			this.log("Account is banned. RIP!");
			this.client.disconnect(3);
			return;
		} else if (failure.id == 0 && failure.description.equalsIgnoreCase("Account already has 1 active characters")) {
			this.accountData.charId = this.accountData.nextCharId;
		}
		this.client.disconnect(2);
	}

	@Override
	public void onSuccess(SuccessPacket success) {
		this.accountData.charId = success.charId;
		this.objectId = success.objectId;
	}

	@Override
	public void onMapInfo(MapInfoPacket mapInfo) {
		this.mapInfo = new MapInfo(mapInfo.name, mapInfo.displayName, mapInfo.width, mapInfo.height);
		this.log(String.format("Connecting to %s!", mapInfo.name));
		if (accountData.charId > 0) {
			LoadPacket load = new LoadPacket();
			load.charId = accountData.charId;
			load.isFromArena = false;
			this.client.sendPacket(load);
		} else {
			CreatePacket create = new CreatePacket();
			create.classType = Characters.WIZARD;
			create.skinType = 0;
			this.client.sendPacket(create);
			this.log("Creating a new character.");
		}
	}

	int followId = -1;
	
	@Override
	public void onUpdate(UpdatePacket update) {
		this.client.sendPacket(updateAck);
		for(ObjectData osd : update.newObjects) {
			if (osd.status.objectId == this.objectId) {
				this.position = osd.status.pos;
				this.playerData = ObjectStatusData.processObject(osd);
				continue;
			}
			if (Characters.isCharacter(osd.objectType)) {
				PlayerData pd = ObjectStatusData.processObject(osd);
				if (pd.name.equals("BertXDaA")) {
					this.followId = osd.status.objectId;
					continue;
				}
			}
		}
	}

	@Override
	public void onNewTick(NewTickPacket newTick) {
		MovePacket move = new MovePacket();
		move.newPosition = this.position;
		move.tickId = newTick.tickId;
		move.time = this.lastUpdate;
		int clearTime = this.moveRecords.lastClearTime;
		move.records = new ArrayList<>();
		if (clearTime >= 0 && move.time - clearTime > 125) {
			int max = 10;
			for(MoveRecord mv : this.moveRecords.records) {
				if (mv.time >= move.time - 25)
					break;
				move.records.add(mv);
				max--;
				if (max <= 0) {
					break;
				}
			}
		}
		this.moveRecords.clear(move.time);
		this.client.sendPacket(move);
		for(ObjectStatusData osd : newTick.statuses) {
			if (osd.objectId == this.followId) {
				this.moveTowards(osd.pos);
				break;
			}
		}
	}

	@Override
	public void onPing(PingPacket ping) {
		pong.serial = ping.serial;
		pong.time = this.intTime();
		this.client.sendPacket(pong);
	}

	@Override
	public void onGoto(GotoPacket go) {
		GotoAckPacket gotoAck = new GotoAckPacket();
		gotoAck.time = this.intTime();
		this.client.sendPacket(gotoAck);
		if (go.objectId == this.objectId) {
			this.position = go.pos;
		}
	}

	@Override
	public void render(long delta) {
		int time = this.intTime();
		if (hasTarget) {
			double speed = delta * this.getSpeed();
			if (target.distanceTo(this.position) <= speed) {
				this.position = target;
				this.hasTarget = false;
			} else {
				this.position.x += speed * Math.cos(moveDir);
				this.position.y += speed * Math.sin(moveDir);
			}
		}
		if (this.position != null) 
			this.moveRecords.addRecord(time, this.position.x, this.position.y);
		this.lastUpdate = time;
	}
	
	public void moveTowards(WorldPosData target) {
		this.target = target;
		this.moveDir = Math.atan2(target.y - this.position.y, target.x - this.position.x);
		this.hasTarget = true;
	}
	
	public double getSpeed() {
		return 0.004 + this.playerData.speed * 0.0133333333333333 * 0.0056;
	}
	
	public void reconnect() {
		this.log("Reconnecting!");
		try {
			this.client = new Client(this.reconnect);
			this.client.addPacketHandler(this);
			this.client.addClientListener(this);
			new Thread(this.client).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int intTime() {
		return (int) (System.currentTimeMillis() - this.connectTime);
	}
	
	public void log(String message) {
		System.out.printf("[%s]: %s%n", this.alias, message);
	}
}
