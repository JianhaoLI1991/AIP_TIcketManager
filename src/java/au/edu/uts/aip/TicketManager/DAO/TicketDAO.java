/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.TicketManager.DAO;

import au.edu.uts.aip.TicketManager.DTO.TicketDTO;
import au.edu.uts.aip.TicketManager.Controller.UserController;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * this class is UserDAO that used to connect and CRUD(create, read, update,
 * delete) data with table ticketdetail in javadb
 *
 * @author Jianhao_LI
 */
public class TicketDAO {

    /**
     * this method will connect to ticketdetail table and return all tickets of
     * the current user
     *
     * @return a list of ticket of the current user
     * @throws SQLException when the SQL access is illegal, exception will
     * called
     * @throws NamingException when user input illegal email or password to find
     */
    public static ArrayList<TicketDTO> findAll() throws NamingException, SQLException, ParseException {
        ArrayList<TicketDTO> ticketList = new ArrayList<TicketDTO>();
        DataSource ds = (DataSource) InitialContext.doLookup("jdbc/aip");
        Connection conn = ds.getConnection();
        PreparedStatement ps;
        ps = conn.prepareStatement("select * from ticketdetail where userid=?");
        ps.setString(1, UserController.getUserID());
        ResultSet rs = ps.executeQuery();
        conn.close();
        while (rs.next()) {
            TicketDTO ticket = new TicketDTO();
            ticket.setTicketID(rs.getInt("ticketID"));
            ticket.setFlightNumber(rs.getString("flightnumber"));
            ticket.setOrigin(rs.getString("origin"));
            ticket.setDestination(rs.getString("destination"));
            Date departure_date = rs.getDate("departuretime");
            Date departure_time = rs.getTime("fromtime");
            Date arrive_date = rs.getDate("arrivetime");
            Date arrive_time = rs.getTime("totime");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");//dd/MM/yyyy
            departure_date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateFormat.format(departure_date) + " " + timeFormat.format(departure_time));
            arrive_date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateFormat.format(arrive_date) + " " + timeFormat.format(arrive_time));
            ticket.setDepartureTime(departure_date);
            ticket.setArriveTime(arrive_date);
            ticketList.add(ticket);
        }
        return ticketList;
    }

    /**
     * the method find can find the specific ticket based on the ticket id and
     * return the ticket the view page. it will be called when user click update
     * ticket button and used to pre-load the ticket information to to edit
     * ticket page
     *
     * @param ticketID determine which ticket will be used to pre-fill into the
     * edit ticket page
     * @return return the ticket select from the database based on ticket id
     * @throws SQLException when the SQL access is illegal, exception will
     * called
     * @throws NamingException when user input illegal email or password to find
     * @throws ParseException the exception will called when the parse action
     * cannot be done.
     */
    public static TicketDTO find(String ticketID) throws NamingException, SQLException, ParseException {
        DataSource ds = (DataSource) InitialContext.doLookup("jdbc/aip");
        Connection conn = ds.getConnection();
        PreparedStatement ps;
        ps = conn.prepareStatement("select * from ticketdetail where ticketid=?");
        ps.setInt(1, Integer.parseInt(ticketID));
        ResultSet rs = ps.executeQuery();
        conn.close();
        rs.next();
        TicketDTO ticket = new TicketDTO();
        ticket.setTicketID(rs.getInt("ticketID"));
        ticket.setFlightNumber(rs.getString("flightnumber"));
        ticket.setOrigin(rs.getString("origin"));
        ticket.setDestination(rs.getString("destination"));
        Date departure_date = rs.getDate("departuretime");
        Date departure_time = rs.getTime("fromtime");
        Date arrive_date = rs.getDate("arrivetime");
        Date arrive_time = rs.getTime("totime");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");//dd/MM/yyyy
        departure_date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateFormat.format(departure_date) + " " + timeFormat.format(departure_time));
        arrive_date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateFormat.format(arrive_date) + " " + timeFormat.format(arrive_time));
        ticket.setDepartureTime(departure_date);
        ticket.setArriveTime(arrive_date);
        return ticket;
    }

    /**
     * the method will delete a ticket based on the ticket id
     *
     * @param ticketId used to determine which ticket is going to be removed
     * from the data base
     * @throws SQLException when the SQL access is illegal, exception will
     * called
     * @throws NamingException when user input illegal email or password to find
     */
    public static void deleteTicket(int ticketId) throws NamingException, SQLException {
        DataSource ds = (DataSource) InitialContext.doLookup("jdbc/aip");
        Connection conn = ds.getConnection();
        PreparedStatement ps;
        ps = conn.prepareStatement("delete from ticketdetail where ticketid=?");
        ps.setInt(1, ticketId);
        ps.executeUpdate();
    }

    /**
     * when user click update ticket button, connect the database and overwrite
     * the new information of ticket to a specific ticket based on ticket id
     *
     * @param ticket used to determine which ticket is going to be updated
     * @throws SQLException when the SQL access is illegal, exception will
     * called
     * @throws NamingException when user input illegal email or password to find
     * @throws ParseException the exception will called when the parse action
     * cannot be done.
     */
    public static void updateTicket(TicketDTO ticket) throws NamingException, SQLException, ParseException {
        Date date = new Date();
        DataSource ds = (DataSource) InitialContext.doLookup("jdbc/aip");
        Connection conn = ds.getConnection();
        PreparedStatement ps;
        ps = conn.prepareStatement("update ticketdetail set flightnumber=?, origin=?, destination=?,departuretime=?,arrivetime=?, fromtime=?, totime=? where ticketid=?");
        ps.setString(1, ticket.getFlightNumber());
        ps.setString(2, ticket.getOrigin());
        ps.setString(3, ticket.getDestination());
        date = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(ticket.getDepartureTime()));
//        System.out.println("ID:"+ticket.getTicketID());
        ps.setDate(4, new java.sql.Date(ticket.getDepartureTime().getTime()));
        ps.setDate(5, new java.sql.Date(ticket.getArriveTime().getTime()));
        ps.setTime(6, new java.sql.Time(ticket.getDepartureTime().getTime()));
        ps.setTime(7, new java.sql.Time(ticket.getArriveTime().getTime()));
        ps.setInt(8, ticket.getTicketID());
        ps.executeUpdate();
        conn.close();
    }

    /**
     * the method is used to insert a new ticket information to the data base
     *
     * @param ticket the new ticket that is going to insert into data base
     * @throws SQLException when the SQL access is illegal, exception will
     * called
     * @throws NamingException when user input illegal email or password to find
     */
    public static void addTicket(TicketDTO ticket) throws NamingException, SQLException {
        DataSource ds = (DataSource) InitialContext.doLookup("jdbc/aip");
        Connection conn = ds.getConnection();
        PreparedStatement ps;
        ps = conn.prepareStatement("insert into ticketdetail (userid,flightnumber,origin,destination,departuretime,arrivetime,fromtime,totime) values (?,?,?,?,?,?,?,?)");
        ps.setString(1, UserController.getUserID());
        ps.setString(2, ticket.getFlightNumber());
        ps.setString(3, ticket.getOrigin());
        ps.setString(4, ticket.getDestination());
        ps.setDate(5, new java.sql.Date(ticket.getDepartureTime().getTime()));
        ps.setDate(6, new java.sql.Date(ticket.getArriveTime().getTime()));
        ps.setTime(7, new java.sql.Time(ticket.getDepartureTime().getTime()));
        ps.setTime(8, new java.sql.Time(ticket.getArriveTime().getTime()));
        ps.executeUpdate();
        conn.close();
    }
}
