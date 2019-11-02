import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import com.andrei1058.bedwars.api.events.gameplay.GameStateChangeEvent;

import xyz.derkades.ssx_connector.Addon;

public class Bedwars1058 extends Addon {

	private String state;

	@Override
	public String getAuthor() {
		return "Derkades";
	}

	@Override
	public String getDescription() {
		return "Addon for Bedwars1058 API version 13";
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
		this.state = "unknown";
		this.addPlaceholder("bw-state", () -> this.config.getString(this.state));
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onStateChange(final GameStateChangeEvent event) {
		this.state = event.getNewState().toString().toLowerCase();
	}

}
