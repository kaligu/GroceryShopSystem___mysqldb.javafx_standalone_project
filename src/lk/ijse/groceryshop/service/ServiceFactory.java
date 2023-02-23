package lk.ijse.groceryshop.service;

import lk.ijse.groceryshop.service.custom.Impl.CustomerServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        return serviceFactory==null?(serviceFactory=new ServiceFactory()):serviceFactory;
    }

    public <T extends SuperService> T getService(ServiceTypes serviceTypes){
        switch (serviceTypes){
            case CUSTOMER:
                return (T)new CustomerServiceImpl();

            default:
                return null;
        }
    }
}
