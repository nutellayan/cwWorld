package com.napier.sem;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class UserAuthentication {
    private static Set<User> users = new HashSet<>();

    public static class User {
        private String username;
        private String password;
        private Set<Role> roles;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
            this.roles = new HashSet<>();
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public void addRole(Role role) {
            roles.add(role);
        }

        // Getters and setters for roles
    }

    public static class Role {
        private String name;
        private Set<Permission> permissions;

        public Role(String name) {
            this.name = name;
            this.permissions = new HashSet<>();
        }

        public void addPermission(Permission permission) {
            permissions.add(permission);
        }

        // Getters and setters for permissions
    }

    public static class Permission {
        private String name;

        public Permission(String name) {
            this.name = name;
        }

        // Getters and setters for name
    }

    public static class AuthenticationToken {
        private String token;
        private User user;
        private LocalDateTime expirationDateTime;

        public AuthenticationToken(String token, User user, LocalDateTime expirationDateTime) {
            this.token = token;
            this.user = user;
            this.expirationDateTime = expirationDateTime;
        }

        // Getters and setters for token, user, and expirationDateTime
    }

    // Methods to interact with users, roles, and permissions
    // e.g., addUser, addRole, addPermission, authenticateUser, generateToken, etc.
}
