package bert.engine;

/**
 * Canvas interface that is used for an {@link Engine}'s loop logic.
 * 
 * @author Bert
 */
public interface Canvas {
	/**
	 * Called once per frame
	 * @param delta Milliseconds since last frame
	 */
	public void render(long delta);
}
