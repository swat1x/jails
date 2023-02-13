package ru.swat1x.pmjail.management;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class JailListener implements Listener {

    JailManager manager;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        manager.handleJoin(e.getPlayer());
    }

}
