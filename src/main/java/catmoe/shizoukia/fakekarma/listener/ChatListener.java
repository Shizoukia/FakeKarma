package catmoe.shizoukia.fakekarma.listener;

import catmoe.fallencrystal.moefilter.api.event.EventListener;
import catmoe.fallencrystal.moefilter.api.event.FilterEvent;
import catmoe.fallencrystal.moefilter.api.event.events.bungee.AsyncChatEvent;
import catmoe.fallencrystal.moefilter.network.bungee.util.bconnection.ConnectionUtil;
import catmoe.fallencrystal.moefilter.util.message.v2.packet.type.MessagesType
import catmoe.fallencrystal.moefilter.util.message.v2.MessageUtil;
import catmoe.shizoukia.fakekarma.FakeKarma;
import catmoe.shizoukia.fakekarma.config.PermissionGroup;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public final class ChatListener implements EventListener {

    @FilterEvent
    public void onChat(AsyncChatEvent event) {
        if (!event.isCancelled() && event.getMessage().equalsIgnoreCase("gg")) {
            final ProxiedPlayer player = event.getSender();
            final ConnectionUtil connection = ConnectionUtil(player.pendingConnection);
            int point = 0;
            // 直接equals的话可能不需要!event.isBackendCommand() && !event.isProxyCommand(). 因为命令前面带"/"

            if (CacheCooldown.isCooldown(player)) {
                final String cooldownMessage = FakeKarma.config.getString("cooldown-message");
                if (!cooldownMessage.isEmpty()) { MessageUtil.INSTANCE.sendMessage(connection, MessagesType.CHAT, cooldownMessage); }
                return;
            }

            // 权限 可在配置文件中设置Group.
            for (PermissionGroup it : FakeKarma.groups) {
                if (player.hasPermission("gkfbp.group." + it.getPermission())) { point=it.getPoint(); break; }
            }
            final String sendMessage = (FakeKarma.config.getString("message").replace("[point]", String.valueOf(point)));
            if (sendMessage.isEmpty()) { return; }
            MessageUtil.INSTANCE.sendMessage(connection, MessagesType.CHAT, sendMessage);
        }
    }
}
