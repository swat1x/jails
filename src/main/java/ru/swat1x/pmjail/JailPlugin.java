package ru.swat1x.pmjail;

import co.aikar.commands.BukkitCommandManager;
import lombok.var;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import ru.swat1x.database.Database;
import ru.swat1x.database.DatabaseDriver;
import ru.swat1x.pmjail.command.JailCommands;
import ru.swat1x.pmjail.config.JailConfig;
import ru.swat1x.pmjail.management.JailListener;
import ru.swat1x.pmjail.management.JailManager;
import ru.swat1x.pmjail.management.JailRepository;

import java.io.File;

import static ru.swat1x.pmjail.config.JailConfig.IMP;

public final class JailPlugin extends JavaPlugin {

    Location jailLocation;
    Location unjailLocation;
    Database database;

    @Override
    public void onEnable() {
        IMP.reload(new File(getDataFolder(), "config.yml"));
        saveDefaultConfig();

        jailLocation = parseLocation(IMP.LOCATIONS.JAIL);
        unjailLocation = parseLocation(IMP.LOCATIONS.UNJAIL);

        database = formDatabase(
                IMP.DATABASE.HOST,
                IMP.DATABASE.DATABASE,
                IMP.DATABASE.USER,
                IMP.DATABASE.PASSWORD
        );
        JailRepository repository = new JailRepository(database);
        JailManager manager = new JailManager(repository, jailLocation, unjailLocation);

        Bukkit.getPluginManager().registerEvents(new JailListener(manager), this);

        var commandManager = new BukkitCommandManager(this);
        commandManager.registerCommand(new JailCommands(manager));
    }

    private Location parseLocation(String data) {
        var array = data.split(";");
        return new Location(
                Bukkit.getWorld(array[0]),
                Double.parseDouble(array[1]),
                Double.parseDouble(array[2]),
                Double.parseDouble(array[3]),
                Float.parseFloat(array[4]),
                Float.parseFloat(array[5])
        );
    }

    private Database formDatabase(String host, String databaseName, String user, String password) {
        return new Database(host, databaseName, user, password, DatabaseDriver.MYSQL);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
