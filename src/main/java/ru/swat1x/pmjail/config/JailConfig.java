package ru.swat1x.pmjail.config;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import ru.swat1x.pmjail.util.YamlConfig;

@FieldDefaults(level = AccessLevel.PUBLIC)
public class JailConfig extends YamlConfig {

    @Ignore
    public static final JailConfig IMP = new JailConfig();

    @Create
    Messages MESSAGES = new Messages();

    @Create
    Locations LOCATIONS = new Locations();

    @Create
    Database DATABASE = new Database();

    @FieldDefaults(level = AccessLevel.PUBLIC)
    public class Messages {

        String JAIL_PERM = "&c{admin} посадил в тюрьму {player} навсегда";
        String JAIL = "&c{admin} посадил в тюрьму {player} на {time}";
        String UNJAIL = "&aВышел из тюрьмы {player}";
        String JAILED = "&cВы сидите в тюьме {admin}. Вы выйдете: {end}";
        String NEVER = "никогда";

    }

    @FieldDefaults(level = AccessLevel.PUBLIC)
    public class Locations {

        String JAIL = "world;0;0;0;0;0";
        String UNJAIL = "world;0;0;0;0;0";

    }

    @FieldDefaults(level = AccessLevel.PUBLIC)
    public class Database {

        String HOST = "localhost:3306";
        String USER = "root";
        String PASSWORD = "1234";
        String DATABASE = "server";

    }

}
