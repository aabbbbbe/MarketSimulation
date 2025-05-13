
import lombok.extern.java.*;
import lombok.extern.log4j.Log4j;


import java.util.Scanner;

@Log
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String userString = scanner.next();

        log.warning(userString);


    }

}
