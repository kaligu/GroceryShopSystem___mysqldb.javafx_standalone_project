package lk.ijse.groceryshop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "order_code")
    private String orderId;

    private String date;

    @Column(name = "order_total_cost")
    private double totalCost;

    @ManyToOne
    @JoinColumn(
            name = "customer_id"
    )
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = {
            CascadeType.ALL
    })
    private List<OrderDetails> orderDetailsList= new ArrayList<>();

}
