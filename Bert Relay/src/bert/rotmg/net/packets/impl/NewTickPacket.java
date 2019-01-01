package bert.rotmg.net.packets.impl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import bert.rotmg.model.ObjectStatusData;
import bert.rotmg.net.packets.Packet;
import bert.rotmg.net.packets.Packets;

public class NewTickPacket extends Packet {

	public int tickId;
	public int tickTime;
	public ObjectStatusData[] statuses;
	
	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		this.tickId = in.readInt();
		this.tickTime = in.readInt();
		this.statuses = new ObjectStatusData[in.readShort()];
		for(int i = 0; i < statuses.length; i++) {
			(this.statuses[i] = new ObjectStatusData()).read(in);
		}
	}

	@Override
	public int type() {
		// TODO Auto-generated method stub
		return Packets.NEWTICK;
	}

}
