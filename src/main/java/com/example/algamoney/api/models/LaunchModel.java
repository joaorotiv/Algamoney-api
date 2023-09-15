package com.example.algamoney.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "launch")
public class LaunchModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String description;

    @NotNull
    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @NotNull
    private BigDecimal price;

    private String observation;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LaunchType type;

    @NotNull
    @ManyToOne
    @JoinColumn(name= "category_id")
    private CategoryModel categoryId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonModel personId;

}
