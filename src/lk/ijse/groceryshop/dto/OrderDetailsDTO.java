package lk.ijse.groceryshop.dto;

import lk.ijse.groceryshop.entity.Item;
import lk.ijse.groceryshop.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {
    private long id;

    private int qty;

    private OrdersDTO ordersDTO;

    private ItemDTO itemDTO;
}
