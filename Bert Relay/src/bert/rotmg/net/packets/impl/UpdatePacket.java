package bert.rotmg.net.packets.impl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import bert.rotmg.model.GroundTileData;
import bert.rotmg.model.ObjectData;
import bert.rotmg.net.packets.Packet;
import bert.rotmg.net.packets.Packets;

public class UpdatePacket extends Packet {

	public GroundTileData[] tiles;
	public ObjectData[] newObjects;
	public int[] drops;
	
	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		this.tiles = new GroundTileData[in.readShort()];
		for(int i = 0; i < this.tiles.length; i++) {
			(this.tiles[i] = new GroundTileData()).read(in);
		}
		this.newObjects = new ObjectData[in.readShort()];
		for(int i = 0; i < this.newObjects.length; i++) {
			(this.newObjects[i] = new ObjectData()).read(in);
		}
		drops = new int[in.readShort()];
		for(int i = 0; i < drops.length; i++) {
			drops[i] = in.readInt();
		}
	}

	@Override
	public int type() {
		// TODO Auto-generated method stub
		return Packets.UPDATE;
	}

}
