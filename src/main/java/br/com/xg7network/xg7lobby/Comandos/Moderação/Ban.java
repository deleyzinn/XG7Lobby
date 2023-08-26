package br.com.xg7network.xg7lobby.Comandos.Moderação;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static br.com.xg7network.xg7lobby.XG7Lobby.*;

public class Ban implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission(PermissionType.BAN_COMMAND.getPerm())) {
            if (args.length == 1) {
                String pName = args[0];
                Player pIsOn = Bukkit.getPlayerExact(pName);
                OfflinePlayer pIsOff = Bukkit.getOfflinePlayer(pName);
                if (pIsOn != null || pIsOff != null) {
                    if (!Bukkit.getBanList(BanList.Type.NAME).isBanned(pIsOn.getName()) || !Bukkit.getBanList(BanList.Type.NAME).isBanned(pIsOff.getName())) {
                        if (pIsOn != null) {
                            pIsOn.kickPlayer("");
                            Bukkit.getBanList(BanList.Type.NAME).addBan(pIsOn.getName(), "", null, null);
                            if (sender instanceof Player) {
                                Player p = (Player) sender;
                                va.mandarMensagem(prefix + ChatColor.GREEN + "Você baniu " + ChatColor.YELLOW + pIsOn.getName(), p);
                            } else {
                                sender.sendMessage(prefix + ChatColor.GREEN + "Você baniu " + ChatColor.YELLOW + pIsOn.getName());
                            }
                        } else if (pIsOff != null) {
                            Bukkit.getBanList(BanList.Type.NAME).addBan(pIsOff.getName(), "", null, null);
                            if (sender instanceof Player) {
                                Player p = (Player) sender;
                                va.mandarMensagem(prefix + ChatColor.GREEN + "Você baniu " + ChatColor.YELLOW + pIsOff.getName(), p);
                            } else {
                                sender.sendMessage(prefix + ChatColor.GREEN + "Você baniu " + ChatColor.YELLOW + pIsOff.getName());
                            }
                        } else {
                            if (sender instanceof Player) {
                                Player p = (Player) sender;
                                va.mandarMensagem(prefix + ChatColor.GREEN + "Você baniu " + ChatColor.YELLOW + pIsOff.getName(), p);
                            } else {
                                sender.sendMessage(prefix + ChatColor.GREEN + "Você baniu " + ChatColor.YELLOW + pIsOff.getName());
                            }
                        }
                    } else {
                        if (sender instanceof Player) {
                            Player p = (Player) sender;
                            va.mandarMensagem(ChatColor.RED + "Este jogador foi encontrado na lista de banidos!", p);
                        } else {
                            sender.sendMessage(ChatColor.RED + "Este jogador foi encontrado na lista de banidos!");
                        }
                    }
                } else {
                    if (sender instanceof Player) {
                        Player p = (Player) sender;
                        va.mandarMensagem(ChatColor.RED + "Este jogador não existe!", p);

                    } else {
                        sender.sendMessage(ChatColor.RED + "Este jogador não existe!");
                    }
                }
            } else if (args.length >= 2) {
                String pName = args[0];
                Player pIsOn = Bukkit.getPlayerExact(pName);
                OfflinePlayer pIsOff = Bukkit.getOfflinePlayer(pName);
                String str = "";
                for (int i = 1; i < args.length; i++) {
                    str += args[i] + " ";
                }
                if (pIsOn.isOnline()) {
                    pIsOn.kickPlayer(str.trim().replace("&", "§"));
                }
                if (pIsOn != null || pIsOff != null && !Bukkit.getBanList(BanList.Type.NAME).isBanned(pIsOn.getName()) || !Bukkit.getBanList(BanList.Type.NAME).isBanned(pIsOff.getName())) {
                    if (pIsOn != null) {
                        Bukkit.getBanList(BanList.Type.NAME).addBan(pIsOn.getName(), str.trim().replace("&", "§"), null, null);
                        if (sender instanceof Player) {
                            Player p = (Player) sender;
                            va.mandarMensagem(prefix + ChatColor.GREEN + "Você baniu " + ChatColor.YELLOW + pIsOn.getName() + ChatColor.GREEN + " por" + ChatColor.RESET + str.trim(), p);
                        } else {
                            sender.sendMessage(prefix + ChatColor.GREEN + "Você baniu " + ChatColor.YELLOW + pIsOn.getName() + ChatColor.GREEN + " por" + ChatColor.RESET + str.trim());
                        }
                    } else if (pIsOff != null) {
                        Bukkit.getBanList(BanList.Type.NAME).addBan(pIsOff.getName(), str.trim().replace("&", "§"), null, null);
                        if (sender instanceof Player) {
                            Player p = (Player) sender;
                            va.mandarMensagem(prefix + ChatColor.GREEN + "Você baniu " + ChatColor.YELLOW + pIsOff.getName() + ChatColor.GREEN + " por" + ChatColor.RESET + str.trim(), p);
                        } else {
                            sender.sendMessage(prefix + ChatColor.GREEN + "Você baniu " + ChatColor.YELLOW + pIsOff.getName() + ChatColor.GREEN + " por" + ChatColor.RESET + str.trim().replace("&", "§"));
                        }
                    } else {
                        if (sender instanceof Player) {
                            Player p = (Player) sender;
                            va.mandarMensagem(ChatColor.RED + "Este jogador foi encontrado na lista de banidos!", p);
                        } else {
                            sender.sendMessage(ChatColor.RED + "Este jogador foi encontrado na lista de banidos!");
                        }
                    }
                } else {
                    if (sender instanceof Player) {
                        Player p = (Player) sender;
                        va.mandarMensagem(ChatColor.RED + "Este jogador não existe!", p);

                    } else {
                        sender.sendMessage(ChatColor.RED + "Este jogador não existe!");
                    }
                }
            } else {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    va.mandarMensagem(ChatColor.RED + "O jeito ceto de usar o comando é " + ChatColor.YELLOW + "/" + ChatColor.GREEN + "unban <Player>", p);
                } else {
                    sender.sendMessage(ChatColor.RED + "O jeito ceto de usar o comando é " + ChatColor.YELLOW + "/" + ChatColor.GREEN + "unban <Player>");
                }
            }
        } else {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                va.mandarMensagem(cm.getMessage().getString("commands.permission"), p);
            }
        }
        return true;
    }
}
