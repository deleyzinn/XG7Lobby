package br.com.xg7network.xg7lobby.Comandos.Lobby;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import br.com.xg7network.xg7lobby.Modulo.Seletores.HotbarItens;
import br.com.xg7network.xg7lobby.Modulo.Seletores.HotbarManager;
import br.com.xg7network.xg7lobby.XG7Lobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

import static br.com.xg7network.xg7lobby.Modulo.Seletores.HotbarManager.vanished;
import static br.com.xg7network.xg7lobby.XG7Lobby.*;
import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class Vanish implements CommandExecutor {

    private XG7Lobby plugin;
    private HotbarItens HbItens;

    public Vanish(XG7Lobby plugin) {
        this.plugin = plugin;
        this.HbItens = new HotbarItens();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (p.hasPermission(PermissionType.VANISH_COMMAND.getPerm())) {
                if (!vanished.contains(p.getUniqueId())) {
                    vanished.add(p.getUniqueId());
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        p.hidePlayer(target);
                    }
                    va.mandarMensagem(cm.getMessage().getString("eventos.EJ-esconder"), p);
                    if (p.getInventory().contains(HbItens.getEJ(p).get(0))) {
                        for (ItemStack item : p.getInventory().getContents()) {
                            if (item != null) {
                                if (item.isSimilar(HbItens.getEJ(p).get(0))) {
                                    HbItens.trocarItem(p, item);
                                }
                            }
                        }
                    }
                } else if (vanished.contains(p.getUniqueId())) {
                    vanished.remove(p.getUniqueId());
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        p.showPlayer(target);
                    }
                    va.mandarMensagem(cm.getMessage().getString("eventos.EJ-mostrar"), p);
                    if (p.getInventory().contains(HbItens.getEJ(p).get(1))) {
                        for (ItemStack item : p.getInventory().getContents()) {
                            if (item != null) {
                                if (item.isSimilar(HbItens.getEJ(p).get(1))) {
                                    HbItens.trocarItem(p, item);
                                }
                            }
                        }
                    }
                }
            }
        } else {
            commandSender.sendMessage(prefix + ChatColor.RED + "Este comando só pode ser executado por players");
        }
        return true;
    }
}
