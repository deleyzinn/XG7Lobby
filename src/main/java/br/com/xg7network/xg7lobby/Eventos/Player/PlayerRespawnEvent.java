package br.com.xg7network.xg7lobby.Eventos.Player;

import br.com.xg7network.xg7lobby.XG7Lobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class PlayerRespawnEvent implements Listener {

    private XG7Lobby pl;

    public PlayerRespawnEvent(XG7Lobby pl) {
        this.pl = pl;
    }

    String s = cm.getData().getString("lobby.mundo");
    World w = Bukkit.getWorld(s);

    @EventHandler
    public void onRespawn(org.bukkit.event.player.PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (pl.getConfig().getBoolean("tp-quando-morrer")) {
            if (cm.getData().get("lobby.mundo") != null) {

                p.teleport(new Location(w,
                        cm.getData().getDouble("lobby.x"),
                        cm.getData().getDouble("lobby.y"),
                        cm.getData().getDouble("lobby.z"),
                        (float) cm.getData().getDouble("lobby.yaw"),
                        (float) cm.getData().getDouble("lobby.pitch")
                ));
            } else {
                p.teleport(w.getSpawnLocation());
            }
        }
        if (pl.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
            if (!pl.getConfig().getBoolean("tp-quando-morrer")) {
                if (cm.getData().get("lobby.mundo") != null) {

                    p.teleport(new Location(w,
                            cm.getData().getDouble("lobby.x"),
                            cm.getData().getDouble("lobby.y"),
                            cm.getData().getDouble("lobby.z"),
                            (float) cm.getData().getDouble("lobby.yaw"),
                            (float) cm.getData().getDouble("lobby.pitch")
                    ));
                } else {
                    p.teleport(w.getSpawnLocation());
                }
            }
        }

    }
}
