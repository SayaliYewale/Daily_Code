package array.removedup;

import java.util.*;
import java.util.stream.*;

public class removeDup {

        // Brute Force
        public static int bruteForce(int[] nums) {

            List<Integer> list = new ArrayList<>();

            for (int num : nums) {
                if (!list.contains(num)) {
                    list.add(num);
                }
            }

            for (int i = 0; i < list.size(); i++) {
                nums[i] = list.get(i);
            }

            return list.size();
        }

        // Optimal Approach
        public static int optimalApproach(int[] nums) {

            if (nums.length == 0) {
                return 0;
            }

            int i = 0;

            for (int j = 1; j < nums.length; j++) {

                if (nums[i] != nums[j]) {
                    i++;
                    nums[i] = nums[j];
                }
            }

            return i + 1;
        }

        // Stream Approach
        public static int streamApproach(int[] nums) {

            int[] unique = Arrays.stream(nums)
                    .distinct()
                    .toArray();

            for (int i = 0; i < unique.length; i++) {
                nums[i] = unique[i];
            }

            return unique.length;
        }

        public static void main(String[] args) {

            int[] nums1 = {1, 1, 2, 2, 3};
            System.out.println("Brute Force: " + bruteForce(nums1));

            int[] nums2 = {1, 1, 2, 2, 3};
            System.out.println("Optimal: " + optimalApproach(nums2));

            int[] nums3 = {1, 1, 2, 2, 3};
            System.out.println("Stream: " + streamApproach(nums3));
        }

}
