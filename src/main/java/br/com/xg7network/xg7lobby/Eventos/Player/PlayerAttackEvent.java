package br.com.xg7network.xg7lobby.Eventos.Player;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import br.com.xg7network.xg7lobby.XG7Lobby;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.Listener;

import static br.com.xg7network.xg7lobby.XG7Lobby.*;

public class PlayerAttackEvent implements Listener {

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
                if (!cm.getConfig().getBoolean("Ataque")) {
                    if (!(p.hasPermission(PermissionType.ADMIN.getPerm()) || p.hasPermission(PermissionType.ATACAR.getPerm()))) {
                        e.setCancelled(true);
                        va.mandarMensagem(cm.getMessage().getString("eventos.permissão-atacar"), p);
                    }
                }
                if (cm.getConfig().getBoolean("LevarDanoPorAtaque")) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
