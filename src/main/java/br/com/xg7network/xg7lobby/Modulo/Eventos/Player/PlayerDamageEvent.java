package br.com.xg7network.xg7lobby.Modulo.Eventos.Player;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.Listener;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;
import static br.com.xg7network.xg7lobby.XG7Lobby.va;

public class PlayerDamageEvent implements Listener {


    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
                if (!cm.getConfig().getBoolean("take-damage")) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDrown(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
                if (cm.getConfig().getBoolean("take-damage")) {
                    if (!cm.getConfig().getBoolean("take-drowning-damage")) {
                        if (e.getCause().equals(EntityDamageEvent.DamageCause.DROWNING)) {
                            e.setCancelled(!p.hasPermission(PermissionType.ADMIN.getPerm()));
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player) && e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {

                if (cm.getConfig().getBoolean("take-damage")) {
                    if (!cm.getConfig().getBoolean("take-damage-by-entities")) {
                        e.setCancelled(!p.hasPermission(PermissionType.ADMIN.getPerm()));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerFire(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
                if (cm.getConfig().getBoolean("take-damage")) {
                    if (!cm.getConfig().getBoolean("take-damage-by-fire")) {
                        if (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE)) {
                            e.setCancelled(!p.hasPermission(PermissionType.ADMIN.getPerm()));
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerExplosion(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
                if (cm.getConfig().getBoolean("take-damage")) {
                    if (!cm.getConfig().getBoolean("take-blast-damage")) {
                        if (e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) || e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) {
                            e.setCancelled(!p.hasPermission(PermissionType.ADMIN.getPerm()));
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void onPlayerFall(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
                if (cm.getConfig().getBoolean("take-damage")) {
                    if (!cm.getConfig().getBoolean("take-damage-by-fall")) {
                        if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                            e.setCancelled(!p.hasPermission(PermissionType.ADMIN.getPerm()));
                        }

                    }
                }
            }
        }
    }

    @EventHandler
    public void onBlockAttack(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
                if (cm.getConfig().getBoolean("take-damage")) {
                    if (!cm.getConfig().getBoolean("take-damage-per-falling-block")) {
                        if (e.getCause().equals(EntityDamageEvent.DamageCause.FALLING_BLOCK) && e.getCause().equals(EntityDamageEvent.DamageCause.SUFFOCATION)) {
                            e.setCancelled(!p.hasPermission(PermissionType.ADMIN.getPerm()));
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerLava(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
                if (cm.getConfig().getBoolean("take-damage")) {
                    if (!cm.getConfig().getBoolean("take-damage-by-lava")) {
                        if (e.getCause().equals(EntityDamageEvent.DamageCause.LAVA)) {
                            e.setCancelled(!p.hasPermission(PermissionType.ADMIN.getPerm()));
                        }
                    }
                }
            }
        }
    }
}
