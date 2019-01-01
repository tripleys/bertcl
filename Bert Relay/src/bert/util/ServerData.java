package bert.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;

import bert.cache.DataPacket;
import bert.rotmg.model.Server;

public class ServerData extends DataPacket {

	public HashMap<String, Server> data;
	
	public ServerData() {
		this.data = new HashMap<>();
	}
	
	public ServerData(HashMap<String, Server> data) {
		this.data = data;
	}
	
	@Override
	public void read(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		int size = in.readInt();
		for(int i = 0; i < size; i++) {
			String host = in.readUTF();
			String name = in.readUTF();
			data.put(name, new Server(host, name, in.readFloat(), in.readFloat()));
		}
	}

	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeInt(data.size());
		for(Server s : data.values()) {
			out.writeUTF(s.getHost());
			out.writeUTF(s.getName());
			out.writeFloat(s.getLatitude());
			out.writeFloat(s.getLongitude());
		}
	}

}
