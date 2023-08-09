package br.com.xg7network.xg7lobby.Modulo.Mundo;

import br.com.xg7network.xg7lobby.Modulo.Module;
import br.com.xg7network.xg7lobby.XG7Lobby;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;


public class CancelDayCycle extends Module {

    public CancelDayCycle(XG7Lobby plugin) {
        super(plugin);
    }

    @Override
    public void onEnable() {
        Bukkit.getScheduler().runTaskTimer(this.getPlugin(), () -> {
            for (String s : cm.getConfig().getStringList("mundos-ativados")) {
                World w = Bukkit.getWorld(s);
                if (w != null) {
                    if (!cm.getConfig().getBoolean("CicloDoDia")) {
                        w.setTime(cm.getConfig().getInt("TempoDoDia"));
                        w.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                    } else {
                        w.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
                    }
                }
            }
        }, 20l, 0l);

    }

    @Override
    public void onDisable() {

    }
}
