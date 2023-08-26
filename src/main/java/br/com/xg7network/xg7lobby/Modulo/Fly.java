package br.com.xg7network.xg7lobby.Modulo;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import br.com.xg7network.xg7lobby.XG7Lobby;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import static br.com.xg7network.xg7lobby.Comandos.Lobby.Fly.voar;
import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class Fly extends Module {
    public Fly(XG7Lobby plugin) {
        super(plugin);
    }

    @Override
    public void onEnable() {

        Bukkit.getScheduler().runTaskTimer(getPlugin(), () -> {

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
                    if (p.hasPermission(PermissionType.FLY_COMMAND.getPerm())) {
                        p.setAllowFlight(true);
                    }
                } else {
                    p.setAllowFlight(p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR);
                }

            }
        }, 0, 5);
    }

    @Override
    public void onDisable() {

    }
}
