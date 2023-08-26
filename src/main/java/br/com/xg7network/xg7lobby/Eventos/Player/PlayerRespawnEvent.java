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

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class PlayerRespawnEvent implements Listener {
    private XG7Lobby pl;

    public PlayerRespawnEvent(XG7Lobby pl) {
        this.pl = pl;
    }

    @EventHandler
    public void onRespawn(org.bukkit.event.player.PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (cm.getConfig().getBoolean("tp-when-die")) {
            String s = cm.getData().getString("lobby.world");
            Bukkit.getScheduler().runTaskLater(this.pl, () -> {
                if (s != null) {
                    World w = Bukkit.getWorld(s);
                    p.teleport(new Location(w, cm.getData().getDouble("lobby.x"), cm.getData().getDouble("lobby.y"), cm.getData().getDouble("lobby.z"), (float) cm.getData().getDouble("lobby.yaw"), (float) cm.getData().getDouble("lobby.pitch")));
                } else {
                    p.teleport(p.getWorld().getSpawnLocation());
                }

            }, 1L);
        }

    }
}
