package cz.cvut.fel.nss.vestana.model.enums;

public enum ArticleState {

    BOOKED("BOOKED"),
    AVAILABLE("AVAILABLE"),
    IN_CLEANING("IN_CLEANING");

    private final String name;

    ArticleState(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
