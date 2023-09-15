package com.example.algamoney.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
public class PersonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "name")
    @NotNull
    @Size(min=3, max=50)
    private String name;

    @Embedded
    private Address address;

    @Column(name= "activity")
    @NotNull
    private Boolean activity;

    @JsonIgnore
    @Transient
    public boolean isInactive(){
      return !this.activity;
    }

}
