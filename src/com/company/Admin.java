package com.company;

import java.util.ArrayList;

public class Admin extends User
{
    private ArrayList<Registration> driverRegistration;

    Admin()
    {
        driverRegistration = new ArrayList<Registration>();
    }
    public void suspend(User user)
    {

        user.setSuspended(true);
    }

    @Override
    public void update(Object type, Object data) {
      driverRegistration.add((Registration)data);
    }

    public void registerDriver(DriverRegistration registration)
    {
        //Driver driver = new Driver();
    }

    public void print()
    {
        System.out.println(driverRegistration);
    }
}
