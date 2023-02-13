package ru.swat1x.pmjail.management;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class JailManager {

    Map<String, JailerModel> actualModels = new HashMap<>();

    JailRepository repository;
    Location jailLocation;
    Location unjailLocation;

    public void jailPlayer(Player admin, String target) {

    }

    public void unjailPlayer(Player admin, String target) {

    }

    public void handleJoin(Player player) {
        repository.loadActualModel(player.getName()).thenAccept(model -> {
            if (model != null) {
               player.teleport(jailLocation);
            }
        });
    }

}
