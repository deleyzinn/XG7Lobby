package br.com.xg7network.xg7lobby.Modulo.Mundo;

import br.com.xg7network.xg7lobby.XG7Lobby;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class CancelLeavesDecay implements Listener {

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent e) {
        if (cm.getConfig().getStringList("mundos-ativados").contains(e.getBlock().getWorld().getName())) {
            if (!cm.getConfig().getBoolean("FolhasCaindo")) {
                e.setCancelled(true);
            }
        }
    }
}
