//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package br.com.xg7network.xg7lobby.Eventos;

import br.com.xg7network.xg7lobby.XG7Lobby;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class PingEvent implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent e) {
        if (XG7Lobby.cm.getConfig().getBoolean("motd.ativado")) {
            String motd = String.join("\n", XG7Lobby.cm.getConfig().getStringList("motd.texto"));
            e.setMotd(motd.replace("&", "§"));

            try {
                e.setServerIcon(Bukkit.loadServerIcon(new File("icon.png")));
            } catch (Exception var4) {
                if (XG7Lobby.cm.getConfig().getBoolean("aviso-imagem")) {
                    Bukkit.getConsoleSender().sendMessage("Cuidado! Seu servidor não tem imagem, tente colocar um arquivo com o nome icon.png");
                }
            }
        }

    }
}
