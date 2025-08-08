package com.pahanaedu.factory;

import com.pahanaedu.dao.*;

public class DAOFactory {
    public static ICustomerDAO getCustomerDAO() {
        return new CustomerDAOImpl();
    }

    public static IUserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    public static IItemDAO getItemDAO() {
        return new ItemDAOImpl();
    }
}
