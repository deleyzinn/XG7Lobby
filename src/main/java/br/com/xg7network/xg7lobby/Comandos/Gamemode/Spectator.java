package br.com.xg7network.xg7lobby.Comandos.Gamemode;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import br.com.xg7network.xg7lobby.XG7Lobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static br.com.xg7network.xg7lobby.XG7Lobby.*;

public class Spectator implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length == 0) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if (p.hasPermission(PermissionType.GAMEMODE_SPECTATOR.getPerm())) {
                    p.setGameMode(GameMode.SPECTATOR);
                    va.mandarMensagem(cm.getMessage().getString("commands.gamemode-player").replace("[GAMEMODE]", p.getGameMode().toString()), p);
                } else {
                    va.mandarMensagem(cm.getMessage().getString("commands.permission"), p);
                }
            } else {
                commandSender.sendMessage(XG7Lobby.prefix + ChatColor.RED + "Este comando só pode ser executado por players");
            }
        } else if (strings.length == 1) {
            if (commandSender.hasPermission(PermissionType.GAMEMODE_OTHERS.getPerm())) {
                Player target = Bukkit.getPlayer(strings[0]);
                if (target != null) {
                    target.setGameMode(GameMode.SPECTATOR);
                    va.mandarMensagem(cm.getMessage().getString("commands.gamemode-player").replace("[GAMEMODE]", target.getGameMode().toString()), target);
                    if (commandSender instanceof Player) {
                        Player p = (Player) commandSender;
                        va.mandarMensagem(cm.getMessage().getString("commands.gamemode-target").replace("[GAMEMODE]", target.getGameMode().toString()).replace("[PLAYERG]", target.getName()), p);
                    } else {
                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', cm.getMessage().getString("commands.gamemode-target").replace("[PLAYERG]", target.getName()).replace("[GAMEMODE]", target.getGameMode().toString())));
                    }
                } else {
                    commandSender.sendMessage(prefix + ChatColor.RED + "Esse jogador não foi encontrado");
                }
            } else {
                if (commandSender instanceof Player) {
                    Player p = (Player) commandSender;
                    va.mandarMensagem(cm.getMessage().getString("commands.permission"), p);
                }
            }
        }
        return true;
    }
}
