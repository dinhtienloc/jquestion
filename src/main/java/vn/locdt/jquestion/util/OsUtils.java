package vn.locdt.jquestion.util;

public class OsUtils {
    private final static String OS = System.getProperty("os.name");

    public static boolean isWindowOS() {
        return OS.startsWith("Windows");
    }

    public static boolean isMac() {
        return OS.contains("mac");
    }

    public static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
    }
}
