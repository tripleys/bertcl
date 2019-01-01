package bert.rotmg;

/**
 * Class for account information used to connect to the game
 * 
 * @author Bert
 */
public class AccountData {
	/**
	 * Email of the account
	 */
	private final String guid;
	/**
	 * Password of the account
	 */
	private final String password;
	/**
	 * Character Id of the account
	 */
	public int charId;
	/**
	 * Next Character Id of the account
	 */
	public int nextCharId;
	/**
	 * Creates new {@linkplain AccountData} instance.
	 * 
	 * Use character id <code>0</code> if you want to create new character upon connecting
	 * 
	 * @param guid Account's email address
	 * @param password Account's password
	 * @param charId Character Id that will be used when loading a character
	 * @param nextCharId Account's next character id
	 */
	public AccountData(String guid, String password, int charId, int nextCharId) {
		this.guid = guid;
		this.password = password;
		this.charId = charId;
		this.nextCharId = nextCharId;
	}
	/**
	 * Getter for {@link #guid}
	 * @return Email of the account
	 */
	public String getGUID() {
		return this.guid;
	}
	/**
	 * Getter for {@link #password}
	 * @return Password of the account
	 */
	public String getPassword() {
		return this.password;
	}
}
