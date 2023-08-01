package br.com.xg7network.xg7lobby;

import br.com.xg7network.xg7lobby.Configs.ConfigType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;

public class ConfigManager {

    public FileConfiguration config = null;
    public File file = null;

    public void reloadConfig(ConfigType configtype) {
        if (this.file == null) {
            this.file = new File(new XG7Lobby().getDataFolder(), configtype.get());
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public FileConfiguration getConfig(ConfigType configtype) {
        if (configtype == null) {
            reloadConfig(configtype);
        }
        return this.config;
    }

    public void saveConfig(ConfigType configType) {
        if (this.config == null || this.file == null)
            return;
        try {
            this.getConfig(configType).save(configType.get());
        } catch (IOException e) {
            new XG7Lobby().getLogger().log(Level.SEVERE, "Não foi possível carregar o arquivo: " + this.messageFile, e);
        }
    }
}
