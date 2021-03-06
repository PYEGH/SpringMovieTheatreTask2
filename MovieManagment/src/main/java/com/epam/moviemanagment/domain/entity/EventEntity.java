package com.epam.moviemanagment.domain.entity;

import java.util.Calendar;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;

import com.epam.moviemanagment.domain.EventRating;


public class EventEntity extends DomainObjectEntity {

    private String name;

    private NavigableSet<Calendar> airDates = new TreeSet<>();

    private double basePrice;

    private EventRating rating;

    private NavigableMap<Calendar, AuditoriumEntity> auditoriumEntities = new TreeMap<>();

    /**
     * Checks if event is aired on particular <code>dateTime</code> and assigns
     * auditorium to it.
     * 
     * @param dateTime
     *            Date and time of aired event for which to assign
     * @param auditoriumEntity
     *            Auditorium that should be assigned
     * @return <code>true</code> if successful, <code>false</code> if event is
     *         not aired on that date
     */
    public boolean assignAuditorium(Calendar dateTime, AuditoriumEntity auditoriumEntity) {
        if (airDates.contains(dateTime)) {
            auditoriumEntities.put(dateTime, auditoriumEntity);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes auditorium assignment from event
     * 
     * @param dateTime
     *            Date and time to remove auditorium for
     * @return <code>true</code> if successful, <code>false</code> if not
     *         removed
     */
    public boolean removeAuditoriumAssignment(Calendar dateTime) {
        return auditoriumEntities.remove(dateTime) != null;
    }

    /**
     * Add date and time of event air
     * 
     * @param dateTime
     *            Date and time to add
     * @return <code>true</code> if successful, <code>false</code> if already
     *         there
     */
    public boolean addAirDateTime(Calendar dateTime) {
        return airDates.add(dateTime);
    }

    /**
     * Adding date and time of event air and assigning auditorium to that
     * 
     * @param dateTime
     *            Date and time to add
     * @param auditoriumEntity
     *            Auditorium to add if success in date time add
     * @return <code>true</code> if successful, <code>false</code> if already
     *         there
     */
    public boolean addAirDateTime(Calendar dateTime, AuditoriumEntity auditoriumEntity) {
        boolean result = airDates.add(dateTime);
        if (result) {
            auditoriumEntities.put(dateTime, auditoriumEntity);
        }
        return result;
    }

    /**
     * Removes the date and time of event air. If auditorium was assigned to
     * that date and time - the assignment is also removed
     * 
     * @param dateTime
     *            Date and time to remove
     * @return <code>true</code> if successful, <code>false</code> if not there
     */
    public boolean removeAirDateTime(Calendar dateTime) {
        boolean result = airDates.remove(dateTime);
        if (result) {
            auditoriumEntities.remove(dateTime);
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NavigableSet<Calendar> getAirDates() {
        return airDates;
    }

    public void setAirDates(NavigableSet<Calendar> airDates) {
        this.airDates = airDates;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public EventRating getRating() {
        return rating;
    }

    public void setRating(EventRating rating) {
        this.rating = rating;
    }

    public NavigableMap<Calendar, AuditoriumEntity> getAuditoriums() {
        return auditoriumEntities;
    }

    public void setAuditoriums(NavigableMap<Calendar, AuditoriumEntity> auditoriumEntities) {
        this.auditoriumEntities = auditoriumEntities;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        EventEntity other = (EventEntity) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
