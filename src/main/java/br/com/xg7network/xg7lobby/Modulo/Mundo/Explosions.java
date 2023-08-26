package br.com.xg7network.xg7lobby.Modulo.Mundo;

import br.com.xg7network.xg7lobby.XG7Lobby;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;


public class Explosions implements Listener {

    @EventHandler
    public void onExplosion(EntityExplodeEvent e) {
        if (cm.getConfig().getBoolean("cancel-explosions") && cm.getConfig().getStringList("enabled-worlds").contains(e.getEntity().getWorld().getName())) {
            e.setCancelled(true);
        }

    }
}
