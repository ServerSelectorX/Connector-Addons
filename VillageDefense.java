

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import plugily.projects.villagedefense.api.event.game.VillageGameStateChangeEvent;
import xyz.derkades.ssx_connector.Addon;

public class VillageDefense extends Addon {

	private String state;
	private String arena;

	@Override
	public String getAuthor() {
		return "Lmmb74";
	}

	@Override
	public String getDescription() {
		return "Addon for the minigame VillageDefense by Plugily-Projects";
	}

	@Override
	public String getVersion() {
		return "1.0.0";
	}

	@Override
	public void onLoad() {
		this.registerListeners();
		this.state = "Unknown";
		this.addPlaceholder("vd-state", () -> this.config.getString(this.state));
		this.addPlaceholder("vd-arena", () -> this.arena);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onStateChange(final VillageGameStateChangeEvent e) {
		this.state = e.getArenaState().getFormattedName();
		this.arena = e.getArena().getMapName();
	}
}
