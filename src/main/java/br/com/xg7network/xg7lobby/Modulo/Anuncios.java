package br.com.xg7network.xg7lobby.Modulo;

import br.com.xg7network.xg7lobby.XG7Lobby;

import java.util.ArrayList;
import java.util.List;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class Anuncios extends Module {
    public Anuncios(XG7Lobby plugin) {
        super(plugin);
    }

    int posicao = 0;


    @Override
    public void onEnable() {

        Bukkit.getScheduler().runTaskTimer(getPlugin(), () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                List<List<String>> anuncios = new ArrayList<>();
                for (String anuncio : cm.getConfig().getConfigurationSection("announcements.announcements").getKeys(false)) {
                    anuncios.add(cm.getConfig().getStringList("announcements.announcements." + anuncio));
                }
                if (cm.getConfig().getConfigurationSection("announcements") != null) {
                    List<String> anuncio = anuncios.get(posicao);


                    for (String linhas : anuncio) {
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            linhas = PlaceholderAPI.setPlaceholders(p, linhas);
                        }
                        if (cm.getConfig().getBoolean("announcements.ASNL")) {
                            if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
                                String s = cm.getConfig().getString("announcements.sound");
                                String[] s2 = s.split(", ");
                                p.playSound(p.getLocation(), Sound.valueOf(s2[0]), Float.parseFloat(s2[1]), Float.parseFloat(s2[2]));
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', linhas));
                            }
                        } else {
                            String s = cm.getConfig().getString("announcements.sound");
                            String[] s2 = s.split(", ");
                            p.playSound(p.getLocation(), Sound.valueOf(s2[0]), Float.parseFloat(s2[1]), Float.parseFloat(s2[2]));
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', linhas));
                        }

                    }

                    posicao++;

                    if (posicao == anuncios.size()) {
                        posicao = 0;
                    }
                }
            }

        }, 0, cm.getConfig().getInt("announcements.cooldown") * 20);

    }

    @Override
    public void onDisable() {

    }
}
