package bert.rotmg.model;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class WorldPosData {

	/**
	 * Coordinate on the horizontal axis
	 */
	public float x;
	/**
	 * Coordinate on the vertical axis
	 */
	public float y;
	/**
	 * Creates new instance with X and Y values
	 * @param x
	 * @param y
	 */
	public WorldPosData(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public WorldPosData() {}
	/**
	 * Writes the position into {@link DataOutput}
	 * @param out
	 * @throws IOException
	 */
	public void write(DataOutput out) throws IOException {
		out.writeFloat(this.x);
		out.writeFloat(this.y);
	}
	/**
	 * Reads the position from {@link DataInput}
	 * @param in
	 * @throws IOException
	 */
	public void read(DataInput in) throws IOException {
		this.x = in.readFloat();
		this.y = in.readFloat();
	}
	/**
	 * Calculates distance from this position to target position
	 * @param pos Target position
	 * @return Distance to target
	 */
	public double distanceTo(WorldPosData pos) {
		float deltaX = this.x - pos.x;
		float deltaY = this.y - pos.y;
		return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	}
}
