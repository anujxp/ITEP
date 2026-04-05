public class ConcatenateArrays {
    public static void main(String[] args) {
        int[] array1 = {1, 2, 3};
        int[] array2 = {4, 5, 6};

        int[] result = concatenateArrays(array1, array2);

        for (int val : result) {
            System.out.print(val + " ");
        }
    }

    public static int[] concatenateArrays(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];
        for (int i = 0; i < arr1.length; i++) {
            merged[i] = arr1[i];
        }
        for (int i = 0; i < arr2.length; i++) {
            merged[arr1.length + i] = arr2[i];
        }
        return merged;
    }
}