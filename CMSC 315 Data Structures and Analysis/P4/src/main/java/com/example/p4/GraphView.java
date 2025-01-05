package com.example.p4;

import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;


public class GraphView extends BorderPane {
    private UndirectedGraph graph;

    /* no-arg constructor */
    public GraphView(UndirectedGraph graph) {
        this.graph = graph;
        setEventHandler();
    }

    public void setEventHandler() {
        /* mouse click event listener for x, y points */
        setOnMouseClicked(event ->  {
            //The center portion's height is 437
            //top border height is 0 - 50
            //bottom border height is 498 - 600
            if(event.getButton() == MouseButton.PRIMARY) {
                double x = event.getSceneX();
                double y = event.getSceneY();
                //keeping x and y within center border
                if(y > 50 && y < 498) {
                    //create a new point and turn it into a circle
                    Point newPoint = graph.addVertex(x, y);
                    drawVertex(newPoint);
                }
            }
        });
    }

    public void drawVertex(Point point) {
        getChildren().addAll(new Circle(point.getX(), point.getY(), 5.0),
                new Text(point.getX() - 5, point.getY() - 10, point.getName()));
    }

    public void drawEdge(String v1, String v2) {
        if(v1.length() == 1 && v2.length() == 1) {
            int index1 = graph.calculateNameIndex(v1); //use new method here
            int index2 = graph.calculateNameIndex(v2);
            Point p1 = graph.getPoint(index1);
            Point p2 = graph.getPoint(index2);
            graph.addEdge(index1, p1, index2, p2);
            getChildren().add(new Line(p1.getX(), p1.getY(), p2.getX(), p2.getY()));
        }
    }

}
