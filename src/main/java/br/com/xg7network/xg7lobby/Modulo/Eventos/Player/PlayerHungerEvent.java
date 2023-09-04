package br.com.xg7network.xg7lobby.Modulo.Eventos.Player;

import br.com.xg7network.xg7lobby.Modulo.Module;
import br.com.xg7network.xg7lobby.XG7Lobby;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class PlayerHungerEvent extends Module {

    public PlayerHungerEvent(XG7Lobby plugin) {
        super(plugin);
    }

    @Override
    public void onEnable() {

        Bukkit.getScheduler().runTaskTimer(getPlugin(), () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
                    if (!cm.getConfig().getBoolean("hunger-loss")) {
                        p.setFoodLevel(20);
                    }
                }
            }
        },0, 5);

    }

    @Override
    public void onDisable() {

    }
}
