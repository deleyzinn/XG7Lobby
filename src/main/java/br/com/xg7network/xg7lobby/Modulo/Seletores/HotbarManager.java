package br.com.xg7network.xg7lobby.Modulo.Seletores;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import br.com.xg7network.xg7lobby.Modulo.Module;
import br.com.xg7network.xg7lobby.XG7Lobby;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static br.com.xg7network.xg7lobby.XG7Lobby.*;

public class HotbarManager extends Module implements Listener {


    private HotbarItens HbItens;

    public static List<UUID> vanished = new ArrayList<>();
    boolean verf = false;

    private Cache<UUID, Long> cooldown = CacheBuilder.newBuilder().expireAfterWrite(cm.getSeletor().getInt("EsconderJogadores.cooldown"), TimeUnit.SECONDS).build();
    private Cache<UUID, Long> Scooldown = CacheBuilder.newBuilder().expireAfterWrite(cm.getSeletor().getInt("Seletores.cooldown"), TimeUnit.SECONDS).build();

    public HotbarManager(XG7Lobby plugin) {
        super(plugin);
        this.HbItens = new HotbarItens();
    }



    @Override
    public void onEnable() {
        Bukkit.getScheduler().runTaskTimer(this.getPlugin(), () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
                    for (ItemStack item : HbItens.getItens(p)) {
                        if (!p.getInventory().contains(item)) {
                            if (!p.hasPermission(PermissionType.ITENS_JOGAR.getPerm()) || !p.hasPermission(PermissionType.BLOCOS_COLOCAR.getPerm()) || !p.hasPermission(PermissionType.BLOCOS_QUEBRAR.getPerm())) {
                                HbItens.colocarItens(p);
                                HbItens.colocarEJ(p);
                            } else if (!verf) {
                                verf = true;
                                HbItens.colocarItens(p);
                                HbItens.colocarEJ(p);

                            }
                        }
                    }
                }
            }


        }, 0, cm.getSeletor().getInt("cooldown"));

    }

    @Override
    public void onDisable() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            for (ItemStack item : HbItens.getItens(p)) {
                if (p.getInventory().contains(item)) {
                    p.getInventory().removeItem(item);
                }
            }
            for (ItemStack item : HbItens.getEJ(p)) {
                if (p.getInventory().contains(item)) {
                    p.getInventory().removeItem(item);
                }
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.sendMessage(p.getName());
        if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
            HbItens.colocarItens(p);
            HbItens.colocarEJ(p);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        for (ItemStack item : HbItens.getItens(p)) {
            if (p.getInventory().contains(item)) {
                p.getInventory().removeItem(item);
            }
        }
        for (ItemStack item : HbItens.getEJ(p)) {
            if (p.getInventory().contains(item)) {
                p.getInventory().removeItem(item);
            }
        }
    }

    @EventHandler
    public void onWorldChange(PlayerTeleportEvent e) {

        World to = e.getTo().getWorld();
        Player p = e.getPlayer();


        if (cm.getConfig().getStringList("mundos-ativados").contains(to.getName())) {
            verf = false;
            HbItens.colocarItens(p);
            HbItens.colocarEJ(p);
            vanished.remove(p.getUniqueId());
        } else {
            HbItens.removerItens(p);
            HbItens.removerEJ(p);
            vanished.remove(p.getUniqueId());
        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
            if (e.getItem() != null) {
                if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                    for (ItemStack item : HbItens.getItens(p)) {
                        if (e.getItem().isSimilar(item)) {
                            if (this.Scooldown.asMap().containsKey(p.getUniqueId()) && this.Scooldown.asMap().get(p.getUniqueId()) > System.currentTimeMillis()) {
                                long distancia = this.Scooldown.asMap().get(p.getUniqueId()) - System.currentTimeMillis();
                                va.mandarMensagem(cm.getMessage().getString("eventos.no-cooldown").replace("[SEGUNDOS]", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(distancia))), p);
                            } else {
                                this.Scooldown.put(p.getUniqueId(), (System.currentTimeMillis() + cm.getSeletor().getLong("Seletores.cooldown") * 1000L));

                                HbItens.executarAction(p, item);
                            }
                        }
                    }

                }
            }
        }
    }

    @EventHandler
    public void onEJInteract(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
            if (e.getItem() != null) {
                if (HbItens.getEJ(p).contains(e.getItem())) {
                    if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {

                        if (this.cooldown.asMap().containsKey(p.getUniqueId())) {
                            long distancia = cooldown.asMap().get(p.getUniqueId()) - System.currentTimeMillis();
                            va.mandarMensagem(cm.getMessage().getString("eventos.no-cooldown").replace("[SEGUNDOS]", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(distancia))), p);
                        } else {
                            this.cooldown.put(p.getUniqueId(), System.currentTimeMillis() + cm.getSeletor().getLong("EsconderJogadores.cooldown") * 1000L);
                            if (!vanished.contains(p.getUniqueId()) && e.getItem().isSimilar(HbItens.getEJ(p).get(0))) {
                                vanished.add(p.getUniqueId());
                                for (Player target : Bukkit.getOnlinePlayers()) {
                                    p.showPlayer(target);
                                }
                                HbItens.trocarItem(p, e.getItem());
                                va.mandarMensagem(cm.getMessage().getString("eventos.EJ-esconder"), p);
                                ac.executar(cm.getSeletor().getStringList("EsconderJogadores.acoesA"), p);
                            } else if (vanished.contains(p.getUniqueId()) && e.getItem().isSimilar(HbItens.getEJ(p).get(1))) {
                                vanished.remove(p.getUniqueId());
                                for (Player target : Bukkit.getOnlinePlayers()) {
                                    p.hidePlayer(target);
                                }
                                HbItens.trocarItem(p, e.getItem());
                                va.mandarMensagem(cm.getMessage().getString("eventos.EJ-mostrar"), p);
                                ac.executar(cm.getSeletor().getStringList("EsconderJogadores.acoesD"), p);
                            }

                        }
                    }
                }
            }
        }
    }


}
