package dev.ehyeon.move.entity;

public enum Role {

    ADMIN("admin"),
    MEMBER("member");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
