package com.example.programmers.hash;

import java.util.Arrays;

public class 폰켓몬 {
    public static void main(String[] args) {

        System.out.println(solution(new int[]{3,3,3,2,2,4}));
    }

    public static int solution(int[] nums) {
        final int half = nums.length / 2;
        final int count = ((int) Arrays.stream(nums).distinct().count());

        return Math.min(count, half);
    }
}
