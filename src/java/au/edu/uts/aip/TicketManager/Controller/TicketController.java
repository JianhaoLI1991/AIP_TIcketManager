/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.TicketManager.Controller;

import au.edu.uts.aip.TicketManager.DAO.TicketDAO;
import au.edu.uts.aip.TicketManager.DTO.TicketDTO;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.enterprise.context.*;
import javax.inject.Named;
import javax.naming.NamingException;

/**
 * this class is the controller of ticket that includes all logic control of
 * ticket
 *
 * @author Jianhao_LI
 */
@Named
@RequestScoped
public class TicketController implements Serializable {

    private ArrayList<TicketDTO> ticketList = new ArrayList<TicketDTO>();
    private TicketDTO ticket = new TicketDTO();

    /**
     * used to return the variable ticket
     *
     * @return ticket the ticket that will return
     */
    public TicketDTO getTicket() {
        return ticket;
    }

    /**
     * used to set the value of variable ticket
     *
     * @param ticket the ticket that is going to be set
     */
    public void setTicket(TicketDTO ticket) {
        this.ticket = ticket;
    }

    /**
     * this method always return all the tickets of the current user as a list
     * array to data table
     *
     * @return list of ticket
     */
    public ArrayList<TicketDTO> getTickets() {
//        System.out.println("loading data");
        return ticketList;
    }

    /**
     * this method will load the ticket list from database and check if the
     * ticket list of the current user is empty or not
     *
     * @return return false means the ticket list is empty and the data table
     * will not show and display a sentence to notify the empty list; return
     * true will show to detail of ticket list to the data table in ticket list
     * page
     * @throws SQLException when the SQL access is illegal, exception will
     * called
     * @throws NamingException when user input illegal email or password to find
     */
    public boolean checkTicketEmpty() throws NamingException, SQLException, ParseException {
        ticketList = TicketDAO.findAll();
        if (ticketList.isEmpty()) {
//            System.out.println("is empty");
            return false;
        } else {
//            System.out.println("is not empty");
            return true;
        }
    }

    /**
     * the method will delete a specific ticket based on the ticket id
     *
     * @param ticketID used to determine which ticket will be removed from the
     * data base
     * @throws SQLException when the SQL access is illegal, exception will
     * called
     * @throws NamingException when user input illegal email or password to find
     */
    public void deleteTicket(int ticketID) throws NamingException, SQLException {
        TicketDAO.deleteTicket(ticketID);
    }

    /**
     * * this method return a specific ticket based on ticketId and will be
     * used for editing ticket
     *
     * @param ticketID used to determine return which ticket
     * @throws SQLException when the SQL access is illegal, exception will
     * called
     * @throws NamingException when user input illegal email or password to find
     * @throws ParseException the exception will called when the parse action
     * cannot be done.
     */
    public void loadTicket(String ticketID) throws NamingException, SQLException, ParseException {
        System.out.println("loading ticket information" + ticketID);
        ticket = TicketDAO.find(ticketID);
//        System.out.print(ticket.getDepartureTime());
    }

    /**
     * method will be called when user update a specific ticket and then connect
     * to database and update relevant row of ticket
     *
     * @return redirect the page to ticket list page after the ticket update
     * finished
     * @throws SQLException when the SQL access is illegal, exception will
     * called
     * @throws NamingException when user input illegal email or password to find
     * @throws ParseException the exception will called when the parse action
     * cannot be done.
     */
    public String updateTicket() throws NamingException, SQLException, ParseException {
//        System.out.println(ticket.getArriveTime());
//        System.out.println("ID of Updating Ticket:"+ticket.getTicketID()+ticket.getDestination());
        if (ticket.getDepartureTime().getTime() >= ticket.getArriveTime().getTime()) {
            UserController.showErrorMessage("Departure time must be before the arrive time");
            return null;
        }
        TicketDAO.updateTicket(ticket);
        return "ticketlist?faces-redirect=true";
    }

    /**
     * This method is used to add new ticket
     *
     * @return re-direct the page the ticket list
     * @throws SQLException when the SQL access is illegal, exception will
     * called
     * @throws NamingException when user input illegal email or password to find
     */
    public String addTicket() throws NamingException, SQLException {
        if (ticket.getDepartureTime().getTime() >= ticket.getArriveTime().getTime()) {
            UserController.showErrorMessage("Departure time must be before the arrive time");
            return null;
        }
        TicketDAO.addTicket(ticket);
        return "ticketlist?faces-redirect=true";
    }
}
