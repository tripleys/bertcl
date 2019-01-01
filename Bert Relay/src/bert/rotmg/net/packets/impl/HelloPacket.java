package bert.rotmg.net.packets.impl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import bert.crypto.RSA;
import bert.rotmg.net.packets.Packet;
import bert.rotmg.net.packets.Packets;

public class HelloPacket extends Packet {

	public String build;
	
	public int gameId;
	
	public String guid;
	
	public String password;
	
	public String secret;
	
	public int keyTime;
	
	public byte[] key;
	
	public String mapJSON;

	public String entryTag;
	public String gameNet;
	public String gameNetUserId;
	public String playPlatform;
	public String platformToken;
	public String userToken;
	
	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeUTF(this.build);
		out.writeInt(this.gameId);
		out.writeUTF(RSA.encrypt(this.guid));
		out.writeInt((int)Math.floor(Math.random() * 1000000000));
		out.writeUTF(RSA.encrypt(this.password));
		out.writeInt((int)Math.floor(Math.random() * 1000000000));
		out.writeUTF(RSA.encrypt(this.secret));
		out.writeInt(this.keyTime);
		if (key == null) {
			out.writeShort(0);
		} else {
			out.writeShort(this.key.length);
			for(byte b : this.key)
				out.writeByte(b);
		}
		if (mapJSON == null) {
			out.writeInt(0);
		} else {
			out.writeInt(mapJSON.length());
			for(byte b : mapJSON.getBytes()) {
				out.writeByte(b);
			}
		}
		out.writeUTF(this.entryTag);
		out.writeUTF(this.gameNet);
		out.writeUTF(this.gameNetUserId);
		out.writeUTF(this.playPlatform);
		out.writeUTF(this.platformToken);
		out.writeUTF(this.userToken);
	}

	@Override
	public void read(DataInput in) {
	}

	@Override
	public int type() {
		// TODO Auto-generated method stub
		return Packets.HELLO;
	}

}
