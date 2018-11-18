abstract class GuessGame {
    protected String welcome;
    protected String prompt;
    protected String correct;
    protected String bigger;
    protected String smaller;
   
    void go() {
        message(welcome);
 
        int number = (int) (Math.random() * 10);
        int guess = 0;
        do {
            message(prompt);
            guess = guess();
            if(guess > number) {
                message(bigger);
            }
            else if(guess < number) {
                message(smaller);
            }
        } while(guess != number);
       
        message(correct);
    }

    protected abstract void message(String message);
    protected abstract int guess();
}

import java.util.Scanner;

class ConsoleGame extends GuessGame {
    private Scanner scanner;
   
    ConsoleGame() {
        welcome = "歡迎";
        prompt = "輸入";
        correct = "猜中了";
        bigger = "你猜的比較大";
        smaller = "你猜的比較小";
        scanner = new Scanner(System.in);
    }
   
    protected void message(String msg) {
        System.out.println(msg);
    }
   
    protected int guess() {
        return scanner.nextInt();
    }
}

public class Main {
    public static void main(String[] args) {
        GuessGame game = new ConsoleGame();
        game.go();
    }
}