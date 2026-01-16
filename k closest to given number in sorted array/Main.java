import java.util.*;

class Main {

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;

        // Binary search to locate the best starting point for the k-window
        int left = 0, right = n - k - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (x - arr[mid] > arr[mid + k] - x)
                left = mid + 1;
            else
                right = mid - 1;
        }


        List<Integer> ans = new ArrayList<>();
        for (int i = left; i < left + k; i++)
            ans.add(arr[i]);

        // ===== remove duplicates =====
        // Not using HashSet to save O(k) space
        
        // List<Integer> filtered = new ArrayList<>();
        // int prev = Integer.MIN_VALUE;
        // for (int num : ans) { 
        //     if (num != prev)
        //         filtered.add(num);
        //     prev = num;
        // }
        // return filtered;
        

        return ans; // include duplicates
    }

    public static void main(String[] args) {

        int[][] testArrays = {
            {1, 2, 3, 4, 5},       // simple increasing
            {1, 2, 2, 3, 4},       // duplicates
            {1, 1, 1, 10, 10, 10}  // clustered duplicates
        };

        int[] ks = {3, 2, 1};
        int[] xs = {0, 6, 2, 9, 10};

        for (int i = 0; i < testArrays.length; i++) {
            System.out.println("Array: " + Arrays.toString(testArrays[i]));
            for (int k : ks) {
                for (int x : xs) {
                    List<Integer> result = findClosestElements(testArrays[i], k, x);
                    System.out.println("k=" + k + ", x=" + x + " -> " + result);
                }
            }
            System.out.println("-----");
        }
    }
}
