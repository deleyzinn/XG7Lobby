//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package br.com.xg7network.xg7lobby.Modulo.Scores;

import br.com.xg7network.xg7lobby.XG7Lobby;
import br.com.xg7network.xg7lobby.Modulo.Module;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class ScoreBoard extends Module implements Listener {
    int scoreb;

    public void onEnable() {

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (cm.getConfig().getBoolean("scores.scoreboard.enabled")) {
                if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
                    this.colocarScore(p);
                    Bukkit.getScheduler().runTaskTimer(this.getPlugin(), () -> this.updateScore(p), 0L, cm.getConfig().getInt("scores.update"));
                }
            }
        }

    }

    public void onDisable() {
        Bukkit.getScheduler().cancelTask(this.scoreb);

        for (Player p : Bukkit.getOnlinePlayers()) {
            this.removerScore(p);
        }

    }

    public ScoreBoard(XG7Lobby plugin) {
        super(plugin);
    }

    Scoreboard getScore(Player p) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("dummy", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(cm.getConfig().getString("scores.scoreboard.title").replace("&", "§"));
        List<String> linhas = cm.getConfig().getStringList("scores.scoreboard.lines");
        int size = linhas.size() + 1;

        for (String s : linhas) {
            --size;
            Team linha = board.registerNewTeam("linhas" + size);
            s = s.replace("&", "§");
            s = s.replace("[PLAYER]", p.getName());
            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                s = PlaceholderAPI.setPlaceholders(p, s);
            }

            linha.addEntry(s);
            obj.getScore(s).setScore(size);
        }

        return board;
    }

    void colocarScore(Player p) {
        p.setScoreboard(this.getScore(p));
    }

    void removerScore(Player p) {
        p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
            Bukkit.getScheduler().runTaskLater(this.getPlugin(), () -> {
                this.colocarScore(p);
            }, 5L);
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        this.removerScore(p);
    }

    @EventHandler
    public void onWorldChange(PlayerTeleportEvent e) {
        World to = e.getTo().getWorld();
        Player p = e.getPlayer();
        Bukkit.getScheduler().runTaskLater(this.getPlugin(), () -> {
            if (cm.getConfig().getStringList("enabled-worlds").contains(to.getName())) {
                this.colocarScore(p);
            } else {
                this.removerScore(p);
            }

        }, 5L);
    }

    void updateScore(Player p) {
        if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
            if (p.getScoreboard() != null) {
                Scoreboard scoreboard = p.getScoreboard();
                Objective objective = scoreboard.getObjective("dummy");

                scoreboard.getEntries().forEach(scoreboard::resetScores);

                List<String> linhas = cm.getConfig().getStringList("scores.scoreboard.lines");
                int size = linhas.size() + 1;

                for (String s : linhas) {
                    --size;
                    Team linha = scoreboard.getTeam("linhas" + size);

                    if (linha != null) {

                        s = s.replace("&", "§");
                        s = s.replace("[PLAYER]", p.getName());

                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            s = PlaceholderAPI.setPlaceholders(p, s);
                        }

                        linha.addEntry(s);
                        objective.getScore(s).setScore(size);
                    }
                }
            }
        }
    }
}
