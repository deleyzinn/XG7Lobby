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

import static br.com.xg7network.xg7lobby.XG7Lobby.*;

public class Unban implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission(PermissionType.UNBAN_COMMAND.getPerm())) {
            if (args.length == 1) {
                String prName = args[0];
                OfflinePlayer p = Bukkit.getOfflinePlayer(prName);
                if (p != null) {
                    Bukkit.getBanList(BanList.Type.NAME).pardon(p.getName());
                    if (sender instanceof Player) {
                        Player pm = (Player) sender;
                        va.mandarMensagem(prefix + ChatColor.GREEN + "Você perdoou " + ChatColor.YELLOW + p.getName(), pm);
                    } else {
                        sender.sendMessage(prefix + ChatColor.GREEN + "Você perdoou " + ChatColor.YELLOW + p.getName());
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
