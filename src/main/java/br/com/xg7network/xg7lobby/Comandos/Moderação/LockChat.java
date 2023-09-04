package br.com.xg7network.xg7lobby.Comandos.Moderação;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;
import org.bukkit.event.Listener;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;
import static br.com.xg7network.xg7lobby.XG7Lobby.va;


public class LockChat implements CommandExecutor, Listener {

    static boolean Locked = false;
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender.hasPermission(PermissionType.LOCK_CHAT.getPerm())) {
            if (!Locked) {
                Locked = true;
                if (commandSender instanceof Player) {
                    Player p = (Player) commandSender;
                    va.mandarMensagem(cm.getMessage().getString("commands.lock-chat"), p);
                } else {
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', cm.getMessage().getString("commands.lock-chat")));
                }
            } else {
                Locked = false;
                if (commandSender instanceof Player) {
                    Player p = (Player) commandSender;
                    va.mandarMensagem(cm.getMessage().getString("commands.unlock-chat"), p);
                } else {
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', cm.getMessage().getString("commands.unlock-chat")));
                }
            }
        } else {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                va.mandarMensagem(cm.getMessage().getString("commands.permission"), p);
            } else {
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', cm.getMessage().getString("commands.permission")));
            }
        }

        return true;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (Locked && !p.hasPermission(PermissionType.LOCK_CHAT.getPerm())) {
            e.setCancelled(true);
            va.mandarMensagem(cm.getMessage().getString("events.on-lock-chat"), p);
        }
    }
}
