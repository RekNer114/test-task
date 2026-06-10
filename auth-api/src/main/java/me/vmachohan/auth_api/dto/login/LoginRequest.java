package me.vmachohan.auth_api.dto.login;

public record LoginRequest(
        String email,
        String password
) {
}
