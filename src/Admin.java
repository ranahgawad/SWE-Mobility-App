//package com.company;

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
        SQLImplementation connection = SQLImplementation.getInstance();
        connection.updateUserSuspened(user);
    }

    @Override
    public void update(Object type, Object data) {
      driverRegistration.add((Registration)data);
    }

    public void verifyDriver(Driver driver)
    {
        driver.setVerfied(true);
        SQLImplementation connection = SQLImplementation.getInstance();
        connection.updateDriverVerification(driver, 1);
    }

    public void print()
    {
        System.out.println(driverRegistration);
    }
}
