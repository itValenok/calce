import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String primer;
        Scanner sc = new Scanner(System.in);
        System.out.println("Пример: ");
        primer = sc.nextLine();
        calc(primer);
        System.out.println();

    }

    static void calc(String example) throws IOException{
        String num1, num2, znak;
        String result;
        if (example.contains("+")) {
            if(example.split("\\+").length > 2)
                throw new IOException("Операторов больше, чем 1");
            num1 = example.split("\\+")[0];
            num2 = example.split("\\+")[1];
            znak = "+";
        } else if (example.contains("-")) {
            if(example.split("-").length > 2)
                throw new IOException("Операторов больше, чем 1");
            num1 = example.split("-")[0];
            num2 = example.split("-")[1];
            znak = "-";
        } else if (example.contains("*")) {
            if(example.split("\\*").length > 2)
                throw new IOException("Операторов больше, чем 1");
            num1 = example.split("\\*")[0];
            num2 = example.split("\\*")[1];
            znak = "*";
        } else if (example.contains("/")) {
            if(example.split("/").length > 2)
                throw new IOException("Операторов больше, чем 1");
            num1 = example.split("/")[0];
            num2 = example.split("/")[1];
            znak = "/";
        } else throw new IOException("Нет действия для калькулятора");

        if(isRome(num1) != 0){

            if(isRome(num2) != 0){
                if(calculate(znak, isRome(num1), isRome(num2)) < 0)
                    throw new IOException("Отрицательное число в ответе");
                else {
                    for(RomeNumbers RomeNumber : RomeNumbers.values()){
                        if(RomeNumber.ordinal() == calculate(znak, isRome(num1), isRome(num2)) - 1) {
                            System.out.println(RomeNumber.toString());
                            return;
                        }
                    }
                }
            } else throw new IOException("Не правильный вид 2го числа");
        } else if (isArabic(num1) != 0) {
            if(isArabic(num2) != 0)
                System.out.println(String.valueOf(calculate(znak, isArabic(num1), isArabic(num2))));

        }
    }

    static int isRome(String num) throws IOException{
        for(RomeNumbers RomeNumber : RomeNumbers.values()){
            if(num.equals(RomeNumber.toString())){
                if(RomeNumber.ordinal() + 1 <= 10)
                    return RomeNumber.ordinal() + 1;
                else
                    throw new IOException("Число больше 10");
            }
        }
        return 0;
    }

    static int isArabic(String num)throws IOException{
        for(int i = 1; i <= 10; i++){
            if(num.equals(String.valueOf(i))){
                return i;
            }
        }
        throw new IOException("Не правильный пример");
    }

    static int calculate(String znak, int num1, int num2) throws IOException{
        int result = switch (znak){
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> throw new IOException();
        };
        return result;
    }

}