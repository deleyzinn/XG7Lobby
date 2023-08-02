package br.com.xg7network.xg7lobby.Utilidades;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class VerifAction {


    public void mandarMensagem(String s, Player p) {
        if (s.startsWith("[ACTION] ")) {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName()))));
        } else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())));
        }
    }


}
