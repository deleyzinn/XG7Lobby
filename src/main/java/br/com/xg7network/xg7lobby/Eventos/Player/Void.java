package br.com.xg7network.xg7lobby.Eventos.Player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class Void implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
            if(Bukkit.getServer().getVersion().contains("1.18") || Bukkit.getServer().getVersion().contains("1.19") || Bukkit.getServer().getVersion().contains("1.20")) {
                if (p.getLocation().getBlockY() <= -66) {
                    if (cm.getData().getString("lobby.mundo") == null) {
                        p.teleport(p.getWorld().getSpawnLocation());
                    } else {
                        String lobbyWN = cm.getData().getString("lobby.mundo");
                        if (lobbyWN != null) {
                            World w = Bukkit.getWorld(lobbyWN);
                            p.teleport(new Location(
                                    w,
                                    cm.getData().getDouble("lobby.x"),
                                    cm.getData().getDouble("lobby.y"),
                                    cm.getData().getDouble("lobby.z"),
                                    (float) cm.getData().getDouble("lobby.yaw"),
                                    (float) cm.getData().getDouble("lobby.pitch")));

                        }
                    }
                }
            } else {
                if (p.getLocation().getBlockY() <= -2) {

                    if (cm.getData().getString("lobby.mundo") == null) {
                        p.teleport(p.getWorld().getSpawnLocation());
                    } else {
                        String lobbyWN = cm.getData().getString("lobby.mundo");
                        if (lobbyWN != null) {
                            World w = Bukkit.getWorld(lobbyWN);
                            p.teleport(new Location(
                                    w,
                                    cm.getData().getDouble("lobby.x"),
                                    cm.getData().getDouble("lobby.y"),
                                    cm.getData().getDouble("lobby.z"),
                                    (float) cm.getData().getDouble("lobby.yaw"),
                                    (float) cm.getData().getDouble("lobby.pitch")));

                        }
                    }

                }
            }
        }
    }
}
