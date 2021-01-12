package calc;

public class RomanArabicConverter {
    private RomanArabicConverter() {
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in roman numbers range");
        }
        StringBuilder s = new StringBuilder();
        while (number >= 1000) {
            s.append("M");
            number -= 1000;
        }
        while (number >= 900) {
            s.append("CM");
            number -= 900;
        }
        while (number >= 500) {
            s.append("D");
            number -= 500;
        }
        while (number >= 400) {
            s.append("CD");
            number -= 400;
        }
        while (number >= 100) {
            s.append("C");
            number -= 100;
        }
        while (number >= 90) {
            s.append("XC");
            number -= 90;
        }
        while (number >= 50) {
            s.append("L");
            number -= 50;
        }
        while (number >= 40) {
            s.append("XL");
            number -= 40;
        }
        while (number >= 10) {
            s.append("X");
            number -= 10;
        }
        while (number >= 9) {
            s.append("IX");
            number -= 9;
        }
        while (number >= 5) {
            s.append("V");
            number -= 5;
        }
        while (number >= 4) {
            s.append("IV");
            number -= 4;
        }
        while (number >= 1) {
            s.append("I");
            number -= 1;
        }
        return s.toString();
    }

    public static int romanToArabic(String roman) {
        roman = roman.toUpperCase();
        int answer = 0;
        int current = 0;
        int previous = 0;
        if (isValidRoman(roman)) {
            for (int i = 0; i < roman.length(); i++) {
                switch (roman.charAt(i)) {
                    case 'I' -> current = 1;
                    case 'V' -> current = 5;
                    case 'X' -> current = 10;
                    case 'L' -> current = 50;
                    case 'C' -> current = 100;
                    case 'D' -> current = 500;
                    case 'M' -> current = 1000;
                }
                if (previous < current && current != 0) {
                    current -= previous;
                    answer -= previous;
                    answer += current;
                    previous = current;
                } else {
                    previous = current;
                    answer += current;
                }
                current = 0;
            }
        } else {
            throw new IllegalArgumentException("Not a valid roman number");
        }
        return answer;
    }

    public static boolean isValidRoman(String roman) {
        String romanCharacters = "MCDLXVI";
        String illegalRomanTwiceChars = "CLV";
        int occurrence = 0;
        for (int i = 0; i < romanCharacters.length(); i++) {
            for (int j = 0; j < roman.length(); j++) {
                if (roman.charAt(j) == romanCharacters.charAt(i)) {
                    occurrence++;
                }
            }
            if (occurrence > 3) {
                //checks if there is no more than 3 times occurrence of a symbol
                return false;
            } else if (illegalRomanTwiceChars.contains(String.valueOf(romanCharacters.charAt(i))) && occurrence > 1) {
                //this one checks if there is no occurrences of symbols that must not be in roman number twice, like VV which must be X instead
                return false;
            } else {
                occurrence = 0;
            }
        }
        return roman.matches("^[MDCLXVI]+");
    }

    public static boolean isValidArabic(String arabic) {
        try {
            Integer.parseInt(arabic);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
