package com.example.algamoney.api.repositories.launch;

import com.example.algamoney.api.models.LaunchModel;
import com.example.algamoney.api.models.LaunchModel_;
import com.example.algamoney.api.repositories.filters.LaunchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class LaunchRepositoryImpl implements LaunchRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;

    public Page<LaunchModel> filter(LaunchFilter launchFilter, Pageable pageable) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<LaunchModel> criteria = builder.createQuery(LaunchModel.class);
        Root<LaunchModel> launchRoot = criteria.from(LaunchModel.class);

        // criar restrições
        Predicate[] predicates= createRestrictions(launchFilter, builder, launchRoot);
        criteria.where(predicates);

        TypedQuery<LaunchModel> query = manager.createQuery(criteria);
        addPaginationRestrictions(query, pageable);


        return new PageImpl<>(query.getResultList(), pageable, total(launchFilter));
    }

    private Predicate[] createRestrictions(LaunchFilter launchFilter, CriteriaBuilder builder,
                                           Root<LaunchModel> launchRoot) {

        List<Predicate> predicates = new ArrayList<>();
        if (!ObjectUtils.isEmpty(launchFilter.getDescription())){
            predicates.add(builder.like(
                    builder.lower(launchRoot.get(LaunchModel_.description)), "%" + launchFilter.getDescription().toLowerCase() + "%"));
        }
        if ( launchFilter.getFromDueDate() != null) {
            predicates.add(
                    builder.greaterThanOrEqualTo(launchRoot.get(LaunchModel_.dueDate), launchFilter.getFromDueDate()));
        }
        if ( launchFilter.getToDueDate() != null) {
            predicates.add(
                    builder.lessThanOrEqualTo(launchRoot.get(LaunchModel_.dueDate), launchFilter.getToDueDate()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void addPaginationRestrictions(TypedQuery<LaunchModel> query, Pageable pageable) {

        int currentPage = pageable.getPageNumber();
        int totalRegistersPerPage = pageable.getPageSize();
        int firstPageRegister = currentPage * totalRegistersPerPage;

        query.setFirstResult(firstPageRegister);
        query.setMaxResults(totalRegistersPerPage);
    }

    private Long total(LaunchFilter launchFilter) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<LaunchModel> launchRoot = criteria.from(LaunchModel.class);

        Predicate[] predicates = createRestrictions(launchFilter, builder, launchRoot);
        criteria.where(predicates);

        criteria.select(builder.count(launchRoot));
        return manager.createQuery(criteria).getSingleResult();
    }

}
