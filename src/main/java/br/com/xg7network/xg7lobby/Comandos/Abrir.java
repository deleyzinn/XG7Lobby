//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package br.com.xg7network.xg7lobby.Comandos;

import br.com.xg7network.xg7lobby.XG7Lobby;
import br.com.xg7network.xg7lobby.Configs.PermissionType;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Abrir implements CommandExecutor {
    public Abrir() {
    }

    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player) {
            Player p = (Player)commandSender;
            if (strings.length == 1) {
                if (p.hasPermission(PermissionType.GUI_COMMAND.getPerm())) {
                    String comando = "[ABRIR] " + strings[0];
                    List<String> lista = new ArrayList();
                    lista.add(comando);
                    XG7Lobby.ac.executar(lista, p);
                }
            } else {
                commandSender.sendMessage(ChatColor.RED + "O jeito certo de usar o comando é " + ChatColor.YELLOW + "/" + ChatColor.GREEN + "gui <ID do inventário>");
            }
        } else {
            commandSender.sendMessage(XG7Lobby.prefix + ChatColor.RED + "Este comando só pode ser executado por players");
        }

        return true;
    }
}
