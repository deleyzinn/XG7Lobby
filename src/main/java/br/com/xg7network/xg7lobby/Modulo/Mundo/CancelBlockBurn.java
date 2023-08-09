package br.com.xg7network.xg7lobby.Modulo.Mundo;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class CancelBlockBurn implements Listener {

    @EventHandler
    public void onBlockBurn(BlockBurnEvent e) {

        if (cm.getConfig().getStringList("mundos-ativados").contains(e.getBlock().getWorld().getName())) {
            if (cm.getConfig().getBoolean("QueimaDeBlocos")) {
                e.setCancelled(true);
            }
        }
    }
}
