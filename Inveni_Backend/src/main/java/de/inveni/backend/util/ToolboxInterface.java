package de.inveni.backend.util;

import de.inveni.backend.h2.Property;
import de.inveni.backend.h2.PropertyDTO;
import de.inveni.backend.h2.User;
import de.inveni.backend.h2.UserDTO;

public interface ToolboxInterface {
    double getDistance(double lat1, double lon1, double lat2, double lon2);
    User toUser(UserDTO userTDO);
    Property toProperty(PropertyDTO propertyDTO);
}
