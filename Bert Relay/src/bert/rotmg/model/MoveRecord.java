package bert.rotmg.model;

import java.io.DataOutput;
import java.io.IOException;

public class MoveRecord {

	public int time;
	public float x;
	public float y;
	
	public MoveRecord() {
		
	}
	
	public MoveRecord(int time, float x, float y) {
		this.time = time;
		this.x = x;
		this.y = y;
	}
	
	public void write(DataOutput out) throws IOException {
		out.writeInt(this.time);
		out.writeFloat(this.x);
		out.writeFloat(this.y);
	}
}
