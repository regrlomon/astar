package org.my.astar;

public class Test {
    public static void main(String[] args) {
        int[][] maps = {
            {0, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 1},
            {0, 0, 0, 1}
        };
        printMap(maps);
        Map map = new Map(maps, maps[0].length, maps.length, new Node(0, 0), new Node(3, 1));
        new AStar().start(map);
        printMap(maps);
    }

    /**
     * 打印地图
     */
    public static void printMap(int[][] maps) {
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length; j++) {
                System.out.print(maps[i][j] + " ");
            }
            System.out.println();
        }
    }
}
