package br.com.xg7network.xg7lobby.Eventos.Player;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static br.com.xg7network.xg7lobby.Comandos.Lobby.Fly.voar;
import static br.com.xg7network.xg7lobby.XG7Lobby.cm;
import static br.com.xg7network.xg7lobby.XG7Lobby.va;

public class DoubleJump implements Listener {

    private Cache<UUID, Long> cooldown = CacheBuilder.newBuilder().expireAfterWrite(cm.getConfig().getInt("doublejump.cooldown"), TimeUnit.SECONDS).build();

    @EventHandler
    public void onPlayerFly(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        if (cm.getConfig().getBoolean("doublejump.ativado")) {
            if (!(p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR)) {
                if (p.hasPermission(PermissionType.DOUBLE_JUMP.getPerm())) {
                    if (!voar.containsKey(p.getUniqueId())) {
                        voar.put(p.getUniqueId(), false);
                    }
                    if (e.isFlying() && !voar.get(p.getUniqueId())) {
                        if (this.cooldown.asMap().containsKey(p.getUniqueId()) && (Long) this.cooldown.asMap().get(p.getUniqueId()) > System.currentTimeMillis()) {
                            long distancia = this.cooldown.asMap().get(p.getUniqueId()) - System.currentTimeMillis();
                            va.mandarMensagem(cm.getMessage().getString("eventos.no-cooldown").replace("[SEGUNDOS]", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(distancia))), p);
                        } else {
                            this.cooldown.put(p.getUniqueId(), System.currentTimeMillis() + cm.getConfig().getLong("doublejump.cooldown") * 1000L);
                            if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
                                if (p.hasPermission(PermissionType.DOUBLE_JUMP.getPerm())) {
                                    Block b = p.getWorld().getBlockAt(p.getLocation().subtract(0, 1, 0));
                                    if (b.getType().equals(Material.AIR)) {
                                        String s = cm.getConfig().getString("doublejump.som");
                                        String[] s2 = s.split(", ");
                                        p.playSound(p.getLocation(), Sound.valueOf(s2[0]), Float.parseFloat(s2[1]), Float.parseFloat(s2[2]));
                                        p.setVelocity(p.getEyeLocation().getDirection().multiply(cm.getConfig().getDouble("doublejump.força")).setY(cm.getConfig().getDouble("doublejump.pulo")));

                                    }
                                }

                            }
                        }
                    }
                }
                e.setCancelled(true);
            }
        }

    }
}