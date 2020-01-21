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
		return "1.1.0";
	}

	@Override
	public void onLoad() {
		final ConfigurationSection global = this.config.getConfigurationSection("global");
		final ConfigurationSection player = this.config.getConfigurationSection("player");

		for (final String key : global.getKeys(false)) {
			addPlaceholder(key, () -> me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(null, "%" + global.getString(key) + "%"));
		}

		for (final String key : player.getKeys(false)) {
			addPlayerPlaceholder(key, (uuid, name) -> me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(Bukkit.getOfflinePlayer(uuid), "%" + player.getString(key) + "%"));
		}
	}

}
