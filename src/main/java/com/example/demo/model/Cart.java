package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Set;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date dateCreated;

    @JsonManagedReference
    @Transient
    @JsonProperty
    @OneToMany(mappedBy = "cart", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<CartItem> cartItems;

    @Column(name = "cart_total")
    private Double cartTotal = 0.0;;

}
