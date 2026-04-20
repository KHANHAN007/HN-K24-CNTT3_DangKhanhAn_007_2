package com.session05.demo1.model;

import jakarta.validation.constraints.*;

public class Artwork {
    private Long id;

    @NotBlank(message = "Tên tác phẩm không được để trống")
    @Size(min = 5, max = 150, message = "Tên tác phẩm phải từ 5 đến 150 ký tự")
    private String title;

    @NotBlank(message = "Tên họa sĩ không được để trống")
    private String artist;

    @NotNull(message = "Năm sáng tác không được để trống")
    @Min(value = 1000, message = "Năm sáng tác phải lớn hơn hoặc bằng 1000")
    private Integer year;

    private String artImage;

    public Artwork() {
    }

    public Artwork(Long id, String title, String artist, Integer year, String artImage) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.artImage = artImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getArtImage() {
        return artImage;
    }

    public void setArtImage(String artImage) {
        this.artImage = artImage;
    }
}

