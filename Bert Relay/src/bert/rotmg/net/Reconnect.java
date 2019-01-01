package bert.rotmg.net;

/**
 * Class for holding connection information
 * 
 * @author Bert
 */
public class Reconnect {
	/**
	 * Default reconnect
	 * Set to EUNorth2 Nexus
	 */
	public static final Reconnect DEFAULT = new Reconnect("52.59.198.155", 2050, -2);
	/**
	 * IP Address of the reconnect target
	 */
	private String host;
	/**
	 * Port of the reconnect target
	 */
	private int port;
	/**
	 * Game Id for the reconnect target
	 */
	private int gameId;
	/**
	 * Time when the key was received
	 */
	private int keyTime;
	/**
	 * Reconnect key
	 */
	private byte[] key;
	/**
	 * New {@linkplain Reconnect} instance with host, port, gameId, keyTime and key
	 * @param host IP Address of the target
	 * @param port Port of the target
	 * @param gameId GameID of the target
	 * @param keyTime Creation time for the key
	 * @param key Reconnect key
	 * <pre>
	 * <code>Nexus = -2</code>
	 * <code>Vault = -5</code>
	 * <code>Random Realm = -3</code>
	 * </pre>
	 */
	public Reconnect(String host, int port, int gameId, int keyTime, byte[] key) {
		this.host = host;
		this.port = port;
		this.gameId = gameId;
		this.keyTime = keyTime;
		this.key = key;
	}
	/**
	 * New {@linkplain Reconnect} instance without keyTime and key.
	 * Host, port and gameId are required.
	 * @param host IP Address of the target
	 * @param port Port of the target
	 * @param gameId GameID of the target
	 * <pre>
	 * <code>Nexus = -2</code>
	 * <code>Vault = -5</code>
	 * <code>Random Realm = -3</code>
	 * </pre>
	 */
	public Reconnect(String host, int port, int gameId) {
		this(host, port, gameId, 0, new byte[0]);
	}
	/**
	 * Getter for {@link #host}
	 * @return {@linkplain #host}
	 */
	public String getHost() {
		return this.host;
	}
	/**
	 * Getter for {@link #port}
	 * @return {@linkplain #port}
	 */
	public int getPort() {
		return this.port;
	}
	/**
	 * Getter for {@link #gameId}
	 * @return {@linkplain #gameId}
	 */
	public int getGameId() {
		return this.gameId;
	}
	/**
	 * Getter for {@link #keyTime}
	 * @return {@linkplain #keyTime}
	 */
	public int getKeyTime() {
		return this.keyTime;
	}
	/**
	 * Getter for {@link #key}
	 * @return {@linkplain #key}
	 */
	public byte[] getKey() {
		return this.key;
	}
}
