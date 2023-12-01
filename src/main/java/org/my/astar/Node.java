package org.my.astar;

public class Node implements Comparable<Node> {

    private Point point;
    private Node parent;

    private int G;

    private int H;


    public Node(int x, int y) {
        this.point = new Point(x, y);
    }

    public Node(Point point, Node parent, int g, int h) {
        this.point = point;
        this.parent = parent;
        this.G = g;
        this.H = h;
    }

    @Override
    public int compareTo(Node o) {
        if (o == null) return -1;
        if (G + H > o.G + o.H) {
            return 1;
        }
        if (G + H < o.G + o.H) {
            return -1;
        }
        return 0;
    }


    public Point getPoint() {
        return point;
    }

    public Node getParent() {
        return parent;
    }

    public int getG() {
        return G;
    }

    public int getH() {
        return H;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setG(int g) {
        G = g;
    }

    public void setH(int h) {
        H = h;
    }
}
