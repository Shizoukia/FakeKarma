package catmoe.getkarmabc;

import net.md_5.bungee.api.plugin.Plugin;
//import net.md_5.bungee.*;

public final class GetKarmaForBungeeCord extends Plugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[+25KarmaForBungeeCord] Plugin Enabled");
        getProxy().getPluginManager().registerListener(this, new PlayerMessageListener());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[+25KarmaForBungeeCord] Plugin Dsiabled");
    }
}
