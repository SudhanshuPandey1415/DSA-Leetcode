class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;

        int sumLeft = 0;
        int sumRight = 0;
        int qLeft = 0;
        int qRight = 0;

        for (int i = 0; i < half; i++) {
            if (num.charAt(i) == '?') {
                qLeft++;
            } else {
                sumLeft += num.charAt(i) - '0';
            }
        }

        for (int i = half; i < n; i++) {
            if (num.charAt(i) == '?') {
                qRight++;
            } else {
                sumRight += num.charAt(i) - '0';
            }
        }

        // Odd number of '?' -> Alice wins
        if ((qLeft + qRight) % 2 == 1) {
            return true;
        }

        int diff = sumLeft - sumRight;
        int qDiff = qRight - qLeft;

        // Bob wins only if the difference can be exactly balanced
        return diff != 9 * (qDiff / 2);
    }
}