package com.example.securesongs.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SongRequest {

    @NotBlank(message = "Title is required and cannot be empty")
    private String title;

    @NotBlank(message = "Artist is required and cannot be empty")
    private String artist;

    // Integer (not int) so a missing value is null and can be reported
    // as "required" instead of silently becoming 0.
    @NotNull(message = "Duration is required")
    @Min(value = 30, message = "Duration must be at least 30 seconds")
    private Integer duration;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }
}
