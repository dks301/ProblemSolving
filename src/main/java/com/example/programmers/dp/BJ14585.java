package com.example.programmers.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ14585 {

    private static final String SPACE = " ";
    private static final int MAX_X_Y = 300;

    public static void main(String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final String[] input = bufferedReader.readLine().split(SPACE);
        final int N = Integer.parseInt(input[0]);
        final int M = Integer.parseInt(input[1]);

        final int[][] dp = new int[MAX_X_Y + 1][MAX_X_Y + 1];

        for (int i = 0; i < N; i++) {
            final String[] basketPosition = bufferedReader.readLine().split(SPACE);
            final int x = Integer.parseInt(basketPosition[0]);
            final int y = Integer.parseInt(basketPosition[1]);
            dp[x][y] = M;
        }

        for (int i = 1; i <= MAX_X_Y; i++) {
            final int remainCandyX = dp[i][0] - i;
            dp[i][0] = remainCandyX > 0 ? dp[i - 1][0] + remainCandyX : dp[i - 1][0];
            final int remainCandyY = dp[0][i] - i;
            dp[0][i] = remainCandyY > 0 ? dp[0][i - 1] + remainCandyY : dp[0][i - 1];
        }
        for (int i = 1; i <= MAX_X_Y; i++) {
            for (int j = 1; j <= MAX_X_Y; j++) {
                final int remainCandy = dp[i][j] - (i + j);
                final int left = dp[i - 1][j];
                final int down = dp[i][j - 1];
                dp[i][j] = Math.max(left, down) + Math.max(remainCandy, 0);
            }
        }

        System.out.println(dp[MAX_X_Y][MAX_X_Y]);
    }
}
