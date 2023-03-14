package lk.ijse.groceryshop.modal;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ItemDetails {
    private String code;
    private double unitPrice;
    private int qty;
}
