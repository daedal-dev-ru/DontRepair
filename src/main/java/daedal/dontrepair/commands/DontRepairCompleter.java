package daedal.dontrepair.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class DontRepairCompleter implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("dontrepair")) {
            if (!sender.hasPermission("dontrepair.admin")) {
                return null;
            }
            return Arrays.asList("tag", "untag", "reload");
        }
        return null;
    }
}
