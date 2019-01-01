package bert.rotmg.model;

import java.io.DataInput;
import java.io.IOException;

public class ObjectData {

	public int objectType;
	
	public ObjectStatusData status;
	
	public void read(DataInput in) throws IOException {
		this.objectType = in.readUnsignedShort();
		this.status = new ObjectStatusData();
		this.status.read(in);
	}
}
