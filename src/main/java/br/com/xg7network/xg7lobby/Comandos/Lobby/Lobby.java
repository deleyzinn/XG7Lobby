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
            if (cm.getData().getString("lobby.Mundo") == null) {
                if (p.hasPermission(PermissionType.ADMIN.getPerm()) || p.hasPermission(PermissionType.SETLOBBY_COMMAND.getPerm()) || p.hasPermission(PermissionType.COMMAND.getPerm())) {
                    va.mandarMensagem(cm.getMessage().getString("comandos.aviso-lobby-adm"), p);
                } else {
                    va.mandarMensagem(cm.getMessage().getString("comandos.aviso-lobby"), p);
                }
            } else {
                String lobbyWN = cm.getData().getString("lobby.Mundo");
                if (lobbyWN != null) {
                    World w = Bukkit.getWorld(lobbyWN);
                    p.teleport(new Location(
                            w,
                            cm.getData().getDouble("lobby.X"),
                            cm.getData().getDouble("lobby.Y"),
                            cm.getData().getDouble("lobby.Z"),
                            (float) cm.getData().getDouble("lobby.Yaw"),
                            (float) cm.getData().getDouble("lobby.Pitch")));

                }
            }
        } else {
            sender.sendMessage(prefix + ChatColor.RED + "Este comando só pode ser executado por players");
        }
        return true;
    }
}
