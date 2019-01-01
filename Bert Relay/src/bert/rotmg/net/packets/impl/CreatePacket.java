package bert.rotmg.net.packets.impl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import bert.rotmg.net.packets.Packet;
import bert.rotmg.net.packets.Packets;

public class CreatePacket extends Packet {

	public short classType;
	public short skinType;
	
	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeShort(this.classType);
		out.writeShort(this.skinType);
	}

	@Override
	public void read(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int type() {
		// TODO Auto-generated method stub
		return Packets.CREATE;
	}

}
