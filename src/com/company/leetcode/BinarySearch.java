package com.company.leetcode;

public class BinarySearch {

    //  find index with value equal to target or -1 if no such value
    static int find(int A[], int target) {
        int lo = 0;
        int hi = A.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] > target) {
                hi = mid - 1;
            } else if (A[mid] < target) {
                lo = mid + 1;
            }
        }
        return -1;
    }

    //  greatest index with value less than or equal to given target
    static int floorKey(int A[], int target) {
        int lo = 0;
        int hi = A.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (A[mid] == target || mid < A.length - 1 && A[mid + 1] > target && A[mid] < target) {
                return mid;
            } else if(A[mid] > target){
                hi = mid - 1;
            } else if(A[mid] < target) {
                lo = mid + 1;
            }
        }
        return lo;
    }
    // least index with value greater than or equal to the given target
    static int ceilingKey(int A[], int target) {
        int lo = 0;
        int hi = A.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (A[mid] > target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo - 1;
    }

    // perform std bs
    // check which half is sorted and if target withing boundaries of the halves
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            }
            // left is sorted
            if(nums[left] <= nums[mid]) {
                if(nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } // right is sorted
            else {
                if(nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        /*
        int[] A = new int[]{1, 4, 5, 7, 7, 7, 7, 7, 11, 24};
        System.out.println(floorKey(A, 6));
        System.out.println(floorKey(A, 7));
        System.out.println(ceilingKey(A, 11));
        System.out.println(find(A, 4));
        */

        int[] B = new int[]{1,1,1,10,10,10};
        System.out.println(floorKey(B, 5));
        System.out.println(Integer.MAX_VALUE);
     }
}
