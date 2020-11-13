

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import plugily.projects.murdermystery.api.events.game.MMGameStateChangeEvent;
import xyz.derkades.ssx_connector.Addon;

public class MurderMystery extends Addon {

	private String state;
	private String arena;

	@Override
	public String getAuthor() {
		return "Lmmb74";
	}

	@Override
	public String getDescription() {
		return "Addon for the minigame MurderMystery by Plugily-Projects";
	}

	@Override
	public String getVersion() {
		return "1.1.0";
	}

	@Override
	public void onLoad() {
		this.registerListeners();
		this.state = "Unknown";
		this.addPlaceholder("mm-state", () -> this.config.getString(this.state));
		this.arena = "Unknown";
		this.addPlaceholder("mm-arena", () -> this.arena);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onStateChange(final MMGameStateChangeEvent e) {
		this.state = e.getArenaState().getFormattedName();
		this.arena = e.getArena().getMapName();
	}

}
