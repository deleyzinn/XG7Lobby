package br.com.xg7network.xg7lobby.Modulo.Eventos;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class Lauchpad implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location blockunder = p.getLocation();
        blockunder.setY(blockunder.getY() - 1);
        if (cm.getConfig().getBoolean("launch-pad.enabled")) {
            if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
                if (cm.getConfig().getBoolean("launch-pad.one-block")) {
                    if (cm.getConfig().getBoolean("launch-pad.pressure-plate")) {
                        if (p.getLocation().getBlock().getType().equals(Material.valueOf(cm.getConfig().getString("launch-pad.block")))) {
                            String s = cm.getConfig().getString("launch-pad.sound");
                            String[] s2 = s.split(", ");
                            p.playSound(p.getLocation(), Sound.valueOf(s2[0]), Float.parseFloat(s2[1]), Float.parseFloat(s2[2]));
                            p.setVelocity(p.getEyeLocation().getDirection().multiply(cm.getConfig().getDouble("launch-pad.strength")).setY(cm.getConfig().getDouble("launch-pad.jump")));
                        }

                    } else {
                        if (blockunder.getBlock().getType().equals(Material.valueOf(cm.getConfig().getString("launch-pad.block")))) {
                            String s = cm.getConfig().getString("launch-pad.sound");
                            String[] s2 = s.split(", ");
                            p.playSound(p.getLocation(), Sound.valueOf(s2[0]), Float.parseFloat(s2[1]), Float.parseFloat(s2[2]));
                            p.setVelocity(p.getEyeLocation().getDirection().multiply(cm.getConfig().getDouble("launch-pad.strength")).setY(cm.getConfig().getDouble("launch-pad.jump")));
                        }
                    }
                } else {
                    if (p.getLocation().getBlock().getType().equals(Material.valueOf(cm.getConfig().getString("launch-pad.top-block"))) && blockunder.getBlock().getType().equals(Material.valueOf(cm.getConfig().getString("launch-pad.bottom-block")))) {
                        String s = cm.getConfig().getString("launch-pad.sound");
                        String[] s2 = s.split(", ");
                        p.playSound(p.getLocation(), Sound.valueOf(s2[0]), Float.parseFloat(s2[1]), Float.parseFloat(s2[2]));
                        p.setVelocity(p.getEyeLocation().getDirection().multiply(cm.getConfig().getDouble("launch-pad.strength")).setY(cm.getConfig().getDouble("launch-pad.jump")));
                    }
                }
            }
        }
    }
}
