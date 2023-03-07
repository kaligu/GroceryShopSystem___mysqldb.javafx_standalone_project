package lk.ijse.groceryshop.dto;

import lk.ijse.groceryshop.entity.Order;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDTO {
    private String id;
    private String name;
    private String address;
    private double salary;
}
