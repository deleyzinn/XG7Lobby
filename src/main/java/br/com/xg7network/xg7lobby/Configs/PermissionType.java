package br.com.xg7network.xg7lobby.Configs;

public enum PermissionType {

    ADMIN("xg7lobby.admin"),

    SETLOBBY_COMMAND("xg7lobby.command.setlobby"),
    RELOAD_COMMAND("xg7lobby.command.reload"),

    MUTE_COMMAND("xg7lobby.command.mute"),
    UNMUTE_COMMAND("xg7lobby.command.unmute"),
    BAN_COMMAND("xg7lobby.command.ban"),
    UNBAN_COMMAND("xg7lobby.command.unban"),
    TEMPBAN_COMMAND("xg7lobby.command.tempban"),
    KICK_COMMAND("xg7lobby.command.kick"),
    WARN_COMMAND("xg7lobby.command.warn"),

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
