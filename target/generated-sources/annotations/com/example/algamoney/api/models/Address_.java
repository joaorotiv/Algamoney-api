package com.example.algamoney.api.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Address.class)
public abstract class Address_ {

	public static volatile SingularAttribute<Address, String> city;
	public static volatile SingularAttribute<Address, String> postalCode;
	public static volatile SingularAttribute<Address, String> houseNumber;
	public static volatile SingularAttribute<Address, String> place;
	public static volatile SingularAttribute<Address, String> neighborhood;
	public static volatile SingularAttribute<Address, String> state;
	public static volatile SingularAttribute<Address, String> complement;

	public static final String CITY = "city";
	public static final String POSTAL_CODE = "postalCode";
	public static final String HOUSE_NUMBER = "houseNumber";
	public static final String PLACE = "place";
	public static final String NEIGHBORHOOD = "neighborhood";
	public static final String STATE = "state";
	public static final String COMPLEMENT = "complement";

}

