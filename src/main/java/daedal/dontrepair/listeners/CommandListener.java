package daedal.dontrepair.listeners;

import daedal.dontrepair.DontRepair;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class CommandListener implements Listener {

    @EventHandler
    public void onRepair(PlayerCommandPreprocessEvent event) {
        String cmd = event.getMessage();
        if (!cmd.equalsIgnoreCase("/repair")) {
            return;
        }
        if (!cmd.equalsIgnoreCase("/fix")) {
            return;
        }
        Player sender = event.getPlayer();
        ItemStack item = sender.getInventory().getItemInMainHand();
        if (!item.getItemFlags().contains(ItemFlag.HIDE_ATTRIBUTES)) {
            return;
        }
        if (!sender.hasPermission("dontrepair.allow")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', DontRepair.getInstance().getConfig().getString("message")));
            event.setCancelled(true);
        }
    }
}
