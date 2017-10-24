# Connector-Addons
Repository with compiled addons for ServerSelectorX connector

# How to write an addon
In this tutorial I use the Eclipse IDE. The steps should not be much different for other IDEs.

1. Create a new standard java project
2. Right click your project -> Build Path -> Add External Archives -> Select your SSX Connector jar file
3. Without creating a package, create a new class called "code" **lower case** (I know this goes against conventions)
4. Let this class implement `AddonClass`
5. Insert the required methods

## getPlaceholders()
This is where you add your custom placeholders. First create a new hashmap, then add your placeholders to the hashmap like this:
```
// Adds placeholder {players} which is replaced with the number of online players
Map<String, String> map = new HashMap<>();
map.put("players", String.valueOf(Bukkit.getOnlinePlayers()));
return map;
```
## getConfigDefaults()
This is where you create a config if you need one. If you return null, no config will be generated.
```
@Override
public Map<String, String> getPlaceholders() {
  Map<String, String> placeholders = new HashMap<>();
  if (true){
    map.put("example", config.getString("on"));
  } else {
    map.put("example", config.getString("off"));
  }
  return placeholders;
}

@Override
public Map<String, String> getConfigDefaults() {
  Map<String, String> defaults = new HashMap<>();
  defaults.put("on", "This is displayed when on");
  defaults.put("off", "This is displayed when off");
  return defaults;
}
```
Config will look like this:
```
on: 'This is displayed when on'
off: 'This is displayed when off'
```
## info.yml
An example should be clear enough:
```
name: Total Money
description: 'Adds the placeholder {money} which is the combined balance of all players on the server'
author: Derkades
version: 1.0.2
license: MIT
depends: [Vault] #If your addon doesn't depend on anything you can leave this out
```
## Compiling
1. Save your work
2. Navigate to your project folder in file explorer
3. In the `bin` directory, you should find the files `code.class` and `info.yml`
4. Copy both files into a new folder. Optionally add a source file.
5. If your folder now looks like <a href="https://github.com/ServerSelectorX/Connector-Addons/tree/master/PlayerCount">this</a> you are done!
6. Submit a pull request to this repo so your addon is listed here.
## Good to know
- If you use any methods that are only available in certain versions of Bukkit, please explain in the description of your addon
- You can use APIs from other plugins, just make sure that you put that plugin as a dependency in your info.yml and as a soft dependency in <a href="https://github.com/ServerSelectorX/ServerSelectorX/blob/master/src/plugin.yml">SSX plugin.yml</a>
- If you need to do something when your addon is loaded, you can override the method onLoad()
- You can add any listener you want to the class, they are registered automatically. AddonClass already implements Listener.
