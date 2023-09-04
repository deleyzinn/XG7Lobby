package br.com.xg7network.xg7lobby.Modulo.Eventos.Mundo;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class SpawnMobs implements Listener {

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent e) {
        if  (cm.getConfig().getBoolean("spawn-mobs")) {
            if (cm.getConfig().getStringList("enabled-worlds").contains(e.getLocation().getWorld().getName())) {
                e.setCancelled(false);
            }
        } else if (!cm.getConfig().getBoolean("spawn-mobs")) {
            if (cm.getConfig().getStringList("enabled-worlds").contains(e.getLocation().getWorld().getName())) {
                e.setCancelled(true);
            }
        }
    }
}
