package com.epam.races.entity.race;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Race {
    private String title;
    private String organizer;
    private LocalDate date;
    private LocalTime time;
    private Place place = new Place();
    private double ticketPrice;

    public Race() {
    }

    public Race(String title, String organizer, LocalDate date, LocalTime time, double ticketPrice) {
        this.title = title;
        this.organizer = organizer;
        this.date = date;
        this.time = time;
        this.ticketPrice = ticketPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public class Place{
        private String city;
        private String street;
        private int houseNumber;

        public Place() {
        }

        public Place(String city, String street, int houseNumber) {
            this.city = city;
            this.street = street;
            this.houseNumber = houseNumber;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public int getHouseNumber() {
            return houseNumber;
        }

        public void setHouseNumber(int houseNumber) {
            this.houseNumber = houseNumber;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Title: " + title + " Organizer: " + organizer);
        result.append(" Date: " + date + " Time: " + time + "\n");
        result.append("Place: " + place.city + ", " + place.street + ", " + place.houseNumber + "\n");
        return result.toString();
    }
}
