import org.bukkit.Bukkit;
import ro.Fr33styler.Arcade.Arcade;
import xyz.derkades.ssx_connector.Addon;

public class Fr33stylerArcade extends Addon {
    public String getAuthor() {
        return "Derkades";
    }

    public String getDescription() {
        return "Addon for Fr33styler's ArcadeGames plugin";
    }

    public String getVersion() {
        return "1.0.0";
    }

    public void onLoad() {
        Arcade arcade = (Arcade)Bukkit.getPluginManager().getPlugin("ArcadeGames");
        this.addPlaceholder("arcade-state", () -> this.config.getString(arcade.getBungee().state.name()));
    }
}
