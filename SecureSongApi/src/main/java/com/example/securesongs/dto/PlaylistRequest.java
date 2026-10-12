package com.example.securesongs.dto;

import jakarta.validation.constraints.NotBlank;

public record PlaylistRequest(
        @NotBlank(message = "Playlist name is required and cannot be empty") String name) {
}
