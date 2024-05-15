package Enum;
import lombok.Getter;

public enum EndPoints {

    users("/api/users");
    @Getter
    private final String value;

    EndPoints(String value) {
        this.value = value;
    }


}
