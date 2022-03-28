package com.example.sakhishop.entity.laptop;

import com.example.sakhishop.entity.ColorEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "laptop_image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaptopImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "laptop_details_id")
    private LaptopDetails laptopDetails;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private LaptopColors color;

    @Column(name = "image")
    private String Image;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "laptop",
            joinColumns = @JoinColumn(name = "laptop_image_id"),
            inverseJoinColumns = @JoinColumn(name = "laptop_memory_id"))
    private List<LaptopMemory> memories;

    @OneToMany(mappedBy = "laptopImage")
    private List<Laptop> laptops;

}
