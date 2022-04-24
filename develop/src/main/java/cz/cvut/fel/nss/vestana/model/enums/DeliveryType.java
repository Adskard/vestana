package cz.cvut.fel.nss.vestana.model.enums;

public enum DeliveryType {

    DELIVERY_SERVICE("DELIVERY_SERVICE"),
    PERSONAL("PERSONAL");

    private final String name;

    DeliveryType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
