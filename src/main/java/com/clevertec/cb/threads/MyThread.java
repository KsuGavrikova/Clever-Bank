package com.clevertec.cb.threads;

import lombok.NoArgsConstructor;

import java.util.Timer;

@NoArgsConstructor
public class MyThread {
    public static void getThread() {
        Timer time = new Timer();
        ScheduledTask st = new ScheduledTask();
        time.schedule(st, 0, 30000);
    }
}
