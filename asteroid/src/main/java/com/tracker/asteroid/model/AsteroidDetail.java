package com.tracker.asteroid.model;

public class AsteroidDetail {
    private String designation;
    private Float estimatedDiameter;
    private Boolean potentiallyHazardousAsteroid;
    private Double missDistance;

    public AsteroidDetail(String designation, Float estimatedDiameter,
                          Boolean potentiallyHazardousAsteroid, Double missDistance) {

        this.designation = designation;
        this.estimatedDiameter = estimatedDiameter;
        this.potentiallyHazardousAsteroid = potentiallyHazardousAsteroid;
        this.missDistance = missDistance;
        this.missDistance = missDistance;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Float getEstimatedDiameter() {
        return estimatedDiameter;
    }

    public void setEstimatedDiameter(Float estimatedDiameter) {
        this.estimatedDiameter = estimatedDiameter;
    }

    public Boolean getPotentiallyHazardousAsteroid() {
        return potentiallyHazardousAsteroid;
    }

    public void setPotentiallyHazardousAsteroid(Boolean potentiallyHazardousAsteroid) {
        this.potentiallyHazardousAsteroid = potentiallyHazardousAsteroid;
    }

    public Double getMissDistance() {
        return missDistance;
    }

    public void setMissDistance(Double missDistance) {
        this.missDistance = missDistance;
    }
}
