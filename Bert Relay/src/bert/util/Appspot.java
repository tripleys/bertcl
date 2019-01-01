package bert.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import bert.cache.Cache;
import bert.rotmg.AccountData;
import bert.rotmg.model.Server;

public class Appspot {

	public static final String API = "https://www.realmofthemadgod.com";
	
	public static final String CHAR_LIST = "/char/list?guid=%s&password=%s";
	
	public static final String SERVERS = "/char/list";
	
	public static final String PATH = "./cache/servers.dat";
	
	public static HashMap<String, Server> servers;
	
	/**
	 * Loads account data from the RotMG-API End point
	 * @param guid
	 * @param password
	 * @return
	 */
	public static AccountData load(String guid, String password) {
		try {
			Document doc = Jsoup.connect(API + String.format(CHAR_LIST, guid, password)).get();
			Elements el = doc.body().getElementsByTag("Chars");
			if (el.size() > 0) {
				int nextCharId = Integer.parseInt(el.get(0).attr("nextCharId"));
				el = el.get(0).getElementsByTag("Char");
				int charId = el.size() > 0 ? Integer.parseInt(el.get(0).attr("id")) : 0;
				return new AccountData(guid, password, charId, nextCharId);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new AccountData(guid, password, 0, 1);
	}
	/**
	 * Loads servers map from the RotMG-API End point, if connection is failed loads servers from local cache.
	 * @param cache if <code>False</code> forces a new request to API every time
	 * @return
	 */
	public static HashMap<String, Server> getServers(boolean cache) {
		if (servers != null && cache) return servers;
		HashMap<String, Server> map = new HashMap<>();
		try {
			Document doc = Jsoup.connect(API + SERVERS).get();
			Elements e = doc.body().getElementsByTag("Servers");
			if (e.size() > 0) {
				e = e.get(0).getElementsByTag("Server");
				e.forEach(element -> {
					String name = element.getElementsByTag("Name").get(0).html();
					map.put(name, new Server(element.getElementsByTag("DNS").get(0).html(), name, Float.parseFloat(element.getElementsByTag("Lat").get(0).html()), Float.parseFloat(element.getElementsByTag("Long").get(0).html())));
				});
				return (servers = map);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return cache ? tryCache(map) : map;
	}
	/**
	 * Tries to load the server data from cache
	 * @param map
	 * @return
	 */
	public static HashMap<String, Server> tryCache(HashMap<String, Server> map) {
		
		File f = new File(PATH);
		
		if (!f.exists()) {
			return map;
		}
		
		Cache cache = new Cache(f);
		try {
			
			cache.read();
			
			ServerData sd = new ServerData();
			cache.read("servers", sd);
			
			map = servers = sd.data;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return map;
		}
		
		return map;
	}
}
