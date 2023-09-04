package br.com.xg7network.xg7lobby.Modulo.Eventos.Player;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.Listener;

import static br.com.xg7network.xg7lobby.XG7Lobby.*;

public class PlayerAttackEvent implements Listener {

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player attacker = (Player) e.getDamager();
            if (cm.getConfig().getStringList("enabled-worlds").contains(attacker.getWorld().getName())) {
                if (!cm.getConfig().getBoolean("attack")) {
                    if (!attacker.hasPermission(PermissionType.ATACAR.getPerm())) {
                        e.setCancelled(true);
                        va.mandarMensagem(cm.getMessage().getString("events.permission-attack"), attacker);
                    }
                }
            }
        }
    }


}
