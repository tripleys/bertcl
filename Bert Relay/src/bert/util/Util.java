package bert.util;

import java.security.SecureRandom;

public class Util {
	
	public static final byte[] STATIC_KEY = {
		71,98,68,73,108,61,
		21,99,113,79,103,10,
		89,62,20,35,7,20,122,
		120,78,71,114,65,90,
		83,87,47,93,68,61,27,
		75,59,92,24,91,125,123,
		115,9,7,49,3,124,13,92,
		34,64,64,21,108,42,2,61,
		121,67,70,65,92,51,7,4,2
	};

	public static String encode(String message, byte[] key) {
		byte[] b = message.getBytes();
		byte[] o = new byte[message.length() * 2];
		int j = 0;
		int l = 0;
		for (int i = 0; i < b.length; i++)
			for(byte h : Integer.toHexString(b[i] + key[l == key.length ? l = 0 : l++]).getBytes())
				o[j++] = h;
		return new String(o, 0, j);
	}
	
	public static byte[] hexString(String s) {  
        byte[] b = new byte[s.length() / 2];
        for(int i = 0; i < b.length; i++)
            b[i] = (byte)Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16);
    	return b;
    }
	
	public static String decode(String s, byte[] key) {
        byte[] b = new byte[s.length() / 2];
        int l = 0;
        for(int i = 0; i < b.length; i++)
            b[i] = (byte) (Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16) - key[l == key.length ? l = 0 : l++]);
        return new String(b);
    }
	
	public static byte[] RandomKey(int size) {
		byte[] b = new byte[size];
		SecureRandom r = new SecureRandom();
		for(int i = 0; i < size; i++)
			b[i] = (byte)r.nextInt(Byte.MAX_VALUE);
		return b;
	}
	
}
