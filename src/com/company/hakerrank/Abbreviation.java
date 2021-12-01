package com.company.hakerrank;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
You can perform the following operations on the string, :
Capitalize zero or more of 's lowercase letters.
Delete all of the remaining lowercase letters in .
Given two strings,  and , determine if it's possible to make  equal to  as described. If so, print YES on a new line. Otherwise, print NO.
For example, given  and , in  we can convert  and delete  to match . If  and , matching is not possible because letters may only be capitalized or discarded, not changed.
Function Description
Complete the function  in the editor below. It must return either  or .
abbreviation has the following parameter(s):
a: the string to modify
b: the string to match
Input Format
The first line contains a single integer , the number of queries.
Each of the next  pairs of lines is as follows:
- The first line of each query contains a single string, .
- The second line of each query contains a single string, .
Constraints


String  consists only of uppercase and lowercase English letters, ascii[A-Za-z].
String  consists only of uppercase English letters, ascii[A-Z].
Output Format
For each query, print YES on a new line if it's possible to make string  equal to string . Otherwise, print NO.
Sample Input
1
daBcd
ABC
Sample Output
YES
Explanation
image
We have  daBcd and  ABC. We perform the following operation:
Capitalize the letters a and c in  so that  dABCd.
Delete all the remaining lowercase letters in  so that  ABC.
Because we were able to successfully convert  to , we print YES on a new line.
 */
public class Abbreviation {

    //  a    A
    // ac    A

    // aas   AA
    // aAs   AA

/*
    // Complete the abbreviation function below.
    static String abbreviation(String a, String b) {

        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 1; i <= a.length(); i++) {

            for (int j = 1; j <= b.length(); j++) {

                if (Character.toUpperCase(a.charAt(i - 1)) == b.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);

                }
            }
        }

        int x = a.length();
        int y = b.length();

        Deque<Character> stack = new ArrayDeque<>();
        Queue<Character> stack2 = new LinkedList<>();

        while (x > 0 && y > 0) {
            if (Character.isUpperCase(a.charAt(x - 1))) {
                stack2.offer(a.charAt(x - 1));
            }

            if (y == 77) {
                y = y;
            }
            if (dp[x][y] == dp[x - 1][y]) {
                x--;
                continue;
            }
            if (dp[x - 1][y - 1] + 1 == dp[x][y]) {

                if (!stack2.isEmpty()) {
                    stack2.remove();
                }
                stack.push(b.charAt(y - 1));
            }
            x--;
            y--;
        }

        //deque.re
        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        final String string = sb.toString();
      //  System.out.println(string);
        return b.equals(string) && stack2.isEmpty() ? "YES" : "NO";

    }
*/

    // Complete the abbreviation function below.
    static String abbreviation(String a, String b) {

        final int m = a.length() + 1;
        final int n = b.length() + 1;
        boolean dp[][] = new boolean[n][m];
        dp[0][0]=true;
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < m ; j++) {
                if(i==0 && j!=0){
                    dp[i][j] = Character.isLowerCase(a.charAt(j-1)) && dp[i][j-1];
                }
                else if(i!=0 && j!=0) {
                    if(a.charAt(j-1)==b.charAt(i-1)){
                        dp[i][j] = dp[i-1][j-1];
                    }
                    else if(Character.toUpperCase(a.charAt(j-1))==b.charAt(i-1)){
                        dp[i][j] = dp[i-1][j-1] || dp[i][j-1];
                    }
                    else if(!Character.isUpperCase(a.charAt(j-1))){
                        dp[i][j] = dp[i][j-1];
                    }
                }
            }
        }
        return dp[n-1][m-1]?"YES":"NO";
    }

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws IOException {

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String result = abbreviation(a, b);

            System.out.println(result);
        }

        scanner.close();
    }
}

    /*
    public static void main(String[] args) {
        //  System.out.println(abbreviation("beFgH", "EFG"));
    //    System.out.println(abbreviation("daBcd", "ABC"));
    //    System.out.println(abbreviation("GggG", "GG"));
        //  System.out.println(abbreviation("LLZOSYAMQRMBTZXTQMQcKGLR", "LLZOSYAMBTZXMQKLR"));
        //  System.out.println(abbreviation("MGYXKOVSMAHKOLAZZKWXKS", "MGXKOVSAHKOLZKKDP"));
        //  System.out.println(abbreviation("VLKHNlpsrlrvfxftslslrrh", "VLKHN"));

        //
        //HUVPW
        //   System.out.println(abbreviation("JHMWY", "HUVPW"));

        System.out.println(abbreviation(
                "RDWPJPAMKGRIWAPBZSYWALDBLDOFLWIQPMPLEMCJXKAENTLVYMSJNRJAQQPWAGVcGOHEWQYZDJRAXZOYDMNZJVUSJGKKKSYNCSFWKVNHOGVYULALKEBUNZHERDDOFCYWBUCJGbvqlddfazmmohcewjg",
                "RDPJPAMKGRIWAPBZSYWALDBLOFWIQPMPLEMCJXKAENTLVYMJNRJAQQPWAGVGOHEWQYZDJRAXZOYDMNZJVUSJGKKKSYNCSFWKVNHOGVYULALKEBUNZHERDOFCYWBUCJG"));

        System.out.println(abbreviation("MBQEVZPBjcbswirgrmkkfvfvcpiukuxlnxkkenqp", "MBQEVZP"));

        System.out.println(abbreviation(
                "DINVMKSOfsVQByBnCWNKPRFRKMhFRSkNQRBVNTIKNBXRSXdADOSeNDcLWFCERZOLQjEZCEPKXPCYKCVKALNxBADQBFDQUpdqunpelxauyyrwtjpkwoxlrrqbjtxlkvkcajhpqhqeitafcsjxwtttzyhzvh",
                "DINVMKSOVQBBCWNKPRFRKMFRSNQRBVNTIKNBXRSXADOSNDLWFCERZOLQEZCEPKXPCYKCVKALNBADQBFDQU"));
        System.out.println(abbreviation(
                "BFZZVHdQYHQEMNEFFRFJTQmNWHFVXRXlGTFNBqWQmyOWYWSTDSTMJRYHjBNTEWADLgHVgGIRGKFQSeCXNFNaIFAXOiQORUDROaNoJPXWZXIAABZKSZYFTDDTRGZXVZZNWNRHMvSTGEQCYAJSFvbqivjuqvuzafvwwifnrlcxgbjmigkms",
                "BFZZVHQYHQEMNEFFRFJTQNWHFVXRXGTFNBWQOWYWSTDSTMJRYHBNTEWADLHVGIRGKFQSCXNFNIFAXOQORUDRONJPXWZXIAABZKSZYFTDDTRGZXVZZNWNRHMSTGEQCYAJSF"));


    }
    */

