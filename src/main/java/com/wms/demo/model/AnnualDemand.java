package com.wms.demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = AnnualDemand.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnnualDemand {
    public static final String TABLE_NAME = "annual_demand";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory", referencedColumnName = "id")
    private Inventory inventory;
    @Column(name = "annual_demand")
    private double annualDemand;
    @Column(name = "annual_usage_value")
    private double annualUsageValue;
    @Column(name = "usage_val_proportion")
    private double usageValProportion;
    @Column(name = "cumulative_proportion")
    private double cumulativeProportion;

}
