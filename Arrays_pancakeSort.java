package prampInterviews;

import java.io.*;
import java.util.*;

public class Arrays_pancakeSort {

//Pancake sort ///////////////////////////////////////////////////////////////////
  static int[] flip(int[] arr, int k)
  {
    int mid = k/2;
    for(int i=0; i<=mid; i++)
    {
      int temp = arr[i];
      arr[i] = arr[k-i];
      arr[k-i] = temp;
    }
    return arr;
  }
  
  static int findMaxIndex(int[] arr, int n)
  {
    int maxIndex = 0;
    for(int i=0; i<=n; i++)
    {
      if(arr[i]>arr[maxIndex])
      {
        maxIndex = i;
      }
    }
    return maxIndex;
  }
  
  static int[] pancakeSort(int[] arr) {
    // your code goes here
    for(int i=arr.length-1; i>=1; i--)
    {
      int maxElementIndex = findMaxIndex(arr,i);
      System.arraycopy(arr,0,flip(arr,maxElementIndex),0,arr.length);
      System.arraycopy(arr,0,flip(arr,i),0,arr.length);      
    }
    return arr;
  }
///////////////////////////////////////////////////////////////////////////////////
  public static void main(String[] args) {
    int arr[] = {1,5,4,3,2,0};
    System.out.print(Arrays.toString(pancakeSort(arr)));    
  }

}