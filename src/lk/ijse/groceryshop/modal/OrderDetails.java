package lk.ijse.groceryshop.modal;

import lk.ijse.groceryshop.entity.Item;
import lk.ijse.groceryshop.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    private int qty;

    private String itemPk;
}
