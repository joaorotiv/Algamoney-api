package com.example.algamoney.api.services;

import com.example.algamoney.api.exceptionhandler.AlgamoneyExceptionHandler;
import com.example.algamoney.api.models.LaunchModel;
import com.example.algamoney.api.models.PersonModel;
import com.example.algamoney.api.repositories.LaunchRepository;
import com.example.algamoney.api.repositories.PersonRepository;
import com.example.algamoney.api.services.exceptions.InexistentOrInactivePerson;
import org.apache.catalina.valves.rewrite.ResolverImpl;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LaunchService {

    @Autowired
    private LaunchRepository launchRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MessageSource messageSource;


    public LaunchModel updateLaunch(Long id, LaunchModel launchModel) {
        LaunchModel savedLaunch = this.launchRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        BeanUtils.copyProperties(launchModel, savedLaunch, "id");
        return launchRepository.save(savedLaunch);
    }

    //salvar lançamento verificando se 'pessoa' está ativo, senão não deve ser lançado
    public LaunchModel saveLaunch(@Valid LaunchModel launch) throws InexistentOrInactivePerson {
        Optional<PersonModel> person = personRepository.findById(launch.getPersonId().getId());
        if(!person.isPresent() || person.get().isInactive()) {
            throw new InexistentOrInactivePerson();
        }
        return launchRepository.save(launch);
    }


}
