package Application.Core;//package com.company;

import java.util.ArrayList;

public class Admin extends User
{
    private ArrayList<Registration> driverRegistration;
    private AdminModel adminModel;

    public Admin()
    {
        driverRegistration = new ArrayList<Registration>();
        adminModel = new AdminModel(this);
    }

    @Override
    public void update(Object type, Object data) {
      driverRegistration.add((Registration)data);
    }

    public AdminModel getAdminModel() {
        return adminModel;
    }

    //    public void print()
//    {
//        System.out.println(driverRegistration);
//    }


}
