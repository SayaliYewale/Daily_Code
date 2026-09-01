package array.twosum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

public class twosum {
    public static int[] bruteForce(int[] a, int target){
            for(int i=0;i<a.length;i++){
                for (int j=1;j<a.length;j++){
                    if(a[i]+a[j]==target){
                        return new int[]{i,j};
                    }
                }
            }
            return new int[]{-1,-1};
    }

    public static int[] optimalApproch(int[] a,int target){
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<a.length;i++){
            int num=target-a[i];
            if(map.containsKey(num)){
                return new int[]{map.get(num),i};
            }
            map.put(a[i],i);
        }
        return new int[]{-1,-1};
    }

    public static int[] streamApproch(int[] nums, int target) {
        return IntStream.range(0, nums.length)
                .boxed()
                .flatMap(i ->
                        IntStream.range(i + 1, nums.length)
                                .filter(j -> nums[i] + nums[j] == target)
                                .mapToObj(j -> new int[]{i, j})
                )
                .findFirst()
                .orElse(new int[]{-1, -1});
    }


    static void main() {
        int[] a={1,2,3,4,5};
        int target=3;
        System.out.println(Arrays.toString(twosum.bruteForce(a,target)));
        System.out.println(Arrays.toString(twosum.optimalApproch(a,target)));
        System.out.println(Arrays.toString(twosum.streamApproch(a,target)));
    }
}
