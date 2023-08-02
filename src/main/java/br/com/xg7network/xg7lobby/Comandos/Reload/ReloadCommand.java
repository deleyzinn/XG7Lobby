package br.com.xg7network.xg7lobby.Comandos.Reload;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import br.com.xg7network.xg7lobby.XG7Lobby;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static br.com.xg7network.xg7lobby.XG7Lobby.va;

public class ReloadCommand implements CommandExecutor {

    private XG7Lobby pl;

    public ReloadCommand(XG7Lobby pl) {
        this.pl = pl;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(PermissionType.ADMIN.getPerm())) {
                va.mandarMensagem(ChatColor.GRAY + "Recarregando o plugin...", p);

                pl.getServer().getPluginManager().disablePlugin(pl);
                pl.getServer().getPluginManager().enablePlugin(pl);

                va.mandarMensagem(ChatColor.GREEN + "Recarregado!", p);
            }
        } else {

            sender.sendMessage(ChatColor.GRAY + "Recarregando o plugin..");

            pl.getServer().getPluginManager().disablePlugin(pl);
            pl.getServer().getPluginManager().enablePlugin(pl);

            sender.sendMessage(ChatColor.GREEN + "Recarregado!");

        }
        return true;
    }
}
