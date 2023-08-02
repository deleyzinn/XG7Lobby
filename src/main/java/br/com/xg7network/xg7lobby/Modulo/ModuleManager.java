package br.com.xg7network.xg7lobby.Modulo;

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

        for (Module module : modules) {
            modules.add(new Efeitos(plugin));
            module.onEnable();
        }
    }

    public void unloadModules() {
        for (Module module : modules) {
            module.onDisable();
        }
    }

}
