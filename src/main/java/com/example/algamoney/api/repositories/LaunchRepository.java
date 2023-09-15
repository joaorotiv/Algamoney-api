package com.example.algamoney.api.repositories;

import com.example.algamoney.api.models.LaunchModel;
import com.example.algamoney.api.repositories.launch.LaunchRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaunchRepository extends JpaRepository<LaunchModel, Long>, LaunchRepositoryQuery {
}
