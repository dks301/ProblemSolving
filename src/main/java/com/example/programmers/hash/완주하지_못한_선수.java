package com.example.programmers.hash;

import java.util.HashMap;
import java.util.Map;

public class 완주하지_못한_선수 {
    public static void main(String[] args) {
        System.out.println(solution(new String[]{"mislav", "stanko", "mislav", "ana"}, new String[]{"stanko", "ana", "mislav"}));
    }

    public static String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();

        for (final String p : participant) {
            map.merge(p, 1, Integer::sum);
        }

        for (final String key : completion) {
            map.merge(key, -1, Integer::sum);
        }

        return map.entrySet().stream().filter(it -> it.getValue() == 1).findFirst().get().getKey();
    }
}
