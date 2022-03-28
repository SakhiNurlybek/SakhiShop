package com.example.sakhishop.entity.laptop;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "laptop_drive")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaptopDriveType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "drive_type")
    private String type;
}
