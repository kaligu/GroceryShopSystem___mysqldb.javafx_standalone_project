package lk.ijse.groceryshop.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "order_details")
public class OrderDetails implements SuperEntity{
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;

    private int qty;

    @ManyToOne
    @JoinColumn(
            name = "order_code"
    )
    private Order order;

    @ManyToOne
    @JoinColumn(
            name = "item_code"
    )
    private Item item;

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", qty=" + qty +
                '}';
    }
}
