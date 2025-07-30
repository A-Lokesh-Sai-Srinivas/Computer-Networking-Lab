import java.util.Scanner;

public class CharStuffing {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the data: ");
        String data = sc.nextLine();

        System.out.print("Enter the flag data: ");
        String flag = sc.nextLine();

        System.out.print("Enter the esc data: ");
        String esc = sc.nextLine();

        System.out.println("Entered data: " + data);

        String stuffed = charStuff(flag, esc, data);
        System.out.println("Data after stuffing: " + stuffed);

        String destuffed = charDestuff(flag, esc, stuffed);
        System.out.println("Data after destuffing: " + destuffed);
    }

    // Stuffing function without using replace()
    public static String charStuff(String flag, String esc, String data) {
        StringBuilder stuffed = new StringBuilder();

        // Insert escape before any flag or esc sequence in data
        for (int i = 0; i < data.length();) {
            if (i + flag.length() <= data.length() && data.substring(i, i + flag.length()).equals(flag)) {
                stuffed.append(esc).append(flag);
                i += flag.length();
            } else if (i + esc.length() <= data.length() && data.substring(i, i + esc.length()).equals(esc)) {
                stuffed.append(esc).append(esc);
                i += esc.length();
            } else {
                stuffed.append(data.charAt(i));
                i++;
            }
        }

        return flag + stuffed.toString() + flag;
    }

    // Destuffing function without using replace()
    public static String charDestuff(String flag, String esc, String data) {
        // Remove starting and ending flag
        if (data.startsWith(flag)) {
            data = data.substring(flag.length());
        }
        if (data.endsWith(flag)) {
            data = data.substring(0, data.length() - flag.length());
        }

        StringBuilder destuffed = new StringBuilder();

        for (int i = 0; i < data.length();) {
            if (i + esc.length() <= data.length() && data.substring(i, i + esc.length()).equals(esc)) {
                // Next part is either flag or esc
                i += esc.length();
                if (i + flag.length() <= data.length() && data.substring(i, i + flag.length()).equals(flag)) {
                    destuffed.append(flag);
                    i += flag.length();
                } else if (i + esc.length() <= data.length() && data.substring(i, i + esc.length()).equals(esc)) {
                    destuffed.append(esc);
                    i += esc.length();
                } else {
                    // Escape character not followed by valid sequence, treat as normal
                    destuffed.append(esc);
                }
            } else {
                destuffed.append(data.charAt(i));
                i++;
            }
        }

        return destuffed.toString();
    }
}
