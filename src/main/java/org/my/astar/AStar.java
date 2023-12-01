package org.my.astar;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * ClassName: AStar
 * @Description: A星算法
 * @author kesar
 */
public class AStar {
    public static final int BAR = 1; // 障碍值
    public static final int PATH = 2; // 路径
    public static final int DIRECT_VALUE = 10; // 横竖移动代价
    public static final int OBLIQUE_VALUE = 14; // 斜移动代价

    Queue<Node> openList = new PriorityQueue<>(); // 优先队列(升序)
    List<Node> closeList = new ArrayList<>();

    /**
     * 开始算法
     */
    public void start(Map map) {
        if (map == null) {
            return;
        }
        openList.clear();
        closeList.clear();
        // 开始搜索
        openList.add(map.getStart());
        moveNodes(map);
    }

    /**
     * 移动当前结点
     */
    private void moveNodes(Map map) {
        while (!openList.isEmpty()) {
            Node current = openList.poll();
            closeList.add(current);
            addNeighborNodeInOpen(map, current);
            if (isCoordInClose(map.getEnd().getPoint())) {
                drawPath(map.getMap(), map.getEnd());
                break;
            }
        }
    }

    /**
     * 在二维数组中绘制路径
     */
    private void drawPath(int[][] maps, Node end) {
        if (end == null || maps == null) {
            return;
        }
        System.out.println("总代价：" + end.getG());
        while (end != null) {
            Point c = end.getPoint();
            maps[c.getY()][c.getX()] = PATH;
            end = end.getParent();
        }
    }

    /**
     * 添加所有邻结点到open表
     */
    private void addNeighborNodeInOpen(Map map, Node current) {
        int x = current.getPoint().getX();
        int y = current.getPoint().getY();
        // 左
        addNeighborNodeInOpen(map, current, x - 1, y, DIRECT_VALUE);
        // 上
        addNeighborNodeInOpen(map, current, x, y - 1, DIRECT_VALUE);
        // 右
        addNeighborNodeInOpen(map, current, x + 1, y, DIRECT_VALUE);
        // 下
        addNeighborNodeInOpen(map, current, x, y + 1, DIRECT_VALUE);
//        // 左上
//        addNeighborNodeInOpen(map, current, x - 1, y - 1, OBLIQUE_VALUE);
//        // 右上
//        addNeighborNodeInOpen(map, current, x + 1, y - 1, OBLIQUE_VALUE);
//        // 右下
//        addNeighborNodeInOpen(map, current, x + 1, y + 1, OBLIQUE_VALUE);
//        // 左下
//        addNeighborNodeInOpen(map, current, x - 1, y + 1, OBLIQUE_VALUE);
    }

    /**
     * 添加一个邻结点到open表
     */
    private void addNeighborNodeInOpen(Map map, Node current, int x, int y, int value) {
        if (canAddNodeToOpen(map, x, y)) {
            Node end = map.getEnd();
            Point point = new Point(x, y);
            int G = current.getG() + value; // 计算邻结点的G值
            Node child = findNodeInOpen(point);
            if (child == null) {
                int H = calcH(end.getPoint(), point); // 计算H值
                if (isEndNode(end.getPoint(), point)) {
                    child = end;
                    child.setParent(current);
                    child.setG(G);
                    child.setH(H);
                } else {
                    child = new Node(point, current, G, H);
                }
                openList.add(child);
            } else if (child.getG() > G) {
                child.setG(G);
                child.setParent(current);
                openList.add(child);
            }
        }
    }

    /**
     * 从Open列表中查找结点
     */
    private Node findNodeInOpen(Point coord) {
        if (coord == null || openList.isEmpty()) {
            return null;
        }
        for (Node node : openList) {
            if (node.getPoint().equals(coord)) {
                return node;
            }
        }
        return null;
    }


    /**
     * 计算H的估值：“曼哈顿”法，坐标分别取差值相加
     */
    private int calcH(Point end, Point coord) {
        return (Math.abs(end.getX() - coord.getX()) + Math.abs(end.getY() - coord.getY())) * DIRECT_VALUE;
    }

    /**
     * 判断结点是否是最终结点
     */
    private boolean isEndNode(Point end, Point coord) {
        return end.equals(coord);
    }

    /**
     * 判断结点能否放入Open列表
     */
    private boolean canAddNodeToOpen(Map map, int x, int y) {
        // 是否在地图中
        if (x < 0 || x >= map.getWidth() || y < 0 || y >= map.getHeight()) {
            return false;
        }
        // 判断是否是不可通过的结点
        if (map.getMap()[y][x] == BAR) {
            return false;
        }
        // 判断结点是否存在close表
        return !isCoordInClose(x, y);
    }

    /**
     * 判断坐标是否在close表中
     */
    private boolean isCoordInClose(Point point) {
        return point != null && isCoordInClose(point.getX(), point.getY());
    }

    /**
     * 判断坐标是否在close表中
     */
    private boolean isCoordInClose(int x, int y) {
        if (closeList.isEmpty()) {
            return false;
        }
        for (Node node : closeList) {
            if (node.getPoint().getX() == x && node.getPoint().getY() == y) {
                return true;
            }
        }
        return false;
    }
}
