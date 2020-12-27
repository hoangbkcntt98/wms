package com.wms.demo.dto.request;

import com.wms.demo.model.Inventory;
import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryUpdateDTO {
    private int id;
    private Inventory data;
}
