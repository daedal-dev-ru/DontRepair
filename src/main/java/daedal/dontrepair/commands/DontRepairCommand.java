package daedal.dontrepair.commands;

import daedal.dontrepair.DontRepair;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class DontRepairCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("dontrepair.admin")) {
            sender.sendMessage(ChatColor.RED + "У Вас нет прав для выполнения данной команды!");
            return true;
        }
        Player p = sender.getServer().getPlayer(sender.getName());
        assert p != null;
        ItemStack item = p.getInventory().getItemInMainHand();
        switch (args[0]) {
            case "reload":
                DontRepair.getInstance().reloadConfig();
                sender.sendMessage(ChatColor.GOLD + "Конфигурация плагина перезагружена!");
                return true;
            case "tag":
                if (item.getItemFlags().contains(ItemFlag.HIDE_ATTRIBUTES)) {
                    sender.sendMessage(ChatColor.GOLD + "Предмет уже и так "
                            + ChatColor.YELLOW + "нельзя" + ChatColor.GOLD + " ремонтировать");
                    return true;
                }
                item.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                sender.sendMessage(ChatColor.GOLD + "Предмет успешно помечен как неремонтируемый!");
                return true;
            case "untag":
                if (!item.getItemFlags().contains(ItemFlag.HIDE_ATTRIBUTES)) {
                    sender.sendMessage(ChatColor.GOLD + "Предмет уже и так "
                            + ChatColor.YELLOW + "можно" + ChatColor.GOLD + " ремонтировать");
                    return true;
                }
                item.removeItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                sender.sendMessage(ChatColor.GOLD + "Предмет успешно помечен как ремонтируемый!");
                return true;
            default:
                sender.sendMessage(ChatColor.GOLD + "Не удалось выполнить команду! Укажите один из аргументов: "
                        + ChatColor.YELLOW + "tag / untag / reload");
                return true;
        }
    }
}
