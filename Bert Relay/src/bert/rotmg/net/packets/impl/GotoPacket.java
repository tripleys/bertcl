package bert.rotmg.net.packets.impl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import bert.rotmg.model.WorldPosData;
import bert.rotmg.net.packets.Packet;
import bert.rotmg.net.packets.Packets;

public class GotoPacket extends Packet {

	public int objectId;
	public WorldPosData pos;
	
	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		this.objectId = in.readInt();
		this.pos = new WorldPosData();
		this.pos.read(in);
	}

	@Override
	public int type() {
		// TODO Auto-generated method stub
		return Packets.GOTO;
	}

}
