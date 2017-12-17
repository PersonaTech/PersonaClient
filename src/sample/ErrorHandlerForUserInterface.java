package sample;

/**
 * Created by itamarfredavrahami on 09/12/2017.
 */
public class ErrorHandlerForUserInterface {

    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }


}
