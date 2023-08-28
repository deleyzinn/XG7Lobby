package br.com.xg7network.xg7lobby.Modulo.Scores.TabList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class TabListSender {

    public static final String VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    public static final String MINECRAFT = "net.minecraft.server." + VERSION + ".";
    public static final String CRAFTBUKKIT = "org.bukkit.craftbukkit." + VERSION + ".";


    public static Class<?> getNMS(String name) {
        try {
            return Class.forName(MINECRAFT + name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Class<?> getCraft(String name) {
        try {
            return Class.forName(CRAFTBUKKIT + name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendTablist(Player player, String header, String footer) {
        if (VERSION.contains("1_8") || VERSION.contains("1_9") || VERSION.contains("1_10") || VERSION.contains("1_11") || VERSION.contains("1_12")) {
            try {
                Class<?> craftPlayerClass = getCraft("entity.CraftPlayer");
                Object craftPlayer = craftPlayerClass.cast(player);

                Class<?> packetPlayOutPlayerListHeaderFooterClass = getNMS("PacketPlayOutPlayerListHeaderFooter");
                Object packetPlayOutPlayerListHeaderFooter = packetPlayOutPlayerListHeaderFooterClass.newInstance();

                Class<?> iChatBaseComponentClass = getNMS("IChatBaseComponent");
                Object headerComponent = iChatBaseComponentClass.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + header + "\"}");
                Object footerComponent = iChatBaseComponentClass.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + footer + "\"}");

                Field a = packetPlayOutPlayerListHeaderFooter.getClass().getDeclaredField("a");
                a.setAccessible(true);
                Field b = packetPlayOutPlayerListHeaderFooter.getClass().getDeclaredField("b");
                b.setAccessible(true);

                a.set(packetPlayOutPlayerListHeaderFooter, headerComponent);
                b.set(packetPlayOutPlayerListHeaderFooter, footerComponent);

                Object playerHandle = craftPlayerClass.getMethod("getHandle").invoke(craftPlayer);
                Object playerConnection = playerHandle.getClass().getDeclaredField("playerConnection").get(playerHandle);
                playerConnection.getClass().getMethod("sendPacket", getNMS("Packet")).invoke(playerConnection, packetPlayOutPlayerListHeaderFooter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            player.setPlayerListHeader(header);
            player.setPlayerListFooter(footer);
        }
    }


}
