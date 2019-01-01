package bert.rotmg.net.packets.impl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import bert.rotmg.net.packets.Packet;
import bert.rotmg.net.packets.Packets;

public class LoadPacket extends Packet {

	public int charId;
	public boolean isFromArena;
	
	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeInt(this.charId);
		out.writeBoolean(this.isFromArena);
	}

	@Override
	public void read(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int type() {
		// TODO Auto-generated method stub
		return Packets.LOAD;
	}

}
