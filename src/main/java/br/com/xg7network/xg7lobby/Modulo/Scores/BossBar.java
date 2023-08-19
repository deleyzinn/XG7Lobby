package br.com.xg7network.xg7lobby.Modulo.Scores;

import br.com.xg7network.xg7lobby.Modulo.Module;
import br.com.xg7network.xg7lobby.XG7Lobby;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;
import static br.com.xg7network.xg7lobby.XG7Lobby.prefix;

public class BossBar extends Module {

    org.bukkit.boss.BossBar boss;

    public BossBar(XG7Lobby plugin) {
        super(plugin);
    }
    int bossbar;

    @Override
    public void onEnable() {
        String bossTitulo = ChatColor.translateAlternateColorCodes('&', cm.getConfig().getString("scores.bossbar.título"));
        boss = Bukkit.createBossBar(bossTitulo,
                BarColor.valueOf(cm.getConfig().getString("scores.bossbar.cor")),
                BarStyle.valueOf(cm.getConfig().getString("scores.bossbar.estilo")));

        boss.setProgress(cm.getConfig().getDouble("scores.bossbar.progresso"));



        bossbar = Bukkit.getScheduler().runTaskTimer(this.getPlugin(), () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {

                if (cm.getConfig().getBoolean("scores.bossbar.ativado")) {
                        if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
                            boss.addPlayer(p);
                        } else {
                            boss.removePlayer(p);
                        }
                }
            }

        }, 0, 5).getTaskId();
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTask(this.bossbar);
        for (Player p : Bukkit.getOnlinePlayers()) {
            boss.removePlayer(p);
        }
    }
}
