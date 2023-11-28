package com.example.acmicpc.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ5525 {
    private static final char I = 'I';
    private static final char O = 'O';

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int pNLength = 2 * n + 1;
        final int sLength = Integer.parseInt(br.readLine());
        final char[] s = br.readLine().toCharArray();
        int left = 0;
        int right = 0;
        int correctSize = 0;
        int answer = 0;

        for (int i = 0; i < sLength; i++) {
            switch (s[i]) {
                case I:
                    if (correctSize == 0) {
                        correctSize++;
                        left = i;
                        right = i;
                    } else if (correctSize % 2 == 0) {
                        correctSize++;
                        right++;
                    } else if (correctSize % 2 == 1) {
                        correctSize = 1;
                        left = i;
                        right = i;
                    }
                    if (correctSize == pNLength) {
                        answer++;
                        left += 2;
                        correctSize -= 2;
                    }
                    break;
                case O:
                    if (i == sLength - 1) {
                        break;
                    }
                    if (correctSize % 2 == 1) {
                        correctSize++;
                        right++;
                    } else if (correctSize % 2 == 0) {
                        correctSize = 0;
                        left = i;
                        right = i;
                    }
                    break;
            }
        }

        System.out.println(answer);
    }
}
