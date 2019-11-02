import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import xyz.derkades.ssx_connector.Addon;

public class Whitelist extends Addon {

	@Override
	public String getAuthor() {
		return "Derkades";
	}

	@Override
	public String getDescription() {
		return "Placeholders related to Bukkit whitelist";
	}

	@Override
	public String getLicense() {
		return "MIT";
	}

	@Override
	public String getVersion() {
		return "1.0.0";
	}

	@Override
	public void onLoad() {
		this.addPlaceholder("whitelist", () -> Bukkit.hasWhitelist() ? this.config.getString("whitelist-on") : this.config.getString("whitelist-off"));

		this.addPlayerPlaceholder("player-whitelisted", (uuid, name) -> {
			final OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
			if (player == null) {
				return this.config.getString("player-unknown");
			} else if (player.isWhitelisted()) {
				return this.config.getString("player-whitelisted");
			} else {
				return this.config.getString("player-not-whitelisted");
			}
		});

		this.addPlaceholder("whitelist-list", () -> String.join(this.config.getString("list-separator"),
				Bukkit.getWhitelistedPlayers().stream().map(OfflinePlayer::getName).collect(Collectors.toList())));

		this.addPlaceholder("whitelist-count", () -> Bukkit.getWhitelistedPlayers().size() + "");
	}

}
