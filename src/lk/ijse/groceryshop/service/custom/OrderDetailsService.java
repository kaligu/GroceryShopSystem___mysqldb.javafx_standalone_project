package lk.ijse.groceryshop.service.custom;

import lk.ijse.groceryshop.dto.CustomerDTO;
import lk.ijse.groceryshop.dto.OrderDetailsDTO;
import lk.ijse.groceryshop.entity.OrderDetails;
import lk.ijse.groceryshop.service.SuperService;

public interface OrderDetailsService extends SuperService {

    boolean saveOrderDetails(OrderDetailsDTO orderDetailsDTO);
}
