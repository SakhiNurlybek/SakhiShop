package com.example.sakhishop.entity;

import com.example.sakhishop.entity.phone.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "phone_id")
    private Phone phone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "quantity")
    private int quantity;

    @Transient
    public float getSubtotal(){
        return this.phone.getPrice()*quantity;
    }
}
