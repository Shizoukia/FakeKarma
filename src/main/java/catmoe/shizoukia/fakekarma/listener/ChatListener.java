package catmoe.shizoukia.fakekarma.listener;

import catmoe.fallencrystal.moefilter.api.event.EventListener;
import catmoe.fallencrystal.moefilter.api.event.FilterEvent;
import catmoe.fallencrystal.moefilter.api.event.events.bungee.AsyncChatEvent;
import catmoe.fallencrystal.moefilter.util.message.MessageUtil;
import catmoe.shizoukia.fakekarma.FakeKarma;
import catmoe.shizoukia.fakekarma.config.PermissionGroup;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ChatListener implements EventListener {

    @FilterEvent
    public final void onChat(AsyncChatEvent event) {
        final String message = event.getMessage();
        final boolean isCancelled = event.isCancelled();
        final ProxiedPlayer player = event.getSender();
        final Integer[] addPoint = {0};
        // 直接equals的话可能不需要!event.isBackendCommand() && !event.isProxyCommand(). 因为命令前面带"/"

        // 权限 可在配置文件中设置Group.
        for (PermissionGroup it : FakeKarma.groups) {
            if (player.hasPermission("gkfbp.group." + it.getPermission())) { addPoint[0]=it.getPoint(); return; }
        }
        if (!isCancelled && message.equalsIgnoreCase("gg")) {
            MessageUtil.INSTANCE.sendMessage(player, "&d" + Arrays.toString(addPoint) + " 人品值 &8(虽然屁用没有)");
        }
    }
}
