package br.com.xg7network.xg7lobby.Eventos.Player;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;
import static br.com.xg7network.xg7lobby.XG7Lobby.va;

public class BlockEvents implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
            if (!(p.hasPermission(PermissionType.BLOCOS_COLOCAR.getPerm()))) {
                va.mandarMensagem(cm.getMessage().getString("eventos.permissão-colocar"), p);
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
            if (!(p.hasPermission(PermissionType.BLOCOS_QUEBRAR.getPerm()))) {
                va.mandarMensagem(cm.getMessage().getString("eventos.permissão-quebrar"), p);
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (clickedBlock != null) {
                Material blockType = clickedBlock.getType();
                World world = clickedBlock.getWorld();

                if (cm.getConfig().getStringList("mundos-ativados").contains(world.getName())) {
                    if (!(player.hasPermission(PermissionType.BLOCOS_INTERAGIR.getPerm()))
                            && blockType == Material.ANVIL || blockType == Material.HOPPER
                            || blockType == Material.DISPENSER || blockType == Material.DROPPER || blockType == Material.CHEST || blockType == Material.FURNACE) {
                        va.mandarMensagem(cm.getMessage().getString("eventos.permissão-interagir"), player);
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

}
