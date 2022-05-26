import java.lang.management.ManagementFactory;

import xyz.derkades.ssx_connector.Addon;

public class TimeSince extends Addon {

	@Override
	public String getAuthor() {
		return "Derkades";
	}

	@Override
	public String getDescription() {
		return "Add placeholders which count the time after a specific date";
	}

	@Override
	public String getLicense() {
		return "MIT";
	}

	@Override
	public String getVersion() {
		return "1.1.2";
	}

	@Override
	public void onLoad() {
		final int placeholderType = this.config.getInt("placeholder-type");

		for (final String placeholderName : this.config.getConfigurationSection("placeholders").getKeys(false)) {
			addPlaceholder(placeholderName, () -> {
				long diff; // In seconds
				if (this.config.isLong("placeholders." + placeholderName) || this.config.isInt("placeholders." + placeholderName)) {
					final long timestamp = this.config.getLong("placeholders." + placeholderName);
					diff = System.currentTimeMillis() / 1000 - timestamp;
				} else {
					final String type = this.config.getString("placeholders." + placeholderName);
					if (type.equals("uptime")) {
						diff = ManagementFactory.getRuntimeMXBean().getUptime() / 1000;
					} else {
						return "error: invalid value";
					}
				}

				if (diff < 0) {
					return "in the future";
				}

				int weeks = -1;
				int days = -1;

				if (placeholderType >= 3) {
					weeks = (int) (diff / (60*60*24*7));
					diff -= weeks*60*60*24*7;
				}

				if (placeholderType >= 2) {
					days = (int) (diff / (60*60*24));
					diff -= days*60*60*24;
				}

				final int hours = (int) (diff / (60*60));
				diff -= hours*60*60;

				final int minutes = (int) (diff / 60);
				diff -= minutes*60;

				final int seconds = (int) diff;

				final String formattedDiff = this.config.getString("format")
						.replace("{weeks}", weeks + "")
						.replace("{days}", days + "")
						.replace("{hours}", hours + "")
						.replace("{minutes}", minutes + "")
						.replace("{seconds}", seconds + "");
				return formattedDiff;
			});
		}
	}

}
