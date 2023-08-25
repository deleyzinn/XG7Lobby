//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package br.com.xg7network.xg7lobby.Comandos.Lobby;

import br.com.xg7network.xg7lobby.XG7Lobby;
import br.com.xg7network.xg7lobby.Configs.PermissionType;
import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Fly implements CommandExecutor {
    public static HashMap<UUID, Boolean> voar = new HashMap();

    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (commandSender instanceof Player) {
            Player p = (Player)commandSender;
            if (args.length == 0) {
                if (p.hasPermission(PermissionType.FLY_COMMAND.getPerm())) {
                    if (!voar.containsKey(p.getUniqueId())) {
                        voar.put(p.getUniqueId(), false);
                    }

                    if (voar.get(p.getUniqueId())) {
                        voar.put(p.getUniqueId(), false);
                        p.setFlying(false);
                        XG7Lobby.va.mandarMensagem(XG7Lobby.cm.getMessage().getString("comandos.fly-desativado"), p);
                    } else {
                        voar.put(p.getUniqueId(), true);
                        p.setFlying(true);
                        XG7Lobby.va.mandarMensagem(XG7Lobby.cm.getMessage().getString("comandos.fly-ativado"), p);
                    }
                } else {
                    XG7Lobby.va.mandarMensagem(XG7Lobby.cm.getMessage().getString("comandos.permissão"), p);
                }
            } else if (args.length == 1) {
                if (p.hasPermission(PermissionType.FLY_OTHER.getPerm())) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (!voar.containsKey(target.getUniqueId())) {
                        voar.put(target.getUniqueId(), false);
                    }

                    if (voar.get(target.getUniqueId())) {
                        voar.put(target.getUniqueId(), false);
                        target.setFlying(false);
                        XG7Lobby.va.mandarMensagem(ChatColor.GRAY + "Você desativou o fly de " + ChatColor.AQUA + target.getName(), p);
                        XG7Lobby.va.mandarMensagem(XG7Lobby.cm.getMessage().getString("comandos.fly-desativado"), target);
                    } else {
                        voar.put(target.getUniqueId(), true);
                        target.setFlying(true);
                        XG7Lobby.va.mandarMensagem(ChatColor.GRAY + "Você ativou o fly de " + ChatColor.AQUA + target.getName(), p);
                        XG7Lobby.va.mandarMensagem(XG7Lobby.cm.getMessage().getString("comandos.fly-ativado"), target);
                    }
                } else {
                    XG7Lobby.va.mandarMensagem(XG7Lobby.cm.getMessage().getString("comandos.permissão"), p);
                }
            }
        } else {
            commandSender.sendMessage(XG7Lobby.prefix + ChatColor.RED + "Este comando só pode ser executado por players");
        }

        return true;
    }
}
