class Solution {
    public int smallestNumber(int n, int t) {
        while(true){
            int temp = 1;
            int num = n;
            int n1=0;
            while(num>0){
                n1 =num%10;
                temp*=n1;
                num= num/10;
            }
                if (temp%t==0){
                    return n;
                }
               
            
                n++;
        }
        
    }
}