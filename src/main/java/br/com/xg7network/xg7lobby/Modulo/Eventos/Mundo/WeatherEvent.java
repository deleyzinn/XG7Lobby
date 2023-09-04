package br.com.xg7network.xg7lobby.Modulo.Eventos.Mundo;

import br.com.xg7network.xg7lobby.Modulo.Module;
import br.com.xg7network.xg7lobby.XG7Lobby;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class WeatherEvent extends Module {

    public WeatherEvent(XG7Lobby plugin) {
        super(plugin);
    }


    @Override
    public void onEnable() {
        Bukkit.getScheduler().runTaskTimer(getPlugin(), () -> {
            for (World w : Bukkit.getWorlds()) {
                if (!cm.getConfig().getBoolean("weather-cycle")) {
                    if (cm.getConfig().getStringList("enabled-worlds").contains(w.getName())) {
                        w.setThundering(false);
                        w.setStorm(false);
                    }
                }
            }
        },0,5);
    }

    @Override
    public void onDisable() {

    }
}
