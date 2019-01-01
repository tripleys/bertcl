package bert.rotmg.model;

import java.io.DataInput;
import java.io.IOException;

public class GroundTileData {

	public short x;
	
	public short y;
	
	public int type;
	
	public void read(DataInput input) throws IOException {
		this.x = input.readShort();
		this.y = input.readShort();
		this.type = input.readUnsignedShort();
	}
}
