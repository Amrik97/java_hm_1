//1. Вычислить n-ое треугольного число (сумма чисел от 1 до n), n! 
//(произведение чисел от 1 до n)


// import java.util.Scanner;
import java.util.Random;

public class task_1 {
    public static void main(String[] args) throws Exception {
        clearScreen();
        Random item = new Random();
        int randomNumber = item.nextInt(15, 20);

        System.out.printf("Случайное число - %d\nCумма 1 - n = %d\n%d! = %d\n", randomNumber, randomNumber*(randomNumber+1)/2, randomNumber, factorial(randomNumber));
    }
    public static long factorial(long n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    //Метод очистки консоли терминала
    
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}