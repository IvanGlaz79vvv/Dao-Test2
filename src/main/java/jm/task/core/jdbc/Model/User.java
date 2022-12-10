package jm.task.core.jdbc.Model;

public class User {

    int id;
    private String passenger_no;
    private String passenger_name;
    private int flight_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassenger_no() {
        return passenger_no;
    }

    public void setPassenger_no(String passenger_no) {
        this.passenger_no = passenger_no;
    }

    public String getPassenger_name() {
        return passenger_name;
    }

    public void setPassenger_name(String passenger_name) {
        this.passenger_name = passenger_name;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public String getSeat_no() {
        return seat_no;
    }

    public void setSeat_no(String seat_no) {
        this.seat_no = seat_no;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    private String seat_no;
    private double cost;

    public User(int id, String passenger_no, String passenger_name, int flight_id, String seat_no, double cost) {
        this.id = id;
        this.passenger_no = passenger_no;
        this.passenger_name = passenger_name;
        this.flight_id = flight_id;
        this.seat_no = seat_no;
        this.cost = cost;
    }

    public User() {
    }

    /*public User(String passenger_no, String passenger_name, int flight_id, String seat_no, double cost) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.date = date;
    }*/

   /* public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String passenger_no() {
        return passenger_no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }*/

    @Override
    public String toString() {
        return String.format(
                "  User id = " + getId() + ";\n " +
                        " passenger_no = " + getPassenger_no() + ";\n " +
                        " passenger_name = " + getPassenger_name() + ";\n " +
                        " flight_id = " + getFlight_id() + ";\n " +
                        " seat_no = " + getSeat_no() + ";\n " +
                        " cost = " + getCost());
    }
}