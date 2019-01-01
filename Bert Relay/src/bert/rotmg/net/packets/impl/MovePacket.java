package bert.rotmg.net.packets.impl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;

import bert.rotmg.model.MoveRecord;
import bert.rotmg.model.WorldPosData;
import bert.rotmg.net.packets.Packet;
import bert.rotmg.net.packets.Packets;

public class MovePacket extends Packet {

	public int tickId;
	public int time;
	public WorldPosData newPosition;
	public ArrayList<MoveRecord> records;
	
	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeInt(this.tickId);
		out.writeInt(this.time);
		this.newPosition.write(out);
		out.writeShort(this.records.size());
		for(MoveRecord mr : this.records)
			mr.write(out);
	}

	@Override
	public void read(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int type() {
		// TODO Auto-generated method stub
		return Packets.MOVE;
	}

}
