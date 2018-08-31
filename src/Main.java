import java.util.*;
public class Main {

    public static void main(String[] args) {
//        int[] nums={-1,0,1,2,-1,-4};
//        int[] numsort={6,2,4,1,5,9,8,7,0,3};
//        int[] num = {6 ,1 ,2 ,7 ,9 ,3 ,4 ,5 ,10 ,8};
//        quicksort2(num,0,num.length-1);
//        for(int i = 0; i < num.length;i++){
//            System.out.println(num[i]);
//        }
//        System.out.println(generateParenthesis(3));
        //System.out.println(removeElement(nums,1));
        System.out.println(digitCounts(0,19));
    }

    public static int removeElement(int[] nums, int val) {
        int res = 0;
        for(int i = 0;i < nums.length;i++){
            if(nums[i]!=val)
                nums[res++] = nums[i];
        }
        return res;
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        System.out.println(nums.length);
        Arrays.sort(nums);
        for(int i = 0;i+3<nums.length;i++){
            if(i!=0&&nums[i]==nums[i-1])
                continue;
            for(int j = i+1;j+2<nums.length;j++){
                if(j!=i+1&&nums[j]==nums[j-1]) continue;
                int low = j+1,high = nums.length-1;
                while(low<high){
                    int sum = nums[i]+nums[j]+nums[low]+nums[high];
                    if(sum==target) {
                            res.add(Arrays.asList(nums[i],nums[j],nums[low],nums[high]));
                            low++;
                            high--;
                            while(low<high&&nums[low]==nums[low-1]) low++;
                            while(low<high&&nums[high]==nums[high+1]) high--;
                    }
                    else if(sum>target){
                        high--;
                    }else{
                        low++;
                    }
                }

            }
        }
        return res;
    }
    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        recursion(list,"",0,0,n);
        for (int i = 0;i<list.size();i++) {
            System.out.println(list.get(i));
        }
        return list;
    }
    public static void recursion(List<String> list,String str,int open,int close,int max){
        if(str.length()==max*2){
            list.add(str);
            return;
        }
        if(open<max){
            recursion(list,str+"(",open+1,close,max);
        }
        if(close<open){
            recursion(list,str+")",open,close+1,max);
        }
    }
    public ListNode swapPairs(ListNode head) {
        ListNode current = null;
        current.next=head;
        while(head!=null||head.next!=null){
            ListNode first = current.next;
            ListNode second = current.next.next;
            first.next=head.next.next;
            second.next=head;
            head=head.next.next;
        }

        return null;
    }
    public static void quicksort(int[]array,int low,int high){
        if(low<high){
            int x = low,y = high,z = array[low];
            while(x < y){
                while( x<y && array[y]>=z)
                    y--;
                if(x < y){
                    array[x] = array[y];
                    x++;
                }
                while( x<y && array[x]<=z)
                    x++;
                if(x<y){
                    array[y] = array[x];
                    y--;
                }
            }
            array[x] = z;
            quicksort(array,low,x-1);
            quicksort(array,x+1,high);
        }

    }
    public static void quicksort2(int[]array,int low,int high){
        if(low<high){
            int x = low,y = high,z = array[low],temp;
            while(x != y){
                while( x<y && array[y]>=z)
                    y--;
                while( x<y && array[x]<=z)
                    x++;
                if(x<y){
                    temp = array[x];
                    array[x] = array[y];
                    array[y] = temp;
                }

            }
            array[low] = array[x];
            array[x] = z;
            System.out.println(low + "  "+ high);
            quicksort(array,low,x-1);

            quicksort(array,x+1,high);
        }
    }
    public static int digitCounts(int k, int n) {
        // write your code here
        int count = 0;
        for(int m = n;m>=0;m--){
            if(k==0&&m==0){
                count++;
            }
            int num = m;
                while(num!=0){
                    System.out.println(num);
                    if(num%10==k){
                        count++;
                    }
                    num=num/10;
            }
        }
        return count;
    }
    public static int nthUglyNumber(int n) {
        // write your code here
        int[] ugly = new int[n];
        ugly[0] = 1;
        int nextUglyIndex = 1;

        int mul2 = 0;
        int mul3 = 0;
        int mul5 = 0;
        int min = 0;
        while(nextUglyIndex<n){
            min = compare_min(ugly[mul2]*2,ugly[mul3]*3,ugly[mul5]*5);
            ugly[nextUglyIndex] = min;
            while(ugly[mul2]*2<=ugly[nextUglyIndex]){
                mul2++;
            }
            while(ugly[mul3]*3<=ugly[nextUglyIndex]){
                mul3++;
            }
            while(ugly[mul5]*5<=ugly[nextUglyIndex]){
                mul5++;
            }

            nextUglyIndex++;


        }
        int result = ugly[n - 1];
        return result;
    }
    public static int compare_min(int a,int b,int c){
        if(a<=b){
            if(a<=c)
                return a;
            return c;
        }else if(c<=b){
            return c;
        }
        return b;
    }
}
