package br.com.xg7network.xg7lobby.Comandos.Moderação;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import br.com.xg7network.xg7lobby.XG7Lobby;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import static br.com.xg7network.xg7lobby.XG7Lobby.*;

public class Tempban implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission(PermissionType.TEMPBAN_COMMAND.getPerm())) {
            if (args.length == 2) {
                String pName = args[0];
                Player pIsOn = Bukkit.getPlayerExact(pName);
                OfflinePlayer pIsOff = Bukkit.getOfflinePlayer(pName);
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.HOUR, Integer.parseInt(args[1]));
                if (pIsOn != null || pIsOff != null) {
                    if (!Bukkit.getBanList(BanList.Type.NAME).isBanned(pIsOn.getName()) || !Bukkit.getBanList(BanList.Type.NAME).isBanned(pIsOff.getName())) {
                        if (pIsOn != null) {
                            pIsOn.kickPlayer("");
                            Bukkit.getBanList(BanList.Type.NAME).addBan(pIsOn.getName(), "", cal.getTime(), null);
                            if (sender instanceof Player) {
                                Player p = (Player) sender;
                                va.mandarMensagem(ChatColor.GREEN + "[XG7Lobby] Você baniu " + ChatColor.YELLOW + pIsOn.getName() + ChatColor.GREEN + " por " + ChatColor.RESET + cal.getTime() + ChatColor.GREEN + " horas", p);
                            } else {
                                sender.sendMessage(ChatColor.GREEN + "[XG7Lobby] Você baniu " + ChatColor.YELLOW + pIsOn.getName());
                            }
                        } else if (pIsOff != null) {
                            Bukkit.getBanList(BanList.Type.NAME).addBan(pIsOff.getName(), "", cal.getTime(), null);
                            if (sender instanceof Player) {
                                Player p = (Player) sender;
                                va.mandarMensagem(ChatColor.GREEN + "[XG7Lobby] Você baniu " + ChatColor.YELLOW + pIsOff.getName() + ChatColor.GREEN + " por " + ChatColor.RESET + cal.getTime() + ChatColor.GREEN + " horas",p);
                            } else {
                                sender.sendMessage(ChatColor.GREEN + "[XG7Lobby] Você baniu " + ChatColor.YELLOW + pIsOff.getName() + ChatColor.GREEN + " por " + ChatColor.RESET + cal.getTime() + ChatColor.GREEN + " horas");
                            }
                        } else {
                            if (sender instanceof Player) {
                                Player p = (Player) sender;
                                va.mandarMensagem(ChatColor.GREEN + "[XG7Lobby] Você baniu " + ChatColor.YELLOW + pIsOff.getName(), p);
                            } else {
                                sender.sendMessage(ChatColor.GREEN + "[XG7Lobby] Você baniu " + ChatColor.YELLOW + pIsOff.getName());
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
            } else if (args.length >= 3) {
                String pName = args[0];
                Player pIsOn = Bukkit.getPlayerExact(pName);
                OfflinePlayer pIsOff = Bukkit.getOfflinePlayer(pName);
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.HOUR, Integer.parseInt(args[1]));
                String str = "";
                for (int i = 1; i < args.length; i++) {
                    str += args[i] + " ";
                }
                if (pIsOn.isOnline()) {
                    pIsOn.kickPlayer(str.trim().replace("&", "§"));
                }
                if (pIsOn != null || pIsOff != null && !Bukkit.getBanList(BanList.Type.NAME).isBanned(pIsOn.getName()) || !Bukkit.getBanList(BanList.Type.NAME).isBanned(pIsOff.getName())) {
                    if (pIsOn != null) {
                        Bukkit.getBanList(BanList.Type.NAME).addBan(pIsOn.getName(), str.trim().replace("&", "§"), cal.getTime(), null);
                        if (pIsOn != null) {
                            pIsOn.kickPlayer("");
                            Bukkit.getBanList(BanList.Type.NAME).addBan(pIsOn.getName(), "", cal.getTime(), null);
                            if (sender instanceof Player) {
                                Player p = (Player) sender;
                                va.mandarMensagem(ChatColor.GREEN + "[XG7Lobby] Você baniu " + ChatColor.YELLOW + pIsOn.getName() + ChatColor.GREEN + " por " + ChatColor.RESET + cal.getTime() + ChatColor.GREEN + " horas", p);
                            } else {
                                sender.sendMessage(ChatColor.GREEN + "[XG7Lobby] Você baniu " + ChatColor.YELLOW + pIsOn.getName());
                            }
                        } else if (pIsOff != null) {
                            Bukkit.getBanList(BanList.Type.NAME).addBan(pIsOff.getName(), str.trim().replace("&", "§"), cal.getTime(), null);
                            if (sender instanceof Player) {
                                Player p = (Player) sender;
                                va.mandarMensagem(ChatColor.GREEN + "[XG7Lobby] Você baniu " + ChatColor.YELLOW + pIsOff.getName() + ChatColor.GREEN + " por " + ChatColor.RESET + cal.getTime() + ChatColor.GREEN + " horas", p);
                            } else {
                                sender.sendMessage(ChatColor.GREEN + "[XG7Lobby] Você baniu " + ChatColor.YELLOW + pIsOff.getName() + ChatColor.GREEN + " por " + ChatColor.RESET + cal.getTime() + ChatColor.GREEN + " horas");
                            }
                        } else {
                            if (sender instanceof Player) {
                                Player p = (Player) sender;
                                va.mandarMensagem(ChatColor.GREEN + "[XG7Lobby] Você baniu " + ChatColor.YELLOW + pIsOff.getName(), p);
                            } else {
                                sender.sendMessage(ChatColor.GREEN + "[XG7Lobby] Você baniu " + ChatColor.YELLOW + pIsOff.getName());
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
                        Player pm = (Player) sender;
                        va.mandarMensagem(ChatColor.RED + "Este jogador não existe!", pm);
                    } else {
                        sender.sendMessage(ChatColor.RED + "Este jogador não existe!");
                    }
                }
            } else {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    va.mandarMensagem(ChatColor.RED + "O jeito ceto de usar o comando é " + ChatColor.YELLOW + "/" + ChatColor.GREEN + "tempban <Player> <Tempo> [Razão]", p);
                } else {
                    sender.sendMessage(ChatColor.RED + "O jeito ceto de usar o comando é " + ChatColor.YELLOW + "/" + ChatColor.GREEN + "tempban <Player> <Tempo> [Razão]");
                }
            }
        } else {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                va.mandarMensagem(cm.getMessage().getString("comandos.permissão"), p);
            }
        }
        return true;
    }
}
