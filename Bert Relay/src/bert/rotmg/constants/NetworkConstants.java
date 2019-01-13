package bert.rotmg.constants;

/**
 * Class for immutable values used in the network protocol
 * 
 * @author Bert
 */
public class NetworkConstants {

	/**
	 * RSA server public-key
	 */
	public static final String SERVER_PUBLIC_KEY = 
			"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDCKFctVrhfF3m2Kes0FBL/"
			+ "JFeOcmNg9eJz8k/hQy1kadD+XFUpluRqa//Uxp2s9W2qE0EoUCu59ugcf/"
			+ "p7lGuL99UoSGmQEynkBvZct+/M40L0E0rZ4BVgzLOJmIbXMp0J4PnPcb6V"
			+ "LZvxazGcmSfjauC7F3yWYqUbZd/HCBtawwIDAQAB\n";
	
	/**
	 * Incoming RC4 key
	 */
	public static final String INCOMING_KEY = "c79332b197f92ba85ed281a023";
	/**
	 * Outgoing RC4 key
	 */
	public static final String OUTGOING_KEY = "6a39570cc9de4ec71d64821894";
	
	/**
	 * Build version the only constant you need to change
	 */
	public static final String BUILD = "X31.2.3";
}
