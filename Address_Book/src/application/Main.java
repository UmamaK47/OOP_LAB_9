package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	private TableView<Contact> tableView;
    private TextField nameTextField;
    private TextField phoneTextField;
    private TextField emailTextField;
    private Button addButton;
    private Button editButton;
    private Button removeButton;
    private ObservableList<Contact> contacts;

    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            root.setPadding(new Insets(10));
            GridPane formGrid = new GridPane();
            formGrid.setHgap(10);
            formGrid.setVgap(5);
            Label nameLabel = new Label("Name:");
            nameTextField = new TextField();
            formGrid.addRow(0, nameLabel, nameTextField);
            Label phoneLabel = new Label("Phone:");
            phoneTextField = new TextField();
            formGrid.addRow(1, phoneLabel, phoneTextField);
            Label emailLabel = new Label("Email:");
            emailTextField = new TextField();
            formGrid.addRow(2, emailLabel, emailTextField);
            addButton = new Button("Add Contact");
            addButton.setOnAction(event -> addContact());
            editButton = new Button("Edit Contact");
            editButton.setOnAction(event -> editContact());
            editButton.setDisable(true);
            removeButton = new Button("Delete Contact");
            removeButton.setOnAction(event -> removeContact());
            removeButton.setDisable(true);
            HBox buttonBox = new HBox(10, addButton, editButton, removeButton);
            formGrid.add(buttonBox, 1, 3);
            tableView = new TableView<>();
            TableColumn<Contact, String> nameColumn = new TableColumn<>("Name");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            TableColumn<Contact, String> idColumn = new TableColumn<>("Phone");
            idColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
            TableColumn<Contact, String> emailColumn = new TableColumn<>("Email");
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            tableView.getColumns().addAll(nameColumn, idColumn, emailColumn);
            tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    nameTextField.setText(newSelection.getName());
                    phoneTextField.setText(newSelection.getPhone());
                    emailTextField.setText(newSelection.getEmail());
                    addButton.setDisable(true);
                    editButton.setDisable(false);
                    removeButton.setDisable(false);
                } else {
                    clearFields();
                    addButton.setDisable(false);
                    editButton.setDisable(true);
                    removeButton.setDisable(true);
                }
            });

            root.setTop(formGrid);
            root.setCenter(tableView);
            Scene scene = new Scene(root, 600, 400);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Address Book");
            primaryStage.show();
            contacts = FXCollections.observableArrayList();
            tableView.setItems(contacts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void addContact() {
    	String name = nameTextField.getText();
        String phone = phoneTextField.getText();
        String email = emailTextField.getText();
        if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
            Contact contact = new Contact(name, phone, email);
            contacts.add(contact);
            clearFields();
        } else {
            showAlert("Error", "Please fill in all fields.");
        }
    }
    void removeContact() {
    	Contact selectedContact = tableView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
        	contacts.remove(selectedContact);
        	clearFields();
        	addButton.setDisable(false);
        	editButton.setDisable(true);
        	removeButton.setDisable(true);
            } 
        }
    void editContact() {
    	Contact selectedContact = tableView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
        	
       	
        	String name = nameTextField.getText();
            String phone = phoneTextField.getText();
            String email = emailTextField.getText();
            if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
            	 
                selectedContact.setName(name);
                selectedContact.setPhone(phone);
                selectedContact.setEmail(email);
                contacts.remove(selectedContact);
                contacts.add(selectedContact);
                clearFields();
                addButton.setDisable(false); 
                editButton.setDisable(true); 
                removeButton.setDisable(true); 
            } else {
                showAlert("Error", "Please fill in all fields.");
            }
            
        }
    }
    private void clearFields() {
        nameTextField.clear();
        phoneTextField.clear();
        emailTextField.clear();
        tableView.getSelectionModel().clearSelection();
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
