package com.example.movies.Model;

import java.io.Serializable;

public class Movies implements Serializable {
    private static final long id = 1L;
    private String title;
    private String director;
    private String year;
    private String runtime;
    private String imdbID;
    private String poster;
    private String genre;
    private String writer;
    private String actor;
    private String plot;
    private String rating;
    private String dvd_release;
    private String production_comapany;
    private String country;
    private String awards;
    private String tv_rated;
    private String movie_TYPE;

    public Movies() {
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDvd_release() {
        return dvd_release;
    }

    public void setDvd_release(String dvd_release) {
        this.dvd_release = dvd_release;
    }

    public String getProduction_comapany() {
        return production_comapany;
    }

    public void setProduction_comapany(String production_comapany) {
        this.production_comapany = production_comapany;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getTv_rated() {
        return tv_rated;
    }

    public void setTv_rated(String tv_rated) {
        this.tv_rated = tv_rated;
    }

    public String getMovie_TYPE() {
        return movie_TYPE;
    }

    public void setMovie_TYPE(String movie_TYPE) {
        this.movie_TYPE = movie_TYPE;
    }




}
