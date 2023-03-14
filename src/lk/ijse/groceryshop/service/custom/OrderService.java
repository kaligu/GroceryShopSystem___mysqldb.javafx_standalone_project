package lk.ijse.groceryshop.service.custom;

import lk.ijse.groceryshop.dto.OrderDetailsDTO;
import lk.ijse.groceryshop.dto.OrdersDTO;
import lk.ijse.groceryshop.service.SuperService;

public interface OrderService extends SuperService {

    String getLastorderID();
}
