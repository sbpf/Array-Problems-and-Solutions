import java.util.*;

public class Arrays_SingleParse_OnePointer {
	/*665. Non-decreasing Array
	 *  Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.
		We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

		Example 1:
		Input: [4,2,3]
		Output: True
		Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
		Example 2:
		Input: [4,2,1]
		Output: False
		Explanation: You can't get a non-decreasing array by modify at most one element.
	 */
	//Time complexity: O(N) Space: O(1)
	public static boolean checkPossibility(int[] nums) {
        int index = -1;
        for(int i=0; i<nums.length-1; i++)
        {
            if(nums[i]>nums[i+1])
            {
                if (index!=-1) return false;
                else
                    index = i;
            }                
        }
        return( index==-1 || index==0 || index == nums.length-2 || nums[index-1] <= nums[index+1] || nums[index] <= nums[index+2]);
    }
	
	
	/*747. Largest Number At Least Twice of Others
	 * In a given integer array nums, there is always exactly one largest element.

	Find whether the largest element in the array is at least twice as much as every other number in the array.
	
	If it is, return the index of the largest element, otherwise return -1.
	
	Example 1:	
	Input: nums = [3, 6, 1, 0]
	Output: 1
	Explanation: 6 is the largest integer, and for every other number in the array x,
	6 is more than twice as big as x.  The index of value 6 is 1, so we return 1.
	 */
	//This function parses the array twice.
	//Time complexity: O(N) Space: O(1)
	public static int dominantIndex(int[] nums) {
        int max = -1, max_Index = -1;
        for(int i=0; i<nums.length; i++)
        {
            if(nums[i] > max)
            {
                max = nums[i];
                max_Index = i;
            }
        }
        for(int j = 0; j<nums.length; j++)
        {
            if(max < (nums[j] + nums[j]) && max_Index != j)                
            return -1;
        }
        return max_Index;
    }
	
	/* 189. Rotate Array
	 * Given an array, rotate the array to the right by k steps, where k is non-negative.

		Example 1:
		
		Input: [1,2,3,4,5,6,7] and k = 3
		Output: [5,6,7,1,2,3,4]
		Explanation:
		rotate 1 steps to the right: [7,1,2,3,4,5,6]
		rotate 2 steps to the right: [6,7,1,2,3,4,5]
		rotate 3 steps to the right: [5,6,7,1,2,3,4]
	 */
	//Time complexity: O(N) Space: O(1)
	public static int[] rotate(int[] nums, int k) {
        k = k % nums.length;
        int start=0,current=0,next=0,prev = 0,count = 0;
        for(start = 0; count<nums.length; start++)
        {
            int temp;
            prev = nums[start];         
            current = start;
            do{
                next = (current+k) % nums.length;
                temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;            
                count++;                
            }while(current != start);
        }
        return nums;
    }
	
	/* 414. Third Maximum Number
	 * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).
		
		Example 1:
		Input: [3, 2, 1]		
		Output: 1		
		Explanation: The third maximum is 1.
	 */	
	//Time complexity: O(N) Space: O(1)	
	public static int thirdMax(int[] nums) {
        Integer firstMax = null, secondMax = null, thirdMax = null;
        
        for(Integer n: nums)
        {
            if(n.equals(firstMax) || n.equals(secondMax) || n.equals(thirdMax))
                continue;
            
            if(firstMax == null || n > firstMax)
            { 
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = n;                
            }
            else if(secondMax == null || n > secondMax)
            {
                thirdMax = secondMax;
                secondMax = n;
            }
            else if(thirdMax == null || n>thirdMax)
                thirdMax = n;
        }
        return thirdMax != null ? thirdMax :firstMax;        
    }
	
	 /* 605. Can Place Flowers
     * Suppose you have a long flowerbed in which some of the plots are planted and some are not. However,
     *  flowers cannot be planted in adjacent plots - 
     * they would compete for water and both would die.

	   Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty),
	   and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.
	   Example 1:
		Input: flowerbed = [1,0,0,0,1], n = 1
		Output: True
		Example 2:
		Input: flowerbed = [1,0,0,0,1], n = 2
		Output: False
     * 
     */
    public static boolean canPlaceFlowers(int[] flowerbed, int n)
    {
        int i = 0, count = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i++] = 1;
                count++;
            }
             if(count>=n)
                return true;
            i++;
        }
        return false;
    }
    
    /*121. Best Time to Buy and Sell Stock
     * Say you have an array for which the ith element is the price of a given stock on day i.

		If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
		
		Note that you cannot sell a stock before you buy one.
		Example 1:

		Input: [7,1,5,3,6,4]
		Output: 5
		Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
		             Not 7-1 = 6, as selling price needs to be larger than buying price.
		Example 2:
		
		Input: [7,6,4,3,1]
		Output: 0
		Explanation: In this case, no transaction is done, i.e. max profit = 0.
	*/
    public static int maxProfit(int[] prices)
    {
        int min = Integer.MAX_VALUE, profit=0;
        
        for(int i=0; i<prices.length; i++)
        {
            if(prices[i] < min)
                min = prices[i];
            else if(prices[i] - min > profit)
                profit = prices[i]-min;
        }
        return profit;
    } 
    
    /*122. Best Time to Buy and Sell Stock II
      
       Say you have an array for which the ith element is the price of a given stock on day i.
		Design an algorithm to find the maximum profit. 
		You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).		
		Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
		Input: [7,1,5,3,6,4]
		Output: 7
		Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
		             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
	*/
    public static int maxProfit2(int[] prices) {
        int profit = 0;
        for(int i=1; i<prices.length; i++)
        {
            if(prices[i]>prices[i-1])
                profit += prices[i] - prices[i-1];
        }
        return profit;
    }
    
    /*628. Maximum Product of Three Numbers
     * Given an integer array, find three numbers whose product is maximum and output the maximum product.

		Example 1:
		Input: [1,2,3]
		Output: 6
		Example 2:
		Input: [1,2,3,4]
		Output: 24
     * 
     */
    public static int maximumProduct(int[] nums) {
        int negativeMin1 = Integer.MAX_VALUE, negativeMin2 = Integer.MAX_VALUE, negativeMin3 = Integer.MAX_VALUE;
        int positiveMax1 = Integer.MIN_VALUE, positiveMax2 = Integer.MIN_VALUE, positiveMax3 = Integer.MIN_VALUE;
        
        for(int n : nums)
        { 
            if(n < negativeMin1)
            {
                negativeMin3 = negativeMin2;
                negativeMin2 = negativeMin1;
                negativeMin1 = n;
            }
            else if (n < negativeMin2)
            {
                negativeMin3 = negativeMin2;
                negativeMin2 = n;
            }
            else if(n<negativeMin3)
            {
                negativeMin3 = n;
            }

            if(n>=positiveMax1)
            {
                positiveMax3 = positiveMax2;
                positiveMax2 = positiveMax1;
                positiveMax1 = n;
            }
            else if(n>=positiveMax2)
            {
                positiveMax3 = positiveMax2;
                positiveMax2 = n;
            }
            else if(n>positiveMax3)
            {
                positiveMax3 = n;
            }       
        }
        int productUsingNegatives = Math.max((negativeMin3 * negativeMin2 * negativeMin1),(negativeMin2 * negativeMin1 * positiveMax1));
        return Math.max(productUsingNegatives, (positiveMax1*positiveMax2*positiveMax3));
    }
    
    /*
     * 746. Min Cost Climbing Stairs
     * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

		Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.
		
		Example 1:
		Input: cost = [10, 15, 20]
		Output: 15
		Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
		Example 2:
		Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
		Output: 6
		Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
     * 
     * Dynamic programming - check more
     */
    public static int minCostClimbingStairs(int[] cost) {
        int c1 = 0, c2 = 0;
        
        for(int i= cost.length-1; i>=0; --i)
        {
            int c0 = cost[i] +  Math.min(c2,c1);
            c2 = c1;
            c1 = c0;
        }
        return(Math.min(c2,c1));
    }
    
	public static void main(String[] args) {
		//Testing non-decreasing array
		int[] nums = {1,4,2,3};
		System.out.println(checkPossibility(nums));
		
		// Testing dominantIndex
		int[] arr = {6,3,2,12};
		System.out.println(dominantIndex(arr));
		
		//Testing rotate 
		System.out.println(Arrays.toString(rotate(arr,2)));
		
		//Testing thirdMax
		System.out.println(thirdMax(arr));
		
		//testing maxProfit
		int[] stocks = {7,1,5,3,6,4};
		System.out.println("Maximum Profit = " + maxProfit(stocks));
		
		//testing  maxProfit2
		System.out.println("Maximum profit2 = "+ maxProfit2(stocks));
	}	
}

