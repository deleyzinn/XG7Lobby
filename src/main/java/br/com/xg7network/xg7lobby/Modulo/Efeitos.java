package br.com.xg7network.xg7lobby.Modulo;

import br.com.xg7network.xg7lobby.XG7Lobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChangedWorldEvent;
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
            for (String s : cm.getConfig().getStringList("Efeitos")) {
                String[] e = s.split(", ");
                for (Player p: Bukkit.getOnlinePlayers()) {
                    if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
                        PotionEffectType effect = PotionEffectType.getByName(e[0]);
                        p.addPotionEffect(new PotionEffect(effect, 20, Integer.valueOf(e[1])));
                    }
                }
            }
        }, 20, 0);

    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        if (cm.getConfig().getStringList("mundos-ativados").contains(e.getFrom().getName())) {
            for (String s : cm.getConfig().getStringList("Efeitos")) {
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
