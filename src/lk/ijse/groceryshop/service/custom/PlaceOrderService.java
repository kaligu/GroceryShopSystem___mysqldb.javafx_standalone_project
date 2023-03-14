package lk.ijse.groceryshop.service.custom;

import lk.ijse.groceryshop.dto.OrderDetailsDTO;
import lk.ijse.groceryshop.dto.OrdersDTO;
import lk.ijse.groceryshop.dto.PlaceOrderDTO;
import lk.ijse.groceryshop.entity.OrderDetails;
import lk.ijse.groceryshop.service.SuperService;

public interface PlaceOrderService extends SuperService {

    boolean PlaceAOrder(PlaceOrderDTO placeOrderDTO);
}
