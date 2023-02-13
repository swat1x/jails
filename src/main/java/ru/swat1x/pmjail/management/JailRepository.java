package ru.swat1x.pmjail.management;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.var;
import ru.swat1x.database.Database;
import ru.swat1x.database.DatabaseDriver;

import java.sql.ResultSet;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JailRepository {

    Database database;

    @SneakyThrows
    private JailerModel map(ResultSet rs) {
        return new JailerModel(
                rs.getString("owner"),
                rs.getString("admin"),
                rs.getLong("start"),
                rs.getLong("end")
        );
    }

    public JailRepository(Database database) {
        this.database = database;

        createTable();
    }

    public void createTable() {
        database.sync().update("CREATE TABLE IF NOT EXISTS `jail` (" +
                "`owner` varchar(16), " +
                "`admin` varchar(16), " +
                "`start` long, " +
                "`end` long, " +
                "`state` boolean)"
        );
    }

    public CompletableFuture<JailerModel> loadActualModel(String username) {
        return database.async().query("SELECT * FROM `jail` WHERE `owner`=? AND `end` > ?",
                rs -> rs.next() ? null : map(rs),
                username, System.currentTimeMillis());
    }

    public void saveModel(JailerModel model) {
        database.async()
                .update("INSERT INTO `jail` (`owner`, `admin`, `start`, `end`) VALUES (?, ?, ?, ?)",
                        model.getOwner(),
                        model.getAdmin(),
                        model.getStart(),
                        model.getEnd()
                );
    }

    public JailerModel createModel(String admin, String username, Duration duration) {
        var start = System.currentTimeMillis();
        var end = start + duration.toMillis();
        JailerModel model = new JailerModel(username, admin, start, end);
        saveModel(model);
        return model;
    }

}
