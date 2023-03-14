package lk.ijse.groceryshop.modal;

import lk.ijse.groceryshop.entity.OrderDetails;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private String orderId;// D-1
    private Date date;
    private double totalCost;
    private String customer;
    private ArrayList<ItemDetails> itemDetails;

}
