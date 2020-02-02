package com.tryingpfq.letcode;

class Solution {
    public static int[] twoSum(int[] nums, int target) {
        int first = 0,last = nums.length -1;

        while(first != last){
            if((nums[first] + nums[last]) == target){
                return new int[]{first,last};
            }else{
                first++;
                last++;
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int[] dd = new int[]{1,2,3,4,5};
    }
}