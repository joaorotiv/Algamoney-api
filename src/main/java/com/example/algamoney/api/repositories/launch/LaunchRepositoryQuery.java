package com.example.algamoney.api.repositories.launch;

import com.example.algamoney.api.models.LaunchModel;
import com.example.algamoney.api.repositories.filters.LaunchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LaunchRepositoryQuery {

    public Page<LaunchModel> filter(LaunchFilter launchFilter, Pageable pageable);
}
