package daedal.dontrepair;

import daedal.dontrepair.commands.DontRepairCommand;
import daedal.dontrepair.commands.DontRepairCompleter;
import daedal.dontrepair.listeners.CommandListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class DontRepair extends JavaPlugin {
    Logger log = getLogger();
    private static DontRepair instance;

    @Override
    public void onEnable() {
        long start = System.currentTimeMillis();
        setupEvents();
        setupCommands();
        setInstance();
        long end = System.currentTimeMillis();
        log.info(ChatColor.GREEN + "Плагин успешно загружен за " + (end-start) + " мс.");
        log.info(ChatColor.YELLOW + "Разработано vk.com/daedal_dev специально для QueerZizz");
    }

    @Override
    public void onDisable() {
        log.info(ChatColor.RED + "Плагин выключен.");
    }

    private void setupEvents() {
        Bukkit.getPluginManager().registerEvents(new CommandListener(), this);
    }

    private void setupCommands() {
        PluginCommand cmd = getCommand("dontrepair");
        assert cmd != null;
        cmd.setExecutor(new DontRepairCommand());
        cmd.setTabCompleter(new DontRepairCompleter());
    }

    private void setInstance() {
        instance = this;
    }

    public static DontRepair getInstance() {
        return instance;
    }
}
