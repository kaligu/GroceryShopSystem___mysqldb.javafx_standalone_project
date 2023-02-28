package lk.ijse.groceryshop.entity;

import lombok.*;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "Customer")
public class Customer implements SuperEntity{
    @Id @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "salary")
    private double salary;
}
