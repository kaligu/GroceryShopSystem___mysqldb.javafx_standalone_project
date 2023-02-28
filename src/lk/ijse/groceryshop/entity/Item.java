package lk.ijse.groceryshop.entity;

import javafx.scene.control.Button;
import lombok.*;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "Item")
public class Item implements SuperEntity {
    @Id @Column
    private String code;
    @Column
    private String description;
    @Column
    private double unitPrice;
    @Column
    private int qtyOnHand;

}
