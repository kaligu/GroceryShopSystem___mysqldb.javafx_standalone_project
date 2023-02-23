package lk.ijse.groceryshop.service.util;

import lk.ijse.groceryshop.dto.CustomerDTO;
import lk.ijse.groceryshop.entity.Customer;

public class Convertor {
    public CustomerDTO fromCustomer(Customer customer){
        return new CustomerDTO(customer.getId(),customer.getName(), customer.getAddress(),customer.getSalary());
    }
    public Customer toCustomer(CustomerDTO customerDTO){
        return new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getSalary());
    }
}
