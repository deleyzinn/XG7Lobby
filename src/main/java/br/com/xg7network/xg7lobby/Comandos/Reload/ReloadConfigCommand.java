package br.com.xg7network.xg7lobby.Comandos.Reload;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static br.com.xg7network.xg7lobby.XG7Lobby.*;

public class ReloadConfigCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (p.hasPermission(PermissionType.RELOAD_COMMAND.getPerm())) {
                va.mandarMensagem(prefix + ChatColor.GRAY + "Recarregando as configurações...", p);
                cm.saveAll();
                cm.reloadAll();

                va.mandarMensagem(prefix + ChatColor.GREEN + "Recarregado!", p);
            }
        } else {

            commandSender.sendMessage(prefix + ChatColor.GRAY + "Recarregando as configurações...");
            cm.saveAll();
            cm.reloadAll();
            commandSender.sendMessage(prefix + ChatColor.GREEN + "Recarregado!");

        }
        return true;
    }
}
