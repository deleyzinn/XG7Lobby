package br.com.xg7network.xg7lobby.Modulo;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import br.com.xg7network.xg7lobby.XG7Lobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class Fly extends Module {
    public Fly(XG7Lobby plugin) {
        super(plugin);
    }

    @Override
    public void onEnable() {

        Bukkit.getScheduler().runTaskTimer(this.getPlugin(), () -> {

            for (Player p : Bukkit.getOnlinePlayers()) {

                if (!p.hasPermission(PermissionType.FLY_COMMAND.getPerm())) {
                    p.setAllowFlight(cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName()));
                } else {
                    p.setAllowFlight(true);
                }

            }
        }, 0, 20);
    }

    @Override
    public void onDisable() {

    }
}
