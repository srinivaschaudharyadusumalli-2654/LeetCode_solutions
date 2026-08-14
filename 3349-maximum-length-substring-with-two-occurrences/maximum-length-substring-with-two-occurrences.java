class Solution {
    public int maximumLengthSubstring(String s) {
        int[] freq = new int[26];
        int left = 0;
        int maxlength = 0;
        for (int i= 0; i<s.length(); i++){
            freq[s.charAt(i) -'a']++;
            while(freq[s.charAt(i) -'a'] > 2){
                freq[s.charAt(left) - 'a']--;
                left++;
            }
            maxlength = Math.max(maxlength , i - left + 1);
        }
        return maxlength;
    }
}