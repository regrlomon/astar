package org.my.astar;

public class Map {
    private int[][] map;
    private int width;
    private int height;
    private Node start;

    private Node end;

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public Map(int[][] map, int width, int height, Node start, Node end) {
        this.map = map;
        this.width = width;
        this.height = height;
        this.start = start;
        this.end = end;
    }
}
