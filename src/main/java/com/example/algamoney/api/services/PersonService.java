package com.example.algamoney.api.services;

import com.example.algamoney.api.models.PersonModel;
import com.example.algamoney.api.repositories.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonModel updatePerson(Long id, PersonModel personModel) {
        PersonModel savedPerson = searchPersonByID(id);
        BeanUtils.copyProperties(personModel, savedPerson, "id");
        return personRepository.save(savedPerson);
    }

    public void updateActivityProperty(Long id, Boolean activity) {
        PersonModel savedPerson = searchPersonByID(id);
        savedPerson.setActivity(activity);
        personRepository.save(savedPerson);

    }

    public PersonModel searchPersonByID(Long id) {
        PersonModel savedPerson = this.personRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return savedPerson;
    }


}
