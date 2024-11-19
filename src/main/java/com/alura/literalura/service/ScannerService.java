package com.alura.literalura.service;

import java.util.Scanner;

import com.alura.literalura.service.PrintService.Color;

public class ScannerService {
    
    private Scanner scanner = new Scanner(System.in, "UTF-8");
    private PrintService printService = new PrintService();

    public String getUserInput(String msg) {
        printService.cPrint(msg, Color.WHITE);
        return scanner.nextLine();
    }

    public Double parseInt(String input, double min) {
        try {
            double parsedValue = Integer.parseInt(input);
            if (parsedValue < min) {
                printService.cPrintln("ERROR: Ingrese un valor mayor a: " + min, Color.RED);
                return null;
            }
            return parsedValue;
        } catch (NumberFormatException ex) {
            printService.cPrintln("ERROR: Valor ingresado inválido.", Color.RED);
            return null;
        }
    }

    public void close() {
        scanner.close();
    }

}