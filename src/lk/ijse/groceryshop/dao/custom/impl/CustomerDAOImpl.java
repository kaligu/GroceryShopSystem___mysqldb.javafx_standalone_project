package lk.ijse.groceryshop.dao.custom.impl;

import lk.ijse.groceryshop.dao.custom.CustomerDAO;
import lk.ijse.groceryshop.dto.CustomerDTO;
import lk.ijse.groceryshop.entity.Customer;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {
    private final Connection connection;

    public CustomerDAOImpl(Connection connection){
        this.connection=connection;
    }

    @Override
    public Customer save(Customer entity) {
        return null;
    }

    @Override
    public Customer update(Customer entity) {
        return null;
    }

    @Override
    public void deleteByPk(String pk) {

    }

    @Override
    public Optional<Customer> findByPk(String pk) {
        return Optional.empty();
    }

    @Override
    public boolean existByPk(String pk) {
        return false;
    }

    @Override
    public List<Customer> SearchCustomersByTesxt(String text) {
        return null;
    }
}
