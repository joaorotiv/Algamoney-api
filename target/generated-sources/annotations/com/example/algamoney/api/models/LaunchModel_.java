package com.example.algamoney.api.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LaunchModel.class)
public abstract class LaunchModel_ {

	public static volatile SingularAttribute<LaunchModel, BigDecimal> price;
	public static volatile SingularAttribute<LaunchModel, String> observation;
	public static volatile SingularAttribute<LaunchModel, LocalDate> dueDate;
	public static volatile SingularAttribute<LaunchModel, String> description;
	public static volatile SingularAttribute<LaunchModel, PersonModel> personId;
	public static volatile SingularAttribute<LaunchModel, Long> id;
	public static volatile SingularAttribute<LaunchModel, LocalDate> paymentDate;
	public static volatile SingularAttribute<LaunchModel, LaunchType> type;
	public static volatile SingularAttribute<LaunchModel, CategoryModel> categoryId;

	public static final String PRICE = "price";
	public static final String OBSERVATION = "observation";
	public static final String DUE_DATE = "dueDate";
	public static final String DESCRIPTION = "description";
	public static final String PERSON_ID = "personId";
	public static final String ID = "id";
	public static final String PAYMENT_DATE = "paymentDate";
	public static final String TYPE = "type";
	public static final String CATEGORY_ID = "categoryId";

}

