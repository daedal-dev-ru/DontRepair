package daedal.dontrepair.listeners;

import daedal.dontrepair.DontRepair;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class CommandListener implements Listener {

    @EventHandler
    public void onRepair(ServerCommandEvent event) {
        String cmd = event.getCommand();
        if (!cmd.equalsIgnoreCase("repair")) {
            return;
        }
        if (!cmd.equalsIgnoreCase("fix")) {
            return;
        }
        CommandSender sender = event.getSender();
        Player p = sender.getServer().getPlayer(sender.getName());
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item.getItemFlags().contains(ItemFlag.HIDE_ATTRIBUTES)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', DontRepair.getInstance().getConfig().getString("message")));
            event.setCancelled(true);
        }
    }
}
