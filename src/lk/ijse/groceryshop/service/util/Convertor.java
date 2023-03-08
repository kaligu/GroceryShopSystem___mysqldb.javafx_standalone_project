package lk.ijse.groceryshop.service.util;

import lk.ijse.groceryshop.dto.CustomerDTO;
import lk.ijse.groceryshop.dto.ItemDTO;
import lk.ijse.groceryshop.dto.OrderDetailsDTO;
import lk.ijse.groceryshop.dto.OrdersDTO;
import lk.ijse.groceryshop.embeded.CustMobile;
import lk.ijse.groceryshop.embeded.CustName;
import lk.ijse.groceryshop.entity.Customer;
import lk.ijse.groceryshop.entity.Item;
import lk.ijse.groceryshop.entity.Order;
import lk.ijse.groceryshop.entity.OrderDetails;

import java.util.ArrayList;
import java.util.List;

public class Convertor {
    public CustomerDTO fromCustomer(Customer customer){
        return new CustomerDTO(customer.getId(), customer.getName(),customer.getPhoneNos(), customer.getAddress(),customer.getSalary(), customer.getOrdersList());
    }


    public Customer toCustomer(CustomerDTO customerDTO){
        return new Customer(customerDTO.getId(), customerDTO.getName(),customerDTO.getPhoneNosArray(), customerDTO.getAddress(),customerDTO.getSalary(), customerDTO.getOrdersList());
    }
/*
    public List<CustomerDTO> fromCustomerList(List<Customer> customerList){
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer c:customerList){
            customerDTOS.add(new CustomerDTO(c.getId(),c.getName(), c.getAddress(),c.getSalary(), (ArrayList<CustMobile>) c.getPhoneNos()));
        }
        return customerDTOS;
    }

 */
/*
    private List<Order> toOrderList(List<OrdersDTO> ordersDTOS) {
        List<Order> orders = new ArrayList<>();
        for(OrdersDTO od:ordersDTOS){
            orders.add(new Order(od.getOrderId(),od.getDate(),od.getTotalCost(),toCustomer(od.getCustomerDTO()),toOrderDetailsList(od.getOrderDetailsDTOList())));
        }
        return orders;
    }

    private List<OrdersDTO> fromOrderList(List<Order> orderslist) {
        List<OrdersDTO> ordersDTOS = new ArrayList<>();
        for(Order od:orderslist){
            ordersDTOS.add(new OrdersDTO(od.getOrderId(),od.getDate(),od.getTotalCost(),fromCustomer(od.getCustomer()),fromOrderDetailsList(od.getOrderDetailsList())));
        }
        return ordersDTOS;
    }

    public Item toItem(ItemDTO itemDTO){
        return new Item(itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand(),toOrderDetailsList(itemDTO.getOrderDetailsDTOList()));
    }

    public ItemDTO fromItem(Item item){
        return new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand(),fromOrderDetailsList(item.getOrderDetailsList()));
    }

    public List<OrderDetails> toOrderDetailsList(List<OrderDetailsDTO> orderDetailsDTOS){
        List<OrderDetails> orderDetails = new ArrayList<>();
        for (OrderDetailsDTO odt:orderDetailsDTOS){
            orderDetails.add(new OrderDetails(odt.getId(),odt.getQty(),toOrder(odt.getOrdersDTO()),toItem(odt.getItemDTO())));
        }
        return orderDetails;
    }

    public List<OrderDetailsDTO> fromOrderDetailsList(List<OrderDetails> orderDetails){
        List<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>();
        for (OrderDetails od:orderDetails){
            orderDetailsDTOS.add(new OrderDetailsDTO(od.getId(),od.getQty(),fromOrder(od.getOrder()),fromItem(od.getItem())));
        }
        return orderDetailsDTOS;
    }

    public Order toOrder(OrdersDTO ordersDTO){
        return new Order(ordersDTO.getOrderId(),ordersDTO.getDate(),ordersDTO.getTotalCost(),toCustomer(ordersDTO.getCustomerDTO()),toOrderDetailsList(ordersDTO.getOrderDetailsDTOList()));
    }

    public OrdersDTO fromOrder(Order order){
        return new OrdersDTO(order.getOrderId(),order.getDate(),order.getTotalCost(),fromCustomer(order.getCustomer()),fromOrderDetailsList(order.getOrderDetailsList()));
    }

    public Order toOrderDetails(OrdersDTO ordersDTO){
        return new Order(ordersDTO.getOrderId(),ordersDTO.getDate(),ordersDTO.getTotalCost(),toCustomer(ordersDTO.getCustomerDTO()),toOrderDetailsList(ordersDTO.getOrderDetailsDTOList()));
    }

    public OrdersDTO fromOrderDetails(Order order){
        return new OrdersDTO(order.getOrderId(),order.getDate(),order.getTotalCost(),fromCustomer(order.getCustomer()),fromOrderDetailsList(order.getOrderDetailsList()));
    }

    public List<CustomerDTO> fromCustomerList(List<Customer> customerList){
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer c:customerList){
            customerDTOS.add(new CustomerDTO(c.getId(),c.getName(), c.getAddress(),c.getSalary(), fromOrderList(c.getOrdersList())));
        }
        return customerDTOS;
    }

     */



}
