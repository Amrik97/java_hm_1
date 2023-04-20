//2. Вывести все простые числа от 1 до 1000
//Используем алгоритм Эратосфена
//Сделаем профилирование

import java.util.Arrays;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import jdk.jfr.Recording;
import jdk.jfr.FlightRecorder;
import jdk.jfr.consumer.RecordedEvent;
import jdk.jfr.consumer.RecordingFile;


public class task_2 {
    public static void main(String[] args) {
        // Начинаем запись событий
        Recording recording = new Recording();
        recording.start();
        clearScreen();
        int n = 1000;
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);// true - простое число, false - составное
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i <= Math.sqrt(n); i++) { // Цикл до корня из n, потому, что
            // ниже мы заполняем массив составных чисел степенями i
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) { // Заполняем false все кратные степени i до n
                    isPrime[j] = false; // Пометили составные числа
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                System.out.println(i);
            }
        }

        // Останавливаем запись событий
        recording.stop();

        try {
            // Сохраняем записанные события в файл
            Files.createDirectories(Paths.get("profile"));
            recording.dump(Paths.get("profile", "myrecording.jfr"));
        } catch (IOException e) {
            // Читаем записанные события из файла
            try {
                RecordingFile recordingFile = new RecordingFile(Paths.get("profile", "myrecording.jfr"));
                while (recordingFile.hasMoreEvents()) {
                    RecordedEvent event = recordingFile.readEvent();
                    // Обрабатываем записанные события
                    // ...
                }

            } catch (IOException e1) {
                // Обработка исключения
                e1.printStackTrace();
            }
            // Обработка исключения
            e.printStackTrace();
        }
    }

    /*
     * Метод очистки консоли терминала
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}