import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(calc(SCANNER.nextLine()));
    }

    public static String calc(String input) {
        String[] date = input.split(" ");
        if (date.length > 3) throw new IllegalStateException("Не арифмитическая операция");
        return definitionOfTheNumberSystem(date[0], date[2], date[1]);
    }

    /**
     * Метод создает Map возможных римских цифр, принимает строку и возвращает арабское значения
     * римсокого числа
     * @param s римское число
     * @return арабские цифры
     */
    private static int romanToInt(String s) {
        Map<String, Integer> validRomanNumber = new TreeMap<>();
        validRomanNumber.put("I", 1);
        validRomanNumber.put("II", 2);
        validRomanNumber.put("III", 3);
        validRomanNumber.put("IV", 4);
        validRomanNumber.put("V", 5);
        validRomanNumber.put("VI", 6);
        validRomanNumber.put("VII", 7);
        validRomanNumber.put("VIII", 8);
        validRomanNumber.put("IX", 9);
        validRomanNumber.put("X", 10);
        return validRomanNumber.get(s);
    }


    /**
     * Метод конвертирует число типа int в число в Римской системе исчисления, максимальное число 100 т.к согласно
     * условию входящие числа не могут быть больше 10, значит максимальное возможное число 10*10 = 100
     * @param number число которое нужно перевести в Римские цифры
     * @return возвращаем строку полученного значения
     */
    private static String intToRoman(int number) {
        int[] values = new int[] {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbol = new String[] {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.length && number >= 0; i++) {
            while (values[i] <= number) {
                number -= values[i];
                builder.append(symbol[i]);
            }
        }
        return builder.toString();
    }

    /**
     * Метод проверяет соответствует ли входящее римское число , разрешенному диапазону
     * @param s строка, входящее число
     * @return возвращает true если соотвествует условию и число от 1 до 10;
     */
    private static boolean isValidNumberRoman(String s) {
        return s.matches("(I|II|III|IV|V|VI|VII|VIII|IX|X)$");
    }

    /**
     * Метод проверяет соответсвует ли строка число в диапазоне от 1 до 10
     * @param s строка, которая содержит число число
     * @return возвращает true если число соотвествует требованиям диапазона
     */
    private static boolean isValidNumberInt(String s) {
        return s.matches("^[1-9]|10$");
    }

    /**
     * Метод проверяет что бы оба числа были одной системы исчисления и производит
     * математическую операцию в зависимости от арифмитического знака
     * В случае передачи смешанных систем исчисления или некорректных данных, выбрасывается
     * исключение и программа завершается, с выводом сообщения из за чего происзошла ошибка
     * @param a первое число
     * @param b второе число
     * @param arithmeticSing арифмитический знак
     * @return возвращает результат математической операции в строке
     */
    private static String definitionOfTheNumberSystem(String a, String b, String arithmeticSing) {
        if (isValidNumberRoman(a) && isValidNumberRoman(b)) {
            int romanInt =  mathematicalOperation(romanToInt(a), romanToInt(b), arithmeticSing);
            if (romanInt <= 0) {
                throw new IllegalStateException("Римские цифры не могут быть отрицательными");
            } else {
                return intToRoman(romanInt);
            }
        } else if (isValidNumberInt(a) && isValidNumberInt(b)) {
            return String.valueOf(mathematicalOperation(Integer.parseInt(a), Integer.parseInt(b), arithmeticSing));
        } else if (!isValidNumberInt(a) && !isValidNumberInt(b) || !isValidNumberRoman(a) && !isValidNumberRoman(b)){
            throw new IllegalStateException("Некорректные значения");
        } else {
            throw new IllegalStateException("Разные системы исчисления");
        }
    }

    /**
     * Метод производит математическую операцию, взависимости от переданного арифмитического знака
     * @param a первое число
     * @param b второе число
     * @param arithmeticSing арифмитический знак
     * @return результат математической операции
     */
    private static int mathematicalOperation(int a, int b, String arithmeticSing) {
        return switch (arithmeticSing) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "/" -> a / b;
            case "*" -> a * b;
            default -> throw new IllegalStateException("Unexpected value: " + arithmeticSing);
        };
    }
}