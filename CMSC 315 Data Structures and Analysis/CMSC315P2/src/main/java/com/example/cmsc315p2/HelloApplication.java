//James Mutry
//CMSC 315
//PROJECT2
package com.example.cmsc315p2;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.input.MouseButton;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Read points from a file (mock method, replace with actual file reading)
        ArrayList<Point> points = readPointsFromFile("/Users/james/IdeaProjects/CMSC315P2/src/main/java/com/example/cmsc315p2/points");

        // Create a pane to display the points and lines
        PointPane pane = new PointPane(points);

        // Create the main scene and set it on the primary stage
        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setTitle("Maximal Points");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ArrayList<Point> readPointsFromFile(String filename) {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(200.0, 300.0));
        points.add(new Point(250.0, 300.0));
        points.add(new Point(330.0, 270.0));
        points.add(new Point(150.0, 380.0));
        points.add(new Point(126.0, 172.0));
        points.add(new Point(397.0, 379.0));
        points.add(new Point(334.0, 441.0));
        points.add(new Point(53.0, 288.0));
        points.add(new Point(89.0, 433.0));
        points.add(new Point(182.0, 215.0));
        points.add(new Point(251.0, 414.0));
        points.add(new Point(116.0, 54.0));
        return points;
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }

    // Custom Point class that represents a point with x and y coordinates
    static class Point implements Comparable<Point> {
        final double x;
        final double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        // Check if this point is below and to the left of another point
        public boolean isBelowAndToLeftOf(Point other) {
            return this.y < other.y || (this.y == other.y && this.x < other.x);
        }

        // Compare points based on their x coordinates
        @Override
        public int compareTo(Point other) {
            return Double.compare(this.x, other.x);
        }
    }

    // Custom Pane class to display points and lines
    static class PointPane extends Pane {
        ArrayList<Point> points;

        public PointPane(ArrayList<Point> points) {
            this.points = points;
            drawPane(); // Initialize the pane with points and lines
            // Handle mouse clicks to add or remove points
            setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    // Add a new point on left click
                    Point newPoint = new Point(e.getX(), e.getY());
                    points.add(newPoint);
                    drawPane(); // Redraw the pane with the updated points
                } else if (e.getButton() == MouseButton.SECONDARY) {
                    // Remove a point on right click
                    Point pointToRemove = findPointAt(e.getX(), e.getY());
                    points.remove(pointToRemove);
                    drawPane(); // Redraw the pane with the updated points
                }
            });
        }

        // Find a point at a specific location (used for removing points)
        private Point findPointAt(double x, double y) {
            for (Point point : points) {
                if (Math.abs(point.getX() - x) < 5 && Math.abs(point.getY() - y) < 5) {
                    return point;
                }
            }
            return null;
        }

        // Redraw the pane with points and lines
        private void drawPane() {
            getChildren().clear(); // Clear previous points and lines
            ArrayList<Point> maximalPoints = findMaximalPoints(); // Find maximal points
            for (Point point : points) {
                Circle circle = new Circle(point.getX(), point.getY(), 3);
                getChildren().add(circle); // Add circles to represent points
            }
            connectMaximalPoints(maximalPoints); // Connect maximal points with lines
        }

        // Find maximal points based on x-coordinate sorting
        private ArrayList<Point> findMaximalPoints() {
            Collections.sort(points); // Sort points by x coordinate
            ArrayList<Point> result = new ArrayList<>();
            for (Point p : points) {
                while (!result.isEmpty() && !result.get(result.size() - 1).isBelowAndToLeftOf(p)) {
                    result.remove(result.size() - 1); // Remove if not maximal
                }
                result.add(p);
            }
            return result;
        }

        // Connect maximal points with horizontal and vertical lines
        private void connectMaximalPoints(ArrayList<Point> maximalPoints) {
            for (int i = 0; i < maximalPoints.size() - 1; i++) {
                Point p1 = maximalPoints.get(i);
                Point p2 = maximalPoints.get(i + 1);
                Line line = new Line(p1.getX(), p1.getY(), p2.getX(), p1.getY());
                Line line2 = new Line(p2.getX(), p1.getY(), p2.getX(), p2.getY());
                line.setStroke(Color.BLACK);
                line2.setStroke(Color.BLACK);
                getChildren().addAll(line, line2); // Add lines to connect points
            }
        }
    }
}