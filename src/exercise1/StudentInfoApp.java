package exercise1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StudentInfoApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Information Form");

        // The Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        // text fields for personal details
        TextField nameField = new TextField();
        nameField.setPromptText("Full Name");
        TextField addressField = new TextField();
        addressField.setPromptText("Address");
        TextField cityField = new TextField();
        cityField.setPromptText("City");
        TextField provinceField = new TextField();
        provinceField.setPromptText("Province");
        TextField postalCodeField = new TextField();
        postalCodeField.setPromptText("Postal Code");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        // Add fields to grid
        grid.add(new Label("Full Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Address:"), 0, 1);
        grid.add(addressField, 1, 1);
        grid.add(new Label("City:"), 0, 2);
        grid.add(cityField, 1, 2);
        grid.add(new Label("Province:"), 0, 3);
        grid.add(provinceField, 1, 3);
        grid.add(new Label("Postal Code:"), 0, 4);
        grid.add(postalCodeField, 1, 4);
        grid.add(new Label("Phone Number:"), 0, 5);
        grid.add(phoneField, 1, 5);
        grid.add(new Label("Email:"), 0, 6);
        grid.add(emailField, 1, 6);

        // Radio buttons for major selection using FlowPane
        ToggleGroup majorGroup = new ToggleGroup();
        RadioButton csRadio = new RadioButton("Computer Science");
        csRadio.setToggleGroup(majorGroup);
        RadioButton businessRadio = new RadioButton("Business");
        businessRadio.setToggleGroup(majorGroup);
        FlowPane majorFlowPane = new FlowPane(10, 10, csRadio, businessRadio);
        grid.add(new Label("Major:"), 0, 7);
        grid.add(majorFlowPane, 1, 7);

        // ComboBox for course selection
        ComboBox<String> courseCombo = new ComboBox<>();
        ListView<String> courseList = new ListView<>();
        courseList.setPrefHeight(100);

        // Fill courses based on selected major
        csRadio.setOnAction(_ -> {
            courseCombo.getItems().clear();
            courseCombo.getItems().addAll("Java Programming", "Web Development", "Database");
        });
        businessRadio.setOnAction(_ -> {
            courseCombo.getItems().clear();
            courseCombo.getItems().addAll("Accounting", "Business Finance", "Management");
        });

        // Add course to list if not already added
        Button addCourseBtn = new Button("Add Course");
        addCourseBtn.setOnAction(_ -> {
            String selectedCourse = courseCombo.getValue();
            if (selectedCourse != null && !courseList.getItems().contains(selectedCourse)) {
                courseList.getItems().add(selectedCourse);
            }
        });

        // Add ComboBox and ListView to grid
        grid.add(new Label("Select Course:"), 0, 8);
        grid.add(courseCombo, 1, 8);
        grid.add(addCourseBtn, 2, 8);
        grid.add(new Label("Courses List:"), 0, 9);
        grid.add(courseList, 1, 9);

        // Checkboxes for additional activities using FlowPane
        CheckBox codingCheck = new CheckBox("Coding");
        CheckBox sportCheck = new CheckBox("Sport");
        CheckBox musicCheck = new CheckBox("Music");
        FlowPane activitiesFlowPane = new FlowPane(10, 10, codingCheck, sportCheck, musicCheck);
        grid.add(new Label("Activities:"), 0, 10);
        grid.add(activitiesFlowPane, 1, 10);

        // TextArea to display all student information
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPrefHeight(200);

        // Submit button to compile information and display in TextArea
        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(_ -> {
            StringBuilder info = new StringBuilder();
            info.append("Full Name: ").append(nameField.getText()).append("\n")
                    .append("Address: ").append(addressField.getText()).append("\n")
                    .append("City: ").append(cityField.getText()).append("\n")
                    .append("Province: ").append(provinceField.getText()).append("\n")
                    .append("Postal Code: ").append(postalCodeField.getText()).append("\n")
                    .append("Phone Number: ").append(phoneField.getText()).append("\n")
                    .append("Email: ").append(emailField.getText()).append("\n")
                    .append("Major: ").append(((RadioButton) majorGroup.getSelectedToggle()).getText()).append("\n")
                    .append("Courses: ").append(String.join(", ", courseList.getItems())).append("\n")
                    .append("Activities: ");

            if (codingCheck.isSelected()) info.append("[Coding] ");
            if (sportCheck.isSelected()) info.append("[Sport] ");
            if (musicCheck.isSelected()) info.append("[Music] ");

            resultArea.setText(info.toString());
        });

        // Add Submit button and result TextArea to grid
        grid.add(submitBtn, 0, 11);
        grid.add(resultArea, 0, 12, 3, 1);

        // Scene setup
        Scene scene = new Scene(grid, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

