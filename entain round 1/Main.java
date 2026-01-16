import java.util.*;

public class Main {

// Input: s = "abacaba"Output: 4Explanation:Two possible partitions are ("a","ba","cab","a") and ("ab","a","ca","ba").
// It can be shown that 4 is the minimum number of substrings needed.
    public static void main(String[] args) {

        String input = " asdasd df f";
        System.out.println(reverseWords(input));

    }

//     bacaba
//     b; 
//     bac, ab, a


//     F(idx) {

//     }

// }
//                             caba| ab, a      caba|a, ba      caba| a,b,a

//                         acaba|ab              acaba| a, b
//                                     bacaba| a           
//                                               abacaba| .


//                                               F(n) --> F(n-1)

//                                               ----------------------------------

//                                               Input: s = "a good   example"
// Output: "example good a"
// Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.

static String getWord(String str, int[] element) {
    return str.substring(element[0], element[0]+element[1]);
}

public static String reverseWords (String str) {
    StringBuilder result = new StringBuilder();
 
    // [] --> start, len
    PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
        (a, b) -> {
            return b[0] - a[0];
        }
    );

    int len = 0;
    int start = 0;

    for(int i=0; i<str.length(); i++) {
        char c = str.charAt(i);
        if(c == ' ') {
           if(i!=0 && str.charAt(i-1) == ' ')
                continue;
           if(len != 0) {
              maxHeap.add(new int[]{start, len});
           }
           len = 0;
        } else {
            // System.out.println(c +" ")
            if(len == 0)
               start = i;
            len++;
        }
        if(i == str.length() - 1) {
            maxHeap.add(new int[]{start, len});
        }

    }

    while(maxHeap.size() > 0) {
        int[] rem = maxHeap.remove();
        result.append(getWord(str, rem) + " ");
    }

    return result.toString();
}

}