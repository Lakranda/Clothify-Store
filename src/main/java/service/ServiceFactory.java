package service;

import service.custom.Impl.*;
import util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory(){


    }

    public static ServiceFactory getInstance(){
        return instance==null ? instance=new ServiceFactory() : instance;
    }

    public <T extends SuperService>T getServiceType(ServiceType type){
        switch (type){
            case USER: return (T) new UserServiceImpl();
            case EMPLOYEE: return (T) new EmployeeServiceImpl();
            case ORDER: return (T) new OrderServiceImpl();
            case SUPPLIER: return (T) new SupplierServiceImpl();
            case PRODUCT: return (T) new ProductServiceImpl();
            case RETURN: return (T) new ReturnServiceImpl();

        }

        return null;
    }


}
