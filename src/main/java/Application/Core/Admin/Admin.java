package Application.Core.Admin;//package com.company;

import Application.Core.Ride.Ride;
import Application.Core.User.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.ArrayList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Admin extends User
{
    private static ArrayList<Ride> rides= new ArrayList<>();
    private AdminModel adminModel;

     public Admin()
    {
        super();
        adminModel = new AdminModel(this);
    }

    @Override
    public void update(Object type, Object data) {
//      driverRegistration.add((Registration)data);
    }

    public AdminModel getAdminModel() {
        return adminModel;
    }

    public static ArrayList<Ride> getRides() {
        return rides;
    }

    public static void addRide(Ride ride){
        Admin.rides.add(ride);
    }


}
