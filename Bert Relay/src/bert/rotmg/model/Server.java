package bert.rotmg.model;

import bert.rotmg.net.Reconnect;

/**
 * Class for Game Servers like EUNorth or EUWest.
 * 
 * @author Bert
 */
public class Server {
	/**
	 * Server IP Address
	 */
	private final String host;
	/**
	 * Server name
	 */
	private final String name;
	/**
	 * Server latitude
	 */
	private final float latitude;
	/**
	 * Server longitude
	 */
	private final float longitude;
	/**
	 * Creates a new server instance
	 * @param host Host of the server
	 * @param name Name of the server
	 * @param latitude Latitude of the server
	 * @param longitude Longitude of the server
	 */
	public Server(String host, String name, float latitude, float longitude) {
		this.host = host;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	/**
	 * Returns the host of the server
	 * 
	 * @return {@linkplain #host}
	 */
	public String getHost() {
		return host;
	}
	/**
	 * Returns the name of the server
	 * 
	 * @return {@linkplain #name}
	 */
	public String getName() {
		return name;
	}
	/**
	 * Returns the latitude of the server
	 * 
	 * @return {@linkplain #latitude}
	 */
	public float getLatitude() {
		return latitude;
	}
	/**
	 * Returns the longitude of the server
	 * 
	 * @return {@linkplain #longitude}
	 */
	public float getLongitude() {
		return longitude;
	}
	/**
	 * Creates a new {@link Reconnect} instance with this servers host and returns it
	 * 
	 * @return {@link Reconnect} instance targeted to this server
	 */
	public Reconnect toReconnect() {
		return new Reconnect(this.host, 2050, -2);
	}

	@Override
	public String toString() {
		return String.format("[name=%s, host=%s, lat=%f, long=%f]", name, host, latitude, longitude);
	}
}
