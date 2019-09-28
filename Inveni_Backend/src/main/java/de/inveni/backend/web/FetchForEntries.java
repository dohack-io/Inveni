package de.inveni.backend.web;

import de.inveni.backend.h2.Property;
import de.inveni.backend.h2.PropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FetchForEntries {

    private PropertiesRepository repo;

    @Autowired
    public FetchForEntries(PropertiesRepository repo){
        this.repo = repo;
    }

    @GetMapping("/fetchitem")
    public List<Property> fetchItem(@RequestParam(value="dateBefore", required = true) long dateBefore, @RequestParam(value="dateAfter", required = true) long dateAfter, @RequestParam(value="desc", required = true) String desc) {
        System.out.printf("DateBefore: %s, DateAfter: %s, Description: %s\n", dateBefore, dateAfter, desc);
        return repo.queryItems(dateBefore, dateAfter, desc);
    }

    @PostMapping(value = "/additem", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Property addItem(@RequestBody Property property) {
        return repo.save(property);
    }

}
