package com.example.acmicpc;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BJ12787 {
    private static final String NEW_LINE = "\n";
    private static final String DOT = ".";
    private static final String IPv8ToInteger = "1";
    private static final String IntegerToIPv8 = "2";

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int T = Integer.parseInt(br.readLine());

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            final StringTokenizer input = new StringTokenizer(br.readLine());
            sb.append(solve(input.nextToken(), input.nextToken())).append(NEW_LINE);
        }
        System.out.print(sb);
    }

    private static String solve(final String convertingType, final String before) {
        switch (convertingType) {
            case IPv8ToInteger:
                return convertIPv8ToLong(before);
            case IntegerToIPv8:
                return convertLongToIPv8(before);
            default:
                return "error";
        }
    }

    private static String convertIPv8ToLong(final String IPv8) {
        final StringTokenizer st = new StringTokenizer(IPv8, DOT);
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            final int num = Integer.parseInt(st.nextToken());
            final int leadingZeros = Integer.numberOfLeadingZeros(num) - 24;
            sb.append("0".repeat(Math.max(0, leadingZeros)));
            sb.append(num == 0 ? "" : Long.toBinaryString(num));
        }

        return new BigInteger(sb.toString(), 2).toString();
    }

    private static String convertLongToIPv8(final String number) {
        final StringBuilder sb = new StringBuilder();
        final String binaryNumber = new BigInteger(number).toString(2);
        final int leadingZeros = 64 - binaryNumber.length();
        final String temp = "0".repeat(leadingZeros) + binaryNumber;

        for (int i = 0; i < 64; i += 8) {
            sb.append(Long.parseLong(temp.substring(i, i + 8), 2)).append(i + 8 < 64 ? DOT : "");
        }
        return sb.toString();
    }
}
