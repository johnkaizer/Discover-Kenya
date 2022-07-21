package com.project.elimu.Model;

public class PopularSchool {
    String Title;
    String Location;
    String Population;
    String Status;
    String Description;
    String ImageUrl;

    public PopularSchool() {
    }

    public PopularSchool(String title, String location, String population, String status, String description, String imageUrl) {
        Title = title;
        Location = location;
        Population = population;
        Status = status;
        Description = description;
        ImageUrl = imageUrl;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getPopulation() {
        return Population;
    }

    public void setPopulation(String population) {
        Population = population;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
