package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class Reader {

    public static final int POS_ERROR = -1;
    public static final int NEG_ERROR = 1;
    BufferedReader in;

    public Reader() {
        this.in = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readString(String message) {
        System.out.print(message);

        try {
            return this.in.readLine();
        } catch (IOException var3) {
            return null;
        }
    }

    public int readPositiveInt(String message) {
        System.out.print(message);

        try {
            String str = this.in.readLine();
            int num = Integer.parseInt(str);
            return num < 0 ? -1 : num;
        } catch (IOException var5) {
            return -1;
        } catch (NumberFormatException var6) {
            return -1;
        }
    }

    public int readNegativeInt(String message) {
        System.out.print(message);

        try {
            String str = this.in.readLine();
            int num = Integer.parseInt(str);
            return num >= 0 ? 1 : num;
        } catch (IOException var5) {
            return 1;
        } catch (NumberFormatException var6) {
            return 1;
        }
    }

    public float readPositiveFloat(String message) {
        System.out.print(message);

        try {
            String str = this.in.readLine();
            float num = Float.parseFloat(str);
            return num < 0.0F ? -1.0F : num;
        } catch (IOException var5) {
            return -1.0F;
        } catch (NumberFormatException var6) {
            return -1.0F;
        }
    }

    public float readNegativeFloat(String message) {
        System.out.print(message);

        try {
            String str = this.in.readLine();
            float num = Float.parseFloat(str);
            return num >= 0.0F ? 1.0F : num;
        } catch (IOException var5) {
            return 1.0F;
        } catch (NumberFormatException var6) {
            return 1.0F;
        }
    }

    public Date readDate(String message) {
        System.out.print(message);

        try {
            String str = this.in.readLine();
            Locale l = new Locale("el", "GR");
            DateFormat df = DateFormat.getDateInstance(3, l);
            return df.parse(str);
        } catch (IOException var5) {
            return null;
        } catch (ParseException var6) {
            return null;
        }
    }

    public Date readTime(String message) {
        System.out.print(message);

        try {
            String str = this.in.readLine();
            DateFormat df = DateFormat.getTimeInstance(3);
            return df.parse(str);
        } catch (IOException var4) {
            return null;
        } catch (ParseException var5) {
            return null;
        }
    }
}

