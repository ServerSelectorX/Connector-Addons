import org.bukkit.Bukkit;
import nl.rslot.ssx.connector.Addon;

public class Motd extends Addon {
  public String getDescription() {
    return "Motd placeholder";
  }

  public String getAuthor() {
    return "Derkades";
  }

  public String getVersion() {
    return "1.0.0";
  }

  public String getLicense() {
    return "MIT";
  }

  public void onLoad() {
    addPlaceholder("motd", () -> Bukkit.getServer().getMotd());
  }
}
