package bert.rotmg.net.packets;

import bert.rotmg.net.packets.impl.FailurePacket;
import bert.rotmg.net.packets.impl.GotoPacket;
import bert.rotmg.net.packets.impl.MapInfoPacket;
import bert.rotmg.net.packets.impl.NewTickPacket;
import bert.rotmg.net.packets.impl.PingPacket;
import bert.rotmg.net.packets.impl.SuccessPacket;
import bert.rotmg.net.packets.impl.UpdatePacket;

/**
 * An interface for handling packets received from a {@link Client}
 * 
 * @author Bert
 */
public interface PacketHandler {

	@Listen(Packets.FAILURE)
	public void onFailure(FailurePacket failure);
	
	@Listen(Packets.SUCCESS)
	public void onSuccess(SuccessPacket success);
	
	@Listen(Packets.MAPINFO)
	public void onMapInfo(MapInfoPacket mapInfo);
	
	@Listen(Packets.UPDATE)
	public void onUpdate(UpdatePacket update);
	
	@Listen(Packets.NEWTICK)
	public void onNewTick(NewTickPacket newTick);
	
	@Listen(Packets.PING)
	public void onPing(PingPacket ping);
	
	@Listen(Packets.GOTO)
	public void onGoto(GotoPacket go);
}
