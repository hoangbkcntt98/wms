package com.wms.demo.dto.request;

import com.wms.demo.model.Inventory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class InventoryUpdateDTO {
    private int id;
    private Inventory data;
}
