package br.com.xg7network.xg7lobby;

import br.com.xg7network.xg7lobby.Comandos.Abrir;
import br.com.xg7network.xg7lobby.Comandos.Lobby.Fly;
import br.com.xg7network.xg7lobby.Comandos.Lobby.Lobby;
import br.com.xg7network.xg7lobby.Comandos.Lobby.Setlobby;
import br.com.xg7network.xg7lobby.Comandos.Moderação.*;
import br.com.xg7network.xg7lobby.Comandos.Reload.ReloadCommand;
import br.com.xg7network.xg7lobby.Comandos.Reload.ReloadConfigCommand;
import br.com.xg7network.xg7lobby.Configs.ConfigManager;
import br.com.xg7network.xg7lobby.Eventos.Lauchpad;
import br.com.xg7network.xg7lobby.Eventos.EntradaESaida;
import br.com.xg7network.xg7lobby.Eventos.PingEvent;
import br.com.xg7network.xg7lobby.Eventos.Player.*;
import br.com.xg7network.xg7lobby.Eventos.Player.Void;
import br.com.xg7network.xg7lobby.Modulo.ModuleManager;
import br.com.xg7network.xg7lobby.Modulo.Mundo.*;
import br.com.xg7network.xg7lobby.Modulo.Scores.ScoreBoard;
import br.com.xg7network.xg7lobby.Modulo.Scores.Tablist;
import br.com.xg7network.xg7lobby.Utilidades.Ações;
import br.com.xg7network.xg7lobby.Utilidades.Inventário;
import br.com.xg7network.xg7lobby.Utilidades.VerifAction;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class XG7Lobby extends JavaPlugin {

    public static ConfigManager cm;

    public static Ações ac;

    ModuleManager MM;

    public static VerifAction va;


    public static String prefix = ChatColor.BLUE + "[XG7 " + ChatColor.DARK_AQUA + "Lob" + ChatColor.AQUA + "by] " + ChatColor.RESET;

    @Override
    public void onEnable() {
        this.getServer().getConsoleSender().sendMessage(prefix + "Carregando...");
        this.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "__   __  ___   ______     " + ChatColor.DARK_AQUA + "_       ____    ____ " + ChatColor.AQUA + "  ____ __   __");
        this.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "\\ \\ / / / __| |___   /   " + ChatColor.DARK_AQUA + "| |     / __ \\  | __ ) " + ChatColor.AQUA + "| __ )\\ \\ / /");
        this.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + " \\ v / | |  _     / /    " + ChatColor.DARK_AQUA + "| |    | | | |  | \\ \\\\" + ChatColor.AQUA + " | \\ \\\\ \\ V /");
        this.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + " / . \\ | |_| |   / /     " + ChatColor.DARK_AQUA + "| |___ | |_| |  | |_) |" + ChatColor.AQUA + "| |_) | | |  ");
        this.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "/_/ \\_\\ \\____|  /_/      " + ChatColor.DARK_AQUA + "|_____| \\____/  |____/ " + ChatColor.AQUA + "|____/  |_|");

        try {
            Class.forName("org.spigotmc.SpigotConfig");
        } catch (ClassNotFoundException var4) {
            this.getServer().getConsoleSender().sendMessage("                       SPIGOT NÃO DETECTADO                    ");
            this.getServer().getConsoleSender().sendMessage("ESTE PLUGIN PRECISA DO SPIGOT PARA FUNCIONAR!                  ");
            this.getServer().getConsoleSender().sendMessage("BAIXE AQUI: https://www.spigotmc.org/wiki/spigot-installation/.");
            this.getServer().getConsoleSender().sendMessage("O PLUGIN IRÁ DESLIGAR!                                         ");
            this.getPluginLoader().disablePlugin(this);
            return;
        }

        this.getServer().getConsoleSender().sendMessage(prefix + "Carregando arquivos de configuração...");
        cm = new ConfigManager(this);
        cm.loadAll();
        ac = new Ações(this);
        va = new VerifAction();


        this.getServer().getConsoleSender().sendMessage(prefix + "Carregando comandos...");
        this.getCommand("xg7lobbyreloadconfig").setExecutor(new ReloadConfigCommand());
        this.getCommand("xg7lobbyreload").setExecutor(new ReloadCommand(this));
        this.getCommand("xg7lobbysetlobby").setExecutor(new Setlobby(this));
        this.getCommand("xg7lobbylobby").setExecutor(new Lobby());
        this.getCommand("xg7lobbymute").setExecutor(new Mute(this));
        this.getServer().getPluginManager().registerEvents(new Mute(this), this);
        this.getCommand("xg7lobbyunmute").setExecutor(new Unmute());
        this.getCommand("xg7lobbyban").setExecutor(new Ban());
        this.getCommand("xg7lobbyunban").setExecutor(new Unban());
        this.getCommand("xg7lobbykick").setExecutor(new Kick());
        this.getCommand("xg7lobbytempban").setExecutor(new Tempban());
        this.getCommand("xg7lobbywarn").setExecutor(new Warn(this));
        this.getCommand("xg7lobbywarns").setExecutor(new Warn(this));
        this.getServer().getPluginManager().registerEvents(new Warn(this), this);
        this.getCommand("xg7lobbyfly").setExecutor(new Fly());
        this.getCommand("xg7lobbygui").setExecutor(new Abrir());

        this.getServer().getConsoleSender().sendMessage(prefix + "Carregando eventos...");
        this.getServer().getPluginManager().registerEvents(new Inventário(), this);
        this.getServer().getPluginManager().registerEvents(new EntradaESaida(), this);
        this.getServer().getPluginManager().registerEvents(new Lauchpad(), this);
        this.getServer().getPluginManager().registerEvents(new DoubleJump(), this);
        this.getServer().getPluginManager().registerEvents(new PingEvent(), this);

        this.getServer().getPluginManager().registerEvents(new Void(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerHungerEvent(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerDropPickupEvent(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerAttackEvent(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerDamageEvent(), this);
        this.getServer().getPluginManager().registerEvents(new BlockEvents(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerRespawnEvent(this), this);

        this.getServer().getPluginManager().registerEvents(new CancelBlockBurn(), this);
        this.getServer().getPluginManager().registerEvents(new CancelLeavesDecay(), this);
        this.getServer().getPluginManager().registerEvents(new Explosions(), this);
        this.getServer().getPluginManager().registerEvents(new SpawnMobs(), this);
        this.getServer().getPluginManager().registerEvents(new CancelBlockBurn(), this);
        this.getServer().getPluginManager().registerEvents(new WeatherEvent(), this);

        this.getServer().getConsoleSender().sendMessage(prefix + "Carregando Módulo...");
        MM = new ModuleManager(this);
        MM.loadModules();

        this.getServer().getPluginManager().registerEvents(new ScoreBoard(this), this);
        this.getServer().getPluginManager().registerEvents(new Tablist(this), this);



        this.getServer().getConsoleSender().sendMessage(prefix + "Carregado!");


    }

    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage(prefix + "Desligando...");
        cm.saveAll();
        MM.unloadModules();

        // Plugin shutdown logic
    }
}
