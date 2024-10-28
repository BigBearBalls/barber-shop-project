package eu.senla.catalog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog/")
public class CatalogController {

    @GetMapping
    public String sayHello() {
        return "Hello";
    }

}