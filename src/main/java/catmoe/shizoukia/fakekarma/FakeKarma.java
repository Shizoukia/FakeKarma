package catmoe.shizoukia.fakekarma;

import catmoe.fallencrystal.moefilter.api.event.EventManager;
import catmoe.fallencrystal.moefilter.util.message.MessageUtil;
import catmoe.shizoukia.fakekarma.listener.ChatListener;
import net.md_5.bungee.api.plugin.Plugin;
//import net.md_5.bungee.*;

@SuppressWarnings("unused")
public final class FakeKarma extends Plugin {
    public static FakeKarma instance;


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        // MessageUtil are MoeFilter util. INSTANCE is kotlin static object path.
        MessageUtil.INSTANCE.logInfo("[+25KarmaForBungeeCord] plugin Enabled");

        // Use MoeFilter EventListener here.
        EventManager.INSTANCE.registerListener(this, new ChatListener());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        MessageUtil.INSTANCE.logInfo("[+25KarmaForBungeeCord] Plugin disabled.");
    }
}
