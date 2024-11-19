import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Important for using MongoDB
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;


public class Main extends Application {

    VBox vbox = new VBox();
    HBox hBox1 = new HBox();
    HBox hBox2 = new HBox();
    HBox hBox3 = new HBox();
    HBox hBox4 = new HBox();
    HBox hBox5 = new HBox();
    HBox hBox6 = new HBox();

    // Labels for Program
    Label idLabel = new Label("ID:");
    Label nameLabel = new Label("Name:");
    Label ageLabel = new Label("Age:");
    Label cityLabel = new Label("City");

    // Textfields for the Programs
    TextField idField = new TextField();
    TextField nameField = new TextField();
    TextField ageField = new TextField();
    TextField cityField = new TextField();

    // Buttons for the program
    Button addButton = new Button("Add");
    Button readButton = new Button("Read");
    Button updateButton = new Button("Update");
    Button deleteButton = new Button("Delete");

    @Override
    public void start(Stage stage) throws Exception {

        // Adjusting the HBoxes for Labels
        HBox.setHgrow(idLabel, Priority.ALWAYS);
        HBox.setHgrow(nameLabel, Priority.ALWAYS);
        HBox.setHgrow(ageLabel, Priority.ALWAYS);
        HBox.setHgrow(cityLabel, Priority.ALWAYS);

        idLabel.setMaxWidth(Double.MAX_VALUE);
        nameLabel.setMaxWidth(Double.MAX_VALUE);
        ageLabel.setMaxWidth(Double.MAX_VALUE);
        cityLabel.setMaxWidth(Double.MAX_VALUE);


        // Adjusting the Hboxes for stuff
        HBox.setHgrow(idField, Priority.ALWAYS);
        HBox.setHgrow(nameField, Priority.ALWAYS);
        HBox.setHgrow(ageField, Priority.ALWAYS);
        HBox.setHgrow(cityField, Priority.ALWAYS);
        HBox.setHgrow(addButton, Priority.ALWAYS);
        HBox.setHgrow(readButton, Priority.ALWAYS);
        HBox.setHgrow(updateButton, Priority.ALWAYS);
        HBox.setHgrow(deleteButton, Priority.ALWAYS);

        idField.setMaxWidth(Double.MAX_VALUE);
        nameField.setMaxWidth(Double.MAX_VALUE);
        ageField.setMaxWidth(Double.MAX_VALUE);
        cityField.setMaxWidth(Double.MAX_VALUE);
        addButton.setMaxWidth(Double.MAX_VALUE);
        readButton.setMaxWidth(Double.MAX_VALUE);
        updateButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setMaxWidth(Double.MAX_VALUE);

        hBox1.getChildren().addAll(idLabel,idField);
        hBox2.getChildren().addAll(nameLabel,nameField);
        hBox3.getChildren().addAll(ageLabel,ageField);
        hBox4.getChildren().addAll(cityLabel,cityField);
        hBox5.getChildren().addAll(addButton,readButton);
        hBox6.getChildren().addAll(updateButton,deleteButton);


        addButton.setOnAction(e -> addButton());
        readButton.setOnAction(e -> readButton());
        updateButton.setOnAction(e -> updateButton());
        deleteButton.setOnAction(e -> deleteButton());

        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(hBox1, hBox2, hBox3, hBox4, hBox5, hBox6);


        Scene scene = new Scene(vbox, 400, 300);
        stage.setTitle("MongoDB - Mika Grönroos");
        stage.setScene(scene);
        stage.show();
    }

    public void addButton() {
        Document document = new Document("_id", idField.getText())
                .append("name", nameField.getText())
                .append("age", Integer.parseInt(ageField.getText()))
                .append("city", cityField.getText());
        MongoDBConnection.insertDocument("W4_Home_Assignment", "W4_Home_Collection", document);
        addWindow();
    }

    public void readButton() {
        Document foundDoc = MongoDBConnection.readDocument("W4_Home_Assignment", "W4_Home_Collection", idField.getText());
        if (foundDoc != null) {
            resultsWindow(foundDoc);
            System.out.println("Document found: " + foundDoc.toJson());
        } else {
            System.out.println("Document not found");
        }
    }

    public void updateButton() {
        Document updatedDoc = new Document("name", nameField.getText())
                .append("age", Integer.parseInt(ageField.getText()))
                .append("city", cityField.getText());
        MongoDBConnection.updateDocument("W4_Home_Assignment", "W4_Home_Collection", idField.getText(), updatedDoc);
        updateWindow();
    }

    public void deleteButton() {
        MongoDBConnection.deleteDocument("W4_Home_Assignment", "W4_Home_Collection", idField.getText());
        deleteWindow();
    }

    private void resultsWindow(Document foundDocument) {
        Stage resultStage = new Stage();
        VBox resultVbox = new VBox();
        resultVbox.setPadding(new Insets(20));

        Label idLabel = new Label("ID: " + foundDocument.getString("_id"));
        Label nameLabel = new Label("Name: " + foundDocument.getString("name"));
        Label ageLabel = new Label("Age: " + foundDocument.getInteger("age").toString());
        Label cityLabel = new Label("City: " + foundDocument.getString("city"));

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> resultStage.close());

        resultVbox.getChildren().addAll(idLabel,nameLabel,ageLabel,cityLabel, closeButton);

        Scene scene = new Scene(resultVbox, 200,200);
        resultStage.setTitle("Results for Search");
        resultStage.setScene(scene);
        resultStage.showAndWait();
    }

    private void addWindow() {
        Stage addStage = new Stage();
        VBox addVbox = new VBox();
        addVbox.setPadding(new Insets(20));

        Label addLabel = new Label("Document has been added successfully!");
        Button addButton = new Button("OK");
        addButton.setOnAction(e -> addStage.close());

        addVbox.getChildren().addAll(addLabel,addButton);
        Scene addScene = new Scene(addVbox,300,100);
        addStage.setTitle("Message");
        addStage.setScene(addScene);
        addStage.showAndWait();
    }

    private void updateWindow() {
        Stage addStage = new Stage();
        VBox addVbox = new VBox();
        addVbox.setPadding(new Insets(20));

        Label addLabel = new Label("Document has been updated successfully!");
        Button addButton = new Button("OK");
        addButton.setOnAction(e -> addStage.close());

        addVbox.getChildren().addAll(addLabel,addButton);
        Scene addScene = new Scene(addVbox,300,100);
        addStage.setTitle("Message");
        addStage.setScene(addScene);
        addStage.showAndWait();
    }

    private void deleteWindow() {
        Stage addStage = new Stage();
        VBox addVbox = new VBox();
        addVbox.setPadding(new Insets(20));

        Label addLabel = new Label("Document has been deleted successfully!");
        Button addButton = new Button("OK");
        addButton.setOnAction(e -> addStage.close());

        addVbox.getChildren().addAll(addLabel,addButton);
        Scene addScene = new Scene(addVbox,300,100);
        addStage.setTitle("Message");
        addStage.setScene(addScene);
        addStage.showAndWait();

    }
}
