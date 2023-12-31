package br.com.xg7network.xg7lobby.Modulo.Eventos.Mundo;

import br.com.xg7network.xg7lobby.Modulo.Module;
import br.com.xg7network.xg7lobby.XG7Lobby;
import org.bukkit.Bukkit;
import org.bukkit.World;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;


public class CancelDayCycle extends Module {

    public CancelDayCycle(XG7Lobby plugin) {
        super(plugin);
    }

    @Override
    public void onEnable() {
        Bukkit.getScheduler().runTaskTimer(this.getPlugin(), () -> {
            for (String s : cm.getConfig().getStringList("enabled-worlds")) {
                World w = Bukkit.getWorld(s);
                if (w != null) {
                    if (!cm.getConfig().getBoolean("day-cycle")) {
                        w.setTime(cm.getConfig().getInt("time"));
                    }
                }
            }
        }, 20l, 0l);

    }

    @Override
    public void onDisable() {

    }
}
