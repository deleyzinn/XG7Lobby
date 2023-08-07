package br.com.xg7network.xg7lobby.Eventos.Player;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.Listener;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class PlayerDamageEvent implements Listener {

    @EventHandler
    public void onPlayerDrown(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
                if (!cm.getConfig().getBoolean("LevarDanos")) {
                    if (!cm.getConfig().getBoolean("LevarDanoPorAfogamento")) {
                        if (e.getCause().equals(EntityDamageEvent.DamageCause.DROWNING)) {
                            e.setCancelled(!p.hasPermission(PermissionType.ADMIN.getPerm()));
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerFire(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
                if (!cm.getConfig().getBoolean("LevarDanos")) {
                    if (!cm.getConfig().getBoolean("LevarDanoPorFogo")) {
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
            if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
                if (!cm.getConfig().getBoolean("LevarDanos")) {
                    if (!cm.getConfig().getBoolean("LevarDanoPorExplosão")) {
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
            if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
                if (!cm.getConfig().getBoolean("LevarDanos")) {
                    if (!cm.getConfig().getBoolean("LevarDanoPorExplosão")) {
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
            if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
                if (!cm.getConfig().getBoolean("LevarDanos")) {
                    if (!cm.getConfig().getBoolean("LevarDanoPorBlocoCaindo")) {
                        if (e.getCause().equals(EntityDamageEvent.DamageCause.FALLING_BLOCK)) {
                            e.setCancelled(!p.hasPermission(PermissionType.ADMIN.getPerm()));
                        }
                    }
                    if (!cm.getConfig().getBoolean("LevarDanoPorBlocos")) {
                        e.setCancelled(!p.hasPermission(PermissionType.ADMIN.getPerm()));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerFly(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
                if (!cm.getConfig().getBoolean("LevarDanos")) {
                    if (!cm.getConfig().getBoolean("LevarDanoPorExplosão")) {
                        if (e.getCause().equals(EntityDamageEvent.DamageCause.FLY_INTO_WALL)) {
                            e.setCancelled(!p.hasPermission(PermissionType.ADMIN.getPerm()));
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerMagma(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
                if (!cm.getConfig().getBoolean("LevarDanos")) {
                    if (!cm.getConfig().getBoolean("LevarDanoPorExplosão")) {
                        if (e.getCause().equals(EntityDamageEvent.DamageCause.HOT_FLOOR)) {
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
            if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
                if (!cm.getConfig().getBoolean("LevarDanos")) {
                    if (!cm.getConfig().getBoolean("LevarDanoPorExplosão")) {
                        if (e.getCause().equals(EntityDamageEvent.DamageCause.LAVA)) {
                            e.setCancelled(!p.hasPermission(PermissionType.ADMIN.getPerm()));
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDragon(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
                if (!cm.getConfig().getBoolean("LevarDanos")) {
                    if (!cm.getConfig().getBoolean("LevarDanoPorExplosão")) {
                        if (e.getCause().equals(EntityDamageEvent.DamageCause.DRAGON_BREATH)) {
                            e.setCancelled(!p.hasPermission(PermissionType.ADMIN.getPerm()));
                        }
                    }
                }
            }
        }
    }
}
