package com.example.programmers.dbf_bfs;

import java.util.ArrayDeque;

public class 게임_맵_최단거리 {
    private static final int[][] DIRECTIONS = {{1, 0},{-1, 0},{0, 1},{0, -1}};
    private static final int ROW = 0;
    private static final int COL = 1;
    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{1,0,1,1,1}, {1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}}));
        System.out.println(solution(new int[][]{{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}}));
    }

    public static int solution(int[][] maps) {
        final int N = maps.length - 1;
        final int M = maps[0].length - 1;

        int[][] check = new int[maps.length][maps[0].length];

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length; j++) {
                if (maps[i][j] == 0) { // 벽
                    check[i][j] = -1; // 방문 못함
                }
            }
        }

        final ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.addLast(new Node(0, 0));
        check[0][0] = 1;

        while(!queue.isEmpty()) {
            final Node current = queue.removeFirst();

            for (final int[] DIRECTION : DIRECTIONS) {
                final Node next = new Node(current.x + DIRECTION[ROW], current.y + DIRECTION[COL]);

                if (next.x < 0 || next.y < 0 || next.x > N || next.y > M) {
                    continue;
                }

                if (check[next.x][next.y] != -1) {
                    if (check[next.x][next.y] == 0) {
                        queue.addLast(next);
                        check[next.x][next.y] = check[current.x][current.y] + 1;
                    } else {
                        check[next.x][next.y] = Math.min(check[current.x][current.y] + 1, check[next.x][next.y]);
                    }
                }
            }
        }

        return check[N][M] != 0 ? check[N][M] : -1;
    }

    private static class Node {
        private final int x;
        private final int y;

        public Node(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private static void printMap(int[][] check) {

        for (final int[] ints : check) {
            for (int j = 0; j < check[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
