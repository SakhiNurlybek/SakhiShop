package com.example.sakhishop.entity.laptop;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "laptop_colors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaptopColors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "laptopDetails")
    private List<LaptopImage> color;
}
