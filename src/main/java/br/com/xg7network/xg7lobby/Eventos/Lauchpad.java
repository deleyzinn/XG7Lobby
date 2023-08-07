package br.com.xg7network.xg7lobby.Eventos;

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
        if (cm.getConfig().getBoolean("launchPad.ativado")) {
            if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
                if (cm.getConfig().getBoolean("launchPad.umbloco")) {
                    if (cm.getConfig().getBoolean("launchPad.placadepressao")) {
                        if (p.getLocation().getBlock().getType().equals(Material.valueOf(cm.getConfig().getString("launchPad.bloco")))) {
                            String s = cm.getConfig().getString("launchPad.som");
                            String[] s2 = s.split(", ");
                            p.playSound(p.getLocation(), Sound.valueOf(s2[0]), Float.parseFloat(s2[1]), Float.parseFloat(s2[2]));
                            p.setVelocity(p.getEyeLocation().getDirection().multiply(cm.getConfig().getDouble("launchPad.força")).setY(cm.getConfig().getDouble("launchPad.pulo")));
                        }

                    } else {
                        if (blockunder.getBlock().getType().equals(Material.valueOf(cm.getConfig().getString("launchPad.bloco")))) {
                            String s = cm.getConfig().getString("launchPad.som");
                            String[] s2 = s.split(", ");
                            p.playSound(p.getLocation(), Sound.valueOf(s2[0]), Float.parseFloat(s2[1]), Float.parseFloat(s2[2]));
                            p.setVelocity(p.getEyeLocation().getDirection().multiply(cm.getConfig().getDouble("launchPad.força")).setY(cm.getConfig().getDouble("launchPad.pulo")));
                        }
                    }
                } else {
                    if (p.getLocation().getBlock().getType().equals(Material.valueOf(cm.getConfig().getString("launchPad.blocodecima"))) && blockunder.getBlock().getType().equals(Material.valueOf(cm.getConfig().getString("launchPad.blocodebaixo")))) {
                        String s = cm.getConfig().getString("launchPad.som");
                        String[] s2 = s.split(", ");
                        p.playSound(p.getLocation(), Sound.valueOf(s2[0]), Float.parseFloat(s2[1]), Float.parseFloat(s2[2]));
                        p.setVelocity(p.getEyeLocation().getDirection().multiply(cm.getConfig().getDouble("launchPad.força")).setY(cm.getConfig().getDouble("launchPad.pulo")));
                    }
                }
            }
        }
    }
}
