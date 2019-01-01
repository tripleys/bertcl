package bert.rotmg.model;

/**
 * Map Info model, holds the map information in one simple accessible class
 * @author Bert
 */
public class MapInfo {

	/**
	 * Name of the map
	 */
	private final String name;
	/**
	 * Display name of the map
	 */
	private final String displayName;
	/**
	 * The maps width as tiles
	 */
	private final int width;
	/**
	 * The maps height as tiles
	 */
	private final int height;
	/**
	 * The information is final because it is never changed after receiving the map info
	 * @param name {@link #name}
	 * @param displayName {@link #displayName}
	 * @param width {@link #width}
	 * @param height {@link #height}
	 */
	public MapInfo(String name, String displayName, int width, int height) {
		this.name = name;
		this.displayName = displayName;
		this.width = width;
		this.height = height;
	}
	/**
	 * Getter for the map's name
	 * @return {@linkplain #name}
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Getter for the map's display name
	 * @return {@linkplain #displayName}
	 */
	public String getDisplayName() {
		return this.displayName;
	}
	/**
	 * Getter for the map's width
	 * @return {@linkplain #width}
	 */
	public int getWidth() {
		return this.width;
	}
	/**
	 * Getter for the map's height
	 * @return {@linkplain #height}
	 */
	public int getHeight() {
		return this.height;
	}
}
