package com.example.algamoney.api.event.listeners;

import com.example.algamoney.api.event.CreatedEventResource;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class CreatedListenerResource implements ApplicationListener<CreatedEventResource> {

    @Override
    public void onApplicationEvent(CreatedEventResource createdEventResource) {
        HttpServletResponse response = createdEventResource.getResponse();
        Long id = createdEventResource.getId();

        addHeaderLocation(response, id);
    }

    private static void addHeaderLocation(HttpServletResponse response, Long id) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(id).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }

}
