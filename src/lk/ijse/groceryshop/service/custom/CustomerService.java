package lk.ijse.groceryshop.service.custom;

import lk.ijse.groceryshop.dto.CustomerDTO;
import lk.ijse.groceryshop.service.SuperService;

import java.util.List;

public interface CustomerService extends SuperService {
    public CustomerDTO saveCustomer(CustomerDTO customerDTO);

    public CustomerDTO updateCustomer(CustomerDTO customerDTO);

    public void deleteCustomer(String pk);

    public List<CustomerDTO> findAllCustomers();

    public CustomerDTO findCustomerByPk(String pk);

    public List<CustomerDTO> searchCustomerByText(String text);

}
