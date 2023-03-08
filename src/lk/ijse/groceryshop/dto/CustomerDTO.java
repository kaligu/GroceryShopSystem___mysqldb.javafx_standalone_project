package lk.ijse.groceryshop.dto;

import lk.ijse.groceryshop.embeded.CustMobile;
import lk.ijse.groceryshop.embeded.CustName;
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
    private CustName name;
    private List<CustMobile> phoneNosArray=new ArrayList<>();
    private String address;
    private double salary;
    private List<Order> ordersList= new ArrayList<>();
}
