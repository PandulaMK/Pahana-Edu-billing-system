package com.pahanaedu.factory;

import com.pahanaedu.dao.BillDao;
import com.pahanaedu.dao.CustomerDao;
import com.pahanaedu.dao.ItemDao;
import com.pahanaedu.dao.UserDao;
import com.pahanaedu.dao.impl.BillDaoImpl;
import com.pahanaedu.dao.impl.CustomerDaoImpl;
import com.pahanaedu.dao.impl.ItemDaoImpl;
import com.pahanaedu.dao.impl.UserDaoImpl;

/**
 * Simple factory to centralize DAO creation.
 * You can later change it to provide file-based DAOs or mock DAOs for testing.
 */
public class DAOFactory {

    private DAOFactory() {
        // private constructor to prevent instantiation
    }

    public static CustomerDao getCustomerDAO() {
        return new CustomerDaoImpl();
    }

    public static ItemDao getItemDAO() {
        return new ItemDaoImpl();
    }

    public static UserDao getUserDAO() {
        return new UserDaoImpl();
    }
    public static BillDao getBillDAO() {
        return new BillDaoImpl();
    }

}
