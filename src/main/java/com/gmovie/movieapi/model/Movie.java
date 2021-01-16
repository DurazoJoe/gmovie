package com.gmovie.movieapi.model;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Movie {
    @Id
    private String title;
    private String director;
    private String actors;
    private String release;
    private String description;
    private String rating;
    private String avgRating;
    private ArrayList<String> reviews;

    public Movie() {
    }

    public Movie(String title, String director, String actors, String release, String description, String rating) {
        this.title = title;
        this.director = director;
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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
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

    public String getAvgRating() {

        String[] ratings = getRating().split(",");
        double avgVal = 0;
        for (String rating1 : ratings) {
            int val = Integer.parseInt(rating1);
            avgVal += val;
        }
        avgVal = avgVal / ratings.length;
        return String.valueOf(avgVal);
    }

    public void setReview(String review) {
        this.reviews.add(review);
    }
}
