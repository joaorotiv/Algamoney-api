package com.example.algamoney.api.resources;

import com.example.algamoney.api.event.CreatedEventResource;
import com.example.algamoney.api.models.PersonModel;
import com.example.algamoney.api.repositories.PersonRepository;
import com.example.algamoney.api.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonResource {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PersonService personService;

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonModel>listPersons(){
        return personRepository.findAll();
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonModel> searchById(@PathVariable Long personId){
        return personRepository.findById(personId)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<PersonModel> create(@RequestBody @Valid PersonModel person, HttpServletResponse response) {
        PersonModel savedPerson = personRepository.save(person);
        publisher.publishEvent(new CreatedEventResource(this, response, savedPerson.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        personRepository.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PersonModel> updatePerson(@PathVariable Long id, @Valid @RequestBody PersonModel personModel) {
        PersonModel savedPerson = personService.updatePerson(id, personModel);
        return ResponseEntity.ok(savedPerson);
    }

    @PutMapping("/update/activity/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateActivityProperty(@PathVariable Long id, @RequestBody Boolean activity) {
        personService.updateActivityProperty(id, activity);
    }

}
