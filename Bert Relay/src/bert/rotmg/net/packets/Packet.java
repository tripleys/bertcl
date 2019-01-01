package bert.rotmg.net.packets;

import java.io.DataInput;

import java.io.DataOutput;
import java.io.IOException;
/**
 * Class for incoming and outgoing packets
 * 
 * @author Bert
 */
public abstract class Packet {
	/**
	 * Writes the data of the {@link Packet} into {@link DataOutput}
	 * @param out The {@link DataOutput} that data will be written to
	 * @throws IOException
	 */
	public abstract void write(DataOutput out) throws IOException;
	/**
	 * Reads the contents of the {@link Packet} from {@link DataInput}
	 * @param in The {@link DataInput} where data will be read from
	 * @throws IOException
	 */
	public abstract void read(DataInput in) throws IOException;
	/**
	 * Returns the type of the {@link Packet}
	 * @return Type of the packet
	 */
	public abstract int type();
}
