package br.com.xg7network.xg7lobby.Comandos.Moderação;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import br.com.xg7network.xg7lobby.XG7Lobby;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static br.com.xg7network.xg7lobby.XG7Lobby.*;

public class Kick implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission(PermissionType.KICK_COMMAND.getPerm())) {
            if (args.length == 1) {
                Player pr = Bukkit.getPlayerExact(args[0]);
                if (pr.isOnline()) {
                    pr.kickPlayer("");
                } else {
                    if (sender instanceof Player) {
                        Player p = (Player) sender;
                        va.mandarMensagem(ChatColor.RED + "Este jogador não está OnLine", p);
                    } else {
                        sender.sendMessage(ChatColor.RED + "Este jogador não está OnLine");
                    }
                }
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    va.mandarMensagem(prefix + ChatColor.GREEN + "Você expulsou " + ChatColor.YELLOW + pr.getName(), p);
                } else {
                    sender.sendMessage(prefix + ChatColor.GREEN + "Você expulsou " + ChatColor.YELLOW + pr.getName());
                }
            } else if (args.length >= 2) {
                Player pr = Bukkit.getPlayerExact(args[0]);
                String str = "";
                for (int i = 1; i < args.length; i++) {
                    str += args[i] + " ";
                }
                pr.kickPlayer(str.toString().trim().replace("&", "§"));
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    va.mandarMensagem(prefix + ChatColor.GREEN + "Você expulsou " + ChatColor.YELLOW + pr.getName() + ChatColor.GREEN + " por" + ChatColor.RESET + str.trim(), p);
                } else {
                    sender.sendMessage(prefix + ChatColor.GREEN + "Você expulsou " + ChatColor.YELLOW + pr.getName() + ChatColor.GREEN + " por" + ChatColor.RESET + str.trim().replace("&", "§"));
                }
            } else {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    va.mandarMensagem(ChatColor.RED + "O jeito ceto de usar o comando é " + ChatColor.YELLOW + "/" + ChatColor.GREEN + "kick <Player> [Razão]", p);
                } else {
                    sender.sendMessage(ChatColor.RED + "O jeito ceto de usar o comando é " + ChatColor.YELLOW + "/" + ChatColor.GREEN + "kick <Player> [Razão]");
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
