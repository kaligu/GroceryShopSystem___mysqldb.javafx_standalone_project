package lk.ijse.groceryshop.dto;

import javafx.scene.control.Button;
import lk.ijse.groceryshop.entity.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private String code;
    private String description;
    private double unitPrice;
    private int qtyOnHand;
    private List<OrderDetails> orderDetailsList= new ArrayList<>();
}
