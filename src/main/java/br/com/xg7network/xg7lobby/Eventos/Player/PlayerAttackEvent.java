package br.com.xg7network.xg7lobby.Eventos.Player;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import br.com.xg7network.xg7lobby.XG7Lobby;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityUnleashEvent;

import static br.com.xg7network.xg7lobby.XG7Lobby.*;

public class PlayerAttackEvent implements Listener {

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
                Player attacker = (Player) e.getDamager();
                Player victim = (Player) e.getEntity();
                if (cm.getConfig().getStringList("enabled-worlds").contains(attacker.getWorld().getName()) || cm.getConfig().getStringList("enabled-worlds").contains(victim.getWorld().getName())) {
                    if (!attacker.hasPermission(PermissionType.ADMIN.getPerm()) || !attacker.hasPermission(PermissionType.ATACAR.getPerm())) {
                        e.setCancelled(true);
                        va.mandarMensagem(cm.getMessage().getString("events.permission-attack"), attacker);
                    }
                }
            }

        }
    }
}
