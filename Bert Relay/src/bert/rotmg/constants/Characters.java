package bert.rotmg.constants;

/**
 * Character "Class" Types
 * 
 * @author Bert
 */
public class Characters {

	public static final short WIZARD = 782;
	public static final short PRIEST = 784;
	public static final short ARCHER = 775;
	public static final short ROGUE = 768;
	public static final short WARRIOR = 797;
	public static final short KNIGHT = 798;
	public static final short PALADIN = 799;
	public static final short ASSASIN = 800;
	public static final short NECROMANCER = 801;
	public static final short HUNTRESS = 802;
	public static final short MYSTIC = 803;
	public static final short TRICKSTER = 804;
	public static final short SORCERER = 805;
	public static final short NINJA = 806;
	public static final short SAMURAI = 785;
	/**
	 * Determines if object type is a character class
	 * @param type Object type
	 * @return <code>true</code> if the type is a class else returns <code>false</code>
	 */
	public static boolean isCharacter(int type) {
		return (type >= WARRIOR && type <= NINJA) || type == WIZARD || type == PRIEST || type == ARCHER || type == ROGUE || type == SAMURAI;
	}
}
