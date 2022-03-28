package com.example.sakhishop.entity;

import com.example.sakhishop.entity.phone.PhoneDetails;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "color")
public class ColorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER,
    mappedBy = "colors")
    private List<PhoneDetails> phones;



}
