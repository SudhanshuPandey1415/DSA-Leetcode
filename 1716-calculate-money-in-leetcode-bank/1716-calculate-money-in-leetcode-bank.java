class Solution {
    public int totalMoney(int n) {
        int total = 0;
        int monday = 1;

        for (int day = 0; day < n; day++) {
            total += monday + (day % 7);
            
            if (day % 7 == 6) {
                monday++;
            }
        }

        return total;
    }
}