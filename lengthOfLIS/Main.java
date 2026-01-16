import java.util.*;

public class Main {

    public static int lengthOfLIS_NoDuplicates(int[] nums) {
        return computeLIS(nums, true);
    }

    public static int lengthOfLIS_IgnoreDuplicates(int[] nums) {
        return computeLIS(nums, true);
    }

    public static int lengthOfLIS_AllowDuplicates(int[] nums) {
        return computeLIS(nums, false);
    }

    /**
     * backStep = true  → strict LIS, treat equal elements as replacing previous
     * backStep = false → non-decreasing LIS, standard insertion rules
     */
    private static int computeLIS(int[] nums, boolean backStep) {
        List<Integer> tails = new ArrayList<>();

        for (int num : nums) {
            int pos = binaryInsertPosition(tails, num, backStep);

            if (pos == tails.size()) {
                tails.add(num);
            } else {
                tails.set(pos, num);
            }
        }
        return tails.size();
    }

    private static int binaryInsertPosition(List<Integer> list, int target, boolean backStep) {
        int si = 0, ei = list.size() - 1;

        while (si <= ei) {
            int mid = (si + ei) >>> 1;
            int val = list.get(mid);

            if (val <= target) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }
        
        int insertPos = si;
        // if (backStep) {
            int lastIndex = insertPos - 1;
            if (lastIndex >= 0 && list.get(lastIndex) == target) {
                return lastIndex;
            }
        // }

        return insertPos;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 2, 2, 3, 4, 4, 5};
        int[] nums1 = {1, 5, 2, 3, 4, 5};

        System.out.println("Input: nums1[] = " + Arrays.toString(nums1));
        System.out.println("1. No duplicates (strict): " + lengthOfLIS_NoDuplicates(nums1));
        System.out.println("\nInput: nums[] = " + Arrays.toString(nums));
        System.out.println("2. With duplicates (strict): " + lengthOfLIS_IgnoreDuplicates(nums));
        System.out.println("3. Non-decreasing (duplicates allowed): " + lengthOfLIS_AllowDuplicates(nums));
    }
}


// javac -d bin Main.java && java -cp '.;bin;bin\**' Main


// o/p
// Input: nums1[] = [1, 5, 2, 3, 4, 5]
// 1. No duplicates (strict): 5

// Input: nums[] = [1, 5, 2, 2, 3, 4, 4, 5]
// 2. With duplicates (strict): 5
// 3. Non-decreasing (duplicates allowed): 7