import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import xyz.derkades.SSX_Connector.AddonClass;

public class code extends AddonClass {

	@Override
	public Map<String, String> getPlaceholders() {
		Map<String, String> map = new HashMap<>();
		
		if (Bukkit.hasWhitelist()) {
			map.put("whitelist", config.getString("whitelist-on"));
		} else {
			map.put("whitelist", config.getString("whitelist-off"));
		}
		
		List<String> namesList = new ArrayList<>();
		for (OfflinePlayer player : Bukkit.getWhitelistedPlayers()) {
			namesList.add(player.getName());
		}
		
		map.put("whitelisted-players", String.join(", ", namesList));
		
		map.put("whitelisted-count", Bukkit.getWhitelistedPlayers().size() + "");
		
		return map;
	}

	@Override
	public Map<String, String> getConfigDefaults() {
		Map<String, String> map = new HashMap<>();
		map.put("whitelist-on", "Server is in maintenance mode");
		map.put("whitelist-off", "Server is open!");
		return map;
	}

}
