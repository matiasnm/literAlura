package com.alura.literalura.service;

public class PrintService {

    private static final String RESET   = "\u001B[0m";
    private static final String BLACK   = "\033[1;90m"; // BLACK
    private static final String RED     = "\033[1;91m"; // RED
    private static final String GREEN   = "\033[1;92m"; // GREEN
    private static final String YELLOW  = "\033[1;93m"; // YELLOW
    private static final String BLUE    = "\033[1;94m"; // BLUE
    private static final String PURPLE  = "\033[1;95m"; // PURPLE
    private static final String CYAN    = "\033[1;96m"; // CYAN
    private static final String WHITE   = "\033[1;97m"; // WHITE

    public enum Color {
        BLACK,
        RED,
        GREEN,
        YELLOW,
        BLUE,
        PURPLE,
        CYAN,
        WHITE   
    }
    
    public void cPrint(String msg, Color color) {
        switch (color) {
            case BLACK  -> System.out.print(BLACK + msg + RESET);
            case RED    -> System.out.print(RED    + msg + RESET);
            case GREEN  -> System.out.print(GREEN  + msg + RESET);
            case YELLOW -> System.out.print(YELLOW + msg + RESET);
            case BLUE   -> System.out.print(BLUE   + msg + RESET);
            case PURPLE -> System.out.print(PURPLE + msg + RESET);
            case CYAN   -> System.out.print(CYAN   + msg + RESET);
            case WHITE  -> System.out.print(WHITE  + msg + RESET);
        }
    }

    public void cPrintln(String msg, Color color) {
        switch (color) {
            case BLACK  -> System.out.println(BLACK + msg + RESET);
            case RED    -> System.out.println(RED    + msg + RESET);
            case GREEN  -> System.out.println(GREEN  + msg + RESET);
            case YELLOW -> System.out.println(YELLOW + msg + RESET);
            case BLUE   -> System.out.println(BLUE   + msg + RESET);
            case PURPLE -> System.out.println(PURPLE + msg + RESET);
            case CYAN   -> System.out.println(CYAN   + msg + RESET);
            case WHITE  -> System.out.println(WHITE  + msg + RESET);
        }
    }
}