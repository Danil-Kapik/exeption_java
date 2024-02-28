import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Person {
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate birthDate;
    private long phoneNumber;
    private char gender;

    public Person(String lastName, String firstName, String middleName, String birthDate, long phoneNumber, char gender) throws PersonDataException {
        if (lastName == null || firstName == null || middleName == null || birthDate == null)
            throw new PersonDataException("Все поля должны быть заполнены ");

        if (phoneNumber <= 0)
            throw new PersonDataException("Номер телефона должен быть целым положительным числом ");

        if (gender != 'ж' && gender != 'м')
            throw new PersonDataException("Пол должен быть 'ж' или 'м'");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = LocalDate.parse(birthDate, formatter);
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %d %c", lastName, firstName, middleName, birthDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), phoneNumber, gender);
    }
}
