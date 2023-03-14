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
@Table(name = "orders")
public class Order implements SuperEntity{
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

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", date='" + date + '\'' +
                ", totalCost=" + totalCost +
                ", customer=" + customer +

                '}';
    }
}
