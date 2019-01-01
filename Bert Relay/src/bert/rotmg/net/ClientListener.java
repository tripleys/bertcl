package bert.rotmg.net;

/**
 * Listens to events received from {@linkplain Client}
 * 
 * @author Bert
 */
public interface ClientListener {
	/**
	 * {@linkplain Client} connected to the socket
	 * @param reconnect {@linkplain Reconnect} used for the {@linkplain Client}
	 */
	public void connected(Reconnect reconnect);
	/**
	 * {@linkplain Client} has made successful connection to the game server
	 * Emitted after receiving success packet.
	 */
	public void ready();
	/**
	 * {@linkplain Client} failed to parse next packet EOF, socket was closed, or force disconnect
	 * @param exitCode	Determines what caused the disconnect
	 */
	public void disconnect(int exitCode);
}
