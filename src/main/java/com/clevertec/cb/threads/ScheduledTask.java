package com.clevertec.cb.threads;

import com.clevertec.cb.service.Operation;

import java.time.LocalDate;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ScheduledTask extends TimerTask {
    private boolean flag = true;

    private boolean getFlag() {
        return flag;
    }

    private void setFlag() {
        this.flag = false;
    }

    @Override
    public void run() {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            Operation.checkup();
        });
        try {
            future1.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        CompletableFuture.runAsync(() -> {
            LocalDate nowDay = LocalDate.now();
            LocalDate endDay = LocalDate.from((nowDay.withDayOfMonth(nowDay.lengthOfMonth())).atStartOfDay());
            if ((nowDay == endDay) && getFlag()) {
                Operation.accrual();
                setFlag();
            }
        }).join();

    }
}
