class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> Integer.compare(nums[i], nums[j]));
        int[] ans = new int[n];
        for (int i = 0; i < n; ) {
            int j = i + 1;
            while (j < n && nums[idx[j]] - nums[idx[j - 1]] <= limit) {
                j++;
            }
            Integer[] group = Arrays.copyOfRange(idx, i, j);
            Arrays.sort(group);
            for (int k = i; k < j; k++) {
                ans[group[k - i]] = nums[idx[k]];
            }
            i = j;
        }
        return ans;  
    }
}