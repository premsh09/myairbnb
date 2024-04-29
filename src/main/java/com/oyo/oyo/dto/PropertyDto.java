package com.oyo.oyo.dto;

public class PropertyDto {

    private Long id;
    private String propertyName;
    private int bedrooms;
    private int bathrooms;
    private int guests;
    private int nightlyPrice;



    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public int getNightlyPrice() {
        return nightlyPrice;
    }

    public void setNightlyPrice(int nightlyPrice) {
        this.nightlyPrice = nightlyPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
