class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int half = n / 2;

        int[] count = new int[26];

        // Count characters in s
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Check whether a palindrome is possible
        int oddCount = 0;
        int middle = -1;

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                oddCount++;
                middle = i;
            }
        }

        if (oddCount > 1) {
            return "";
        }

        // Number of each character available in the left half
        int[] left = new int[26];

        for (int i = 0; i < 26; i++) {
            left[i] = count[i] / 2;
        }

        String mid = middle == -1
                ? ""
                : String.valueOf((char) ('a' + middle));

        /*
         * Try to make the left half equal to target's left half.
         *
         * left[c] can become negative if target needs a character
         * that we don't have.
         */
        int negative = 0;

        for (int i = 0; i < half; i++) {
            int c = target.charAt(i) - 'a';

            left[c]--;

            if (left[c] == -1) {
                negative++;
            }
        }

        // First check the palindrome whose left half exactly matches target
        if (negative == 0) {
            String leftPart = target.substring(0, half);

            String candidate = leftPart + mid + reverse(leftPart);

            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        /*
         * We couldn't use the exact target prefix.
         * Move from right to left and try to make one character larger.
         */
        for (int i = half - 1; i >= 0; i--) {

            int current = target.charAt(i) - 'a';

            // Restore target[i] to the available characters
            if (left[current] == -1) {
                negative--;
            }

            left[current]++;

            // Prefix is still impossible
            if (negative > 0) {
                continue;
            }

            // Find the smallest character greater than target[i]
            for (int c = current + 1; c < 26; c++) {

                if (left[c] > 0) {
                    left[c]--;

                    StringBuilder leftPart = new StringBuilder();

                    // Keep target's prefix
                    leftPart.append(target, 0, i);

                    // Make this position slightly larger
                    leftPart.append((char) ('a' + c));

                    // Fill remaining positions with smallest characters
                    for (int j = 0; j < 26; j++) {
                        while (left[j] > 0) {
                            leftPart.append((char) ('a' + j));
                            left[j]--;
                        }
                    }

                    String firstHalf = leftPart.toString();

                    return firstHalf + mid + reverse(firstHalf);
                }
            }
        }

        return "";
    }

    private String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}