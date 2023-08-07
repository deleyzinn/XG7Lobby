package br.com.xg7network.xg7lobby;

import br.com.xg7network.xg7lobby.Comandos.Lobby.Lobby;
import br.com.xg7network.xg7lobby.Comandos.Lobby.Setlobby;
import br.com.xg7network.xg7lobby.Comandos.Reload.ReloadCommand;
import br.com.xg7network.xg7lobby.Comandos.Reload.ReloadConfigCommand;
import br.com.xg7network.xg7lobby.Configs.ConfigManager;
import br.com.xg7network.xg7lobby.Eventos.Lauchpad;
import br.com.xg7network.xg7lobby.Eventos.EntradaESaida;
import br.com.xg7network.xg7lobby.Eventos.Player.*;
import br.com.xg7network.xg7lobby.Eventos.Player.Void;
import br.com.xg7network.xg7lobby.Modulo.ModuleManager;
import br.com.xg7network.xg7lobby.Modulo.Mundo.CancelBlockBurn;
import br.com.xg7network.xg7lobby.Modulo.Mundo.CancelLeavesDecay;
import br.com.xg7network.xg7lobby.Modulo.Mundo.Explosions;
import br.com.xg7network.xg7lobby.Modulo.Mundo.SpawnMobs;
import br.com.xg7network.xg7lobby.Utilidades.Ações;
import br.com.xg7network.xg7lobby.Utilidades.Inventário;
import br.com.xg7network.xg7lobby.Utilidades.VerifAction;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class XG7Lobby extends JavaPlugin {

    public static ConfigManager cm;

    public static Ações ac;

    ModuleManager MM = new ModuleManager(this);

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
        MM.loadModules();


        this.getServer().getConsoleSender().sendMessage(prefix + "Carregando comandos...");
        this.getCommand("xg7lobbyreloadconfig").setExecutor(new ReloadConfigCommand());
        this.getCommand("xg7lobbyreload").setExecutor(new ReloadCommand(this));
        this.getCommand("xg7lobbysetlobby").setExecutor(new Setlobby(this));
        this.getCommand("xg7lobbylobby").setExecutor(new Lobby());

        this.getServer().getConsoleSender().sendMessage(prefix + "Carregando eventos...");
        this.getServer().getPluginManager().registerEvents(new Inventário(), this);
        this.getServer().getPluginManager().registerEvents(new EntradaESaida(), this);
        this.getServer().getPluginManager().registerEvents(new Lauchpad(), this);

        this.getServer().getPluginManager().registerEvents(new Void(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerHungerEvent(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerDropPickupEvent(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerAttackEvent(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerDamageEvent(), this);

        this.getServer().getPluginManager().registerEvents(new CancelBlockBurn(), this);
        this.getServer().getPluginManager().registerEvents(new CancelLeavesDecay(), this);
        this.getServer().getPluginManager().registerEvents(new Explosions(), this);
        this.getServer().getPluginManager().registerEvents(new SpawnMobs(), this);
        this.getServer().getPluginManager().registerEvents(new CancelBlockBurn(), this);



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
