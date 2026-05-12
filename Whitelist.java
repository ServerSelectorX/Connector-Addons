import java.util.UUID;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import nl.rslot.ssx.connector.Addon;

public class Whitelist extends Addon {
    public String getAuthor() {
        return "Derkades";
    }

    public String getDescription() {
        return "Placeholders related to Bukkit whitelist";
    }

    public String getVersion() {
        return "1.1.0";
    }

    public void onLoad() {
        this.addPlaceholder("whitelist", () -> Bukkit.hasWhitelist() ? this.config.getString("whitelist-on") : this.config.getString("whitelist-off"));
        this.addPlayerPlaceholder("player-whitelisted", uuid -> {
            if (!this.config.getBoolean("player-whitelist-when-whitelist-off", true) && !Bukkit.hasWhitelist())
                return this.config.getString("player-not-whitelisted");
            OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
            return (player == null) ? this.config.getString("player-unknown") : (player.isWhitelisted() ? this.config.getString("player-whitelisted") : this.config.getString("player-not-whitelisted"));
        });
        this.addPlaceholder("whitelist-list", () -> String.join(this.config.getString("list-separator"), Bukkit.getWhitelistedPlayers().stream().map(OfflinePlayer::getName).collect(Collectors.toList())));
        this.addPlaceholder("whitelist-count", () -> String.valueOf(Bukkit.getWhitelistedPlayers().size()));
    }
}
