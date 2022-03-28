package com.example.sakhishop.entity.laptop;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "laptop_memory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaptopMemory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "memory")
    private int memory;

    @OneToMany(mappedBy = "memory")
    private List<Laptop> laptops;
}
