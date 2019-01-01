package bert.rotmg.net.packets.impl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import bert.rotmg.net.packets.Packet;

public class MapInfoPacket extends Packet {

	public int width;
	public int height;
	public String name;
	public String displayName;
	public int difficulty;
	public long fp;
	public int background;
	public boolean teleport;
	public boolean showDisplays;
	public String[] clientXML;
	public String[] extraXML;
	
	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		this.width = in.readInt();
		this.height = in.readInt();
		this.name = in.readUTF();
		this.displayName = in.readUTF();
		this.fp = Integer.toUnsignedLong(in.readInt());
		this.background = in.readInt();
		this.difficulty = in.readInt();
		this.teleport = in.readBoolean();
		this.showDisplays = in.readBoolean();
		this.clientXML = new String[in.readShort()];
		for(int i = 0; i < this.clientXML.length; i++) {
			byte[] b = new byte[in.readInt()];
			in.readFully(b);
			this.clientXML[i] = new String(b, 0, b.length);
		}
		this.extraXML = new String[in.readShort()];
		for(int i = 0; i < this.clientXML.length; i++) {
			byte[] b = new byte[in.readInt()];
			in.readFully(b);
			this.extraXML[i] = new String(b, 0, b.length);
		}
	}

	@Override
	public int type() {
		// TODO Auto-generated method stub
		return 0;
	}

}
