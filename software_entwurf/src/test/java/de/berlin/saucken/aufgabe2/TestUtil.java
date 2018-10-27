package de.berlin.saucken.aufgabe2;

import com.github.javafaker.Faker;
import de.berlin.saucken.aufgabe1.model.User;


public class TestUtil {

    public static User generateValidFakeUser() {
        Faker faker = new Faker();
        return new User(faker.internet().emailAddress(), faker.name().username(), "abc123ABC%");
    }


}
