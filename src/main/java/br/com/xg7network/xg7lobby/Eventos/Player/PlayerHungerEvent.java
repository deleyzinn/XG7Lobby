package br.com.xg7network.xg7lobby.Eventos.Player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class PlayerHungerEvent implements Listener {

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        Player p = (Player) e.getEntity();
        if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
            if (cm.getConfig().getBoolean("hunger-loss")) {
                ((Player) e.getEntity()).setFoodLevel(20);
            }
        }
    }
}
