package lk.ijse.groceryshop.entity;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Customer implements SuperEntity{
    private String id;
    private String name;
    private String address;
    private double salary;

}