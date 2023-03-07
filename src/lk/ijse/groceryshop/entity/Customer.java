package lk.ijse.groceryshop.entity;

import lk.ijse.groceryshop.embeded.CustMobile;
import lk.ijse.groceryshop.embeded.CustName;
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
@Table(name = "customer")
public class Customer implements SuperEntity{
    @Id
    @Column(name = "cust_id")
    private String id;

    @Column(name = "cust_name")
    private CustName name;

    @ElementCollection //hoynna
    @CollectionTable(
            name = "phone",
            joinColumns = @JoinColumn(name ="cust_Id") //hedena phone table eke id ekata den name eka
    )
    private List<CustMobile> phoneNos;

    @Column(name = "cust_address")
    private String address;

    @Column(name = "cust_salary")
    private double salary;

    @OneToMany(mappedBy = "customer")
    private List<Order> ordersList= new ArrayList<>();

}

