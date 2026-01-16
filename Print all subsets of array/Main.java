public class Main {
    static String subsets(int arr[], int idx) {
        if(idx == arr.length) {
            return "";
        }
        int current = arr[idx];
        String withoutCurrent = subsets(arr, idx + 1);
        String split[] = withoutCurrent.split(", ");
        StringBuilder res = new StringBuilder("");
        for (int i = 0; i < split.length; i++) {
            res.append( "_ " + split[i] + ", ");
        }
        for (int i = 0; i < split.length; i++) {
            res.append(current + " " + split[i] + ", ");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        int arr[] = {10, 20, 30};
        String res = subsets(arr, 0);
        String split[] = res.split(", ");
        System.out.println("Subsets of the array:");
        for (String s : split) {
            System.out.println(s);
        }
    }
}