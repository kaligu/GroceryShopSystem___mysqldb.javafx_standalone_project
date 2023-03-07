package lk.ijse.groceryshop.embeded;

import lombok.*;

import javax.persistence.Embeddable;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Embeddable
public class CustMobile {
    private String phoneNums;
}
