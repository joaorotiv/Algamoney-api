package com.example.algamoney.api.resources;

import com.example.algamoney.api.event.CreatedEventResource;
import com.example.algamoney.api.exceptionhandler.AlgamoneyExceptionHandler;
import com.example.algamoney.api.models.LaunchModel;
import com.example.algamoney.api.repositories.LaunchRepository;
import com.example.algamoney.api.repositories.filters.LaunchFilter;
import com.example.algamoney.api.services.LaunchService;
import com.example.algamoney.api.services.exceptions.InexistentOrInactivePerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/launch")
public class LaunchResource {

    @Autowired
    private LaunchRepository launchRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private LaunchService launchService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<LaunchModel> searchLaunches(LaunchFilter launchFilter, Pageable pageable){
        return launchRepository.filter(launchFilter, pageable);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<LaunchModel> searchLaunchById(@PathVariable Long id){
        return launchRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<LaunchModel> createLaunch(@RequestBody @Valid LaunchModel launch, HttpServletResponse response) {
        LaunchModel savedLaunch = launchService.saveLaunch(launch);
        publisher.publishEvent(new CreatedEventResource(this, response, savedLaunch.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLaunch);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        launchRepository.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LaunchModel> updateLaunch(@PathVariable Long id, @Valid @RequestBody LaunchModel launchModel) {
        LaunchModel savedLaunch = launchService.updateLaunch(id, launchModel);
        return ResponseEntity.ok(savedLaunch);
    }


    //Lançamento de exceção para pessoa inativa e/ou inexistente ao tentar adicionar ao lançamento
    @ExceptionHandler({ InexistentOrInactivePerson.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleInexistentOrInactivePerson(InexistentOrInactivePerson ex){
        String userMessage = messageSource.getMessage("inexistent.or.inactive.person", null, LocaleContextHolder.getLocale());
        String developerMessage = ex.toString();
        List<AlgamoneyExceptionHandler.Error> errors = Arrays.asList( new AlgamoneyExceptionHandler.Error(userMessage,developerMessage));
        return ResponseEntity.badRequest().body(errors);
    }
}
