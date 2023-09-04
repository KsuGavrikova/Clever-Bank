package com.clevertec.cb.ui;

import java.util.Scanner;

public class MainMenu {

    private Scanner scanner = new Scanner(System.in);

    public String executeChoise() {
        System.out.print("Main menu\n"
                + "1. Refill money\n"
                + "2. Withdrawal money\n"
                + "3. Transfer money\n"
                + "4. Exit\n");
        return scanner.nextLine();
    }
}
