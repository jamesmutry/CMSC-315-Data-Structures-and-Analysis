package com.example.p4;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;


public class GraphGUI extends Application {
    private GraphView graphView;
    private UndirectedGraph graph;
    private Button btnConnected;
    private Button btnBfs;
    private Button btnCycles;
    private Button btnDfs;
    private Button btnEdge;
    private TextField tfMessage = new TextField();
    private TextField tfVertexOne;
    private TextField tfVertexTwo;

    @Override
    public void start(Stage primaryStage) {
        try {
            //add the top and bottom border to the pane
            graph = new UndirectedGraph();
            graphView = new GraphView(graph);
            graphView.setTop(getTopBorder());
            graphView.setBottom(getBottomBorder());

            setEventHandlers();

            Scene scene = new Scene(graphView, 550, 600);
            primaryStage.setTitle("Project 4: Graph GUI");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(IndexOutOfBoundsException ex) {
            System.out.print(ex);
        }
        catch(Exception ex) {
            System.out.print(ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public HBox getTopBorder() {
        /* creating hbox */
        HBox hBox = new HBox(15);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(15, 15, 15, 15));

        btnEdge = new Button("Add Edge");
        tfVertexOne = new TextField();
        tfVertexTwo = new TextField();
        tfVertexOne.setPrefWidth(30);
        tfVertexTwo.setPrefWidth(30);

        //Add fields to HBox
        hBox.getChildren().addAll(btnEdge, new Label("Vertex 1"), tfVertexOne,
                new Label("Vertex 2"), tfVertexTwo);

        return hBox;
    }

    public VBox getBottomBorder() {
        /* create hbox to hold buttons*/
        HBox hBox = new HBox(15);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(0, 0, 10, 0));

        /* create buttons */
        btnConnected = new Button("Is Connected?");
        btnCycles = new Button("Has Cycles?");
        btnDfs = new Button("Depth First Search");
        btnBfs = new Button("Breadth First Search");

        /* create large text field to display message */
        tfMessage.setEditable(false);

        /* add buttons to hbox */
        hBox.getChildren().addAll(btnConnected, btnCycles, btnDfs, btnBfs);

        /* add hbox and tfmessage to vbox */
        VBox vBox = new VBox(hBox, tfMessage);
        vBox.setPadding(new Insets(15, 30, 30, 30));

        return vBox;
    }

    public void setEventHandlers() {
        //Add edge handler
        btnEdge.setOnAction(event ->	{
            tfMessage.clear();
            //Verify the vertex input is a letter
            if(isLetter(tfVertexOne.getText()) && isLetter(tfVertexTwo.getText())) {
                //Verify that the vertex input is uppercase, if not set to uppercase
                String vertex1 = isUpperCase(tfVertexOne.getText()) ? tfVertexOne.getText() :
                        setUpperCase(tfVertexOne.getText());
                String vertex2 = isUpperCase(tfVertexTwo.getText()) ? tfVertexTwo.getText() :
                        setUpperCase(tfVertexTwo.getText());

                //Check the vertex exists in vertex list
                if(graph.isVertex(vertex1) && graph.isVertex(vertex2)) {
                    graphView.drawEdge(vertex1, vertex2);
                }
                else
                    tfMessage.setText("Vertex not found.");
            }
            else
                tfMessage.setText("Please enter a valid letter.");
        });

        btnDfs.setOnAction(e -> {
            String dfs = graph.dfs().toString();
            tfMessage.setText("Depth First Search: " + dfs.substring(1, dfs.length() - 1));
        });
        btnBfs.setOnAction(e -> {
            String bfs = graph.bfs().toString();
            tfMessage.setText("Breadth First Search: " + bfs.substring(1, bfs.length() - 1));
        });
        btnConnected.setOnAction(e -> tfMessage.setText(graph.isConnected() ? "The graph is connected" :
                "The graph is not connected"));
        btnCycles.setOnAction(e -> tfMessage.setText(graph.hasCycles() ? "The graph has cycles" :
                "The graph doesn't have cycles."));
    }

    public boolean isUpperCase(String name) {
        return !Character.isLowerCase(name.charAt(0));
    }

    public String setUpperCase(String name) {
        return String.valueOf(Character.toUpperCase(name.charAt(0)));
    }

    public boolean isLetter(String name) {
        return Character.isLetter(name.charAt(0));
    }
}
