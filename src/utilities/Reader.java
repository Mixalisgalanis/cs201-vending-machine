package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class Reader {

    //Class variables
    private static final int POS_ERROR = -1;
    private static final int NEG_ERROR = 1;
    private BufferedReader in;

    //Constructor
    public Reader() {
        this.in = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Prints a message on the screen and asks for a String input
     *
     * @param message to be displayed on screen
     * @return the String the user gave
     */
    public String readString(String message) {
        System.out.print(message);
        try {
            return this.in.readLine();
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * Prints a message on the screen and asks for an Integer input
     *
     * @param message to be displayed on screen
     * @return the Integer Number the user gave
     */
    public int readInt(String message) {
        System.out.print(message);
        try {
            String str = in.readLine();
            return Integer.parseInt(str);
        } catch (IOException | NumberFormatException ex) {
            return -1;
        }
    }

    /**
     * Prints a message on the screen and asks for a Float input
     *
     * @param message to be displayed on screen
     * @return the Float Number the user gave
     */
    public float readFloat(String message) {
        System.out.print(message);
        try {
            return Float.parseFloat(in.readLine());
        } catch (IOException | NumberFormatException ex) {
            return -1.0F;
        }
    }

    /**
     * Prints a message on the screen and asks for a Date input (String Format) - ex "1/1/2018"
     *
     * @param message to be displayed on screen
     * @return the Date the user gave (Date Format)
     */
    public Date readDate(String message) {
        System.out.print(message);
        try {
            Locale l = new Locale("el", "GR");
            DateFormat df = DateFormat.getDateInstance(3, l);
            return df.parse(in.readLine());
        } catch (IOException | ParseException ex) {
            return null;
        }
    }

    /**
     * Prints a message on the screen and asks for a Time input (String Format) - ex "04:35"
     *
     * @param message to be displayed on screen
     * @return the String the user gave (Time Format)
     */
    public Date readTime(String message) {
        System.out.print(message);
        try {
            DateFormat df = DateFormat.getTimeInstance(3);
            return df.parse(in.readLine());
        } catch (IOException | ParseException ex) {
            return null;
        }
    }
}

