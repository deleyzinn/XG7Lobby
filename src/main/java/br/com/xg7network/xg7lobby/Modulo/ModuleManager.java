package br.com.xg7network.xg7lobby.Modulo;

import br.com.xg7network.xg7lobby.Modulo.Mundo.CancelDayCycle;
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
            modules.add(new Warns(plugin));
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
