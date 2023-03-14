package lk.ijse.groceryshop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


@Entity
@Table(name = "item")
public class Item implements SuperEntity{
    @Id
    @Column(name = "item_code")
    private String code;

    @Column(name = "item_description")
    private String description;

    @Column(name = "item_unit_price")
    private double unitPrice;

    @Column(name = "item_qty_on_hand")
    private int qtyOnHand;

    @OneToMany(mappedBy = "item", cascade = {
            CascadeType.ALL
    })
    private List<OrderDetails> orderDetailsList= new ArrayList<>();

    public Item(String code) {
    }

    @Override
    public String toString() {
        return "Item{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyOnHand=" + qtyOnHand +
                '}';
    }
}
