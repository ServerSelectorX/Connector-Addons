# Connector-Addons

Repository with compiled addons for ServerSelectorX connector

## Registering placeholders from a plugin

If you don't want to write an addon and instead want to add placeholders directly from your plugin, you can do the following:

```java
// Register global placeholder {hello} that always says "hello" for all players
PlaceholderRegistry.registerPlaceholder(Optional.empty(), "hello", () -> "hello");

// Register player-specific placeholder, that can return a value specific for each player.
PlaceholderRegistry.registerPlaceholder(Optional.empty(), "helloPlayer", uuid -> "Hello, your UUID is: " + uuid);
```

## How to write an addon

Create a new text file ending in `.java`. Write your addon in this file. Place an SSX-Connector jar file and spigot jar file in the same directory. Compile by running `javac -cp '*:.' *.java`. This will produce a `.class` file which can be loaded by SSX-Connector.

The class should extend `Addon` (import `nl.rslot.ssx.connector.Addon`). Implement the required methods. In the `onLoad` method, use the `addPlaceholder(String key, Supplier<String> placeholder)` and `addPlayerPlaceholder(String key, Function<UUID, String> placeholder)` methods to add placeholders.

### Example

```java
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import nl.rslot.ssx.connector.Addon;

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

        // Add {helloWhitelisted} that greets only whitelisted players
        addPlayerPlaceholder("helloWhitelisted", uuid -> {
            OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
            return player != null && player.isWhitelisted() ? "Hello!" : "...";
        });

        // Use message from configuration
        addPlaceholder("helloConfig", () -> config.getString("message"));
    }

}
```

### Configuration

If you need a config create a .yml file in the same directory and with the same name as the java file, for example `Hello.yml`. You'll be able to read the configuration file using the `config` class variable.
For example:

```yaml
message: 'Bonjour!'
```

### Good to know

- If you use any methods that are only available in certain versions of Bukkit, please explain in the description of your addon. It is assumed that your addon is compatible from 1.7.10 to the most recent Minecraft version.
- You can use Bukkit listeners. `Addon` already implements `Listener`. Register the listeners by calling `registerListeners()` in `onLoad()`.
