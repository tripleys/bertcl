package bert.rotmg.model;

import java.io.DataInput;
import java.io.IOException;

import bert.rotmg.constants.StatType;

/**
 * 
 * Status data that is received from server on Update and NewTick packets
 * 
 * For example status data can be something like a players name or players new position
 * 
 * @author Bert
 */
public class StatData {

	public int statType = 0;

	public int statValue;

	public String stringStatValue;

	public void read(DataInput in) throws IOException {
		this.statType = in.readUnsignedByte();
		if (this.isStringStat()) {
			this.stringStatValue = in.readUTF();
		} else {
			this.statValue = in.readInt();
		}
	}

	private boolean isStringStat() {
		switch (this.statType) {
		case StatType.NAME_STAT:
			return true;
		case StatType.GUILD_NAME_STAT:
			return true;
		case StatType.PET_NAME_STAT:
			return true;
		case StatType.ACCOUNT_ID_STAT:
			return true;
		case StatType.OWNER_ACCOUNT_ID_STAT:
			return true;
		default:
			return false;
		}
	}

}
