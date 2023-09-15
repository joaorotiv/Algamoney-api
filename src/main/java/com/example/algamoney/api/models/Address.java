package com.example.algamoney.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Column(name= "place")
    @Size(min=3, max=30)
    private String place;

    @Column(name= "house_number")
    @Size(min=3, max=10)
    private String houseNumber;

    @Column(name= "complement")
    @Size(min=3, max=30)
    private String complement;

    @Column(name= "neighborhood")
    @Size(min=3, max=20)
    private String neighborhood;

    @Column(name= "postal_code")
    @Size(min=3, max=10)
    private String postalCode;

    @Column(name= "city")
    @Size(min=3, max=20)
    private String city;

    @Column(name= "state")
    @Size(min=3, max=20)
    private String state;

}