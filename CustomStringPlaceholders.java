// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import nl.rslot.ssx.connector.Addon;

public class CustomStringPlaceholders extends Addon {

    public String getDescription() {
        return "Create your own simple string placeholders";
    }

    public String getAuthor() {
        return "Derkades";
    }

    public String getVersion() {
        return "1.0.0";
    }

    public void onLoad() {
        for (String key : this.config.getKeys(false)) {
            this.addPlaceholder(key, () -> this.config.getString(key));
        }
    }
}
