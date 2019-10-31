# Connector-Addons
Repository with compiled addons for ServerSelectorX connector

# How to write an addon

Create a new java project in your IDE and add the spigot and SSX-Connector jar files to the classpath.

Creating a new java file (not in a package) for the addon for example ExamplePlaceholders.java. The class should extend `Addon` (import `xyz.derkades.ssx_connector.Addon`). Implement the required methods. In the `onLoad` method, use the `addPlaceholder(String key, Supplier<String> placeholder)` and `addPlayerPlaceholder(String key, BiFunction<UUID, String, String> placeholder)` methods to add placeholders.

## Example

```java
public class ExamplePlaceholders extends Addon {

	@Override
	public String getAuthor() {
		return "Derkades";
	}

	@Override
	public String getDescription() {
		return "Adds {hello} and {helloPlayer} placeholders";
	}

	@Override
	public String getLicense() {
		return "MIT";
	}

	@Override
	public String getName() {
		return "Example";
	}

	@Override
	public String getVersion() {
		return "1.0.0";
	}

	@Override
	public void onLoad() {
		// Add {hello} placeholder that always returns "Hello!"
		addPlaceholder("hello", () -> "Hello!");

		// Add {helloPlayer} placeholder returns a more personal greeting
		addPlayerPlaceholder("helloPlayer", (uuid, name) -> "Hello, " + name + "!");
	}

}
```

## Configuration
If you need a config create a .yml file in the same directory and with the same name as the java file, for example `ExamplePlaceholders.yml`. You'll be able to read the configuration file using the `config` variable.
For this example something like this:
```
# {example} will be replaced with 'custom text' or 'custom text 2'
on: 'custom text'
off: 'custom text 2'
```

## Exporting
1. Make sure is everything saved. This guide assumes that your IDE automatically compiles files when saving.
2. In the `bin` directory, you should find a `.class` and optionally a `.yml` file. Those two files make up your addon.
3. Drop the two files in the SSX-Connector/addons folder for testing.
4. Submit a pull request to this repository!

## Good to know
- If you use any methods that are only available in certain versions of Bukkit, please explain in the description of your addon. It is assumed that your addon is compatible from 1.7.10 to the most recent Minecraft version.
- You can use Bukkit listeners. `Addon` already implements `Listener` and is registered when the addon is enabled.
