package catmoe.shizoukia.fakekarma;

import catmoe.fallencrystal.moefilter.api.event.EventManager;
import catmoe.fallencrystal.moefilter.util.message.MessageUtil;
import catmoe.shizoukia.fakekarma.config.PermissionGroup;
import catmoe.shizoukia.fakekarma.listener.ChatListener;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import net.md_5.bungee.*;

@SuppressWarnings("unused")
public final class FakeKarma extends Plugin {
    public static FakeKarma instance;
    public static Config config;
    public static File configFile;

    public static List<PermissionGroup> groups = new ArrayList<>();


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        LoadConfig();

        // MessageUtil are MoeFilter util. INSTANCE is kotlin static object path.
        MessageUtil.INSTANCE.logInfo("[FakeKarma] plugin Enabled");

        // Use MoeFilter EventListener here.
        EventManager.INSTANCE.registerListener(this, new ChatListener());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        EventManager.INSTANCE.unregisterListener(new ChatListener());
        MessageUtil.INSTANCE.logInfo("[FakeKarma] Plugin disabled.");
    }

    @SuppressWarnings({"ResultOfMethodCallIgnored"})
    private void LoadConfig() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        configFile = new File(getDataFolder().getAbsolutePath() + "/config.conf");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (Exception exception) {
                exception.printStackTrace();
                MessageUtil.INSTANCE.logError("[FakeKarma] Failed to create config file");
                return;
            }
            try {
                Path file = Paths.get(configFile.getAbsolutePath());
                List<String> exampleConfig = Arrays.asList(
                        " # 不在Group中的将会得到0point(?) 权限为gkfbp.group.设定值.",
                        "group=[",
                        "    {",
                        "        permission=\"mvpplusorhigh\"",
                        "        point=25",
                        "    }",
                        "    {",
                        "        permission=\"mvp\"",
                        "        point=20",
                        "    }",
                        "    {",
                        "        permission=\"vipplus\"",
                        "        point=15",
                        "    }",
                        "    {",
                        "        permission=\"vip\"",
                        "        point=10",
                        "    }",
                        "    {",
                        "        permission=\"default\"",
                        "        point=5",
                        "    }",
                        "]"
                );
                Files.write(file, exampleConfig, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            } catch (Exception exception) {
                exception.printStackTrace();
                return;
            }
        }
        try {
            config = ConfigFactory.parseFile(configFile);
            List<? extends Config> groupList = config.getConfigList("group");
            for (Config group : groupList) {
                String permission = group.getString("permission");
                int point = group.getInt("point");
                PermissionGroup entry = new PermissionGroup();
                entry.setPermission(permission);
                entry.setPoint(point);
                groups.add(entry);
            }
        } catch (Exception exception) {exception.printStackTrace();}
    }
}
