package lk.ijse.groceryshop.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "order_details")
public class OrderDetails {
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
}
