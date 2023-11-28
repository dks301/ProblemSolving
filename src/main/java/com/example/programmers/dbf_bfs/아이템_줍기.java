package com.example.programmers.dbf_bfs;

import javax.swing.text.Position;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class 아이템_줍기 {

    private static final int[][] DIRECTIONS_WITH_DIAGONAL = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static final int ROW = 0;
    private static final int COL = 1;

    public static void main(String[] args) {
        System.out.println(solution(new int[][] {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}}, 1, 3, 7, 8));

    }

    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        final int[][] maps = drawOuterLine(drawRectangle(rectangle, initMap()));
        final boolean[][] check = new boolean[51][51];
        final Deque<Position> queue = new ArrayDeque<>();
        queue.addLast(new Position(characterX, characterY));
        check[characterX][characterY] = true;

        while(!queue.isEmpty()) {
            final Position current = queue.removeFirst();

//            printMap(maps);

            for (final int[] DIRECTION : DIRECTIONS) {
                final int nextX = current.x + DIRECTION[ROW];
                final int nextY = current.y + DIRECTION[COL];

                if (nextX < 0 || nextY < 0 || nextX > 50 || nextY > 50) {
                    continue;
                }
                if (maps[nextX][nextY] != 2 || check[nextX][nextY]) {
                    continue;
                }

                queue.addLast(new Position(nextX, nextY));
                maps[nextX][nextY] = maps[current.x][current.y] + 1;
            }
        }

        return maps[itemX][itemY] - 2;
    }

    private static int[][] drawOuterLine(final int[][] map) {
        final Deque<Position> queue = new ArrayDeque<>();
        final boolean[][] check = new boolean[51][51];
        queue.addLast(new Position(0, 0));
        check[0][0] = true;

        printMap(map);
        while(!queue.isEmpty()) {
            final Position current = queue.removeFirst();

            for (final int[] DIRECTION : DIRECTIONS_WITH_DIAGONAL) {
                final int nextX = current.x + DIRECTION[ROW];
                final int nextY = current.y + DIRECTION[COL];

                if (nextX < 0 || nextY < 0 || nextX > 50 || nextY > 50) {
                    continue;
                }

                if (!check[nextX][nextY]) {
                    if (map[nextX][nextY] == 0) {
                        queue.addLast(new Position(nextX, nextY));
                    } else if (map[nextX][nextY] == 1) {
                        map[nextX][nextY] = 2;
                    }
                    check[nextX][nextY] = true;
                }

            }
        }

        printMap(map);
        return map;
    }

    private static void printMap(final int[][] map) {
        for (int i = 0; i < 51; i++) {
            for (int j = 0; j < 51; j++) {
                System.out.print((map[i][j] < 10 ? " " + map[i][j] : map[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int[][] initMap() {
        final int[][] map = new int[51][51];
        for (int i = 0; i < 51; i++) {
            Arrays.fill(map[i], 0);
        }
        return map;
    }

    private static int[][] drawRectangle(final int[][] rectangle, final int[][] map) {
        for (final int[] rectangleXY : rectangle) {
            final Position leftDown = new Position(rectangleXY[0], rectangleXY[1]);
            final Position rightUp = new Position(rectangleXY[2], rectangleXY[3]);

            for (int i = leftDown.x; i <= rightUp.x; i++) {
                for (int j = leftDown.y; j <= rightUp.y; j++) {
                    map[i][j] = 1; // 사각형 내부는 1로 초기화
                }
            }
        }
        return map;
    }

    private static final class Position {
        private final int x;
        private final int y;

        public Position(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }
}
