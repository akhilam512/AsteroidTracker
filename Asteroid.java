import java.util.Date;

public class Asteroid {
    private String name;
    private Date approach_date;
    private Double diameter, missing_approach;
    private boolean isHazardous;

    public Asteroid(){}

    /**
     * @param name Asteroid Name
     * @param date Asteroid's approaching date
     * @param diameter Asteroid's diameter
     * @param missing_approach Asteroid's approaching distance in km
     * @param isHazardous A boolean represents if asteroid hazardous
     */
    public Asteroid(String name, Date date, Double diameter, Double missing_approach, boolean isHazardous){
        this.name = name;
        this.approach_date = date;
        this.diameter = diameter;
        this.missing_approach = missing_approach;
        this.isHazardous = isHazardous;
    }

    public Date getApproach_date() {
        return approach_date;
    }

    public void setApproach_date(Date approach_date) {
        this.approach_date = approach_date;
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

    public Double getMissing_approach() {
        return missing_approach;
    }

    public void setMissing_approach(Double missing_approach) {
        this.missing_approach = missing_approach;
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
                + " approached to world on " + approach_date.toString()
                + " from the distance of " + missing_approach
                + (missing_approach==1.0 ? " kilometer" : " kilometers") + " and this asteroid is "
                + (isHazardous? "hazardous." : "not hazardous.");
    }
}
