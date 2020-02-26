import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;

import xyz.derkades.ssx_connector.Addon;

public class PlaceholderAPI extends Addon {

	@Override
	public String getAuthor() {
		return "Derkades";
	}

	@Override
	public String getDescription() {
		return "Send PlaceholderAPI placeholders as ServerSelectorX placeholders";
	}

	@Override
	public String getLicense() {
		return "MIT";
	}

	@Override
	public String getVersion() {
		return "1.1.1";
	}

	@Override
	public void onLoad() {
		if (config == null) {
			System.err.println("Config was not loaded");
			return;
		}
		
		if (config.isConfigurationSection("global")) {
			final ConfigurationSection global = this.config.getConfigurationSection("global");
			for (final String key : global.getKeys(false)) {
				addPlaceholder(key, () -> me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(null, "%" + global.getString(key) + "%"));
			}
		} else {
			System.err.println("Global placeholders configuration section missing");
		}
		
		if (config.isConfigurationSection("player")) {
			final ConfigurationSection player = this.config.getConfigurationSection("player");
			for (final String key : player.getKeys(false)) {
				addPlayerPlaceholder(key, (uuid, name) -> me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(Bukkit.getOfflinePlayer(uuid), "%" + player.getString(key) + "%"));
			}
		} else {
			System.err.println("Global placeholders configuration section missing");
		}
	}

}
