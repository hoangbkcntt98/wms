package com.wms.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = Risk.TABLE_NAME)
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Data
public class Risk {
    public static final String TABLE_NAME = "risk_relation";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "risk_id")
    private Integer riskId;
    @Column(name = "parents")
    private String parents;
    @OneToMany
    private List<Risk> parentsList;

}

