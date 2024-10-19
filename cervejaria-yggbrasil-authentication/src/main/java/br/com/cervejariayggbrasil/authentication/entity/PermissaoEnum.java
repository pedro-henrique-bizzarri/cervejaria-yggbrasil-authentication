package br.com.cervejariayggbrasil.authentication.entity;

public enum PermissaoEnum {
    ADMIN("admin"),
    USER("user");

    private String permissao;

    private PermissaoEnum(String permissao) {
        this.permissao = permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public String getPermissao(){
        return permissao;
    }

}
