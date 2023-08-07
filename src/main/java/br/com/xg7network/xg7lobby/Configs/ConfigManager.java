package br.com.xg7network.xg7lobby.Configs;

import br.com.xg7network.xg7lobby.XG7Lobby;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class ConfigManager {

    private XG7Lobby pl;

    public ConfigManager(XG7Lobby pl) {
        this.pl = pl;
    }

    public static FileConfiguration config = null;
    public static  FileConfiguration seletor = null;
    public static  FileConfiguration score = null;
    public static FileConfiguration data = null;
    public static  FileConfiguration mensagem = null;

    public  static File Cfile = null;
    public  static File Dfile = null;
    public  static File Scfile = null;
    public static  File Sefile = null;
    public static  File Mfile = null;



    public void reloadConfig() {
        if (this.Cfile == null) {
            this.Cfile = new File(pl.getDataFolder(), "config.yml");
        }
        this.config = YamlConfiguration.loadConfiguration(this.Cfile);
    }

    public FileConfiguration getConfig() {
        if (config == null) {
            reloadConfig();
        }
        return this.config;
    }

    public void saveConfig() {
        if (this.config == null || this.Cfile == null)
            return;
        try {
            this.getConfig().save("config.yml");
        } catch (IOException e) {
            pl.getLogger().log(Level.SEVERE, "Não foi possível carregar o arquivo: " + this.Cfile, e);
        }
    }

    public void loadConfig() {
        if (Cfile == null) {
            Cfile = new File(pl.getDataFolder(), "config.yml");
        }
        if (!Cfile.exists()) {
            pl.saveResource("config.yml", false);
        }
    }

    public void reloadMessage() {
        if (this.Mfile == null) {
            this.Mfile = new File(pl.getDataFolder(), "mensagens.yml");
        }
        this.mensagem = YamlConfiguration.loadConfiguration(this.Mfile);
    }

    public FileConfiguration getMessage() {
        if (mensagem == null) {
            reloadMessage();
        }
        return this.mensagem;
    }

    public void saveMessage() {
        if (this.mensagem == null || this.Mfile == null)
            return;
        try {
            this.getMessage().save("mensagens.yml");
        } catch (IOException e) {
            pl.getLogger().log(Level.SEVERE, "Não foi possível carregar o arquivo: " + this.Mfile, e);
        }
    }

    public void loadMessage() {
        if (Mfile == null) {
            Mfile = new File(pl.getDataFolder(), "mensagens.yml");
        }
        if (!Mfile.exists()) {
            pl.saveResource("mensagens.yml", false);
        }
    }

    public void reloadSeletor() {
        if (this.Sefile == null) {
            this.Sefile = new File(pl.getDataFolder(), "seletores.yml");
        }
        this.seletor = YamlConfiguration.loadConfiguration(this.Sefile);
    }

    public FileConfiguration getSeletor() {
        if (seletor == null) {
            reloadSeletor();
        }
        return this.seletor;
    }

    public void saveSeletor() {
        if (this.seletor == null || this.Sefile == null)
            return;
        try {
            this.getSeletor().save("seletores.yml");
        } catch (IOException e) {
            pl.getLogger().log(Level.SEVERE, "Não foi possível carregar o arquivo: " + this.Sefile, e);
        }
    }

    public void loadSeletor() {
        if (Sefile == null) {
            Sefile = new File(pl.getDataFolder(), "seletores.yml");
        }
        if (!Sefile.exists()) {
            pl.saveResource("seletores.yml", false);
        }
    }

    public void reloadData() {
        if (this.Dfile == null) {
            this.Dfile = new File(pl.getDataFolder(), "data.yml");
        }
        this.data = YamlConfiguration.loadConfiguration(this.Dfile);
    }

    public FileConfiguration getData() {
        if (data == null) {
            reloadData();
        }
        return this.data;
    }

    public void saveData() {
        if (this.data == null || this.Dfile == null)
            return;
        try {
            this.getData().save("data.yml");
        } catch (IOException e) {
            pl.getLogger().log(Level.SEVERE, "Não foi possível carregar o arquivo: " + this.Dfile, e);
        }
    }

    public void loadData() {
        if (Dfile == null) {
            Dfile = new File(pl.getDataFolder(), "data.yml");
        }
        if (!Dfile.exists()) {
            pl.saveResource("data.yml", false);
        }
    }

    public void reloadAll() {
        this.reloadConfig();
        this.reloadData();
        this.reloadMessage();
        this.reloadSeletor();
    }

    public void loadAll() {
        this.loadConfig();
        this.loadData();
        this.loadMessage();
        this.loadSeletor();
    }

    public void saveAll() {
        this.saveConfig();
        this.saveData();
        this.saveMessage();
        this.saveSeletor();
    }
}
