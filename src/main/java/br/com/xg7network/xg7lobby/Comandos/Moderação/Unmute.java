package br.com.xg7network.xg7lobby.Comandos.Moderação;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;
import static br.com.xg7network.xg7lobby.XG7Lobby.va;

public class Unmute implements CommandExecutor {

    List<String> mutados;

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (p.hasPermission(PermissionType.UNMUTE_COMMAND.getPerm())) {
                va.mandarMensagem(cm.getMessage().getString("comandos.permissão"), p);
                return true;
            }
        }
        if (args.length == 1) {
            String targetUUID;

            Player target = Bukkit.getPlayerExact(args[0]);
            if (target != null) {
                targetUUID = target.getUniqueId().toString();
            } else {
                commandSender.sendMessage(ChatColor.RED + "Esse jogador não existe ou não foi encontrado!");
                return true;
            }

            mutados = cm.getData().getStringList("mutados");
            Iterator<String> iterator = mutados.iterator();
            boolean found = false;

            while (iterator.hasNext()) {
                String a = iterator.next();
                if (a.contains(targetUUID)) {
                    iterator.remove();
                    found = true;
                }
            }

            if (found) {
                cm.getData().set("mutados", mutados);
                cm.saveData();
                commandSender.sendMessage(ChatColor.AQUA + target.getName() + ChatColor.GREEN + " foi removido da lista de mutados com sucesso!");
            } else {
                commandSender.sendMessage(ChatColor.RED + "Este jogador não está na lista de silenciados!");
            }
        } else {
            commandSender.sendMessage(ChatColor.RED + "O jeito certo de usar o comando é " + ChatColor.YELLOW + "/" + ChatColor.GREEN + "unmute <Player>");
        }
        return true;
    }
}
