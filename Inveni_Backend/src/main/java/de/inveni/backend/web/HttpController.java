package de.inveni.backend.web;

import de.inveni.backend.h2.*;
import de.inveni.backend.util.Toolbox;
import de.inveni.backend.util.ToolboxInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class HttpController {

    private PropertyRepository propertyRepository;
    private UserRepository userRepository;
    private CountryRepository countryRepository;
    private ToolboxInterface toolbox;

    @Autowired
    public HttpController(PropertyRepository propertyRepository, UserRepository userRepository, CountryRepository countryRepository, ToolboxInterface toolbox) {
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
        this.countryRepository = countryRepository;
        this.toolbox = toolbox;
    }

    @GetMapping("/fetchpropertiesbyattribsforuser")
    public List<Property> fetchPropertiesByAttribs(@RequestParam(value = "userID", required = true) long userID, @RequestParam(value = "dateBefore", required = true) long dateBefore, @RequestParam(value = "dateAfter", required = true) long dateAfter, @RequestParam(value = "desc", required = true) String desc, @RequestParam(value = "lat", required = true) double lat, @RequestParam(value = "lon", required = true) double lon, @RequestParam(value = "radius", required = true) double radius) {
        List<Property> possibleProperties = propertyRepository.queryItemsWithoutFinder(userID, dateBefore, dateAfter, desc);
        List<Property> properties = new ArrayList<>();
        for(Property property:possibleProperties){
            if(toolbox.getDistance(lat, lon, property.getLatitude(), property.getLongitude())<=radius){
                properties.add(property);
            }
        }
        return properties;
    }

    @GetMapping("/fetchpropertiesbyfinder")
    public List<Property> fetchPropertiesByAttribs(@RequestParam(value = "userID", required = true) long userID) {
        return propertyRepository.queryItemsforFinder(userRepository.getById(userID));
    }

    @GetMapping("/fetchpropertybyid")
    public Property fetchPropertyById(@RequestParam(value = "id", required = true) long id){
        return propertyRepository.getById(id);
    }

    @GetMapping("deleteProperty")
    public Property deleteProperty(@RequestParam(value="id", required = true) long id){
        Property p = propertyRepository.getById(id);
        propertyRepository.deleteById(id);
        return p;
    }

    @PostMapping(value = "/addproperty", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Property addProperty(@RequestBody PropertyDTO propertyDTO) {
        return propertyRepository.save(toolbox.toProperty(propertyDTO));
    }

    @PostMapping(value = "/addpossibleowner", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Property addPossibleOwner(@RequestBody UserPropertyDTO pair){
        Property property = propertyRepository.getById(pair.getPropertyID());
        User user = userRepository.getById(pair.getUserID());
        if(Objects.nonNull(user) && Objects.nonNull(property)){
            property.addUser(user);
            return propertyRepository.save(property);
        }
        return null;
    }

    @PostMapping(value = "/removepossibleowner", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Property removePossibleOwner(@RequestBody UserPropertyDTO pair){
        Property property = propertyRepository.getById(pair.getPropertyID());
        User user = userRepository.getById(pair.getPropertyID());
        if(Objects.nonNull(user) && Objects.nonNull(property)){
            property.removeUser(user);
            return propertyRepository.save(property);
        }
        return null;
    }

    @GetMapping("/fetchuserbyid")
    public User fetchUserById(@RequestParam(value = "id", required = true) long id){
        return userRepository.getById(id);
    }

    @GetMapping("deleteuser")
    public User deleteUser(@RequestParam(value="id", required = true) long id){
        User u = userRepository.getById(id);
        userRepository.deleteById(id);
        return u;
    }

    @PostMapping(value = "/adduser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody UserDTO user) {
        return userRepository.save(toolbox.toUser(user));
    }

    @PostMapping(value = "/addpossibleproperty", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User addPossibleProperty(@RequestBody UserPropertyDTO pair){
        User user = userRepository.getById(pair.getUserID());
        Property property = propertyRepository.getById(pair.getPropertyID());
        if(Objects.nonNull(user) && Objects.nonNull(property)){
            user.addProperty(property);
            return userRepository.save(user);
        }
        return null;
    }

    @PostMapping(value = "/removepossibleproperty", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User removePossibleProperty(@RequestBody UserPropertyDTO pair){
        User user = userRepository.getById(pair.getUserID());
        Property property = propertyRepository.getById(pair.getPropertyID());
        if(Objects.nonNull(user) && Objects.nonNull(property)){
            user.removeProperty(property);
            return userRepository.save(user);
        }
        return null;
    }

}
