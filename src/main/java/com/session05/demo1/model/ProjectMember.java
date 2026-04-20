package com.session05.demo1.model;

import jakarta.validation.constraints.*;

public class ProjectMember {
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 5, max = 50, message = "Name must be between 5 and 50 characters")
    private String fullName;

    @NotBlank(message = "Position is required")
    private String position;

    @PositiveOrZero(message = "Experience must be a positive number")
    private Double experienceYears;

    private String avatar;

    @Email(message = "Email should be valid")
    @Pattern(regexp = ".*@(gmail\\.com|rikkeisoft\\.com)$", message = "Email must end with @gmail.com or @rikkeisoft.com")
    private String email;

    public ProjectMember(Long id, String fullName, String position, Double experienceYears, String avatar, String email) {
        this.id = id;
        this.fullName = fullName;
        this.position = position;
        this.experienceYears = experienceYears;
        this.avatar = avatar;
        this.email = email;
    }

    public ProjectMember() {
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPosition() {
        return position;
    }

    public Double getExperienceYears() {
        return experienceYears;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setExperienceYears(Double experienceYears) {
        this.experienceYears = experienceYears;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
