package test;

import bert.rotmg.AccountData;
import bert.rotmg.GameClient;
import bert.util.Appspot;

public class Test {

	public static void main(String[] args) throws Exception {
		
		// load servers from appspot or cache
		Appspot.getServers(true);
		
		// load account data from appspot char/list
		AccountData accountData = Appspot.load("username", "password");
		
		// start new game client instance, (Alias | AccountData | Server)
		new GameClient("Test", accountData, Appspot.servers.get("EUNorth"));
	}
}
