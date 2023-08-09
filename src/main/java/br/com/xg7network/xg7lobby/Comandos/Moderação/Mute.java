package br.com.xg7network.xg7lobby.Comandos.Moderação;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import br.com.xg7network.xg7lobby.XG7Lobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;
import static br.com.xg7network.xg7lobby.XG7Lobby.va;

public class Mute implements CommandExecutor, Listener {

    private XG7Lobby pl;
    FileConfiguration lobbyC;

    File lobbyF;
    public Mute (XG7Lobby pl) {
        this.pl = pl;
        this.lobbyC = cm.getData();
        this.lobbyF = new File(pl.getDataFolder(), "data.yml");
    }
    List<String> mutados;

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (commandSender instanceof Player) {

            Player p = (Player) commandSender;
            if (p.hasPermission(PermissionType.MUTE_COMMAND.getPerm())) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target != null) {
                        if (!target.hasPermission(PermissionType.ADMIN.getPerm())) {
                            mutados = cm.getData().getStringList("mutados");
                            String mute = target.getName() + ": " + target.getUniqueId();
                            mutados.add(mute);
                            cm.getData().set("mutados", mutados);

                            try {
                                lobbyC.save(lobbyF);
                            } catch (IOException e) {
                                commandSender.sendMessage(ChatColor.RED + "Não foi possível silenciar a pessoa");
                                throw new RuntimeException(e);
                            }


                            va.mandarMensagem(cm.getMessage().getString("eventos.quando-mutado"), target);
                            va.mandarMensagem(ChatColor.AQUA + target.getName() + ChatColor.GREEN + " foi silenciado com sucesso!", p);
                        } else {
                            va.mandarMensagem(ChatColor.RED + "Você não pode silenciar um Administrador", p);
                        }
                    } else {
                        va.mandarMensagem(ChatColor.RED + "Esse jogador não existe ou não foi encontrado!", p);
                    }
                } else {
                    va.mandarMensagem(ChatColor.RED + "O jeito ceto de usar o comando é " + ChatColor.YELLOW + "/" + ChatColor.GREEN + "mute <Player>", p);
                }
            } else {
                va.mandarMensagem(cm.getMessage().getString("comandos.permissão"), p);
            }


        } else {
            if (args.length == 1) {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target != null) {
                    if (!target.hasPermission(PermissionType.ADMIN.getPerm())) {
                        String mute = target.getName() + ": " + target.getUniqueId();
                        mutados = cm.getData().getStringList("mutados");
                        mutados.add(mute);
                        cm.getData().set("mutados", mutados);

                        try {
                            lobbyC.save(lobbyF);
                        } catch (IOException e) {
                            commandSender.sendMessage(ChatColor.RED + "Não foi possível silenciar a pessoa");
                            throw new RuntimeException(e);
                        }

                        try {
                            lobbyC.save(lobbyF);
                        } catch (IOException e) {
                            commandSender.sendMessage(ChatColor.RED + "Não foi possível silenciar a pessoa");
                            throw new RuntimeException(e);
                        }
                        va.mandarMensagem(cm.getMessage().getString("eventos.quando-mutado"), target);
                        commandSender.sendMessage(ChatColor.AQUA + target.getName() + ChatColor.GREEN + "foi silenciado com sucesso!");
                    } else {
                        commandSender.sendMessage(ChatColor.RED + "Você não pode silenciar um Administrador");
                    }
                } else {
                    commandSender.sendMessage(ChatColor.RED + "Esse jogador não existe ou não foi encontrado!");
                }
            } else {
                commandSender.sendMessage(ChatColor.RED + "O jeito ceto de usar o comando é " + ChatColor.YELLOW + "/" + ChatColor.GREEN + "mute <Player>");
            }
        }


        return true;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        mutados = cm.getData().getStringList("mutados");
        for (String s : mutados) {
            String[] s2 = s.split(": ");
            if (s2[1].equals(p.getUniqueId().toString())) {
                va.mandarMensagem(cm.getMessage().getString("eventos.no-mute"), p);
                e.setCancelled(true);
            }
        }
    }
}
