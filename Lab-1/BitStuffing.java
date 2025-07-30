import java.util.Scanner;

public class BitStuffing {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the bit stream (only 0s and 1s): ");
        String data = sc.nextLine();

        System.out.println("Entered data: " + data);

        String stuffed = bitStuff(data);
        System.out.println("Data after stuffing: " + stuffed);

        String destuffed = bitDestuff(stuffed);
        System.out.println("Data after destuffing: " + destuffed);
    }

    // Bit stuffing: After five consecutive '1's, insert a '0'
    public static String bitStuff(String data) {
        StringBuilder stuffed = new StringBuilder();

        int count = 0;
        for (int i = 0; i < data.length(); i++) {
            char bit = data.charAt(i);
            stuffed.append(bit);

            if (bit == '1') {
                count++;
                if (count == 5) {
                    // After 5 consecutive ones, insert a 0
                    stuffed.append('0');
                    count = 0;
                }
            } else {
                count = 0;  // reset count on '0'
            }
        }
        return stuffed.toString();
    }

    public static String bitDestuff(String data) {
        StringBuilder destuffed = new StringBuilder();

        int count = 0;
        for (int i = 0; i < data.length(); i++) {
            char bit = data.charAt(i);

            if (bit == '1') {
                count++;
                destuffed.append(bit);
            } else { // bit == '0'
                if (count == 5) {
                    count = 0;  
                    continue;
                } else {
                    destuffed.append(bit);
                    count = 0;
                }
            }
        }

        return destuffed.toString();
    }
}
