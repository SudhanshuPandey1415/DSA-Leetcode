class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        String answer = "";
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int count = 0;

            for (int j = i; j < n; j++) {
                if (s.charAt(j) == '1') {
                    count++;
                }

                if (count == k) {
                    String current = s.substring(i, j + 1);

                    if (answer.equals("") ||
                        current.length() < answer.length() ||
                        (current.length() == answer.length() &&
                         current.compareTo(answer) < 0)) {
                        answer = current;
                    }

                    break;
                }
            }
        }

        return answer;
    }
}