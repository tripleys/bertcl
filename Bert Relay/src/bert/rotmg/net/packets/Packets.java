package bert.rotmg.net.packets;

import bert.rotmg.net.packets.impl.FailurePacket;
import bert.rotmg.net.packets.impl.GotoPacket;
import bert.rotmg.net.packets.impl.MapInfoPacket;
import bert.rotmg.net.packets.impl.NewTickPacket;
import bert.rotmg.net.packets.impl.PingPacket;
import bert.rotmg.net.packets.impl.SuccessPacket;
import bert.rotmg.net.packets.impl.UpdatePacket;

/**
 * Constains {@link Packet} IDs
 * 
 * @author Bert
 */
public class Packets {

	public static final short FAILURE = 0;

	public static final short HELLO = 100;

	public static final short SUCCESS = 44;

	public static final short MAPINFO = 85;

	public static final short LOAD = 62;

	public static final short CREATE = 52;

	public static final short UPDATE = 31;

	public static final short UPDATEACK = 80;

	public static final short NEWTICK = 36;

	public static final short MOVE = 74;

	public static final short PING = 4;

	public static final short PONG = 86;

	public static final short GOTO = 76;

	public static final short GOTOACK = 81;

	/**
	 * Creates a new {@link Packet} instance by type and returns it.
	 * @param type Type of the packet
	 * @return {@link Packet} for type
	 */
	public static Packet get(int type) {
		switch (type) {
		case FAILURE:
			return new FailurePacket();
		case SUCCESS:
			return new SuccessPacket();
		case MAPINFO:
			return new MapInfoPacket();
		case UPDATE:
			return new UpdatePacket();
		case NEWTICK:
			return new NewTickPacket();
		case PING:
			return new PingPacket();
		case GOTO:
			return new GotoPacket();
		default:
			return null;
		}
	}
}
