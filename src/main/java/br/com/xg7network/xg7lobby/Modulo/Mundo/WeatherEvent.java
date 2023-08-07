package br.com.xg7network.xg7lobby.Modulo.Mundo;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class WeatherEvent implements Listener {

    @EventHandler
    public void onWheather(WeatherChangeEvent e) {
        if (!cm.getConfig().getBoolean("CicloDaChuva")){
            if (cm.getConfig().getStringList("mundos_ativados").contains(e.getWorld().getName())) {
                e.setCancelled(true);
            }
        }
    }



}
