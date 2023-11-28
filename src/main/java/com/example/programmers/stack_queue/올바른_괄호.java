package com.example.programmers.stack_queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class 올바른_괄호 {

    private static final char START = '(';
    private static final char END = ')';


    public static void main(String[] args) {

    }

    boolean solution(String s) {

        final Deque<Character> stack = new ArrayDeque<>();

        for (final char c : s.toCharArray()) {
            switch (c) {
                case START -> stack.push(c);
                case END -> {
                    final Character last = stack.pollLast();
                    if (last == null) {
                        return false;
                    }
                }
            }
        }

        return stack.pollLast() == null;
    }
}
