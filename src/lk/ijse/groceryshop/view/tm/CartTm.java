package lk.ijse.groceryshop.view.tm;

import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CartTm {
    private String code;
    private String description;
    private double unitPrice;
    private int qty;
    private double total;
    private Button btn;
}
