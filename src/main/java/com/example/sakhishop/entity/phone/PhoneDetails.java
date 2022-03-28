package com.example.sakhishop.entity.phone;

import com.example.sakhishop.entity.BrandEntity;
import com.example.sakhishop.entity.ColorEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_phone")
public class PhoneDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private int year;

    @Column(name = "height")
    private double height;

    @Column(name = "display")
    private String display;

    @Column(name = "ram")
    private int ram;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "phone",
    joinColumns = @JoinColumn(name = "t_phone_id"),
    inverseJoinColumns = @JoinColumn(name = "color_id"))
    private List<ColorEntity> colors;

    @OneToMany(mappedBy = "phone")
    private List<Phone> phones;

}
