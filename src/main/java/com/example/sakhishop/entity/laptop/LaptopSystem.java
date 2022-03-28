package com.example.sakhishop.entity.laptop;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "laptop_system")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaptopSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "system")
    private String system;

}
