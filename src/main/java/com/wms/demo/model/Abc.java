package com.wms.demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = Abc.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Abc {
    public static final String TABLE_NAME = "abc_analysis";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "range_abc")
    private Integer range;

}

