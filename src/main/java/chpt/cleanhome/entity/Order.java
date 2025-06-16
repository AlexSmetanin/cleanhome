package chpt.cleanhome.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "dateTime", nullable = false)
    private String dateTime;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "sum")
    private Double sum;

    @Column(name = "payMethod", nullable = false)
    private String payMethod;

    @Column(name = "status", nullable = false)
    private String status;

}