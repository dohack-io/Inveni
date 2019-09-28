package de.inveni.backend.util;

import de.inveni.backend.h2.Country;
import de.inveni.backend.h2.CountryRepository;
import de.inveni.backend.h2.User;
import de.inveni.backend.h2.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.tools.Tool;

@Component
public class Toolbox implements ToolboxInterface {

    private CountryRepository countryRepository;

    @Autowired
    public Toolbox(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    public double getDistance(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);

        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        return 6378.388 * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1));
    }

    public User toUser(UserDTO userDTO){
        Country c = countryRepository.getById(userDTO.getCountryID());
        return new User(userDTO.getName(),userDTO.getGivenName(),userDTO.getStreet(),userDTO.getHouseNumber(),userDTO.getPlz(),userDTO.getEmail(),userDTO.getPhone(),c);
    }
}
