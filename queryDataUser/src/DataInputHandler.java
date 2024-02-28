import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class DataInputHandler {
    public static void handleInput() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Введите данные в формате: Фамилия Имя Отчество дата_рождения(dd.MM.yyyy) номер_телефона" +
                    " пол(м или ж), \nили введите 'stop' для завершения:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("stop")) {
                System.out.println("Программа завершена.");
                break;
            }

            String[] data = input.split("\\s+");
            if (data.length != 6) {
                System.out.println("Ошибка: неверное количество данных");
                continue;
            }

            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            String birthDate = data[3];
            long phoneNumber;
            char gender;

            try {
                phoneNumber = Long.parseLong(data[4]);
                gender = data[5].charAt(0);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Ошибка: неверный формат номера телефона или пола");
                continue;
            }

            try {
                Person person = new Person(lastName, firstName, middleName, birthDate, phoneNumber, gender);
                saveToFile(person);
                System.out.println("Данные успешно записаны в файл.");
            } catch (PersonDataException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        } while (true);
    }

    private static void saveToFile(Person person) {
        String filename = person.getLastName() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(person.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
