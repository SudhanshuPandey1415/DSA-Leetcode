import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        // Store indices and sort them according to their values
        Integer[] indices = new Integer[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] result = new int[n];

        int i = 0;

        while (i < n) {
            int j = i + 1;

            // Find all values that belong to the same group
            while (j < n &&
                   (long) nums[indices[j]] - nums[indices[j - 1]] <= limit) {
                j++;
            }

            // Get the original indices of this group
            Integer[] groupIndices = Arrays.copyOfRange(indices, i, j);

            // Sort positions from left to right
            Arrays.sort(groupIndices);

            // Put smallest values into smallest positions
            for (int k = i; k < j; k++) {
                result[groupIndices[k - i]] = nums[indices[k]];
            }

            i = j;
        }

        return result;
    }
}