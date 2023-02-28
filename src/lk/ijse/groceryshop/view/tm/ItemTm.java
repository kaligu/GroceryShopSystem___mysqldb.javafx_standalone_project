package lk.ijse.groceryshop.view.tm;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemTm {
    private String code;
    private String description;
    private double unitPrice;
    private int qtyOnHand;
    private Button btn;

}
