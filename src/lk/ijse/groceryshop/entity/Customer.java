package lk.ijse.groceryshop.entity;

import lk.ijse.groceryshop.embeded.CustMobile;
import lk.ijse.groceryshop.embeded.CustName;
import lombok.*;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "Customer")
public class Customer implements SuperEntity{

    @Id @Column(name = "id")
    private String id;

    @Column(name = "name")
    private CustName name;

    @ElementCollection //hoynna
    @CollectionTable(
            name = "phone",
            joinColumns = @JoinColumn(name ="cust_Id") //hedena phone table eke id ekata den name eka
    )
    private List<CustMobile> phoneNos;

    @Column(name = "age" , columnDefinition = "SMALLINT")
    private int age;

    @Column(name = "address")
    private String address;

    @Column(name = "salary")
    private double salary;
}
