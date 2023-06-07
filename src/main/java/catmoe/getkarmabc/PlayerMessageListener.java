package catmoe.getkarmabc;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

//package catmoe.getkarmabc.PlayerMessageListener;
public class PlayerMessageListener implements Listener {

    @EventHandler
    public void onPlayerChat(ChatEvent event) {
        // 检查事件类型是否为玩家发送消息事件
        if (!(event.getSender() instanceof ProxiedPlayer)) {
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) event.getSender();
        String message = event.getMessage();

        // 检查玩家发送的消息是否匹配指定条件
        if (message.equalsIgnoreCase("gg") || (message.equalsIgnoreCase("GG"))) {
            // 发送自定义消息给玩家
            if (player.hasPermission("GKFBP.group.mvpplusorhigh")) {
                player.sendMessage(ChatColor.LIGHT_PURPLE + "+25 人品值 §8(虽然屁用没有)");
            } else if (player.hasPermission("GKFBP.group.mvp")){
                player.sendMessage(ChatColor.LIGHT_PURPLE + "+20 人品值 §8(虽然屁用没有)");
            } else if (player.hasPermission("GKFBP.group.vipplus")){
                player.sendMessage(ChatColor.LIGHT_PURPLE + "+15 人品值 §8(虽然屁用没有)");
            } else if (player.hasPermission("GKFBP.group.vip")){
                player.sendMessage(ChatColor.LIGHT_PURPLE + "+10 人品值 §8(虽然屁用没有)");
            } else if (player.hasPermission("GKFBP.group.default")){
                player.sendMessage(ChatColor.LIGHT_PURPLE + "+5 人品值 §8(虽然屁用没有)");
            } else
                player.sendMessage(ChatColor.LIGHT_PURPLE + "+0 人品值 §8(你好像不是任何一个组的成员的说)");
        }
    }
}
