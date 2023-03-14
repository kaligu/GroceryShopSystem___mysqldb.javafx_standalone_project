package lk.ijse.groceryshop.dto;

import lk.ijse.groceryshop.entity.Customer;
import lk.ijse.groceryshop.entity.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
    private String orderId;

    private String date;

    private double totalCost;

    private Customer customer;

    private List<OrderDetails> orderDetailsList= new ArrayList<>();
}
