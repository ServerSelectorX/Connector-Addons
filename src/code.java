import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;

import xyz.derkades.serverselectorx.connector.AddonClass;

public class code implements AddonClass {

	@Override
	public Map<String, String> getPlaceholders() {
		Map<String, String> map = new HashMap<>();
		map.put("online", Bukkit.getOnlinePlayers().size() + "");
		map.put("max", Bukkit.getMaxPlayers() + "");
		return map;
	}

	@Override
	public Map<String, String> getConfigDefaults() {
		return null;
	}

}
