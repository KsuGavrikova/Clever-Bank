package com.clevertec.cb;

import com.clevertec.cb.controller.Controller;
import com.clevertec.cb.controller.impl.Menu;
import com.clevertec.cb.threads.MyThread;
import com.clevertec.cb.ui.MainMenu;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyThread.getThread();
        MainMenu menu = new MainMenu();
        Controller mainController = new Menu(menu);
        mainController.execute();
    }
}
