package br.com.xg7network.xg7lobby.Modulo;

import br.com.xg7network.xg7lobby.XG7Lobby;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class Warns extends Module{
    public Warns(XG7Lobby plugin) {
        super(plugin);
    }

    @Override
    public void onEnable() {

        Bukkit.getScheduler().runTaskTimer(getPlugin(), () -> {
            for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
                if (cm.getData().getStringList("warns." + p.getName() + ".warns") != null) {
                    if (cm.getData().getStringList("warns." + p.getName() + ".warns").size() < cm.getData().getInt("qtd-warns-ban")) {
                        Bukkit.getBanList(BanList.Type.NAME).pardon(p.getName());
                    }
                }
            }
        }, 100, 0);

    }

    @Override
    public void onDisable() {

    }
}
