package com.clevertec.cb.controller.impl;

import com.clevertec.cb.controller.Controller;
import com.clevertec.cb.service.Operation;
import com.clevertec.cb.ui.MainMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Scanner;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements Controller {
    private MainMenu menu;
    
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        boolean bool = true;
        Operation operation = new Operation();
        String number = "";
        long amount;
        while (bool) {
            switch (menu.executeChoise()) {
                case "1":
                    System.out.println("Input bill number");
                    number = scanner.nextLine();
                    System.out.println("Input amount");
                    amount = Long.valueOf(scanner.nextLine());
                    operation.refill(number, amount);
                    System.out.println("Success!");
                    break;
                case "2":
                    System.out.println("Input bill number");
                    number = scanner.nextLine();
                    System.out.println("Input amount");
                    amount = Long.valueOf(scanner.nextLine());
                    operation.withdrawal(number, amount);
                    System.out.println("Success!");
                    break;
                case "3":
                    System.out.println("Input bill number");
                    number = scanner.nextLine();
                    System.out.println("Input bill number");
                    String number2 = scanner.nextLine();
                    System.out.println("Input amount");
                    amount = Long.valueOf(scanner.nextLine());
                    operation.transfer(number, number2, amount);
                    System.out.println("Success!");
                    break;
                case "4":
                    bool = false;
                    break;
                default:
                    System.out.println("You input wrong number");
            }
        }
    }
}
