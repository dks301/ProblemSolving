package com.example.programmers.stack_queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.function.Function;

public class 같은_숫자는_싫어 {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(solution(new int[]{4,4,4,3,3})));

    }

    public static int[] solution(int []arr) {

        final ArrayDeque<Integer> deque = new ArrayDeque<>();

        int before = arr[0];
        deque.addLast(before);

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == before) {
                continue;
            }
            before = arr[i];
            deque.addLast(arr[i]);
        }

        return deque.stream().mapToInt(it -> it).toArray();
    }
}
