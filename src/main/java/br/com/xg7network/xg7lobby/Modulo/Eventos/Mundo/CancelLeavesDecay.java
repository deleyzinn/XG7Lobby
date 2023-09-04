package br.com.xg7network.xg7lobby.Modulo.Eventos.Mundo;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class CancelLeavesDecay implements Listener {

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent e) {
        if (cm.getConfig().getStringList("enabled-worlds").contains(e.getBlock().getWorld().getName())) {
            if (!cm.getConfig().getBoolean("leaves-decay")) {
                e.setCancelled(true);
            }
        }
    }
}
