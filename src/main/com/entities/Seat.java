package main.com.entities;

public class Seat{
    private String ID;
    private boolean booked;

    public Seat(String id, boolean status){
        this.ID = id;
        this.booked = status;
    }
    public void setID(String id) { this.ID = id;}

    public String getID(){return ID;}

    public boolean isBooked(){return booked;}

    public void assign()
    {
        if (!booked);
            booked = true;
    }

    public void unAssign() {
        booked = false;
    }
}
