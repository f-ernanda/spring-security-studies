package com.fernanda.pokemon.security;

public enum UserPermission {
    POKEMON_READ("pokemon:read"),
    POKEMON_WRITE("pokemon:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
