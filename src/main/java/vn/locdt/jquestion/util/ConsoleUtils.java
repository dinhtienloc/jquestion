package vn.locdt.jquestion.util;

public class ConsoleUtils {
    private final static String OS_NAME = System.getProperty("os.name");

    public static boolean isWindowOS() {
        return OS_NAME.startsWith("Windows");
    }

    public static boolean isStringValid(String s) {return s != null && !s.trim().equals("");}
}
