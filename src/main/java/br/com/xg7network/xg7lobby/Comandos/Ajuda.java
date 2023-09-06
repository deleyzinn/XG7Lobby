package br.com.xg7network.xg7lobby.Comandos;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import br.com.xg7network.xg7lobby.Configs.PermissionType;

import br.com.xg7network.xg7lobby.XG7Lobby;
import me.clip.placeholderapi.PlaceholderAPI;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.bukkit.event.Listener;

import static br.com.xg7network.xg7lobby.XG7Lobby.*;

public class Ajuda implements CommandExecutor, Listener {

    FileConfiguration lobbyC;

    File lobbyF;
    public Ajuda (XG7Lobby pl) {
        this.lobbyC = cm.getData();
        this.lobbyF = new File(pl.getDataFolder(), "data.yml");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(PermissionType.HELP_COMMAND.getPerm())) {
                if (args.length == 1) {

                    if (args[0].equals("inv")) {

                        p.sendMessage(prefix + ChatColor.RED + "O jeito certo de usar é /7lajuda inv (pt/eng)");

                    }

                    if (args[0].equals("text")) {

                        p.sendMessage(prefix + ChatColor.RED + "O jeito certo de usar é /7lajuda text (pt/eng)");

                    } else {
                        p.sendMessage(prefix + ChatColor.RED + "O jeito certo de usar é /7lajuda (text/inv) (pt/eng)");
                    }
                } else if (args.length == 2) {
                    if (args[0].equals("inv") && args[1].equals("pt")) {

                        p.openInventory(getPrincipalInv("pt", p));

                    } else if (args[0].equals("inv") && args[1].equals("eng")) {

                        p.openInventory(getPrincipalInv("eng", p));
                    } else if (args[0].equals("text") &&args[1].equals("pt")) {
                        p.sendMessage("NOTA: <> OBRIGATÓRIO, [] NÃO OBRIGATÓRIO");
                        p.sendMessage(ChatColor.GRAY + "-----------------------------------");
                        p.sendMessage(ChatColor.GOLD + "Comandos de moderação:");
                        p.sendMessage(ChatColor.GREEN + "/7lban: " + ChatColor.RESET + "Bane os jogadores");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lban <Jogador> [Motivo]");
                        p.sendMessage(ChatColor.GREEN + "/7lunban: " + ChatColor.RESET + "Perdoa os banidos");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lunban <Jogador>");
                        p.sendMessage(ChatColor.GREEN + "/7ltempban: " + ChatColor.RESET + "Bane os jogadores por um período de tempo");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7ltempban <Jogador> <Tempo> [Razão]");
                        p.sendMessage(ChatColor.GREEN + "/7lkick: " + ChatColor.RESET + "Expulsa os jogadores");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lkick <Jogador> [Motivo]");
                        p.sendMessage(ChatColor.GREEN + "/7lmute: " + ChatColor.RESET + "Silencia os jogadores");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lmute <Jogador>");
                        p.sendMessage(ChatColor.GREEN + "/7lunmute: " + ChatColor.RESET + "Retira os jogadores da lista de silenciados");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lunban <Jogador>");
                        p.sendMessage(ChatColor.GREEN + "/7lwarn: " + ChatColor.RESET + "Alerta os jogadores");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lunban <Jogador> <motivo>");
                        p.sendMessage(ChatColor.GREEN + "/7lwarns: " + ChatColor.RESET + "Vê a lista de avisos que você tem");
                        p.sendMessage(ChatColor.GREEN + "/7llockchat: " + ChatColor.RESET + "Vai bloquear o chat");
                        p.sendMessage(ChatColor.GRAY + "-----------------------------------");
                        p.sendMessage(ChatColor.GOLD + "Comandos de Player:");
                        p.sendMessage(ChatColor.GREEN + "/7lfly: " + ChatColor.RESET + "Faz o jogador voar");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lfly [Jogador]");
                        p.sendMessage(ChatColor.GREEN + "/7llobby: " + ChatColor.RESET + "Teletransporta o jogador para o lobby");
                        p.sendMessage(ChatColor.GREEN + "/7lsetlobby: " + ChatColor.RESET + "Coloca a posição do lobby");
                        p.sendMessage(ChatColor.GREEN + "/7lvanish: " + ChatColor.RESET + "Esconde os jogadores");
                        p.sendMessage(ChatColor.GREEN + "/gmc: " + ChatColor.RESET + "Muda o modo de jogo para o Criativo");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lgmc [Jogador]");
                        p.sendMessage(ChatColor.GREEN + "/gms: " + ChatColor.RESET + "Muda o modo de jogo para o Sobrevivência");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lgms [Jogador]");
                        p.sendMessage(ChatColor.GREEN + "/gma: " + ChatColor.RESET + "Muda o modo de jogo para o Aventura");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lgma [Jogador]");
                        p.sendMessage(ChatColor.GREEN + "/gmsp: " + ChatColor.RESET + "Muda o modo de jogo para o espectador");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lgmsp [Jogador]");
                        p.sendMessage(ChatColor.GRAY + "-----------------------------------");
                        p.sendMessage(ChatColor.GOLD + "Outros:");
                        p.sendMessage(ChatColor.GREEN + "/7lreloadconfig: " + ChatColor.RESET + "Recarrega todos os arquivos de configuração");
                        p.sendMessage(ChatColor.GREEN + "/7lajuda: " + ChatColor.RESET + "comando para ajuda");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lajuda <inv/text>, <pt/eng>");
                        p.sendMessage(ChatColor.GREEN + "/gui: " + ChatColor.RESET + "Abre um inventário de seletores.yml");
                        p.sendMessage(ChatColor.DARK_GREEN + "/gui <id>");
                        p.sendMessage(ChatColor.GRAY + "-----------------------------------");
                        p.sendMessage(ChatColor.GOLD + "Permissões:");
                        p.sendMessage(ChatColor.YELLOW + "/7lban: " + ChatColor.AQUA + "xg7lobby.command.ban");
                        p.sendMessage(ChatColor.YELLOW + "/7lunban: " + ChatColor.AQUA + "xg7lobby.command.unbban");
                        p.sendMessage(ChatColor.YELLOW + "/7ltempban: " + ChatColor.AQUA + "xg7lobby.command.tempban");
                        p.sendMessage(ChatColor.YELLOW + "/7lmute: " + ChatColor.AQUA + "xg7lobby.command.mute");
                        p.sendMessage(ChatColor.YELLOW + "/7lunmute: " + ChatColor.AQUA + "xg7lobby.command.unmute");
                        p.sendMessage(ChatColor.YELLOW + "/7lkick: " + ChatColor.AQUA + "xg7lobby.command.kick");
                        p.sendMessage(ChatColor.YELLOW + "/7lwarn: " + ChatColor.AQUA + "xg7lobby.command.warn");
                        p.sendMessage(ChatColor.YELLOW + "/7lwarns: " + ChatColor.RESET + "Sem permissões");
                        p.sendMessage(ChatColor.YELLOW + "/7lfly: " + ChatColor.AQUA + "xg7lobby.command.fly/xg7lobby.command.flyother");
                        p.sendMessage(ChatColor.YELLOW + "/7lvanish: " + ChatColor.AQUA + "xg7lobby.command.vanish");
                        p.sendMessage(ChatColor.YELLOW + "/setlobby: " + ChatColor.AQUA + "xg7lobby.command.setlobby");
                        p.sendMessage(ChatColor.YELLOW + "/lobby: " + ChatColor.RESET +"Sem permissões");
                        p.sendMessage(ChatColor.YELLOW + "/gmc: " + ChatColor.AQUA + "xg7lobby.gamemode.creative/xg7lobby.gamemode.others");
                        p.sendMessage(ChatColor.YELLOW + "/gms: " + ChatColor.AQUA + "xg7lobby.gamemode.surivival/xg7lobby.gamemode.others");
                        p.sendMessage(ChatColor.YELLOW + "/gma: " + ChatColor.AQUA + "xg7lobby.gamemode.adventure/xg7lobby.gamemode.others");
                        p.sendMessage(ChatColor.YELLOW + "/gui: " + ChatColor.AQUA + "xg7lobby.gamemode.spectator/xg7lobby.gamemode.others");
                        p.sendMessage(ChatColor.YELLOW + "/7lajuda: " + ChatColor.AQUA + "xg7lobby.command.help");
                        p.sendMessage(ChatColor.YELLOW + "/7lreloadconfig: " + ChatColor.AQUA + "xg7lobby.command.reload");
                        p.sendMessage(ChatColor.YELLOW + "/7llockchat: " + ChatColor.AQUA + "xg7lobby.command.lockchat");
                        p.sendMessage(ChatColor.GRAY + "-----------------------------------");
                        p.sendMessage(ChatColor.DARK_PURPLE + ChatColor.ITALIC.toString() + "Plugin feito por DaviXG7");



                    } else if (args[0].equals("text") &&args[1].equals("eng")) {


                        p.sendMessage("NOTE: <> REQUIRED, [] NOT REQUIRED");
                        p.sendMessage(ChatColor.GRAY + "----------------------------------");
                        p.sendMessage(ChatColor.GOLD + "Moderation commands:");
                        p.sendMessage(ChatColor.GREEN + "/7lban: " + ChatColor.RESET + "Ban players");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lban <Player> [Reason]");
                        p.sendMessage(ChatColor.GREEN + "/7lunban: " + ChatColor.RESET + "Forgive the banned");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lunban <Player>");
                        p.sendMessage(ChatColor.GREEN + "/7ltempban: " + ChatColor.RESET + "Ban for a period of time");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7ltempban <Player> <Time> [Reason]");
                        p.sendMessage(ChatColor.GREEN + "/7lkick: " + ChatColor.RESET + "Kick Players");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lkick <Player> [Reason]");
                        p.sendMessage(ChatColor.GREEN + "/7lmute: " + ChatColor.RESET + "Mute players");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lmute <Player>");
                        p.sendMessage(ChatColor.GREEN + "/7lunmute: " + ChatColor.RESET + "Remove players from the muted list");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lunban <Player>");
                        p.sendMessage(ChatColor.GREEN + "/7lwarn: " + ChatColor.RESET + "Send a Warn to player");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lunban <Player> <Reason>");
                        p.sendMessage(ChatColor.GREEN + "/7lwarns: " + ChatColor.RESET + "See your warns list");
                        p.sendMessage(ChatColor.GREEN + "/7llockchat: " + ChatColor.RESET + "Will lock the chat");
                        p.sendMessage(ChatColor.GRAY + "----------------------------------");
                        p.sendMessage(ChatColor.GOLD + "Player Commands:");
                        p.sendMessage(ChatColor.GREEN + "/7lfly: " + ChatColor.RESET + "Makes the player fly");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lfly [Player]");
                        p.sendMessage(ChatColor.GREEN + "/7llobby: " + ChatColor.RESET + "Teleports player to lobby");
                        p.sendMessage(ChatColor.GREEN + "/7lsetlobby: " + ChatColor.RESET + "Lobby Position Setting");
                        p.sendMessage(ChatColor.GREEN + "/7lvanish: " + ChatColor.RESET + "Hides players");
                        p.sendMessage(ChatColor.GREEN + "/gmc: " + ChatColor.RESET + "Change game mode to Creative");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lgmc [Player]");
                        p.sendMessage(ChatColor.GREEN + "/gms: " + ChatColor.RESET + "Change game mode to Survival");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lgms [Player]");
                        p.sendMessage(ChatColor.GREEN + "/gma: " + ChatColor.RESET + "Change game mode to Adventure");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lgma [Player]");
                        p.sendMessage(ChatColor.GREEN + "/gmsp: " + ChatColor.RESET + "Change game mode for spectator");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lgmsp [Player]");
                        p.sendMessage(ChatColor.GRAY + "----------------------------------");
                        p.sendMessage(ChatColor.GOLD + "Others:");
                        p.sendMessage(ChatColor.GREEN + "/7lreloadconfig: " + ChatColor.RESET + "Reload all configuration files");
                        p.sendMessage(ChatColor.GREEN + "/7lhelp: " + ChatColor.RESET + "command for help");
                        p.sendMessage(ChatColor.DARK_GREEN + "/7lhelp <inv/text>, <pt/eng>");
                        p.sendMessage(ChatColor.GREEN + "/gui: " + ChatColor.RESET + "Opens an inventory of selectors.yml");
                        p.sendMessage(ChatColor.DARK_GREEN + "/gui <id>");
                        p.sendMessage(ChatColor.GRAY + "----------------------------------");
                        p.sendMessage(ChatColor.GOLD + "Permissions:");
                        p.sendMessage(ChatColor.YELLOW + "/7lban: " + ChatColor.AQUA + "xg7lobby.command.ban");
                        p.sendMessage(ChatColor.YELLOW + "/7lunban: " + ChatColor.AQUA + "xg7lobby.command.unbban");
                        p.sendMessage(ChatColor.YELLOW + "/7ltempban: " + ChatColor.AQUA + "xg7lobby.command.tempban");
                        p.sendMessage(ChatColor.YELLOW + "/7lmute: " + ChatColor.AQUA + "xg7lobby.command.mute");
                        p.sendMessage(ChatColor.YELLOW + "/7lunmute: " + ChatColor.AQUA + "xg7lobby.command.unmute");
                        p.sendMessage(ChatColor.YELLOW + "/7lkick: " + ChatColor.AQUA + "xg7lobby.command.kick");
                        p.sendMessage(ChatColor.YELLOW + "/7lwarn: " + ChatColor.AQUA + "xg7lobby.command.warn");
                        p.sendMessage(ChatColor.YELLOW + "/7lwarns: " + ChatColor.RESET + "No permissions");
                        p.sendMessage(ChatColor.YELLOW + "/7lfly: " + ChatColor.AQUA + "xg7lobby.command.flyxg7lobby.command.flyother");
                        p.sendMessage(ChatColor.YELLOW + "/7lvanish: " + ChatColor.AQUA + "xg7lobby.command.vanish");
                        p.sendMessage(ChatColor.YELLOW + "/setlobby: " + ChatColor.AQUA + "xg7lobby.command.setlobby");
                        p.sendMessage(ChatColor.YELLOW + "/lobby: " + ChatColor.RESET +"No permissions");
                        p.sendMessage(ChatColor.YELLOW + "/gmc: " + ChatColor.AQUA + "xg7lobby.gamemode.creativexg7lobby.gamemode.others");
                        p.sendMessage(ChatColor.YELLOW + "/gms: " + ChatColor.AQUA + "xg7lobby.gamemode.surivivalxg7lobby.gamemode.others");
                        p.sendMessage(ChatColor.YELLOW + "/gma: " + ChatColor.AQUA + "xg7lobby.gamemode.adventurexg7lobby.gamemode.others");
                        p.sendMessage(ChatColor.YELLOW + "/gui: " + ChatColor.AQUA + "xg7lobby.gamemode.spectatorxg7lobby.gamemode.others");
                        p.sendMessage(ChatColor.YELLOW + "/7lajuda: " + ChatColor.AQUA + "xg7lobby.command.help");
                        p.sendMessage(ChatColor.YELLOW + "/7lreloadconfig: " + ChatColor.AQUA + "xg7lobby.command.reload");
                        p.sendMessage(ChatColor.YELLOW + "/7llockchat: " + ChatColor.AQUA + "xg7lobby.command.lockchat");
                        p.sendMessage(ChatColor.GRAY + "----------------------------------");
                        p.sendMessage(ChatColor.DARK_PURPLE + ChatColor.ITALIC.toString() + "Plugin made by DaviXG7");


                    } else {
                        p.sendMessage(prefix + ChatColor.RED + "O jeito certo de usar é /7lajuda (text/inv) (pt/eng)");
                    }
                } else {
                    TextComponent texto = new TextComponent("§8-------------§7**§8-------------- \n\n");
                    TextComponent invtx = new TextComponent("§f- §6Clique para abrir o inventário\n");
                    TextComponent textx = new TextComponent("§f- §6Clique para ver o comandos em texto\n\n");
                    TextComponent texto2 = new TextComponent("§8-------------§7**§8--------------");
                    TextComponent invtx2 = new TextComponent("§f- §6Click to open the help inv\n");
                    TextComponent textx2 = new TextComponent("§f- §6Clique to see the commands in text\n\n");

                    TextComponent Inve = new TextComponent("§fClique para abrir o inventário.");
                    TextComponent COma = new TextComponent("§fClique para ver o comandos em texto.");

                    TextComponent Inve2 = new TextComponent("§fClick to open the help inventory.");
                    TextComponent Coma2 = new TextComponent("§fClique to see the commands in text.");


                    invtx.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{Inve}));
                    invtx.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/xg7lajuda inv pt"));
                    textx.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{COma}));
                    textx.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/xg7lajuda text pt"));
                    invtx2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/xg7lajuda inv eng"));
                    invtx2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{Inve2}));
                    textx2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/xg7lajuda text eng"));
                    textx2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{Coma2}));


                    p.spigot().sendMessage(new BaseComponent[]{texto, invtx, textx, texto, invtx2, textx2, texto2});
                }
            } else {
                va.mandarMensagem(cm.getMessage().getString("commands.permission"), p);
            }
        } else {
            sender.sendMessage(prefix + ChatColor.RED + "Este comando só pode ser executado por players");
        }
        return true;
    }

    Inventory getPrincipalInv(String idioma, Player p) {


        if (idioma.equals("pt")) {

        /*
        00, 01, 02, 03, 04, 05, 06, 07, 08
        09, 10, 11, 12, 13, 14, 15, 16, 17
        18, 19, 20, 21, 22, 23, 24, 25, 26
        27, 28, 29, 30, 31, 32, 33, 34, 35
        36, 37, 38, 39, 40, 41, 42, 43, 44
        45, 46, 47, 48, 49, 52, 51, 52, 53
         */
            Inventory inv = Bukkit.createInventory(p, 54, "&6Ajuda".replace("&", "§"));

            inv.setItem(13, getPrincipalInvItens("pt", p).get(0));
            inv.setItem(30, getPrincipalInvItens("pt", p).get(1));
            inv.setItem(31, getPrincipalInvItens("pt", p).get(2));
            inv.setItem(32, getPrincipalInvItens("pt", p).get(3));
            inv.setItem(46, getPrincipalInvItens("pt", p).get(4));
            inv.setItem(36, getPrincipalInvItens("pt", p).get(5));
            inv.setItem(45, getPrincipalInvItens("pt", p).get(6));
            inv.setItem(52, getPrincipalInvItens("pt", p).get(8));
            inv.setItem(53, getPrincipalInvItens("pt", p).get(7));

            return inv;

        } else if (idioma.equals("eng")) {

            Inventory inv = Bukkit.createInventory(p, 54, "&6Help".replace("&", "§"));

            inv.setItem(13, getPrincipalInvItens("eng", p).get(0));
            inv.setItem(30, getPrincipalInvItens("eng", p).get(1));
            inv.setItem(31, getPrincipalInvItens("eng", p).get(2));
            inv.setItem(32, getPrincipalInvItens("eng", p).get(3));
            inv.setItem(46, getPrincipalInvItens("eng", p).get(4));
            inv.setItem(36, getPrincipalInvItens("eng", p).get(5));
            inv.setItem(45, getPrincipalInvItens("eng", p).get(6));
            inv.setItem(52, getPrincipalInvItens("eng", p).get(8));
            inv.setItem(53, getPrincipalInvItens("eng", p).get(7));

            return inv;


        }
        return null;

    }
    List<ItemStack> getPrincipalInvItens(String idioma, Player p) {
        List<ItemStack> itens = new ArrayList<>();
        if (idioma.equals("eng")) {

            List<String> Skullore = new ArrayList<>();
            Skullore.add("&bThere are &a%server_online% &bpeople online on the server!");
            if (p.getDisplayName().split(p.getName()).length == 0) {
                Skullore.add("&cYou don't have rank!");
            } else {
                Skullore.add("&aYour rank is&r" + p.getDisplayName().split(p.getName())[0]);
            }
            Skullore.add("&eWelcome to help chat");
            boolean isNV = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");

            Material type = Material.matchMaterial(isNV ? "PLAYER_HEAD" : "SKULL_ITEM");

            ItemStack skull = getItemStack(type,"&b" + p.getName(),  1, Skullore, false, p);

            itens.add(skull);

            List<String> Alore = new ArrayList<>();
            Alore.add("&8-> &eClick to open the action inventory");
            ItemStack Aitem = getItemStack(Material.EMERALD, "&b&lActions", 1, Alore, true, p);

            itens.add(Aitem);

            List<String> CmdLore = new ArrayList<>();

            CmdLore.add("&8-> &eClick here to see the commands!");

            ItemStack cmd = getItemStack(Material.COMPASS, "&9&lCommands", 1, CmdLore, true, p);

            itens.add(cmd);

            List<String> SELore = new ArrayList<>();

            SELore.add("&8-> &eClick here to open the guide!");

            ItemStack seletor = getItemStack(Material.BOOK, "&c&lSelectors guide", 1, SELore, false, p);

            itens.add(seletor);




            List<String> DaviXG7Lore = new ArrayList<>();

            DaviXG7Lore.add("&bCreator and Developer of the plugin");

            List<String> RhyanLore = new ArrayList<>();

            RhyanLore.add("&bPlugin helper, he was help with configs!");

            List<String> EDLore = new ArrayList<>();

            EDLore.add("&bBeta tester!");

            ItemStack DaviXG7 = getItemStack(Material.PAPER, "&bDavi&1X&3G&97", 1, DaviXG7Lore, false, p);

            itens.add(DaviXG7);


            ItemStack Rhyan57 = getItemStack(Material.PAPER, "&eRhyan57", 1, RhyanLore, false, p);

            itens.add(Rhyan57);

            ItemStack ed10 = getItemStack(Material.PAPER, "&bDavi&1X&3G&97", 1, EDLore, false, p);

            itens.add(ed10);


            List<String> PTlore = new ArrayList<>();

            ItemStack PT = getItemStack(Material.PAPER, "Trocar pra português", 1, PTlore, false, p);

            List<String> ENlore = new ArrayList<>();

            ItemStack EN = getItemStack(Material.PAPER, "Change to English", 1, ENlore, false, p);

            itens.add(PT);
            itens.add(EN);

        } else if (idioma.equals("pt")) {

            List<String> Skullore = new ArrayList<>();
            Skullore.add("&bHá &a%server_online% &bpessoas on-line no servidor");
            if (p.getDisplayName().split(p.getName()).length == 0) {
                Skullore.add("&cVocê não tem rank!");
            } else {
                Skullore.add("&aSeu rank é &r" + p.getDisplayName().split(p.getName())[0]);
            }
            Skullore.add("&eBem-vindo ao inventário de ajuda!");

            boolean isNV = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");

            Material type = Material.matchMaterial(isNV ? "PLAYER_HEAD" : "SKULL_ITEM");

            ItemStack skull = getItemStack(type, "&b" + p.getName(), 1, Skullore, false, p);

            itens.add(skull);

            List<String> Alore = new ArrayList<>();
            Alore.add("&8-> &eClique para abrir o inventário de ações");
            ItemStack Aitem = getItemStack(Material.EMERALD, "&b&lAções", 1, Alore, true, p);

            itens.add(Aitem);

            List<String> CmdLore = new ArrayList<>();

            CmdLore.add("&8-> &eClique aqui para ver todos os comandos!");

            ItemStack cmd = getItemStack(Material.COMPASS, "&9&lComandos", 1, CmdLore, true, p);

            itens.add(cmd);

            List<String> SELore = new ArrayList<>();

            SELore.add("&8-> &eClique para abrir o guia!");

            ItemStack seletor = getItemStack(Material.BOOK, "&c&lGuia dos Seletores", 1, SELore, false, p);

            itens.add(seletor);

            List<String> DaviXG7Lore = new ArrayList<>();

            DaviXG7Lore.add("&bCriador e programador desse plugin");

            List<String> RhyanLore = new ArrayList<>();

            RhyanLore.add("&bAjudante do plugin, ajudou nas configs");

            List<String> EDLore = new ArrayList<>();

            EDLore.add("&bBeta tester!");

            ItemStack DaviXG7 = getItemStack(Material.PAPER, "&1Da&9vi&bXG7", 1, DaviXG7Lore, false, p);

            itens.add(DaviXG7);

            ItemStack eduardo10YT = getItemStack(Material.PAPER, "&aeduardo10YT", 1, EDLore, false, p);

            itens.add(eduardo10YT);



            ItemStack Rhyan57 = getItemStack(Material.PAPER, "&eRhyan57", 1, RhyanLore, false, p);

            itens.add(Rhyan57);


            List<String> PTlore = new ArrayList<>();

            ItemStack PT = getItemStack(Material.PAPER, "Trocar pra português", 1, PTlore, false, p);

            List<String> ENlore = new ArrayList<>();

            ItemStack EN = getItemStack(Material.PAPER, "Change to English", 1, ENlore, false, p);

            itens.add(PT);
            itens.add(EN);


        }
        return itens;
    }




    Inventory getCMDInv(String idioma, Player p) {

        if (idioma.equals("pt")) {

        /*
        00, 01, 02, 03, 04, 05, 06, 07, 08
        09, 10, 11, 12, 13, 14, 15, 16, 17
        18, 19, 20, 21, 22, 23, 24, 25, 26
        27, 28, 29, 30, 31, 32, 33, 34, 35
        36, 37, 38, 39, 40, 41, 42, 43, 44
        45, 46, 47, 48, 49, 52, 51, 52, 53
         */
            Inventory inv = Bukkit.createInventory(p, 27, "&6Commands".replace("&", "§"));

            inv.setItem(26, getCMDInvItens("pt", p).get(0));
            inv.setItem(11, getCMDInvItens("pt", p).get(1));
            inv.setItem(12, getCMDInvItens("pt", p).get(2));
            inv.setItem(14, getCMDInvItens("pt", p).get(3));
            inv.setItem(15, getCMDInvItens("pt", p).get(4));


            return inv;

        } else if (idioma.equals("eng")) {

            Inventory inv = Bukkit.createInventory(p, 27, "&6Commands".replace("&", "§"));

            inv.setItem(26, getCMDInvItens("eng", p).get(0));
            inv.setItem(11, getCMDInvItens("eng", p).get(1));
            inv.setItem(12, getCMDInvItens("eng", p).get(2));
            inv.setItem(14, getCMDInvItens("eng", p).get(3));
            inv.setItem(15, getCMDInvItens("eng", p).get(4));
            return inv;


        }

        return null;
    }

    List<ItemStack> getCMDInvItens(String idioma, Player p) {
        List<ItemStack> items = new ArrayList<>();
        if (idioma.equals("eng")) {
            List<String> nada = new ArrayList<>();

            ItemStack voltar = getItemStack(Material.BARRIER, "&c&lBack", 1, nada, false, p);
            items.add(voltar);

            List<String> Llore = new ArrayList<>();
            Llore.add("&7-> &aClick to see the lobby commands!");

            ItemStack Litem = getItemStack(Material.COMPASS, "&c&lLobby Commands", 1, Llore, true, p);

            items.add(Litem);

            List<String> Modlore = new ArrayList<>();

            Modlore.add("&7-> &aClick to see the moderation, commands.");

            ItemStack Moditem = getItemStack(Material.BARRIER, "&c&lModeration commands", 1, Modlore, true, p);

            items.add(Moditem);

            List<String> Plore = new ArrayList<>();

            boolean isNV = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");

            Material type = Material.matchMaterial(isNV ? "PLAYER_HEAD" : "SKULL_ITEM");

            Plore.add("&7-> &aClick to see the Player commands");

            ItemStack Pitem = getItemStack(type, "&c&lPlayer commands", 1, Plore, true, p);

            items.add(Pitem);


            List<String> Rlore = new ArrayList<>();

            Rlore.add("&fReload the configuration files");
            Rlore.add("&fof the plugin");
            Rlore.add("");
            Rlore.add("&axg7lobby.command.reload");
            boolean isNVR = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("REPEATER");

            Material Dtype = Material.matchMaterial(isNVR ? "REPEATER" : "DIODE");

            ItemStack Ritem = getItemStack(Dtype, "&e/&b7lreloadconfig", 1, Rlore, false, p);

            items.add(Ritem);
            
        } else if (idioma.equals("pt")) {
            List<String> nada = new ArrayList<>();

            ItemStack voltar = getItemStack(Material.BARRIER, "&c&lVoltar", 1, nada, false, p);
            items.add(voltar);

            List<String> Llore = new ArrayList<>();
            Llore.add("&7-> &aClique para ver os comandos de lobby!");

            ItemStack Litem = getItemStack(Material.COMPASS, "&c&lComados de lobby", 1, Llore, true, p);

            items.add(Litem);

            List<String> Modlore = new ArrayList<>();

            Modlore.add("&7-> &aClique para ver os comandos de moderação.");

            ItemStack Moditem = getItemStack(Material.BARRIER, "&c&lComandos de moderação!", 1, Modlore, true, p);

            items.add(Moditem);

            List<String> Plore = new ArrayList<>();
            boolean isNV = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");

            Material type = Material.matchMaterial(isNV ? "PLAYER_HEAD" : "SKULL_ITEM");

            Plore.add("&7-> &aClique para ver os comandos de jogador");

            ItemStack Pitem = getItemStack(type, "&c&lComandos de Jogador", 1, Plore, true, p);

            items.add(Pitem);


            List<String> Rlore = new ArrayList<>();

            Rlore.add("&fRecarrega as configurações");
            Rlore.add("&fdo plugin");
            Rlore.add("");
            Rlore.add("&axg7lobby.command.reload");

            boolean isNVR = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("REPEATER");

            Material Dtype = Material.matchMaterial(isNVR ? "REPEATER" : "DIODE");

            ItemStack Ritem = getItemStack(Dtype, "&e/&b7lreloadconfig", 1, Rlore, false, p);

            items.add(Ritem);



        }
         return items;
    }

    Inventory getLobbyIventory(String idioma, Player p) {
        if (idioma.equals("eng")) {

            /*
        00, 01, 02, 03, 04, 05, 06, 07, 08
        09, 10, 11, 12, 13, 14, 15, 16, 17
        18, 19, 20, 21, 22, 23, 24, 25, 26
        27, 28, 29, 30, 31, 32, 33, 34, 35
        36, 37, 38, 39, 40, 41, 42, 43, 44
        45, 46, 47, 48, 49, 52, 51, 52, 53
         */
            Inventory inv = Bukkit.createInventory(p, 27, "&6Commands - Lobby".replace("&", "§"));

            inv.setItem(26, getLobbyInvItens("eng", p).get(0));
            inv.setItem(12, getLobbyInvItens("eng", p).get(1));
            inv.setItem(14, getLobbyInvItens("eng", p).get(2));


            return inv;

        } else if (idioma.equals("pt")) {

            Inventory inv = Bukkit.createInventory(p, 27, "&6Comandos - Lobby".replace("&", "§"));

            inv.setItem(26, getLobbyInvItens("pt", p).get(0));
            inv.setItem(12, getLobbyInvItens("pt", p).get(1));
            inv.setItem(14, getLobbyInvItens("pt", p).get(2));


            return inv;
        }
        return null;
    }

    List<ItemStack> getLobbyInvItens(String idioma, Player p) {
        List<ItemStack> itens = new ArrayList<>();
        if (idioma.equals("eng")) {


            List<String> nada = new ArrayList<>();

            ItemStack voltar = getItemStack(Material.BARRIER, "&c&lBack", 1, nada, false, p);

            itens.add(voltar);

            List<String> SetLore = new ArrayList<>();

            SetLore.add("&fPut the lobby position");
            if (cm.getData().getString("lobby.world") != null) {
                SetLore.add("&aCurrent lobby position:");
                SetLore.add("");
                SetLore.add("&bWorld: " + "&a" + cm.getData().getString("lobby.world"));
                SetLore.add("&bX: " + "&a" + cm.getData().getDouble("lobby.x"));
                SetLore.add("&bY: " + "&a" + cm.getData().getDouble("lobby.y"));
                SetLore.add("&bZ: " + "&a" + cm.getData().getDouble("lobby.z"));

                SetLore.add("&aClick here to replace the lobby position.");
                SetLore.add("&axg7lobby.command.setlobby");

                ItemStack Setlobby = getItemStack(Material.COMPASS, "&e/&asetlobby", 1, SetLore, false, p);

                itens.add(Setlobby);

            } else {
                SetLore.add("&cThe lobby has not been positioned!");
                SetLore.add("&aClick here to position the lobby ");
                SetLore.add("&axg7lobby.command.lobby");
                ItemStack Setlobby = getItemStack(Material.COMPASS, "&e/&csetlobby", 1, SetLore, false, p);

                itens.add(Setlobby);
            }

            List<String> Llore = new ArrayList<>();
            Llore.add("&fTeleport to lobby");
            if (cm.getData().getString("lobby.world") != null) {
                Llore.add("&aCurrent position of the Lobby:");
                Llore.add("");
                Llore.add("&bWorld: " + "&a" + cm.getData().getString("lobby.world"));
                Llore.add("&bX: " + "&a" + cm.getData().getDouble("lobby.x"));
                Llore.add("&bY: " + "&a" + cm.getData().getDouble("lobby.y"));
                Llore.add("&bZ: " + "&a" + cm.getData().getDouble("lobby.z"));

                Llore.add("&aClick to teleporta to the lobby");
                Llore.add("&f&iNo need permissions.");

                ItemStack Lobby = getItemStack(Material.COMPASS, "&e/&alobby", 1, Llore, false, p);

                itens.add(Lobby);

            } else {
                Llore.add("&cThe lobby has not been positioned!");
                Llore.add("&aClick here to position the lobby ");
                Llore.add("&f&iNo need permissions.");
                ItemStack Lobby = getItemStack(Material.COMPASS, "&e/&clobby", 1, Llore, false, p);

                itens.add(Lobby);
            }

        } else if (idioma.equals("pt")) {

            List<String> nada = new ArrayList<>();

            ItemStack voltar = getItemStack(Material.BARRIER, "&c&lVoltar", 1, nada, false, p);

            itens.add(voltar);

            List<String> SetLore = new ArrayList<>();

            SetLore.add("&fColoca o lobby");
            if (cm.getData().getString("lobby.world") != null) {
                SetLore.add("&aPosicionamento do lobby atual:");
                SetLore.add("");
                SetLore.add("&bMundo: " + "&a" + cm.getData().getString("lobby.world"));
                SetLore.add("&bX: " + "&a" + cm.getData().getDouble("lobby.x"));
                SetLore.add("&bY: " + "&a" + cm.getData().getDouble("lobby.y"));
                SetLore.add("&bZ: " + "&a" + cm.getData().getDouble("lobby.z"));

                SetLore.add("&aClique aqui para recolocar o lobby");
                SetLore.add("&axg7lobby.command.setlobby");

                ItemStack Setlobby = getItemStack(Material.COMPASS, "&e/&asetlobby", 1, SetLore, false, p);

                        itens.add(Setlobby);

            } else {
                SetLore.add("&cO lobby não foi posicionado!");
                SetLore.add("&aClique aqui para colocar o lobby!");
                SetLore.add("&axg7lobby.command.lobby");
                ItemStack Setlobby = getItemStack(Material.COMPASS, "&e/&csetlobby", 1, SetLore, false, p);

                        itens.add(Setlobby);
            }

            List<String> Llore = new ArrayList<>();

            Llore.add("&fTeletransporta para o lobby");
            if (cm.getData().getString("lobby.world") != null) {
                Llore.add("&aPosicionamento do lobby atual:");
                Llore.add("");
                Llore.add("&bMundo: " + "&a" + cm.getData().getString("lobby.world"));
                Llore.add("&bX: " + "&a" + cm.getData().getDouble("lobby.x"));
                Llore.add("&bY: " + "&a" + cm.getData().getDouble("lobby.y"));
                Llore.add("&bZ: " + "&a" + cm.getData().getDouble("lobby.z"));

                Llore.add("&aClique aqui para teletransportar par ao lobby");
                Llore.add("&f&iNão precisa de permissões.");

                ItemStack Lobby = getItemStack(Material.COMPASS, "&e/&alobby", 1, Llore, false, p);

                itens.add(Lobby);

            } else {
                Llore.add("&cO lobby não foi posicionado!");
                Llore.add("&aClique aqui para colocar o lobby!");
                Llore.add("&f&iNão precisa de permissões.");
                ItemStack Lobby = getItemStack(Material.COMPASS, "&e/&clobby", 1, Llore, false, p);

                itens.add(Lobby);
            }

        }
        return itens;
    }









    Inventory getModInventory(String idioma, Player p) {
        if (idioma.equals("eng")) {

            /*
        00, 01, 02, 03, 04, 05, 06, 07, 08
        09, 10, 11, 12, 13, 14, 15, 16, 17
        18, 19, 20, 21, 22, 23, 24, 25, 26
        27, 28, 29, 30, 31, 32, 33, 34, 35
        36, 37, 38, 39, 40, 41, 42, 43, 44
        45, 46, 47, 48, 49, 52, 51, 52, 53
         */

            Inventory inv = Bukkit.createInventory(p, 45, "&6Commands - Moderation".replace("&", "§"));

            inv.setItem(44, getModInvItens("eng", p).get(0));
            inv.setItem(0, getModInvItens("eng", p).get(1));
            inv.setItem(11, getModInvItens("eng", p).get(2));
            inv.setItem(12, getModInvItens("eng", p).get(3));
            inv.setItem(14, getModInvItens("eng", p).get(4));
            inv.setItem(15, getModInvItens("eng", p).get(5));
            inv.setItem(30, getModInvItens("eng", p).get(6));
            inv.setItem(31, getModInvItens("eng", p).get(7));
            inv.setItem(32, getModInvItens("eng", p).get(8));
            inv.setItem(13, getModInvItens("eng", p).get(9));



            return inv;

        } else if (idioma.equals("pt")) {

            Inventory inv = Bukkit.createInventory(p, 45, "&6Comandos - Moderação".replace("&", "§"));

            inv.setItem(44, getModInvItens("pt", p).get(0));
            inv.setItem(0, getModInvItens("pt", p).get(1));
            inv.setItem(11, getModInvItens("pt", p).get(2));
            inv.setItem(12, getModInvItens("pt", p).get(3));
            inv.setItem(14, getModInvItens("pt", p).get(4));
            inv.setItem(15, getModInvItens("pt", p).get(5));
            inv.setItem(30, getModInvItens("pt", p).get(6));
            inv.setItem(31, getModInvItens("pt", p).get(7));
            inv.setItem(32, getModInvItens("pt", p).get(8));
            inv.setItem(13, getModInvItens("pt", p).get(9));



            return inv;
        }
        return null;
    }

    List<ItemStack> getModInvItens(String idioma, Player p) {
        List<ItemStack> itens = new ArrayList<>();
        if (idioma.equals("eng")) {

            List<String> nada = new ArrayList<>();

            ItemStack voltar = getItemStack(Material.BARRIER, "&c&lBack", 1, nada, false, p);

            itens.add(voltar);

            List<String> notal = new ArrayList<>();
            notal.add("&f<REQUIRED>");
            notal.add("&f[OPTIONAL]");

            ItemStack nota = getItemStack(Material.BOOK, "&6Note:", 1, notal, false, p);

            itens.add(nota);


            List<String> Warnlore = new ArrayList<>();

            Warnlore.add("&fWarn Players");
            Warnlore.add("&axg7lobby.command.warn");
            Warnlore.add("&fTo see your warn list use /warns");

            ItemStack Warn = getItemStack(Material.PAPER, "&e/&bwarn &2<Player> <Reason>", 1, Warnlore, false, p);

            itens.add(Warn);


            List<String> Kicklore = new ArrayList<>();

            Kicklore.add("&fKick Players");
            Kicklore.add("&axg7lobby.command.kick");

            ItemStack kick = getItemStack(Material.DIAMOND_SWORD, "&e/&bkick &2<Player> [Reason]", 1, Kicklore, false, p);

            itens.add(kick);

            List<String> banl = new ArrayList<>();
            banl.add("&fBan Players");
            banl.add("&axg7lobby.command.ban");

            ItemStack ban = getItemStack(Material.BARRIER, "&e/&bban &2<Player> [Reason]", 1, banl, false, p);

                    itens.add(ban);

            List<String> tempbanl = new ArrayList<>();
            tempbanl.add("&fBan Players from a period");
            tempbanl.add("&axg7lobby.command.tempban");

            boolean isNV = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("CLOCK");

            Material type = Material.matchMaterial(isNV ? "CLOCK" : "WATCH");

            ItemStack tempban = getItemStack(type, "&e/&btempban &2<Player> <Time in hours> [Reason]", 1, tempbanl, false, p);

            itens.add(tempban);





            List<String> unbanl = new ArrayList<>();
            unbanl.add("&fUnban Players");
            unbanl.add("&axg7lobby.command.unban");

            ItemStack unban = getItemStack(Material.BONE, "&e/&bunban &2<Player>", 1, unbanl, false, p);

            itens.add(unban);


            List<String> mutel = new ArrayList<>();
            mutel.add("&fMute Players");
            mutel.add("&axg7lobby.command.mute");

            boolean FisNV = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("FIRE_CHARGE");

            Material Ftype = Material.matchMaterial(FisNV ? "FIRE_CHARGE" : "FIREBALL");

            ItemStack mute = getItemStack(Ftype, "&e/&bmute &2<Jogador>" , 1, mutel, false, p);

            itens.add(mute);


            List<String> unmutel = new ArrayList<>();
            unmutel.add("&fRemove the Player from muteds list");
            unmutel.add("&axg7lobby.command.unmute");

            ItemStack unmute = getItemStack(Material.WATER_BUCKET, "&e/&bunmute &2<Jogador>" , 1, unmutel, false, p);

            itens.add(unmute);

            List<String> lockcl = new ArrayList<>();
            lockcl.add("&fLocks the chat");
            lockcl.add("&axg7lobby.command.lockchat");

            ItemStack lockc = getItemStack(Material.GOLD_INGOT, "&e/&blockchat" , 1, lockcl, false, p);

            itens.add(lockc);


        } else if (idioma.equals("pt")) {

            List<String> nada = new ArrayList<>();

            ItemStack voltar = getItemStack(Material.BARRIER, "&c&lVoltar", 1, nada, false, p);

            itens.add(voltar);

            List<String> notal = new ArrayList<>();
            notal.add("&f<OBRIGATÓRIO>");
            notal.add("&f[NÃO OBRIGATÓRIO]");

            ItemStack nota = getItemStack(Material.BOOK, "&6Nota:", 1, notal, false, p);

            itens.add(nota);


            List<String> Warnlore = new ArrayList<>();

            Warnlore.add("&fAvisa jogadores");
            Warnlore.add("&axg7lobby.command.warn");
            Warnlore.add("&fPara ver sua lista de warns use /warns");

            ItemStack Warn = getItemStack(Material.PAPER, "&e/&bwarn &2<Jogador> <Motivo>", 1, Warnlore, false, p);

            itens.add(Warn);


            List<String> Kicklore = new ArrayList<>();

            Kicklore.add("&fExpulsa jogadores");
            Kicklore.add("&axg7lobby.command.kick");

            ItemStack kick = getItemStack(Material.DIAMOND_SWORD, "&e/&bkick &2<Jogador> [Motivo]", 1, Kicklore, false, p);
            itens.add(kick);

            List<String> banl = new ArrayList<>();
            banl.add("&fBane jogadores");
            banl.add("&axg7lobby.command.ban");

            ItemStack ban = getItemStack(Material.BARRIER, "&e/&bban &2<Jogador> [Motivo]", 1, banl, false, p);

            itens.add(ban);

            List<String> tempbanl = new ArrayList<>();
            tempbanl.add("&fBane jogadores por um período de tempo");
            tempbanl.add("&axg7lobby.command.tempban");

            boolean isNV = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("CLOCK");

            Material type = Material.matchMaterial(isNV ? "CLOCK" : "WATCH");

            ItemStack tempban = getItemStack(type, "&e/&btempban &2<Jogador> <Tempo em horas> [Motivo]", 1, tempbanl, false, p);

            itens.add(tempban);





            List<String> unbanl = new ArrayList<>();
            unbanl.add("&fDesbane jogadores");
            unbanl.add("&axg7lobby.command.unban");

            ItemStack unban = getItemStack(Material.BONE, "&e/&bunban &2<Jogador>", 1, unbanl, false, p);

            itens.add(unban);


            List<String> mutel = new ArrayList<>();
            mutel.add("&fSilencia jogadores");
            mutel.add("&axg7lobby.command.mute");

            boolean FisNV = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("FIRE_CHARGE");

            Material Ftype = Material.matchMaterial(FisNV ? "FIRE_CHARGE" : "FIREBALL");

            ItemStack mute = getItemStack(Ftype, "&e/&bmute &2<Jogador>" , 1, mutel, false, p);

            itens.add(mute);


            List<String> unmutel = new ArrayList<>();
            unmutel.add("&Retira o jogador da lista de silenciados");
            unmutel.add("&axg7lobby.command.unmute");

            ItemStack unmute = getItemStack(Material.WATER_BUCKET, "&e/&bunmute &2<Jogador> ", 1, unmutel, false, p);

            itens.add(unmute);

            List<String> lockcl = new ArrayList<>();
            lockcl.add("&fFecha o chat");
            lockcl.add("&axg7lobby.command.lockchat");

            ItemStack lockc = getItemStack(Material.GOLD_INGOT, "&e/&blockchat" , 1, lockcl, false, p);

            itens.add(lockc);

        }
        return itens;
    }





    Inventory getPlayersCMDInv(String idioma, Player p) {
        if (idioma.equals("eng")) {

            /*
            00, 01, 02, 03, 04, 05, 06, 07, 08
            09, 10, 11, 12, 13, 14, 15, 16, 17
            18, 19, 20, 21, 22, 23, 24, 25, 26
            27, 28, 29, 30, 31, 32, 33, 34, 35
            36, 37, 38, 39, 40, 41, 42, 43, 44
            45, 46, 47, 48, 49, 52, 51, 52, 53
            */

            Inventory inv = Bukkit.createInventory(p, 45, "&6Commands - Players".replace("&", "§"));

            inv.setItem(44, getPlayersCMDInvItens("eng", p).get(0));
            inv.setItem(12, getPlayersCMDInvItens("eng", p).get(1));
            inv.setItem(14, getPlayersCMDInvItens("eng", p).get(2));
            inv.setItem(29, getPlayersCMDInvItens("eng", p).get(3));
            inv.setItem(30, getPlayersCMDInvItens("eng", p).get(4));
            inv.setItem(32, getPlayersCMDInvItens("eng", p).get(5));
            inv.setItem(33, getPlayersCMDInvItens("eng", p).get(6));



            return inv;

        } else if (idioma.equals("pt")) {

            Inventory inv = Bukkit.createInventory(p, 45, "&6Comandos - Jogador".replace("&", "§"));

            inv.setItem(44, getPlayersCMDInvItens("pt", p).get(0));
            inv.setItem(12, getPlayersCMDInvItens("pt", p).get(1));
            inv.setItem(14, getPlayersCMDInvItens("pt", p).get(2));
            inv.setItem(29, getPlayersCMDInvItens("pt", p).get(3));
            inv.setItem(30, getPlayersCMDInvItens("pt", p).get(4));
            inv.setItem(32, getPlayersCMDInvItens("pt", p).get(5));
            inv.setItem(33, getPlayersCMDInvItens("pt", p).get(6));



            return inv;

        }
        return null;
    }

    List<ItemStack> getPlayersCMDInvItens(String idioma, Player p) {
        List<ItemStack> itens = new ArrayList<>();
        if (idioma.equals("eng")) {

            List<String> nada = new ArrayList<>();

            ItemStack voltar = getItemStack(Material.BARRIER, "&c&lBack", 1, nada, false, p);

            itens.add(voltar);

            List<String> vanishl = new ArrayList<>();
            vanishl.add("&fHide players");
            vanishl.add("&axg7lobby.command.vanish");

            ItemStack vanish = getItemStack(Material.BARRIER, "&e/&bvanish" , 1, vanishl, false, p);
            itens.add(vanish);


            List<String> flyl = new ArrayList<>();
            flyl.add("&fMake players fly");
            flyl.add("&axg7lobby.command.fly");
            flyl.add("&f&iTo make other players fly");
            flyl.add("&axg7lobby.command.flyother");

            ItemStack fly = getItemStack(Material.FEATHER, "&e/&bfly [Player]" , 1, flyl, false, p);

            itens.add(fly);



            List<String> sl = new ArrayList<>();
            sl.add("&fChange the game mode to survival");
            sl.add("&axg7lobby.gamemode.survival");
            sl.add("&f&iTo change from other players");
            sl.add("&axg7lobby.gamemode.others");

            ItemStack s = getItemStack(Material.IRON_SWORD, "&e/&bgms [Player]" , 1, sl, false, p);

            itens.add(s);



            List<String> cl = new ArrayList<>();
            cl.add("&fChange the game mode to creative");
            cl.add("&axg7lobby.gamemode.creative");
            cl.add("&f&iTo change from other players");
            cl.add("&axg7lobby.gamemode.others");

            boolean isNV = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("GRASS_BLOCK");

            Material Gtype = Material.matchMaterial(isNV ? "GRASS_BLOCK" : "GRASS");

            ItemStack c = getItemStack(Gtype, "&e/&bgmc [Player]" , 1, cl, false, p);
            itens.add(c);

            List<String> al = new ArrayList<>();
            al.add("&fChange the game mode to adventure");
            al.add("&axg7lobby.gamemode.adventure");
            al.add("&f&iTo change from other players");
            al.add("&axg7lobby.gamemode.others");

            ItemStack a = getItemStack(Material.MAP , "&e/&bgma [Player]" , 1, al, false, p);

            itens.add(a);


            List<String> spl = new ArrayList<>();
            spl.add("&fChange the game mode to spectator");
            spl.add("&axg7lobby.gamemode.spectator");
            spl.add("&f&iTo change from other players");
            spl.add("&axg7lobby.gamemode.others");

            ItemStack sp = getItemStack(Material.ENDER_PEARL, "&e/&bgmsp [Player]" , 1, spl, false, p);

            itens.add(sp);


        } else if (idioma.equals("pt")) {

            List<String> nada = new ArrayList<>();

            ItemStack voltar = getItemStack(Material.BARRIER, "&c&lVoltar", 1, nada, false, p);

            itens.add(voltar);

            List<String> vanishl = new ArrayList<>();
            vanishl.add("&fEsconde os jogadores");
            vanishl.add("&axg7lobby.command.vanish");

            ItemStack vanish = getItemStack(Material.BARRIER, "&e/&bvanish" , 1, vanishl, false, p);

            itens.add(vanish);


            List<String> flyl = new ArrayList<>();
            flyl.add("&fFaz o jogador voar ");
            flyl.add("&axg7lobby.command.fly");
            flyl.add("&f&iPara fazer outros jogadores voarem");
            flyl.add("&axg7lobby.command.flyother");

            ItemStack fly = getItemStack(Material.FEATHER, "&e/&bfly [Jogador]" , 1, flyl, false, p);

            itens.add(fly);



            List<String> sl = new ArrayList<>();
            sl.add("&fMuda o modo de jogo para sobrevivência");
            sl.add("&axg7lobby.gamemode.survival");
            sl.add("&f&iPara mudar de outros jogadores");
            sl.add("&axg7lobby.gamemode.others");

            ItemStack s = getItemStack(Material.IRON_SWORD, "&e/&bgms [Jogador]" , 1, sl, false, p);

                    itens.add(s);



            List<String> cl = new ArrayList<>();
            cl.add("&fMuda o modo de jogo para criativo");
            cl.add("&axg7lobby.gamemode.creative");
            cl.add("&f&iPara mudar de outros jogadores");
            cl.add("&axg7lobby.gamemode.others");

            boolean isNV = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("GRASS_BLOCK");

            Material Gtype = Material.matchMaterial(isNV ? "GRASS_BLOCK" : "GRASS");

            ItemStack c = getItemStack(Gtype, "&e/&bgmc [Jogador]" , 1, cl, false, p);
            itens.add(c);

            List<String> al = new ArrayList<>();
            al.add("&fMuda o modo de jogo para aventura");
            al.add("&axg7lobby.gamemode.adventure");
            al.add("&f&iPara mudar de outros jogadores");
            al.add("&axg7lobby.gamemode.others");

            ItemStack a = getItemStack(Material.MAP, "&e/&bgma [Jogador]" , 1, al, false, p);

                    itens.add(a);


            List<String> spl = new ArrayList<>();
            spl.add("&fMuda o modo de jogo para espectador");
            spl.add("&axg7lobby.gamemode.spectator");
            spl.add("&f&iPara mudar de outros jogadores");
            spl.add("&axg7lobby.gamemode.others");

            ItemStack sp = getItemStack(Material.ENDER_PEARL, "&e/&bgmsp [Jogador]" , 1, spl, false, p);

            itens.add(sp);
        }

        return itens;

    }

    Inventory getActionsInventory(String idioma, Player p) {

        if (idioma.equals("eng")) {

            /*
            00, 01, 02, 03, 04, 05, 06, 07, 08
            09, 10, 11, 12, 13, 14, 15, 16, 17
            18, 19, 20, 21, 22, 23, 24, 25, 26
            27, 28, 29, 30, 31, 32, 33, 34, 35
            36, 37, 38, 39, 40, 41, 42, 43, 44
            45, 46, 47, 48, 49, 52, 51, 52, 53
            */

            //14 itens

            Inventory inv = Bukkit.createInventory(p, 45, "&6Actions".replace("&", "§"));

            inv.setItem(44, getActionsInvItens("eng", p).get(0));
            inv.setItem(10, getActionsInvItens("eng", p).get(1));
            inv.setItem(11, getActionsInvItens("eng", p).get(2));
            inv.setItem(12, getActionsInvItens("eng", p).get(3));
            inv.setItem(13, getActionsInvItens("eng", p).get(4));
            inv.setItem(14, getActionsInvItens("eng", p).get(5));
            inv.setItem(15, getActionsInvItens("eng", p).get(6));
            inv.setItem(16, getActionsInvItens("eng", p).get(7));
            inv.setItem(19, getActionsInvItens("eng", p).get(8));
            inv.setItem(20, getActionsInvItens("eng", p).get(9));
            inv.setItem(21, getActionsInvItens("eng", p).get(10));
            inv.setItem(22, getActionsInvItens("eng", p).get(11));
            inv.setItem(23, getActionsInvItens("eng", p).get(12));
            inv.setItem(24, getActionsInvItens("eng", p).get(13));
            inv.setItem(25, getActionsInvItens("eng", p).get(14));

            return inv;

        } else if (idioma.equals("pt")) {

            Inventory inv = Bukkit.createInventory(p, 45, "&6Ações".replace("&", "§"));

            inv.setItem(44, getActionsInvItens("pt", p).get(0));
            inv.setItem(10, getActionsInvItens("pt", p).get(1));
            inv.setItem(11, getActionsInvItens("pt", p).get(2));
            inv.setItem(12, getActionsInvItens("pt", p).get(3));
            inv.setItem(13, getActionsInvItens("pt", p).get(4));
            inv.setItem(14, getActionsInvItens("pt", p).get(5));
            inv.setItem(15, getActionsInvItens("pt", p).get(6));
            inv.setItem(16, getActionsInvItens("pt", p).get(7));
            inv.setItem(19, getActionsInvItens("pt", p).get(8));
            inv.setItem(20, getActionsInvItens("pt", p).get(9));
            inv.setItem(21, getActionsInvItens("pt", p).get(10));
            inv.setItem(22, getActionsInvItens("pt", p).get(11));
            inv.setItem(23, getActionsInvItens("pt", p).get(12));
            inv.setItem(24, getActionsInvItens("pt", p).get(13));
            inv.setItem(25, getActionsInvItens("pt", p).get(14));

            return inv;

        }

        return null;
    }

    List<ItemStack> getActionsInvItens(String idioma, Player p) {
        List<ItemStack> itens = new ArrayList<>();

        if (idioma.equals("eng")) {
            boolean isNV = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
            Material type = Material.matchMaterial(isNV ? "PLAYER_HEAD" : "SKULL_ITEM");
            List<String> nada = new ArrayList<>();

            ItemStack voltar = getItemStack(Material.BARRIER, "&c&lBack", 1, nada, false, p);

            itens.add(voltar);

            List<String> msgl = new ArrayList<>();
            msgl.add("&fSend a message to the player");
            msgl.add("");
            msgl.add("&aUsage:");
            msgl.add("&2&i[MESSAGE] your message");

            ItemStack msg = getItemStack(Material.PAPER, "&6[MESSAGE]" , 1, msgl, false, p);

            itens.add(msg);



            List<String> til = new ArrayList<>();
            til.add("&fSend a title to the player");
            til.add("");
            til.add("&aUsage:");
            til.add("&2&i[TITLE] your titile");

            boolean PisNV = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("OAK_SIGN");

            Material Ptype = Material.matchMaterial(PisNV ? "OAK_SIGN" : "SIGN");

            ItemStack ti = getItemStack(Ptype, "&6[TITLE]" , 1, til, false, p);

            itens.add(ti);


            List<String> titesubl = new ArrayList<>();
            titesubl.add("&fSend a title and subtitle to the player");
            titesubl.add("");
            titesubl.add("&aUsage:");
            titesubl.add("&2&i[TIT&SUBTIT] your title // your subtitle");

            ItemStack tes = getItemStack(Ptype, "&6[TITESUBTIT]" , 2, titesubl, false, p);

            itens.add(tes);


            List<String> broal = new ArrayList<>();
            broal.add("&fSend a message to everybody");
            broal.add("");
            broal.add("&aUsage:");
            broal.add("&2&i[BROADCAST] you message");

            boolean FisNV = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("FIRE_CHARGE");

            Material Ftype = Material.matchMaterial(FisNV ? "FIRE_CHARGE" : "FIREBALL");


            ItemStack broad = getItemStack(Ftype, "&6[BROADCAST]" , 1, broal, false, p);

            itens.add(broad);


            List<String> cmdl = new ArrayList<>();
            cmdl.add("&fMakes the player execute a command");
            cmdl.add("");
            cmdl.add("&aUsage:");
            cmdl.add("&2&i[COMMAND] your command without /");

            boolean CisNV = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("COMMAND_BLOCK");

            Material Ctype = Material.matchMaterial(CisNV ? "COMMAND_BLOCK" : "COMMAND");

            ItemStack cmd = getItemStack(Ctype, "&6[COMMAND] ", 1, cmdl, false, p);

            itens.add(cmd);

            List<String> consolel = new ArrayList<>();
            consolel.add("&fExecute a command in console");
            consolel.add("");
            consolel.add("&aUsage:");
            consolel.add("&2&i[CONSOLE] command");

            ItemStack console = getItemStack(Ctype, "&6[CONSOLE] ", 2, consolel, false, p);

            itens.add(console);

            List<String> summonl = new ArrayList<>();
            summonl.add("&fSummon a entity in the player's local");
            summonl.add("");
            summonl.add("&aUsage:");
            summonl.add("&2&i[SUMMON] entity");

            ItemStack summon = getItemStack(Material.DRAGON_EGG, "&6[SUMMON] ", 1, summonl, false, p);

            itens.add(summon);

            List<String> soml = new ArrayList<>();
            soml.add("&fPlay a sound in the player's local");
            soml.add("");
            soml.add("&aUsage:");
            soml.add("&2&i[SOUND] (Sound), (Volume), (Pitch)");

            ItemStack som = getItemStack(Material.JUKEBOX, "&6[SOUND] ", 1, soml, false, p);

            itens.add(som);

            List<String> flyl = new ArrayList<>();
            flyl.add("&fMake the player fly");
            flyl.add("");
            flyl.add("&aUsage:");
            flyl.add("&2&i[FLY]");

            ItemStack fly = getItemStack(Material.FEATHER, "&6[FLY] ", 1, flyl, false, p);

            itens.add(fly);

            List<String> tpl = new ArrayList<>();
            tpl.add("&fTeleport the player to");
            tpl.add("&fan exact location.");
            tpl.add("");
            tpl.add("&aUsage:");
            tpl.add("&2&i[TP] <World>, <X>, <Y>, <Z>, (Yaw), (Pitch)");

            ItemStack tp = getItemStack(Material.ENDER_PEARL, "&6[TP] ", 1, tpl, false, p);

            itens.add(tp);

            List<String> openl = new ArrayList<>();
            openl.add("&fOpen a inventory from the file");
            openl.add("&aselectors.yml");
            openl.add("");
            openl.add("&aUsage:");
            openl.add("&2&i[OPEN] <ID>");

            ItemStack open = getItemStack(Material.CHEST, "&6[OPEN] ", 1, openl, false, p);

            itens.add(open);

            List<String> closel = new ArrayList<>();
            closel.add("&fClose the opened inventory");
            closel.add("");
            closel.add("&aUsage:");
            closel.add("&2&i[CLOSE]");

            ItemStack close = getItemStack(Material.DISPENSER, "&6[CLOSE] ", 1, closel, false, p);

            itens.add(close);


            List<String> playerl = new ArrayList<>();
            playerl.add("&fReturns the player from action");
            playerl.add("");
            playerl.add("&aUsage:");
            playerl.add("&2&i[YOUR ACTION] content [PLAYER]...");

            ItemStack player = getItemStack(type, "&6[PLAYER] ", 1, playerl, false, p);

            itens.add(player);

            List<String> permissaol = new ArrayList<>();
            permissaol.add("&fOnly executes if the player");
            permissaol.add("&fhas the permission that is on");
            permissaol.add("&fthe right of the action.");
            permissaol.add("");
            permissaol.add("&aUsage if have the permission:");
            permissaol.add("&2&i[YOUR ACTION] content [PERMISSION] your.permission.");
            permissaol.add("");
            permissaol.add("&aUsage if haven't the permission:");
            permissaol.add("&2&i[YOUR ACTION] content [!PERMISSION] your.permission.");

            ItemStack permissao = getItemStack(Material.BARRIER, "&6[PERMISSION] ", 1, permissaol, false, p);

            itens.add(permissao);

        } else if (idioma.equals("pt")) {
            boolean isNV = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
            Material type = Material.matchMaterial(isNV ? "PLAYER_HEAD" : "SKULL_ITEM");
            List<String> nada = new ArrayList<>();

            ItemStack voltar = getItemStack(Material.BARRIER, "&c&lVoltar", 1, nada, false, p);

            itens.add(voltar);

            List<String> msgl = new ArrayList<>();
            msgl.add("&fManda uma mensagem para o jogador");
            msgl.add("");
            msgl.add("&aModo de uso:");
            msgl.add("&2&i[MESSAGE] sua mensagem");

            ItemStack msg = getItemStack(Material.PAPER, "&6[MESSAGE]" , 1, msgl, false, p);

            itens.add(msg);



            List<String> til = new ArrayList<>();
            til.add("&fManda um título para o jogador");
            til.add("");
            til.add("&aModo de uso:");
            til.add("&2&i[TITLE] seu título");

            boolean PisNV = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("OAK_SIGN");

            Material Ptype = Material.matchMaterial(PisNV ? "OAK_SIGN" : "SIGN");

            ItemStack ti = getItemStack(Ptype, "&6[TITLE]" , 1, til, false, p);

            itens.add(ti);


            List<String> titesubl = new ArrayList<>();
            titesubl.add("&fManda um título e um subtítulo para o jogador");
            titesubl.add("");
            titesubl.add("&aModo de uso:");
            titesubl.add("&2&i[TIT&SUBTIT] seu título // seu subtítulo");



            ItemStack tes = getItemStack(Ptype, "&6[TITESUBTIT]" , 2, titesubl, false, p);

            itens.add(tes);


            List<String> broal = new ArrayList<>();
            broal.add("&fManda uma mensagem para todos");
            broal.add("");
            broal.add("&aModo de uso:");
            broal.add("&2&i[BROADCAST] sua mensagem");

            boolean FisNV = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("FIRE_CHARGE");

            Material Ftype = Material.matchMaterial(FisNV ? "FIRE_CHARGE" : "FIREBALL");


            ItemStack broad = getItemStack(Ftype, "&6[BROADCAST]" , 1, broal, false, p);

            itens.add(broad);


            List<String> cmdl = new ArrayList<>();
            cmdl.add("&fFaz o jogador executar o comando");
            cmdl.add("");
            cmdl.add("&aModo de uso:");
            cmdl.add("&2&i[COMMAND] seu comando sem /");

            boolean CisNV = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("COMMAND_BLOCK");

            Material Ctype = Material.matchMaterial(CisNV ? "COMMAND_BLOCK" : "COMMAND");

            ItemStack cmd = getItemStack(Ctype, "&6[COMMAND] ", 1, cmdl, false, p);

            itens.add(cmd);

            List<String> consolel = new ArrayList<>();
            consolel.add("&fExecuta um comando no console");
            consolel.add("");
            consolel.add("&aModo de uso:");
            consolel.add("&2&i[CONSOLE] comando");

            ItemStack console = getItemStack(Ctype, "&6[CONSOLE] ", 2, consolel, false, p);

            itens.add(console);

            List<String> summonl = new ArrayList<>();
            summonl.add("&fInvoca uma entidade no local do player");
            summonl.add("");
            summonl.add("&aModo de uso:");
            summonl.add("&2&i[SUMMON] entidade");

            ItemStack summon = getItemStack(Material.DRAGON_EGG, "&6[SUMMON] ", 1, summonl, false, p);

            itens.add(summon);

            List<String> soml = new ArrayList<>();
            soml.add("&fToca um som no local do player");
            soml.add("");
            soml.add("&aModo de uso:");
            soml.add("&2&i[SOUND] (Som), (Volume), (Pitch)");

            ItemStack som = getItemStack(Material.JUKEBOX, "&6[SOUND] ", 1, soml, false, p);

            itens.add(som);

            List<String> flyl = new ArrayList<>();
            flyl.add("&fFaz o jogador voar");
            flyl.add("");
            flyl.add("&aModo de uso:");
            flyl.add("&2&i[FLY]");

            ItemStack fly = getItemStack(Material.FEATHER, "&6[FLY] ", 1, flyl, false, p);

            itens.add(fly);

            List<String> tpl = new ArrayList<>();
            tpl.add("&fTeletransporta o jogador para");
            tpl.add("&fum local especificado.");
            tpl.add("");
            tpl.add("&aModo de uso:");
            tpl.add("&2&i[TP] <Mundo>, <X>, <Y>, <Z>, (Yaw), (Pitch)");

            ItemStack tp = getItemStack(Material.ENDER_PEARL, "&6[TP] ", 1, tpl, false, p);

            itens.add(tp);

            List<String> openl = new ArrayList<>();
            openl.add("&fAbre um inventário no arquivo");
            openl.add("&aselectors.yml");
            openl.add("");
            openl.add("&aModo de uso:");
            openl.add("&2&i[OPEN] <ID>");

            ItemStack open = getItemStack(Material.CHEST, "&6[OPEN] ", 1, openl, false, p);

            itens.add(open);

            List<String> closel = new ArrayList<>();
            closel.add("&fFecha o inventário aberto");
            closel.add("");
            closel.add("&aModo de uso:");
            closel.add("&2&i[CLOSE]");

            ItemStack close = getItemStack(Material.DISPENSER, "&6[CLOSE] ", 1, closel, false, p);

            itens.add(close);


            List<String> playerl = new ArrayList<>();
            playerl.add("&fRetorna ao jogador da ação");
            playerl.add("");
            playerl.add("&aModo de uso:");
            playerl.add("&2&i[SUA AÇÃO] conteúdo [PLAYER]...");

            ItemStack player = getItemStack(type, "&6[PLAYER] ", 1, playerl, false, p);

            itens.add(player);

            List<String> permissaol = new ArrayList<>();
            permissaol.add("&fSó executa se o jogador da ação");
            permissaol.add("&ftiver a permissão que está");
            permissaol.add("&fà direita da ação.");
            permissaol.add("");
            permissaol.add("&aModo de uso se tiver a permissão:");
            permissaol.add("&2&i[SUA AÇÃO] conteúdo [PERMISSION] sua.permissão.");
            permissaol.add("");
            permissaol.add("&aModo de uso se NÃO tiver a permissão:");
            permissaol.add("&2&i[SUA AÇÃO] conteúdo [!PERMISSION] sua.permissão.");

            ItemStack permissao = getItemStack(Material.BARRIER, "&6[PERMISSION] ", 1, permissaol, false, p);

            itens.add(permissao);
        }



        return itens;
    }




















    boolean verifInv(ItemStack item, Inventory inv) {
        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) != null && inv.getItem(i).isSimilar(item)) {
                return true;
            }
        }
        return false;
    }



    @EventHandler
    public void onInventory(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        //Principal

        if (verifInv(e.getCurrentItem(), getPrincipalInv("eng", p))) {
            e.setCancelled(true);
            if (e.getCurrentItem().isSimilar(getPrincipalInvItens("eng", p).get(1))) {

                p.closeInventory();
                p.openInventory(getActionsInventory("eng", p));

            } else if (e.getCurrentItem().isSimilar(getPrincipalInvItens("eng", p).get(2))) {

                p.closeInventory();
                p.openInventory(getCMDInv("eng", p));

            } else if (e.getCurrentItem().isSimilar(getPrincipalInvItens("eng", p).get(3))) {

                p.closeInventory();

                List<String> EJlore = new ArrayList<>();

                EJlore.add("&i&fGuide for use Hide Players");

                ItemStack EJitem = getItemStack(Material.WRITTEN_BOOK, "&eHD Guide", 1, EJlore, false, p);
                BookMeta EJmeta = (BookMeta) EJitem.getItemMeta();
                EJmeta.setTitle("&eHD Guide".replace("&", "§"));
                EJmeta.setAuthor("&bDaviXG7");
                EJmeta.addPage(
                        "Guide of how to use Hide Players\n\n" +
                                "The hide players has " + ChatColor.AQUA + "2 modes:\n" +
                                ChatColor.GREEN + "Mode enabled;\n" +
                                ChatColor.RED + "Mode disabled;\n\n" + ChatColor.RESET +
                                "On selectors.yml (Configuration file of HD) >"

                );

                EJmeta.addPage(
                        "each part that says enabled" +
                                "or disabled, will be the name of the item, " +
                                "enabled for when it is enabled and disabled " +
                                "for when it is disabled"
                );

                EJmeta.addPage(
                        "Item description\n\n" +
                                "item -> Where to place the Item's material\n\n" +
                                "name -> Item name\n\n" +
                                "lore -> Item Description\n\n" +
                                "grow -> Enchantment or not\n\n" +
                                "->"
                );
                EJmeta.addPage(
                        "amount -> amount of the item\n\n" +
                                "slot -> Inventory slot where the item is"
                );

                EJitem.setItemMeta(EJmeta);



                p.getInventory().addItem(EJitem);

                List<String> SElore = new ArrayList<>();

                SElore.add("&i&fGuia para usar Esconder jogadores");

                ItemStack SEitem = getItemStack(Material.WRITTEN_BOOK, "&eGuia SE", 1, EJlore, false, p);
                BookMeta SEmeta = (BookMeta) SEitem.getItemMeta();
                SEmeta.setTitle("&eGuia EJ".replace("&", "§"));
                SEmeta.setAuthor("&bDaviXG7");
                SEmeta.addPage(
                        "Guide on how to use Selectors\n\n" +
                                "Selectors are the items on the hotbar that when you click, do something\n\n" +
                                "To create the items you need to go to the file selectors.yml\n\n" +
                                "->"

                );

                SEmeta.addPage(
                        "In the part selectors -> Items -> and there where you create your items\n\n" +
                                "In the Items part you can create another part with any name " +
                                "and should put the following things on the next page"
                );

                SEmeta.addPage(
                        "Item description\n\n" +
                                "item -> Where to place the Item's material\n\n" +
                                "name -> Item name\n\n" +
                                "lore -> Item Description\n\n" +
                                "grow -> Enchantment or not\n\n" +
                                "->"
                );
                SEmeta.addPage(
                        "amount -> amount of the item\n\n" +
                                "slot -> Inventory slot where the item is " +
                                "actions -> All actions will be performed when the player clicks"
                );

                SEmeta.addPage(
                        "In the cooldown part is the item refresh time to be given to the player at all times"
                );

                SEitem.setItemMeta(SEmeta);

                p.getInventory().addItem(SEitem);

                List<String> INVlore = new ArrayList<>();

                INVlore.add("&i&fGuia para usar Esconder jogadores");

                ItemStack INVitem = getItemStack(Material.WRITTEN_BOOK, "&eGuia INV", 1, EJlore, false, p);
                BookMeta INVmeta = (BookMeta) INVitem.getItemMeta();
                INVmeta.setTitle("&eGuia INV".replace("&", "§"));
                INVmeta.setAuthor("&bDaviXG7");
                INVmeta.addPage(
                        "Guide on how to use Inventories\n\n" +
                                "Inventories are used as an interactive canvas for players\n\n" +
                                "To create them is very simple!\n" +
                                "Go to the other page to understand better"

                );

                INVmeta.addPage(
                        "How to create inventories\n\n" +
                                "First go to selectors.yml\n" +
                                "in the inventories part is where you can create your inventory " +
                                "with any name.\n" +
                                "Inside your inventory section " +
                                "you must create the following ->"
                );

                INVmeta.addPage(
                        "items -> Where you will create inventory items.\n\n" +
                                "in the items section, the default item serves as inventory fill " +
                                "it puts the same item for all slots except for additional items.\n\n" +
                                "->"
                );
                INVmeta.addPage(
                        "in the items part you can create each item and you must put the following things to create an item\n\n" +
                                "->"
                );

                INVmeta.addPage(
                        "Item description\n\n" +
                                "item -> Where to place the Item's material\n\n" +
                                "name -> Item name\n\n" +
                                "lore -> Item Description\n\n" +
                                "grow -> Enchantment or not\n\n" +
                                "->"
                );
                INVmeta.addPage(
                        "amount -> amount of the item\n\n" +
                                "slot -> Inventory slot where the item is\n\n" +
                                "actions -> All actions will be performed when the player clicks"
                );

                INVitem.setItemMeta(INVmeta);



                p.getInventory().addItem(INVitem);

            } else if (e.getCurrentItem().isSimilar(getPrincipalInvItens("eng", p).get(6))) {

                p.closeInventory();
                p.openInventory(getPrincipalInv("pt",p));

            } else if (e.getCurrentItem().isSimilar(getPrincipalInvItens("eng", p).get(7))) {
                p.closeInventory();
                p.openInventory(getPrincipalInv("eng",p));
                
            }

        } else if (verifInv(e.getCurrentItem(), getPrincipalInv("pt", p))) {
            e.setCancelled(true);
            if (e.getCurrentItem().isSimilar(getPrincipalInvItens("pt", p).get(1))) {
                p.closeInventory();
                p.openInventory(getActionsInventory("pt", p));

            } else if (e.getCurrentItem().isSimilar(getPrincipalInvItens("pt", p).get(2))) {
                p.closeInventory();
                p.openInventory(getCMDInv("pt", p));

            } else if (e.getCurrentItem().isSimilar(getPrincipalInvItens("pt", p).get(3))) {

                p.closeInventory();

                List<String> EJlore = new ArrayList<>();

                EJlore.add("&i&fGuia para usar Esconder jogadores");

                ItemStack EJitem = getItemStack(Material.WRITTEN_BOOK, "&eGuia EJ", 1, EJlore, false, p);
                BookMeta EJmeta = (BookMeta) EJitem.getItemMeta();
                EJmeta.setTitle("&eGuia EJ".replace("&", "§"));
                EJmeta.setAuthor("&bDaviXG7");
                EJmeta.addPage(
                        "Guia de como usar o Esconder jogadores\n\n" +
                                "O esconder jogadores tem " + ChatColor.AQUA + "2 formas:\n" +
                                ChatColor.GREEN + "Modo ativado;\n" +
                                ChatColor.RED + "Modo desativado;\n\n" + ChatColor.RESET +
                                "No selectors.yml (arquivo de configuração do EJ) >"

                );

                EJmeta.addPage(
                        "cada parte que estiver escrito enabled (ativado) " +
                                "ou disabled (desativado), vai ser o nome do item, " +
                                "enabled para quando ele estiver ativado e disabled " +
                                "para quando ele estiver desativado"
                );

                EJmeta.addPage(
                        "Descrição de item\n\n" +
                                "item -> Onde coloca-se o Item\n\n" +
                                "name -> Nome do item\n\n" +
                                "lore -> Descrição do item\n\n" +
                                "grow -> Encantamento ou não\n\n" +
                                "->"
                );
                EJmeta.addPage(
                        "amount -> quantitdade do item\n\n" +
                                "slot -> Slot do inventário que fica o item"
                );

                EJitem.setItemMeta(EJmeta);



                p.getInventory().addItem(EJitem);

                List<String> SElore = new ArrayList<>();

                SElore.add("&i&fGuia para usar Seletores");

                ItemStack SEitem = getItemStack(Material.WRITTEN_BOOK, "&eGuia SE", 1, EJlore, false, p);
                BookMeta SEmeta = (BookMeta) SEitem.getItemMeta();
                SEmeta.setTitle("&eGuia EJ".replace("&", "§"));
                SEmeta.setAuthor("&bDaviXG7");
                SEmeta.addPage(
                        "Guia de como usar os Seletores\n\n" +
                                "Os seletores são os itens da hotbar que quando você clica, executa algo\n\n" +
                                "Para criar os itens você precisa ir no arquivo seletores.yml\n\n" +
                                "->"

                );

                SEmeta.addPage(
                        "Na parte selectors -> Itens -> e ali onde você cria seus itens\n\n" +
                                "Na parte Itens você pode criar uma outra parte com qualquer nome " +
                                "e deve colocar as seguintes coisas na próxima página"
                );

                SEmeta.addPage(
                        "Descrição de item\n\n" +
                                "item -> Onde coloca-se o Item\n\n" +
                                "name -> Nome do item\n\n" +
                                "lore -> Descrição do item\n\n" +
                                "grow -> Encantamento ou não\n\n" +
                                "->"
                );
                SEmeta.addPage(
                        "amount -> quantitdade do item\n\n" +
                                "slot -> Slot do inventário que fica o item\n\n" +
                                "actions -> Será executado todas as ações quando o jogador clicar"
                );

                SEmeta.addPage(
                        "Na parte cooldown é o tempo de atualização do item para ser dado ao jogador a todo o tempo"
                );

                SEitem.setItemMeta(SEmeta);

                p.getInventory().addItem(SEitem);

                List<String> INVlore = new ArrayList<>();

                INVlore.add("&i&fGuia para usar Inventários");

                ItemStack INVitem = getItemStack(Material.WRITTEN_BOOK, "&eGuia INV", 1, EJlore, false, p);
                BookMeta INVmeta = (BookMeta) INVitem.getItemMeta();
                INVmeta.setTitle("&eGuia INV".replace("&", "§"));
                INVmeta.setAuthor("&bDaviXG7");
                INVmeta.addPage(
                        "Guia de como usar os Inventários\n\n" +
                                "Os inventários são usados como uma tela interagível para os jogadores\n\n" +
                                "Para criar eles é muito simples!\n" +
                                "Vá para a outra página para entender melhor"

                );

                INVmeta.addPage(
                        "Como criar inventários\n\n" +
                                "Primeiro vá em seletores.yml\n" +
                                "na parte inventories é onde você pode criar seu inventário " +
                                "com qualquer nome.\n" +
                                "Dentro da sua seção de inventários " +
                                "você deve criar o seguinte ->"
                );

                INVmeta.addPage(
                        "lines -> quantas linhas de 9 slots terá seu inventário\n\n" +
                                "name -> nome que aparecerá do inventário\n\n" +
                                "id -> o que faz abrir o inventário com comando (/gui) ou com ações (número)\n\n" +
                                "->"
                );

                INVmeta.addPage(
                        "items -> Onde você vai criar os itens do inventário.\n\n" +
                                "na seção itens, o item default serve como preenchimento do inventário " +
                                "ele coloca para todos os slots exeto os dos itens adicionais o mesmo item.\n\n" +
                                "->"
                );
                INVmeta.addPage(
                        "na parte items você pode criar cada item e deve colocar as seguintes coisas para se criar um item\n\n" +
                                "->"
                );

                INVmeta.addPage(
                        "Descrição de item\n\n" +
                                "item -> Onde coloca-se o Item\n\n" +
                                "name -> Nome do item\n\n" +
                                "lore -> Descrição do item\n\n" +
                                "grow -> Encantamento ou não\n\n" +
                                "->"
                );
                INVmeta.addPage(
                        "amount -> quantitdade do item\n\n" +
                                "slot -> Slot do inventário que fica o item\n\n" +
                                "actions -> Será executado todas as ações quando o jogador clicar"
                );

                INVitem.setItemMeta(INVmeta);



                p.getInventory().addItem(INVitem);



            } else if (e.getCurrentItem().isSimilar(getPrincipalInvItens("pt", p).get(6))) {

                p.closeInventory();
                p.openInventory(getPrincipalInv("pt",p));

            } else if (e.getCurrentItem().isSimilar(getPrincipalInvItens("pt", p).get(7))) {
                p.closeInventory();
                p.openInventory(getPrincipalInv("eng",p));

            }


        //Comandos
        } else if (verifInv(e.getCurrentItem(), getCMDInv("eng", p))) {
            e.setCancelled(true);
            if (e.getCurrentItem().isSimilar(getCMDInvItens("eng", p).get(0))) {

                p.closeInventory();
                p.openInventory(getPrincipalInv("eng",p));

            } else if (e.getCurrentItem().isSimilar(getCMDInvItens("eng", p).get(1))) {
                p.closeInventory();
                p.openInventory(getLobbyIventory("eng", p));

            } else if (e.getCurrentItem().isSimilar(getCMDInvItens("eng", p).get(2))) {
                p.closeInventory();
                p.openInventory(getModInventory("eng", p));

            } else if (e.getCurrentItem().isSimilar(getCMDInvItens("eng", p).get(3))) {
                p.closeInventory();
                p.openInventory(getPlayersCMDInv("eng", p));
            }

        } else if (verifInv(e.getCurrentItem(), getCMDInv("pt", p))) {
            e.setCancelled(true);
            if (e.getCurrentItem().isSimilar(getCMDInvItens("pt", p).get(0))) {

                p.closeInventory();
                p.openInventory(getPrincipalInv("pt",p));

            } else if (e.getCurrentItem().isSimilar(getCMDInvItens("pt", p).get(1))) {
                p.closeInventory();
                p.openInventory(getLobbyIventory("pt", p));

            } else if (e.getCurrentItem().isSimilar(getCMDInvItens("pt", p).get(2))) {
                p.closeInventory();
                p.openInventory(getModInventory("pt", p));

            } else if (e.getCurrentItem().isSimilar(getCMDInvItens("pt", p).get(3))) {

                p.closeInventory();
                p.openInventory(getPlayersCMDInv("pt", p));

            }

            //Comandos lobby
        } else if (verifInv(e.getCurrentItem(), getLobbyIventory("eng", p))) {
            e.setCancelled(true);
            if (e.getCurrentItem().isSimilar(getLobbyInvItens("eng", p).get(0))) {
                p.closeInventory();
                p.openInventory(getCMDInv("eng", p));

            } else if (e.getCurrentItem().isSimilar(getLobbyInvItens("eng",p).get(1))) {

                if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
                    lobbyC.set("lobby.world", p.getWorld().getName());
                    lobbyC.set("lobby.x", p.getLocation().getX());
                    lobbyC.set("lobby.y", p.getLocation().getY());
                    lobbyC.set("lobby.z", p.getLocation().getZ());
                    lobbyC.set("lobby.yaw", p.getLocation().getYaw());
                    lobbyC.set("lobby.pitch", p.getLocation().getPitch());


                    try {
                        lobbyC.save(lobbyF);
                    } catch (IOException ex) {
                        p.sendMessage(ChatColor.RED + "Cannot save the lobby information's");
                        throw new RuntimeException(ex);
                    }

                    p.sendMessage(ChatColor.GRAY + "The lobby was been configured in coordinate "
                            + ChatColor.GREEN + cm.getData().getInt("lobby.x") + ", "
                            + cm.getData().getInt("lobby.y") + ", "
                            + cm.getData().getInt("lobby.z") + ", " + ChatColor.GRAY + "on world "
                            + ChatColor.GREEN + cm.getData().get("lobby.world") + ".");

                    p.closeInventory();
                    p.openInventory(getLobbyIventory("eng", p));

                } else {
                    va.mandarMensagem(prefix + ChatColor.RED + "You cannot save the lobby in a disable world", p);
                }


            } else if (e.getCurrentItem().isSimilar(getLobbyInvItens("eng",p).get(2))) {

                if (cm.getData().getString("lobby.world") == null) {
                    if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
                        lobbyC.set("lobby.world", p.getWorld().getName());
                        lobbyC.set("lobby.x", p.getLocation().getX());
                        lobbyC.set("lobby.y", p.getLocation().getY());
                        lobbyC.set("lobby.z", p.getLocation().getZ());
                        lobbyC.set("lobby.yaw", p.getLocation().getYaw());
                        lobbyC.set("lobby.pitch", p.getLocation().getPitch());


                        try {
                            lobbyC.save(lobbyF);
                        } catch (IOException ex) {
                            p.sendMessage(ChatColor.RED + "Cannot save the lobby information's");
                            throw new RuntimeException(ex);
                        }

                        p.sendMessage(ChatColor.GRAY + "The lobby was been configured in coordinate"
                                + ChatColor.GREEN + cm.getData().getInt("lobby.x") + ", "
                                + cm.getData().getInt("lobby.y") + ", "
                                + cm.getData().getInt("lobby.z") + ", " + ChatColor.GRAY + "on world "
                                + ChatColor.GREEN + cm.getData().get("lobby.world") + ".");

                        String lobbyWN = cm.getData().getString("lobby.world");
                        if (lobbyWN != null) {
                            World w = Bukkit.getWorld(lobbyWN);
                            p.teleport(new Location(
                                    w,
                                    cm.getData().getDouble("lobby.x"),
                                    cm.getData().getDouble("lobby.y"),
                                    cm.getData().getDouble("lobby.z"),
                                    (float) cm.getData().getDouble("lobby.yaw"),
                                    (float) cm.getData().getDouble("lobby.pitch")));

                        }
                        p.closeInventory();
                        p.openInventory(getLobbyIventory("eng", p));

                    } else {
                        va.mandarMensagem(prefix + ChatColor.RED + "You cannot save the lobby in a disable world", p);
                    }
                } else {
                    String lobbyWN = cm.getData().getString("lobby.world");
                    if (lobbyWN != null) {
                        World w = Bukkit.getWorld(lobbyWN);
                        p.teleport(new Location(
                                w,
                                cm.getData().getDouble("lobby.x"),
                                cm.getData().getDouble("lobby.y"),
                                cm.getData().getDouble("lobby.z"),
                                (float) cm.getData().getDouble("lobby.yaw"),
                                (float) cm.getData().getDouble("lobby.pitch")));

                    }
                }

            }

        } else if (verifInv(e.getCurrentItem(), getLobbyIventory("pt", p))) {
            e.setCancelled(true);

            if (e.getCurrentItem().isSimilar(getLobbyInvItens("pt", p).get(0))) {
                p.closeInventory();
                p.openInventory(getCMDInv("pt", p));

            } else if (e.getCurrentItem().isSimilar(getLobbyInvItens("pt",p).get(1))) {

                if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
                    lobbyC.set("lobby.world", p.getWorld().getName());
                    lobbyC.set("lobby.x", p.getLocation().getX());
                    lobbyC.set("lobby.y", p.getLocation().getY());
                    lobbyC.set("lobby.z", p.getLocation().getZ());
                    lobbyC.set("lobby.yaw", p.getLocation().getYaw());
                    lobbyC.set("lobby.pitch", p.getLocation().getPitch());


                    try {
                        lobbyC.save(lobbyF);
                    } catch (IOException ex) {
                        p.sendMessage(ChatColor.RED + "Não foi possível salvar as informações do lobby");
                        throw new RuntimeException(ex);
                    }

                    p.sendMessage(ChatColor.GRAY + "O lobby de seu servidor foi configurado com sucesso na coordenada "
                            + ChatColor.GREEN + cm.getData().getInt("lobby.x") + ", "
                            + cm.getData().getInt("lobby.y") + ", "
                            + cm.getData().getInt("lobby.z") + ", " + ChatColor.GRAY + "no mundo "
                            + ChatColor.GREEN + cm.getData().get("lobby.world") + ".");

                    p.closeInventory();
                    p.openInventory(getLobbyIventory("pt", p));

                } else {
                    va.mandarMensagem(prefix + ChatColor.RED + "Você não pode salvar o lobby no mundo em que não está ativado pelo plugin!", p);
                }


            } else if (e.getCurrentItem().isSimilar(getLobbyInvItens("pt",p).get(2))) {

                if (cm.getData().getString("lobby.world") == null) {
                    if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
                        lobbyC.set("lobby.world", p.getWorld().getName());
                        lobbyC.set("lobby.x", p.getLocation().getX());
                        lobbyC.set("lobby.y", p.getLocation().getY());
                        lobbyC.set("lobby.z", p.getLocation().getZ());
                        lobbyC.set("lobby.yaw", p.getLocation().getYaw());
                        lobbyC.set("lobby.pitch", p.getLocation().getPitch());


                        try {
                            lobbyC.save(lobbyF);
                        } catch (IOException ex) {
                            p.sendMessage(ChatColor.RED + "Não foi possível salvar as informações do lobby");
                            throw new RuntimeException(ex);
                        }

                        p.sendMessage(ChatColor.GRAY + "O lobby de seu servidor foi configurado com sucesso na coordenada "
                                + ChatColor.GREEN + cm.getData().getInt("lobby.x") + ", "
                                + cm.getData().getInt("lobby.y") + ", "
                                + cm.getData().getInt("lobby.z") + ", " + ChatColor.GRAY + "no mundo "
                                + ChatColor.GREEN + cm.getData().get("lobby.world") + ".");

                        String lobbyWN = cm.getData().getString("lobby.world");
                        if (lobbyWN != null) {
                            World w = Bukkit.getWorld(lobbyWN);
                            p.teleport(new Location(
                                    w,
                                    cm.getData().getDouble("lobby.x"),
                                    cm.getData().getDouble("lobby.y"),
                                    cm.getData().getDouble("lobby.z"),
                                    (float) cm.getData().getDouble("lobby.yaw"),
                                    (float) cm.getData().getDouble("lobby.pitch")));

                        }
                        p.closeInventory();
                        p.openInventory(getLobbyIventory("pt", p));
                    } else {
                        va.mandarMensagem(prefix + ChatColor.RED + "Você não pode salvar o lobby no mundo em que não está ativado pelo plugin!", p);
                    }
                } else {
                    String lobbyWN = cm.getData().getString("lobby.world");
                    if (lobbyWN != null) {
                        World w = Bukkit.getWorld(lobbyWN);
                        p.teleport(new Location(
                                w,
                                cm.getData().getDouble("lobby.x"),
                                cm.getData().getDouble("lobby.y"),
                                cm.getData().getDouble("lobby.z"),
                                (float) cm.getData().getDouble("lobby.yaw"),
                                (float) cm.getData().getDouble("lobby.pitch")));

                    }
                }

                p.closeInventory();
                p.openInventory(getLobbyIventory("pt", p));

            }
        //Comandos moderação
        } else if (verifInv(e.getCurrentItem(), getModInventory("eng", p))) {
            e.setCancelled(true);

            if (e.getCurrentItem().isSimilar(getModInvItens("eng", p).get(0))) {

                p.closeInventory();
                p.openInventory(getCMDInv("eng",p));

            }

        } else if (verifInv(e.getCurrentItem(), getModInventory("pt", p))) {
            e.setCancelled(true);

            if (e.getCurrentItem().isSimilar(getModInvItens("pt", p).get(0))) {

                p.closeInventory();
                p.openInventory(getCMDInv("pt",p));

            }
            //Comandos Player

        } else if (verifInv(e.getCurrentItem(), getPlayersCMDInv("eng", p))) {
            e.setCancelled(true);
            if (e.getCurrentItem().isSimilar(getPlayersCMDInvItens("eng", p).get(0))) {

                p.closeInventory();
                p.openInventory(getCMDInv("eng",p));

            }

        } else if (verifInv(e.getCurrentItem(), getPlayersCMDInv("pt", p))) {
            e.setCancelled(true);
            if (e.getCurrentItem().isSimilar(getPlayersCMDInvItens("pt", p).get(0))) {

                p.closeInventory();
                p.openInventory(getCMDInv("pt",p));

            }
        //Ações
        } else if (verifInv(e.getCurrentItem(), getActionsInventory("eng", p))) {
            e.setCancelled(true);
            if (e.getCurrentItem().isSimilar(getActionsInvItens("eng", p).get(0))) {

                p.closeInventory();
                p.openInventory(getPrincipalInv("eng",p));

            }

        } else if (verifInv(e.getCurrentItem(), getActionsInventory("pt", p))) {
            e.setCancelled(true);
            if (e.getCurrentItem().isSimilar(getActionsInvItens("pt", p).get(0))) {
                p.closeInventory();
                p.openInventory(getPrincipalInv("pt",p));
            }

        }
    }

    ItemStack getItemStack(Material item, String nome, int quantidade, List<String> lore, boolean grow, Player p) {

        ItemStack itemStack = new ItemStack(item, quantidade);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(nome.replace("&", "§"));
        List<String> lore2 = new ArrayList<>();
        for (String l : lore) {
            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                l = PlaceholderAPI.setPlaceholders(p, l);
            }
            l = l.replace("&", "§");
            lore2.add(l);
        }
        meta.setLore(lore2);
        if (grow) {
            meta.addEnchant(Enchantment.DURABILITY, 0, true);
        }

        itemStack.setItemMeta(meta);
        return itemStack;

    }


}
