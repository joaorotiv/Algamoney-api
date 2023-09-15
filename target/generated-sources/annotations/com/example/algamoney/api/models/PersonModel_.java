package com.example.algamoney.api.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PersonModel.class)
public abstract class PersonModel_ {

	public static volatile SingularAttribute<PersonModel, Address> address;
	public static volatile SingularAttribute<PersonModel, Boolean> activity;
	public static volatile SingularAttribute<PersonModel, String> name;
	public static volatile SingularAttribute<PersonModel, Long> id;

	public static final String ADDRESS = "address";
	public static final String ACTIVITY = "activity";
	public static final String NAME = "name";
	public static final String ID = "id";

}

