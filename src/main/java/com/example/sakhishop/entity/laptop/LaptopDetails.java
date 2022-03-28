package com.example.sakhishop.entity.laptop;


import com.example.sakhishop.entity.BrandEntity;
import com.example.sakhishop.entity.ColorEntity;
import com.example.sakhishop.entity.phone.Phone;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "laptop_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaptopDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private int year;

    @Column(name = "display")
    private String display;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "laptop_drive")
    private LaptopDriveType driveType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "laptop_system")
    private LaptopSystem system;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "laptop_image",
            joinColumns = @JoinColumn(name = "laptop_details_id"),
            inverseJoinColumns = @JoinColumn(name = "laptop_colors_id"))
    private List<LaptopColors> colors;

    @OneToMany(mappedBy = "laptopDetails")
    private List<LaptopImage> images;

}
