package br.com.xg7network.xg7lobby.Comandos.Moderação;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import br.com.xg7network.xg7lobby.XG7Lobby;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static br.com.xg7network.xg7lobby.XG7Lobby.cm;
import static br.com.xg7network.xg7lobby.XG7Lobby.va;

public class Warn implements CommandExecutor, Listener {

    private XG7Lobby pl;
    FileConfiguration lobbyC;

    File lobbyF;
    public Warn (XG7Lobby pl) {
        this.pl = pl;
        this.lobbyC = cm.getData();
        this.lobbyF = new File(pl.getDataFolder(), "data.yml");
    }

    List<String> warns;
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (command.getName().equals("xg7lobbywarn")) {
            if (args.length >= 2) {

                if (commandSender.hasPermission(PermissionType.WARN_COMMAND.getPerm())) {

                    Player p = Bukkit.getPlayerExact(args[0]);
                    if (p != null) {

                        ConfigurationSection playerSection = cm.getData().getConfigurationSection("warns." + p.getName());

                        if (playerSection == null) {
                            playerSection = cm.getData().createSection("warns." + p.getName());
                            playerSection.set("warns", new ArrayList<String>());
                            try {
                                lobbyC.save(lobbyF);
                            } catch (IOException e) {
                                p.sendMessage(ChatColor.RED + "Não foi possível salvar o warn");
                                throw new RuntimeException(e);
                            }
                        }

                        warns = cm.getData().getStringList("warns." + p.getName() + ".warns");
                        String str = "";
                        for (int i = 1; i < args.length; i++) {
                            str += args[i] + " ";
                        }

                        warns.add(str.trim());
                        va.mandarMensagem(ChatColor.GOLD + "Você recebeu um aviso pelo motivo: " + ChatColor.RESET + str.trim(), p);
                        playerSection.set("warns", warns);

                        if (warns.size() <= cm.getData().getInt("qtd-warns-kick") && warns.size() == cm.getData().getInt("qtd-warns-ban")) {
                            p.kickPlayer(ChatColor.RED + "Você recebeu " + ChatColor.AQUA + warns.size() + ChatColor.RED + " kicks. Por favor não quebre as regras!");
                        } else if (warns.size() >= cm.getData().getInt("qtd-warns-ban")) {
                            p.kickPlayer(ChatColor.RED + "Você foi banido por receber muitos avisos para não quebrar as regras!");
                            Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(), ChatColor.RED + "Você foi banido por receber muitos avisos!", null, null);
                        }
                        try {
                            lobbyC.save(lobbyF);
                        } catch (IOException e) {
                            p.sendMessage(ChatColor.RED + "Não foi possível salvar o warn");
                            throw new RuntimeException(e);
                        }

                    } else {
                        commandSender.sendMessage(ChatColor.RED + "Este jogador não existe!");
                    }
                } else {
                    if (commandSender instanceof Player) {
                        Player p = (Player) commandSender;
                        va.mandarMensagem(cm.getMessage().getString("comandos.permissão"), p);
                    }
                }
            } else {
                commandSender.sendMessage(ChatColor.RED + "O jeito ceto de usar o comando é " + ChatColor.YELLOW + "/" + ChatColor.GREEN + "warn <Player> <Motivo>");
            }
        } else if (command.getName().equals("xg7lobbywarns")) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                for (String sc : cm.getData().getConfigurationSection("warns").getKeys(false)) {
                    if (sc.equals(p.getName())) {
                        commandSender.sendMessage(ChatColor.YELLOW + "Você tem " + ChatColor.GREEN +
                                cm.getData().getStringList("warns." + sc + ".warns").size() + ChatColor.YELLOW + " warns");
                        commandSender.sendMessage(ChatColor.YELLOW + "Seus warns:");
                        for (String w : cm.getData().getStringList("warns." + sc + ".warns")) {
                            commandSender.sendMessage(w);
                        }
                    }
                }
            }
        }
        return true;
    }
}
