package com.tracker.asteroid.model;

import java.util.Date;

public class Asteroid {

    private String name;
    private Date approachDate;
    private Double diameter, missingApproach;
    private boolean isHazardous;

    public Asteroid(){}

    /**
     * @param name Asteroid Name
     * @param date Asteroid's approaching date
     * @param diameter Asteroid's diameter
     * @param missingApproach Asteroid's approaching distance in km
     * @param isHazardous A boolean represents if asteroid hazardous
     */
    public Asteroid(String name, Date date, Double diameter, Double missingApproach, boolean isHazardous){
        this.name = name;
        this.approachDate = date;
        this.diameter = diameter;
        this.missingApproach = missingApproach;
        this.isHazardous = isHazardous;
    }

    public Date getApproachDate() {
        return approachDate;
    }

    public void setApproachDate(Date approachDate) {
        this.approachDate = approachDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDiameter() {
        return diameter;
    }

    public void setDiameter(Double diameter) {
        this.diameter = diameter;
    }

    public Double getMissingApproach() {
        return missingApproach;
    }

    public void setMissingApproach(Double missingApproach) {
        this.missingApproach = missingApproach;
    }

    public boolean isHazardous() {
        return isHazardous;
    }

    public void setHazardous(boolean hazardous) {
        isHazardous = hazardous;
    }

    @Override
    public String toString() {
        return "Asteroid "+ name
                + " with diameter "+ diameter
                + " approached to world on " + approachDate.toString()
                + " from the distance of " + missingApproach
                + (missingApproach ==1.0 ? " kilometer" : " kilometers") + " and this asteroid is "
                + (isHazardous? "hazardous." : "not hazardous.");
    }
}
