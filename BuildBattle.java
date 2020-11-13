

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import plugily.projects.buildbattle.api.event.game.BBGameChangeStateEvent;
import xyz.derkades.ssx_connector.Addon;

public class BuildBattle extends Addon {

	private String state;
	private String arena;

	@Override
	public String getAuthor() {
		return "Lmmb74";
	}

	@Override
	public String getDescription() {
		return "Addon for the minigame BuildBattle by Plugily-Projects";
	}

	@Override
	public String getVersion() {
		return "1.0.0";
	}

	@Override
	public void onLoad() {
		this.registerListeners();
		this.state = "Unknown";
		this.addPlaceholder("bb-state", () -> this.config.getString(this.state));
		this.arena = "Unknown";
		this.addPlaceholder("bb-arena", () -> this.arena);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onStateChange(final BBGameChangeStateEvent e) {
		this.state = e.getState().getFormattedName();
		this.arena = e.getArena().getMapName();
	}
}
