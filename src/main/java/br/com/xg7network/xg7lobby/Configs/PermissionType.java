package br.com.xg7network.xg7lobby.Configs;

public enum PermissionType {

    ADMIN("xg7lobby.admin");

    private String perm;

    PermissionType(String perm) {
        this.perm = perm;
    }

    public String getPerm() {
        return this.perm;
    }

}
