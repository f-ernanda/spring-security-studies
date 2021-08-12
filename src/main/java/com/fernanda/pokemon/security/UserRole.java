package com.fernanda.pokemon.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.fernanda.pokemon.security.UserPermission.POKEMON_READ;
import static com.fernanda.pokemon.security.UserPermission.POKEMON_WRITE;

public enum UserRole {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(POKEMON_READ, POKEMON_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(POKEMON_READ));


    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
