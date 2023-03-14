package lk.ijse.groceryshop.service.custom.Impl;

import lk.ijse.groceryshop.dao.DAOFactory;
import lk.ijse.groceryshop.dao.DAOTypes;
import lk.ijse.groceryshop.dao.custom.CustomerDAO;
import lk.ijse.groceryshop.dao.custom.ItemDAO;
import lk.ijse.groceryshop.dao.custom.OrderDAO;
import lk.ijse.groceryshop.dao.custom.OrderDetailsDAO;
import lk.ijse.groceryshop.db.DBConnection;
import lk.ijse.groceryshop.dto.OrderDetailsDTO;
import lk.ijse.groceryshop.dto.OrdersDTO;
import lk.ijse.groceryshop.dto.PlaceOrderDTO;
import lk.ijse.groceryshop.entity.Item;
import lk.ijse.groceryshop.entity.Order;
import lk.ijse.groceryshop.entity.OrderDetails;
import lk.ijse.groceryshop.service.custom.PlaceOrderService;
import lk.ijse.groceryshop.service.util.Convertor;
import lk.ijse.groceryshop.util.HbFactoryConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;

public class PlaceOrderServiceImpl implements PlaceOrderService {
    private final OrderDAO orderDAO;
    private final ItemDAO itemDAO;
    private final CustomerDAO customerDAO;
    private final OrderDetailsDAO orderDetailsDAO;
    private final Convertor convertor;
    private final Connection connection;
    private Session session;
    private Transaction transaction;

    public PlaceOrderServiceImpl(){
        connection= DBConnection.getDbcConnection().getConnection();
        orderDAO= DAOFactory.getInstance().getDAO(connection, DAOTypes.ORDER);
        itemDAO= DAOFactory.getInstance().getDAO(connection, DAOTypes.ITEM);
        customerDAO= DAOFactory.getInstance().getDAO(connection, DAOTypes.CUSTOMER);
        orderDetailsDAO= DAOFactory.getInstance().getDAO(connection, DAOTypes.ORDERDETAILS);
        convertor=new Convertor();
    }

    @Override
    public boolean PlaceAOrder(PlaceOrderDTO placeOrderDTO) {
        session= HbFactoryConfiguration.getInstance().getSession();
        transaction=session.beginTransaction();
        try {
            Order o = new Order();
            o.setOrderId(placeOrderDTO.getOrderId());
            o.setDate(placeOrderDTO.getDate());
            o.setCustomer(customerDAO.findByPk(placeOrderDTO.getCustomerPK(), session));
            o.setTotalCost(placeOrderDTO.getTotalCost());

            for(lk.ijse.groceryshop.modal.OrderDetails od:placeOrderDTO.getOrdersDetailsList()){
                OrderDetails newod = new OrderDetails(1,od.getQty(),o, itemDAO.findByPk(od.getItemPk(),session));
                o.getOrderDetailsList().add(newod);
                orderDetailsDAO.save(newod,session);


                Item i =  itemDAO.findByPk(od.getItemPk(),session);

                Item newi = new Item();
                newi.setCode(i.getCode());
                newi.setDescription(i.getDescription());
                newi.setUnitPrice(i.getUnitPrice());
                newi.setQtyOnHand( i.getQtyOnHand()-od.getQty() );
                newi.setOrderDetailsList(i.getOrderDetailsList());


                itemDAO.update(
                        newi,
                        session
                );

            }

            orderDAO.save(o,session);

            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (session!=null)
                transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }
}
