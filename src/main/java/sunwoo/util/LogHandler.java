package sunwoo.util;

public class LogHandler {

    public static void warn(String message){
        System.out.println("WARN: "+ message);
    }
    public static void confirm (String message){
        System.out.println(">>> "+ message);
    }
}
