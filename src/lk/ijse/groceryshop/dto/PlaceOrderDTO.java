package lk.ijse.groceryshop.dto;

import lk.ijse.groceryshop.entity.Customer;
import lk.ijse.groceryshop.entity.Order;
import lk.ijse.groceryshop.modal.OrderDetails;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PlaceOrderDTO {
    private String orderId;

    private String date;

    private double totalCost;

    private String customerPK;

    private List<OrderDetails> ordersDetailsList= new ArrayList<>();
}
