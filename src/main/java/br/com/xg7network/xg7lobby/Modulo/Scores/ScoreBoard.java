package br.com.xg7network.xg7lobby.Modulo.Scores;

import br.com.xg7network.xg7lobby.Modulo.Module;
import br.com.xg7network.xg7lobby.XG7Lobby;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;
import org.bukkit.event.Listener;

import java.util.List;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class ScoreBoard extends Module implements Listener {
    int scoreb;
    @Override
    public void onEnable() {


        for (Player p : Bukkit.getOnlinePlayers()) {
            if (cm.getConfig().getBoolean("scores.scoreboard.ativado")) {
                colocarScore(p);
                Bukkit.getScheduler().runTaskTimer(getPlugin(), () -> {
                    updateScore(p);
                }, 0, cm.getConfig().getInt("scores.atualizacao"));
            }
        }
    }

    @Override
    public void onDisable() {

        Bukkit.getScheduler().cancelTask(scoreb);
        for (Player p : Bukkit.getOnlinePlayers()) {
            this.removerScore(p);
        }

    }


    public ScoreBoard(XG7Lobby plugin) {
        super(plugin);
    }
    org.bukkit.scoreboard.Scoreboard getScore(Player p) {
        org.bukkit.scoreboard.Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("dummy", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(cm.getConfig().getString("scores.scoreboard.titulo").replace("&", "§"));
        List<String> linhas = cm.getConfig().getStringList("scores.scoreboard.linhas");
        int size = linhas.size() + 1;
        for (String s : linhas) {
            size--;
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
        if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {

            Bukkit.getScheduler().runTaskLater(getPlugin(), () -> colocarScore(p), 5);
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        Player p = e.getPlayer();
        removerScore(p);

    }

    @EventHandler
    public void onWorldChange(PlayerTeleportEvent e) {

        World to = e.getTo().getWorld();
        Player p = e.getPlayer();


        Bukkit.getScheduler().runTaskLater(this.getPlugin(), () -> {
            if (cm.getConfig().getStringList("mundos-ativados").contains(to.getName())) {
                this.colocarScore(p);
            } else {
                this.removerScore(p);
            }
        }, 5);

    }

    void updateScore(Player p) {
        if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
            if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
                org.bukkit.scoreboard.Scoreboard scoreboard = p.getScoreboard();
                Objective objective = scoreboard.getObjective("dummy");

                for (String entry : scoreboard.getEntries()) {
                    if (entry.startsWith("linhas")) {
                        scoreboard.resetScores(entry);
                    }
                }

                List<String> linhas = cm.getConfig().getStringList("scores.scoreboard.linhas");
                int size = linhas.size() + 1;
                for (String s : linhas) {
                    size--;
                    Team linha = scoreboard.getTeam("linhas" + size);
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
