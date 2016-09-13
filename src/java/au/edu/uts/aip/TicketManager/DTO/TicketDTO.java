/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.TicketManager.DTO;

import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 *The class TicketDetail is the modal of each air ticket information
 * @author Jianhao_LI
 */
public class TicketDTO {
    private int ticketID;
    private String UserID;
    private String flightNumber;
    private String origin;
    private String destination;
    private Date departureTime;
    private Date arriveTime;

    /**
     * no params required instance constructor
     */
    public TicketDTO() {
    }
    
//    /**
//     * This method is constructor of class TicketDetail with full params except TicketID 
//     * that will create automatically by database and the detail params required is listed below
//     * @param UserId
//     * @param flightNumber
//     * @param origin
//     * @param destination
//     * @param departureTime
//     * @param arriveTime 
//     */
//    public TicketDetail(String UserID, String flightNumber, String origin, String destination, Date departureTime, Date arriveTime) {
//        this.UserID = UserID;
//        this.flightNumber = flightNumber;
//        this.origin = origin;
//        this.destination = destination;
//        this.departureTime = departureTime;
//        this.arriveTime = arriveTime;
//    }

    /**
     * this method will return the TicketID
     *
     * @return TicketID 
     */
//    @NotNull
    public int getTicketID() {
        return ticketID;
    }
    /**
     * this method is used to set the order number
     *
     * @param TicketID provided to set the new TicketID which cannot be
     * null, otherwise Exception will be called
     *
     */
    public void setTicketID(int ticketID) {
       
        this.ticketID = ticketID;
    }

    /**
     * this method will return the UserID
     *
     * @return UserID
     */
    @NotNull
    public String getUserID() {
        return UserID;
    }
    /**
     * this method is used to set the UserID
     *
     * @param UserID provided to set the new TicketID which cannot be null,
     * otherwise Exception will be called
     *
     */
    public void setUserID(String UserID) {
        if (UserID == null) {
            throw new IllegalArgumentException("The UserID cannot be empty");
        }
        this.UserID = UserID;
    }

    /**
     * this method will return the FlightNumber
     *
     * @return FlightNumbe or null if FlightNumbe is none
     */
    @NotNull
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * this method is used to set the flight Number
     *
     * @param flightNumber provided to set the new flight number which cannot be
     * null, otherwise Exception will be called
     *
     */
    public void setFlightNumber(String flightNumber) {
        if (flightNumber == null) {
            throw new IllegalArgumentException("The flightNumber cannot be null");
        }
        this.flightNumber = flightNumber;
    }

    /**
     * this method will return the flight origin
     *
     * @return flight origin which cannot be null
     */
    @NotNull
    public String getOrigin() {
        return origin;
    }

    /**
     * this method is used to set the flight origin
     *
     * @param origin provided to set the new flight origin which cannot be null,
     * otherwise Exception will be called
     *
     */
    public void setOrigin(String origin) {
        if (origin == null) {
            throw new IllegalArgumentException("The flight origin cannot be null");
        }
        this.origin = origin;
    }

    /**
     * this method will return the flight destination
     *
     * @return flight destination which cannot be null
     */
    @NotNull
    public String getDestination() {
        return destination;
    }

    /**
     * this method is used to set the flight destination
     *
     * @param origin provided to set the new flight destination which cannot be
     * null, otherwise Exception will be called
     *
     */
    public void setDestination(String destination) {
        if (destination == null) {
            throw new IllegalArgumentException("The flight destination cannot be null");
        }
        this.destination = destination;
    }

    /**
     * this method will return the time when the flight is going to take off
     *
     * @return departure time which cannot be null
     */
    @NotNull
    public Date getDepartureTime() {
        return departureTime;
    }

    /**
     * this method is used to set the flight departure time
     *
     * @param departureTime provided to set the new flight departure time which
     * cannot be null, otherwise Exception will be called
     *
     */
    public void setDepartureTime(Date departureTime) {
        if (departureTime == null) {
            throw new IllegalArgumentException("The flight departureTime cannot be null");
        }
        this.departureTime = departureTime;
    }

    /**
     * this method will return the time when the flight is going to arrive the
     * destination
     *
     * @return arrive time which cannot be null
     */
    @NotNull
    public Date getArriveTime() {
        return arriveTime;
    }

    /**
     * this method is used to set the flight arrive time
     *
     * @param arriveTime provided to set the new flight arrive time which cannot
     * be null, otherwise Exception will be called
     *
     */
    public void setArriveTime(Date arriveTime) {
        if (arriveTime == null) {
            throw new IllegalArgumentException("The flight arriveTime cannot be null");
        }
        this.arriveTime = arriveTime;
    }
}
