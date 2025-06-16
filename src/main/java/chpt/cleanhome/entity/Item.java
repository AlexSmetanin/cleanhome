package chpt.cleanhome.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idOrder", nullable = false)
    private Long idOrder;

    @Column(name = "idProduct", nullable = false)
    private Long idProduct;

    @Column(name = "quantity", nullable = false)
    private Long quantity;
 }