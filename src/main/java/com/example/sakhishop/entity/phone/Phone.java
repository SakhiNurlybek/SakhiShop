package com.example.sakhishop.entity.phone;


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
@Table(name = "phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "t_phone_id")
    private PhoneDetails phone;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private ColorEntity color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "memory_id")
    private MemoryEntity memory;

    @Column(name = "amount")
    private  int amount;

    @Column(name = "price")
    private int price;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "phone")
    private List<ImageEntity> images;

}
