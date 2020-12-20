package com.wms.demo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = Inventory.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Inventory {
    public static final String TABLE_NAME = "inventories";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "sku")
    private String sku;
    @Column(name = "exp")
    private LocalDateTime exp;
    @Column(name = "type")
    private Integer type;
    @Column(name = "status")
    private Integer status;
    @Column(name = "quantities")
    private double quantities;
    @Column(name = "class")
    private String inventoryClass;
    @Column(name = "price")
    private double price;

}
