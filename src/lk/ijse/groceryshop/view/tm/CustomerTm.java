package lk.ijse.groceryshop.view.tm;

import javafx.scene.control.Button;
import lk.ijse.groceryshop.embeded.CustMobile;
import lk.ijse.groceryshop.embeded.CustName;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerTm {
    private String id;
    private String fname;
    private String mname;
    private String lname;
    private String address;
    private double salary;
    private Button btn;
    private String phonenos;

}
