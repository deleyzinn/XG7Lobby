package br.com.xg7network.xg7lobby.Modulo;

import br.com.xg7network.xg7lobby.Modulo.Eventos.Mundo.CancelDayCycle;
import br.com.xg7network.xg7lobby.Modulo.Eventos.Mundo.WeatherEvent;
import br.com.xg7network.xg7lobby.Modulo.Eventos.Player.PlayerHungerEvent;
import br.com.xg7network.xg7lobby.Modulo.Scores.BossBar;
import br.com.xg7network.xg7lobby.Modulo.Scores.ScoreBoard;
import br.com.xg7network.xg7lobby.Modulo.Scores.TabList.Tablist;
import br.com.xg7network.xg7lobby.Modulo.Seletores.HotbarManager;
import br.com.xg7network.xg7lobby.XG7Lobby;

import java.util.ArrayList;
import java.util.List;


public class ModuleManager {

    private XG7Lobby plugin;
    private List<Module> modules = new ArrayList<>();

    public ModuleManager(XG7Lobby plugin) {
        this.plugin = plugin;
    }

    public void loadModules() {
            modules.add(new Efeitos(plugin));
            modules.add(new CancelDayCycle(plugin));
            modules.add(new Fly(plugin));

            modules.add(new ScoreBoard(plugin));
            modules.add(new Tablist(plugin));
            modules.add(new BossBar(plugin));
            modules.add(new Anuncios(plugin));

            modules.add(new HotbarManager(plugin));
            modules.add(new Chat(plugin));
            modules.add(new PlayerHungerEvent(plugin));
            modules.add(new WeatherEvent(plugin));
        for (Module module : modules) {
            module.onEnable();
        }
    }

    public void unloadModules() {
        for (Module module : modules) {
            module.onDisable();
        }
    }

}
