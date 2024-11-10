package repository;

import repository.custom.Impl.*;

import util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;

    private DaoFactory(){

    }

    public static DaoFactory getInstance(){
        return instance==null ? instance=new DaoFactory() : instance;
    }

    public <T extends SuperDao>T getDaoType(DaoType type){
        switch (type){
            case USER : return (T) new UserDaoImpl();
            case EMPLOYEE : return (T) new EmployeeDaoImpl();
            case ORDER : return (T) new OrderDaoImpl();
            case SUPPLIER: return (T) new SupplierDaoImpl();
            case PRODUCT: return (T) new ProductDaoImpl();
            case RETURN: return (T) new ReturnDaoImpl();

        }

        return null;
    }



}
