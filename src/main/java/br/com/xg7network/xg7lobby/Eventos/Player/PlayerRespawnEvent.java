//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package br.com.xg7network.xg7lobby.Eventos.Player;

import br.com.xg7network.xg7lobby.XG7Lobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerRespawnEvent implements Listener {
    private XG7Lobby pl;

    public PlayerRespawnEvent(XG7Lobby pl) {
        this.pl = pl;
    }

    @EventHandler
    public void onRespawn(org.bukkit.event.player.PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (XG7Lobby.cm.getConfig().getBoolean("tp-quando-morrer")) {
            String s = XG7Lobby.cm.getData().getString("lobby.mundo");
            Bukkit.getScheduler().runTaskLater(this.pl, () -> {
                if (s != null) {
                    World w = Bukkit.getWorld(s);
                    p.teleport(new Location(w, XG7Lobby.cm.getData().getDouble("lobby.x"), XG7Lobby.cm.getData().getDouble("lobby.y"), XG7Lobby.cm.getData().getDouble("lobby.z"), (float)XG7Lobby.cm.getData().getDouble("lobby.yaw"), (float)XG7Lobby.cm.getData().getDouble("lobby.pitch")));
                } else {
                    p.teleport(p.getWorld().getSpawnLocation());
                }

            }, 1L);
        }

    }
}
