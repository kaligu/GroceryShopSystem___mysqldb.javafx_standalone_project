package lk.ijse.groceryshop.service;

import lk.ijse.groceryshop.service.custom.Impl.*;

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
            case ITEM:
                return (T)new ItemServiceImpl();
            case ORDER:
                return (T)new OrderServiceImpl();
            case ORDERDETAILS:
                return (T)new OrderDetailsServiceimpl();
            case PLACEORDER:
                return (T)new PlaceOrderServiceImpl();
            default:
                return null;
        }
    }
}
