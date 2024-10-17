package com.learning.java.dsa;

class InterleavingArray<T> {
    private T[] firstArray;
    private T[] secondArray;
    private int firstIndex;
    private int secondIndex;
    private  int totalSize;
    private  int currentIndex;

    InterleavingArray(T[] first, T[] second) {
        this.firstArray  = first;
        this.secondArray = second;
        this.firstIndex = -1;
        this.secondIndex = -1;
        this.totalSize = first.length + second.length;
    }

    boolean hasNext() {
        return currentIndex < totalSize;
    }

    T next() throws Exception{
        // whoever is lower increase that.
        /// [1,2,3,4,6,7,8]
        // [5,6,7,8]
       if(hasNext()) {
           currentIndex++;
           if(firstIndex + 1 >=  firstArray.length) {
               return secondArray[++secondIndex];
           } else if(secondIndex +1 >= secondArray.length) {
               return firstArray[++firstIndex];
           } else {
               int nextIndex = firstIndex + 1 <= secondIndex + 1 ? firstIndex + 1: secondIndex + 1;
               if(nextIndex == firstIndex + 1) {
                   firstIndex++;
                   return firstArray[nextIndex];
               } else {
                   secondIndex++;
                   return secondArray[nextIndex];
               }
           }
       } else {
           throw new Exception("no element present");
       }
    }

    void reset() {
        this.currentIndex = 0;
    }
}
public class InterleavingIterator {

    public static void main(String[] args) throws Exception {
        Integer[] v1 = {1,2,3,4,5,6,7,8};
        Integer[] v2 = {10,11,12,13,14,15,16,17};
        InterleavingArray<Integer> integerInterleavingArray = new InterleavingArray<Integer>(v1,v2);

        while (integerInterleavingArray.hasNext()) {
            System.out.print(integerInterleavingArray.next() + " ");
        }
    }
}
