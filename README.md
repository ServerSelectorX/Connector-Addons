# Connector-Addons
Repository with compiled addons for ServerSelectorX connector

# How to write an addon
In this tutorial I use the Eclipse IDE. The steps should not be much different for other IDEs.

1. Create a new standard java project
2. Right click your project -> Build Path -> Add External Archives -> Select your SSX Connector jar file
3. Without creating a package, create a new class called "code" **lower case** (I know this goes against conventions)
4. Let this class implement `AddonClass`
5. If you need a config, create a new file called config.yml. In this example it will look like this:
```
on: 'This is displayed when on'
off: 'This is displayed when off'
```
5. Insert the required method getPlaceholders()

This is where you add your custom placeholders. First create a new hashmap, then add your placeholders to the hashmap like this:
```
// Adds placeholder {players} which is replaced with the number of online players
Map<String, String> map = new HashMap<>();
map.put("players", String.valueOf(Bukkit.getOnlinePlayers()));
return map;
```

Your class should now look like this:
```
public class code extends AddonClass {

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

}
```
## Configuration
If you need a config create a config.yml file in the same directory as code.class
For this example something like this:
```
# {example} will be replaced with 'custom text' or 'custom text 2'
on: 'custom text'
off: 'custom text 2'
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
2. At the top, go to `Project` and click `Clean...`
3. Make sure that your project is selected (or all projects selected), then click `Clean`.
4. Right click project > `Show In` > `System Explorer` then open the selected directory
5. In the `bin` directory, you should find the files `code.class` and `info.yml`
6. Copy both files into a new folder. Optionally add a source file.
7. If your folder now looks like <a href="https://github.com/ServerSelectorX/Connector-Addons/tree/master/PlayerCount">this</a> you are done!
8. Submit a pull request to this repo so your addon is listed here.
## Good to know
- If you use any methods that are only available in certain versions of Bukkit, please explain in the description of your addon
- You can use APIs from other plugins, just make sure that you put that plugin as a dependency in your info.yml and as a soft dependency in <a href="https://github.com/ServerSelectorX/ServerSelectorX/blob/master/src/plugin.yml">SSX plugin.yml</a>. If you are using a public plugin please submit a pull request. Otherwise, open the jar using 7-zip and edit plugin.yml.
- If you need to do something when your addon is loaded, you can override the method `onLoad()`. The config is loaded before `onLoad()` is called.
- You can add any listener you want to the class, they are registered automatically. `AddonClass` already implements `Listener`.
