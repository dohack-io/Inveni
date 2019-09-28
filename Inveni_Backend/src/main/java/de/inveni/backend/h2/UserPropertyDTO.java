package de.inveni.backend.h2;

public class UserPropertyDTO {
    private long userID;
    private long propertyID;

    public UserPropertyDTO() {

    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(long propertyID) {
        this.propertyID = propertyID;
    }
}
