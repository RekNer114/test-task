package me.vmachohan.auth_api.dto;

public record RegisterRequest(
        String email,
        String password
) {
}
