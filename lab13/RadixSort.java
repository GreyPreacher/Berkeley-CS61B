import java.util.Arrays;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        if (asciis.length == 1 || asciis.length == 0) {
            return asciis;
        }
        int maxLen = 0;
        for (String s: asciis) {
            maxLen = maxLen > s.length() ? maxLen : s.length();
        }
        String[] res = Arrays.copyOf(asciis, asciis.length);
        for (int i = maxLen - 1; i >= 0; i--) {
            sortHelperLSD(res, i);
        }
        return res;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        final int asciisSum = 256;
        int[] counts = new int[asciisSum + 1];
        for (String s : asciis) {
            int c = charAtOrMinChar(s, index);
            counts[c]++;
        }

        int pos = 0;
        int[] starts = new int[asciisSum + 1];
        for (int i = 0; i <= asciisSum; ++i) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] sorted = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            String item = asciis[i];
            int c = charAtOrMinChar(item, index);
            int place = starts[c];
            sorted[place] = item;
            starts[c]++;
        }

        for (int i = 0; i < asciis.length; ++i) {
            asciis[i] = sorted[i];
        }
    }

    private static int charAtOrMinChar(String item, int index) {
        if (item.length() > index && index >= 0) {
            return item.charAt(index) + 1;
        }
        return 0;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] asciis = {"qsa", "126", "sofa", "time", "yan", "exp", "curl"};
        asciis = RadixSort.sort(asciis);
        for (String s : asciis) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
