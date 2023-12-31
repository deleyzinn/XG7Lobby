package br.com.xg7network.xg7lobby.Modulo;

import br.com.xg7network.xg7lobby.XG7Lobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.event.Listener;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class Efeitos extends Module implements Listener {
    public Efeitos(XG7Lobby plugin) {
        super(plugin);
    }

    @Override
    public void onEnable() {
        Bukkit.getScheduler().runTaskTimer(getPlugin(), () -> {
            for (String s : cm.getConfig().getStringList("effects")) {
                String[] e = s.split(", ");
                for (Player p: Bukkit.getOnlinePlayers()) {
                    if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
                        if (e[0] != null) {
                            PotionEffectType effect = PotionEffectType.getByName(e[0]);
                            p.addPotionEffect(new PotionEffect(effect, 20, Integer.valueOf(e[1]), false, false));
                        }
                    }
                }
            }
        }, 20, 0);

    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        if (cm.getConfig().getStringList("enabled-worlds").contains(e.getTo().getWorld().getName())) {
            for (String s : cm.getConfig().getStringList("effects")) {
                String[] ef = s.split(", ");
                PotionEffectType effect = PotionEffectType.getByName(ef[0]);
                if (p.hasPotionEffect(effect)) {
                    p.removePotionEffect(effect);
                }
            }
        }
    }

    @Override
    public void onDisable() {

    }
}
