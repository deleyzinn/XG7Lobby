package br.com.xg7network.xg7lobby.Configs;

import org.bukkit.entity.Player;

public enum PermissionType {

    ADMIN("xg7lobby.admin"),
    SETLOBBY_COMMAND("xg7lobby.command.setlobby"),
    RELOAD_COMMAND("xg7lobby.command.reload"),

    MUTE_COMMAND("xg7lobby.command.mute"),
    COMMAND("xg7lobby.command.*"),
    ITENS_JOGAR("xg7lobby.itens.jogar"),
    ITENS_PEGAR("xg7lobby.itens.pegar"),
    BLOCOS_INTERAGIR("xg7lobby.blocos.interagir"),
    BLOCOS_QUEBRAR("xg7lobby.blocos.quebrar"),
    BLOCOS_COLOCAR("xg7lobby.blocos.colocar"),
    ATACAR("xg7lobby.atacar");



    private String perm;

    PermissionType(String perm) {
        this.perm = perm;
    }

    public String getPerm() {
        return this.perm;
    }

}
