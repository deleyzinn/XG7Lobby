package br.com.xg7network.xg7lobby.Comandos.Lobby;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import br.com.xg7network.xg7lobby.XG7Lobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import static br.com.xg7network.xg7lobby.XG7Lobby.*;

public class Setlobby implements CommandExecutor {

    private XG7Lobby pl;
    FileConfiguration lobbyC;

    File lobbyF;
    public Setlobby (XG7Lobby pl) {
        this.lobbyC = cm.getData();
        this.lobbyF = new File(pl.getDataFolder(), "data.yml");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, @NotNull String[] args) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission(PermissionType.SETLOBBY_COMMAND.getPerm())) {
                    if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
                        lobbyC.set("lobby.Mundo", p.getWorld().getName());
                        lobbyC.set("lobby.X", p.getLocation().getX());
                        lobbyC.set("lobby.Y", p.getLocation().getY());
                        lobbyC.set("lobby.Z", p.getLocation().getZ());
                        lobbyC.set("lobby.Yaw", p.getLocation().getYaw());
                        lobbyC.set("lobby.Pitch", p.getLocation().getPitch());


                        try {
                            lobbyC.save(lobbyF);
                        } catch (IOException e) {
                            p.sendMessage(ChatColor.RED + "Não foi possível salvar as informações do lobby");
                            throw new RuntimeException(e);
                        }

                        sender.sendMessage(ChatColor.GRAY + "O lobby de seu servidor foi configurado com sucesso na coordenada "
                                + ChatColor.GREEN + cm.getData().getInt("lobby.x") + ", "
                                + cm.getData().getInt("lobby.y") + ", "
                                + cm.getData().getInt("lobby.z") + ", " + ChatColor.GRAY + "no mundo "
                                + ChatColor.GREEN + cm.getData().get("lobby.mundo") + ".");

                    } else {
                        va.mandarMensagem(prefix + ChatColor.RED + "Você não pode executar este comando no mundo em que não está ativado pelo plugin!", p);
                    }
                } else {
                    va.mandarMensagem(cm.getMessage().getString("comandos.permissão"), p);
                }
            } else {
                if (args.length == 4) {
                    System.out.println(args[0]);
                    if (cm.getConfig().getStringList("mundos-ativados").contains(args[0])) {

                        World w = Bukkit.getWorld(args[0]);
                        float x = Float.parseFloat(args[1]);
                        float y = Float.parseFloat(args[2]);
                        float z = Float.parseFloat(args[3]);

                        lobbyC.set("lobby.Mundo", w.getName());
                        lobbyC.set("lobby.X", x);
                        lobbyC.set("lobby.Y", y);
                        lobbyC.set("lobby.Z", z);

                        try {
                            lobbyC.save(lobbyF);
                        } catch (IOException e) {
                            sender.sendMessage(ChatColor.RED + "Não foi possível salvar as informações do lobby");
                            throw new RuntimeException(e);
                        }


                        sender.sendMessage(prefix + ChatColor.GRAY + "O lobby de seu servidor foi configurado com sucesso na coordenada "
                                + ChatColor.GREEN + cm.getData().getInt("lobby.X") + ", "
                                + cm.getData().getInt("lobby.Y") + ", "
                                + cm.getData().getInt("lobby.Z") + ", " + ChatColor.GRAY + "no mundo "
                                + ChatColor.GREEN + cm.getData().get("lobby.Mundo") + ".");

                    }
                } else if (args.length == 6) {
                    System.out.println(args[0]);
                    if (cm.getConfig().getStringList("mundos-ativados").contains(args[0])) {

                        World w = Bukkit.getWorld(args[0]);
                        float x = Float.parseFloat(args[1]);
                        float y = Float.parseFloat(args[2]);
                        float z = Float.parseFloat(args[3]);
                        double yaw = Double.parseDouble(args[4]);
                        double pitch = Double.parseDouble(args[5]);

                        lobbyC.set("lobby.Mundo", w.getName());
                        lobbyC.set("lobby.X", x);
                        lobbyC.set("lobby.Y", y);
                        lobbyC.set("lobby.Z", z);
                        lobbyC.set("lobby.Yaw", yaw);
                        lobbyC.set("lobby.Pitch", pitch);

                        try {
                            lobbyC.save(lobbyF);
                        } catch (IOException e) {
                            sender.sendMessage(ChatColor.RED + "Não foi possível salvar as informações do lobby");
                            throw new RuntimeException(e);
                        }

                        sender.sendMessage(prefix + ChatColor.GRAY + "O lobby de seu servidor foi configurado com sucesso na coordenada "
                                + ChatColor.GREEN + cm.getData().getInt("lobby.X") + ", "
                                + cm.getData().getInt("lobby.Y") + ", "
                                + cm.getData().getInt("lobby.Z") + ", " + ChatColor.GRAY + "no mundo "
                                + ChatColor.GREEN + cm.getData().get("lobby.Mundo") + ".");

                    } else {
                        sender.sendMessage(prefix + ChatColor.RED + "Como você é um console, o jeito de usar é " + ChatColor.GRAY + "/" + ChatColor.YELLOW + "setlobby " + ChatColor.GREEN + "<mundo> <x> <y> <z> (yaw), (pitch)");
                    }
                }
            }
        return true;
    }
}
