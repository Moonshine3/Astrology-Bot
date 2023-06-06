package org.example;

public class ZodiacSignDeterminer {
    public static String getZodiacSign(String dateOfBirth) {
        if (!isDateOfBirthValid(dateOfBirth)) {
            return "Некоректна дата народження";
        }

        String[] parts = dateOfBirth.split("\\.");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);

        if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) {
            return "Водолій";
        } else if ((month == 2 && day >= 19) || (month == 3 && day <= 20)) {
            return "Риби";
        } else if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) {
            return "Овен";
        } else if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) {
            return "Телець";
        } else if ((month == 5 && day >= 21) || (month == 6 && day <= 20)) {
            return "Близнюки";
        } else if ((month == 6 && day >= 21) || (month == 7 && day <= 22)) {
            return "Рак";
        } else if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) {
            return "Лев";
        } else if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) {
            return "Діва";
        } else if ((month == 9 && day >= 23) || (month == 10 && day <= 22)) {
            return "Терези";
        } else if ((month == 10 && day >= 23) || (month == 11 && day <= 21)) {
            return "Скорпіон";
        } else if ((month == 11 && day >= 22) || (month == 12 && day <= 21)) {
            return "Стрілець";
        } else if ((month == 12 && day >= 22) || (month == 1 && day <= 19)) {
            return "Козеріг";
        } else {
            return "Невідомий";
        }
    }

    static boolean isDateOfBirthValid(String dateOfBirth) {
        try {
            String[] parts = dateOfBirth.split("\\.");

            if (parts.length != 3) {
                return false;
            }

            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            if (day < 1 || day > 31 || month < 1 || month > 12 || year < 1900 || year > 2100) {
                return false;
            }

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}


