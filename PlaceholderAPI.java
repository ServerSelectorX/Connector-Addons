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
		return "1.0.0";
	}

	@Override
	public void onLoad() {
		for (final String key : this.config.getKeys(false)) {
			addPlaceholder(key, () -> me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(null, "%" + this.config.getString(key) + "%"));
		}
	}

}
