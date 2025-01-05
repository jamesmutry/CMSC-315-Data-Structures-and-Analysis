package com.example.p4;

import java.util.*;

public class UndirectedGraph {
    private int nameIndex = 0; //first name should be A
    private int vertexListIndex = 0; //keeps track of first index in the adjaceny list
    private List<ArrayList<Point>> vertexList = new ArrayList<>();

    /* no-constructor */
    public UndirectedGraph() {

    }

    public Point addVertex(double x, double y) {
        String name = createName();
        Point point = new Point(x, y, name);
        vertexList.add(new ArrayList<>());
        vertexList.get(vertexListIndex).add(point);
        vertexListIndex++;
        return point;
    }

    /* uses index from global data field to create names through alphabet
     * uses modulo to control the values to only go from A to Z */
    public String createName() {
        char name = (char)('A' + nameIndex % 26);
        int suffix = nameIndex / 26; //creates a suffix to repeat the alphabet labels
        nameIndex++;

        if(suffix == 0)
            return String.valueOf(name);
        else
            return String.valueOf(name) + suffix;
    }

    public Point getPoint(int i) {
        return vertexList.get(i).get(0);
    }

    public void addEdge(int i1, Point p1, int i2, Point p2) {
        vertexList.get(i1).add(p2);
        vertexList.get(i2).add(p1);

    }

    public List<String> dfs() {
        int start = 0; //Start at A

        //Create list
        List<String> searchOrder = new ArrayList<>();

        //Keep track of visited indices
        boolean[] isVisited = new boolean[vertexList.size()];

        // Recursively search
        dfs(start, searchOrder, isVisited);

        return searchOrder;
    }

    private void dfs(int i, List<String> searchOrder,
                     boolean[] isVisited) {
        searchOrder.add(vertexList.get(i).get(0).getName());
        isVisited[i] = true;

        for (Point e : vertexList.get(i)) {
            int next = calculateNameIndex(e.getName());
            if (!isVisited[next]) {
                dfs(next, searchOrder, isVisited);
            }
        }
    }

    public List<String> bfs() {
        int start = 0; //Start at A

        //Create lists
        List<String> searchOrder = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<>();

        //Keep track of visited indices
        boolean[] isVisited = new boolean[vertexList.size()];

        //Begin search
        queue.offer(start);
        isVisited[start] = true;

        while(!queue.isEmpty()) {
            int i = queue.poll();
            searchOrder.add(vertexList.get(i).get(0).getName());
            for (Point e: vertexList.get(i)){
                int next = calculateNameIndex(e.getName());
                if(!isVisited[next]) {
                    queue.offer(next);
                    isVisited[next] = true;
                }
            }
        }

        return searchOrder;
    }

    /* every vertice must be connected so the size of a bfs list should be the same as the vertex list */
    public boolean isConnected() {
        List<String> bfs = bfs();
        if(bfs.size() == vertexList.size())
            return true;
        else
            return false;
    }

    /* Modify dfs so that if a node has been visited but it is not A then it has a cycle */
    public boolean hasCycles() {
        int start = 0; //Start at A
        List<String> searchOrder = new ArrayList<>();
        //Keep track of visited indices
        boolean[] isVisited = new boolean[vertexList.size()];

        return hasCycles(start, searchOrder, isVisited);
    }

    private boolean hasCycles(int i, List<String> searchOrder, boolean[] isVisited) {
        searchOrder.add(vertexList.get(i).get(0).getName());
        isVisited[i] = true;
        boolean cycle = false;

        for (Point e : vertexList.get(i)) {
            int next = calculateNameIndex(e.getName());
            if (isVisited[next] && next != 0)
                cycle = true;
            if (!isVisited[next]) {
                hasCycles(next, searchOrder, isVisited);
            }
        }

        return cycle;
    }

    public boolean isVertex(String name) {
        int index = calculateNameIndex(name);
        if(index > vertexList.size() - 1)
            return false;

        return true;
    }

    public int calculateNameIndex(String name) {
        return (int)name.charAt(0) - 'A';
    }

}
