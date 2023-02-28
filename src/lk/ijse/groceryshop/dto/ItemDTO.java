package lk.ijse.groceryshop.dto;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private String code;
    private String description;
    private double unitPrice;
    private int qtyOnHand;

}
