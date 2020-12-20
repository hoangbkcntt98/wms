package com.wms.demo.dto.request;

import com.wms.demo.model.AnnualDemand;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnnualDemandUpdateDTO {
    private Integer id;
    private AnnualDemand data;
}
