package br.com.xg7network.xg7lobby.Comandos.Lobby;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static br.com.xg7network.xg7lobby.XG7Lobby.*;

public class Lobby implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (cm.getData().getString("lobby.world") == null) {
                if (p.hasPermission(PermissionType.SETLOBBY_COMMAND.getPerm())) {
                    va.mandarMensagem(cm.getMessage().getString("commands.adm-lobby-warn"), p);
                } else {
                    va.mandarMensagem(cm.getMessage().getString("commands.lobby-warn"), p);
                }
            } else {
                String lobbyWN = cm.getData().getString("lobby.world");
                if (lobbyWN != null) {
                    World w = Bukkit.getWorld(lobbyWN);
                    p.teleport(new Location(
                            w,
                            cm.getData().getDouble("lobby.x"),
                            cm.getData().getDouble("lobby.y"),
                            cm.getData().getDouble("lobby.z"),
                            (float) cm.getData().getDouble("lobby.yaw"),
                            (float) cm.getData().getDouble("lobby.pitch")));

                }
            }
        } else {
            sender.sendMessage(prefix + ChatColor.RED + "Este comando só pode ser executado por players");
        }
        return true;
    }
}
