class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        HashMap<Integer,Integer> freq = new HashMap<>();
        int i=0,j=0;
        int ans=0;
        while(j<nums.length){
            if(freq.containsKey(nums[j])){
                freq.put(nums[j],freq.get(nums[j])+1);
            }else{
                freq.put(nums[j],1);
            }
            while(freq.get(nums[j])>k){
                freq.put(nums[i],freq.get(nums[i])-1);
                i++;
            }
            ans = Math.max(ans,j-i+1);
            j++;
        }
        return ans;
    }
}