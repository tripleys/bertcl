package bert.rotmg.net.packets.impl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import bert.rotmg.net.packets.Packet;
import bert.rotmg.net.packets.Packets;

public class PingPacket extends Packet {

	public int serial;
	
	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		this.serial = in.readInt();
	}

	@Override
	public int type() {
		// TODO Auto-generated method stub
		return Packets.PING;
	}

}
