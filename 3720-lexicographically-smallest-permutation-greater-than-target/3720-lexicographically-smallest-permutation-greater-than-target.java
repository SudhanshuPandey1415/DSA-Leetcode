class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] count = new int[26];

        // Count characters of s
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int n = target.length();
        int matched = 0;

        // Match target from left to right
        while (matched < n) {
            int c = target.charAt(matched) - 'a';

            if (count[c] == 0) {
                break;
            }

            count[c]--;
            matched++;
        }

        // Try to make the string greater
        int start = Math.min(matched, n - 1);

        for (int pos = start; pos >= 0; pos--) {

            // Restore the character at this position
            // when moving backwards
            if (pos < matched) {
                count[target.charAt(pos) - 'a']++;
            }

            int targetChar = target.charAt(pos) - 'a';

            // Find the smallest character greater than target[pos]
            for (int c = targetChar + 1; c < 26; c++) {

                if (count[c] > 0) {
                    count[c]--;

                    StringBuilder ans = new StringBuilder();

                    // Keep prefix same as target
                    ans.append(target.substring(0, pos));

                    // Put a character greater than target[pos]
                    ans.append((char) ('a' + c));

                    // Add remaining characters in sorted order
                    for (int j = 0; j < 26; j++) {
                        while (count[j] > 0) {
                            ans.append((char) ('a' + j));
                            count[j]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}