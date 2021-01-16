package com.gmovie.movieapi.model;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Movie {
    @Id
    private String title;
    private String actors;
    private String release;
    private String description;
    private String rating;

    public Movie() {
    }

    public Movie(String title, String actors, String release, String description, String rating) {
        this.title = title;
        this.actors = actors;
        this.release = release;
        this.description = description;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}