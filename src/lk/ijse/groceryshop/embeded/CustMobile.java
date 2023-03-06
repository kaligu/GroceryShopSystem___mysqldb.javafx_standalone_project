package lk.ijse.groceryshop.embeded;

import lombok.*;

import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Embeddable
public class CustMobile {
    private String mobileNo;
}
