package com.example.sakhishop.entity.laptop;


import com.example.sakhishop.entity.ColorEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "laptop")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "laptop_image_id")
    private LaptopImage laptopImage;

    @ManyToOne
    @JoinColumn(name = "laptop_memory_id")
    private LaptopMemory memory;

    @Column(name = "price")
    private int price;

    @Column(name = "amount")
    private int amount;
}
