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

import static br.com.xg7network.xg7lobby.XG7Lobby.*;

public class Fly implements CommandExecutor {
    public static HashMap<UUID, Boolean> voar = new HashMap<>();

    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (commandSender instanceof Player) {
            Player p = (Player)commandSender;
            if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
                if (args.length == 0) {
                    if (p.hasPermission(PermissionType.FLY_COMMAND.getPerm())) {
                        if (!voar.containsKey(p.getUniqueId())) {
                            voar.put(p.getUniqueId(), false);
                        }

                        if (voar.get(p.getUniqueId())) {
                            voar.put(p.getUniqueId(), false);
                            p.setFlying(false);
                            va.mandarMensagem(cm.getMessage().getString("commands.fly-disabled"), p);
                        } else {
                            voar.put(p.getUniqueId(), true);
                            p.setFlying(true);
                            va.mandarMensagem(cm.getMessage().getString("commands.fly-enabled"), p);
                        }
                    } else {
                        va.mandarMensagem(cm.getMessage().getString("commands.permission"), p);
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
                            va.mandarMensagem(ChatColor.GRAY + "Você desativou o fly de " + ChatColor.AQUA + target.getName(), p);
                            va.mandarMensagem(cm.getMessage().getString("commands.fly-disabled"), target);
                        } else {
                            voar.put(target.getUniqueId(), true);
                            target.setFlying(true);
                            va.mandarMensagem(ChatColor.GRAY + "Você ativou o fly de " + ChatColor.AQUA + target.getName(), p);
                            va.mandarMensagem(cm.getMessage().getString("commands.fly-enabled"), target);
                        }
                    } else {
                        va.mandarMensagem(cm.getMessage().getString("commands.permission"), p);
                    }
                }
            } else {
                va.mandarMensagem(prefix + ChatColor.RED + "Você não pode executar este comando no mundo em que não está ativado pelo plugin!", p);
            }
        } else {
            commandSender.sendMessage(XG7Lobby.prefix + ChatColor.RED + "Este comando só pode ser executado por players");
        }

        return true;
    }
}
