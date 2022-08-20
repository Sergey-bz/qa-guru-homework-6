package com.demoqa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.String.*;

public class StudentRegistrationFormTest extends BaseTest {

    Faker faker = new Faker();

    private String firstName,
            lastName,
            email,
            gender,
            userNumber,
            day,
            month,
            year,
            subjects,
            hobbies,
            picture,
            currentAddress,
            state,
            city;

    @BeforeEach
    void prepareTestData() {
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        email = faker.internet().emailAddress();
        gender = "Male";
        userNumber = faker.phoneNumber().subscriberNumber(10);
        day = valueOf(faker.number().numberBetween(10, 31));
        month = "October";
        year = valueOf(faker.number().numberBetween(1990, 2000));
        subjects = "Computer Science";
        hobbies = "Sports";
        picture = "face.png";
        currentAddress = faker.lebowski().quote();
        state = "Haryana";
        city = "Panipat";
    }

    @Test
    void fillFormTest() {
        registrationFormPage
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setUserNumber(userNumber)
                .setBirthDate(day, month, year)
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .uploadPicture(picture)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .pressSubmitButton();

        registrationFormPage
                .checkResultsModalIsVisible()
                .checkResult("Student Name", format("%s %s", firstName, lastName))
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", format("%s %s,%s", day, month, year))
                .checkResult("Subjects", subjects)
                .checkResult("Hobbies", hobbies)
                .checkResult("Picture", picture)
                .checkResult("Address", currentAddress)
                .checkResult("State and City", format("%s %s", state, city));
    }
}
