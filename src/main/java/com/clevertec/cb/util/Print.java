package com.clevertec.cb.util;

import com.clevertec.cb.model.Transaction;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

@UtilityClass
public class Print {

    public static void check(Transaction t) {

        Formatter f = new Formatter();
        f.format(
                "--------------------------------------------------------\n" +
                        "|                      Банковский Чек                  |\n" +
                        "| Чек:%48.10s |\n" +
                        "| %-11s%41.10s |\n" +
                        "| Тип транзакции:%37.10s |\n" +
                        "| Банк отправителя:%35.10s |\n" +
                        "| Банк получателя:%36.10s |\n" +
                        "| Счет отправителя:%35.34s |\n" +
                        "| Счет получателя:%36.34s |\n" +
                        "| Сумма:                     %20.2f%5.4s |\n" +
                        "|------------------------------------------------------|",
                t.getId(), Print.dateToString(t.getDateCurrent()),
                Print.timeToString(t.getDateCurrent()),
                t.getTypeTransaction(),
                t.getSendBill().getBank().getBankName(),
                t.getRecipientBill().getBank().getBankName(),
                t.getSendBill().getNumber(),
                t.getRecipientBill().getNumber(),
                t.getTotal(),
                t.getRecipientBill().getCurrency());

        String dir = "./check/";
        Print.createFile(dir, t.getId().toString(), f);
    }

    private static String dateToString(LocalDateTime dateTime) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dateTime.format(myFormatObj);
    }

    private static String timeToString(LocalDateTime dateTime) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
        return dateTime.format(myFormatObj);
    }

    private static void createFile(String dir, String name, Formatter f) {
        try {
            Files.createDirectories(Paths.get(dir));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Path path = Paths.get(dir + name + ".txt");
        try {
            Path writtenFilePath = Files.writeString(path, f.toString());
            System.out.println("Written content in file:\n" + new String(Files.readAllBytes(writtenFilePath)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}