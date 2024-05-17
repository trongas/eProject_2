package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import database.Database;
import entity.GetData;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;

    @FXML
    private Button close;

//    DATABASE TOOls
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

//    NOW LETS CREATE OUR DATABASE : ) 
    private double x = 0;
    private double y = 0;

    @FXML
    public void loginAdmin() throws SQLException {

        String sql = "SELECT * FROM tbl_admin WHERE username = ? and password = ?";

        connect = Database.connectDb();

        try { // IT WORKS GOOD : ) NOW LETS DESIGN THE DASHBOARD FORM : ) 
            Alert alert;

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());

            result = prepare.executeQuery();
//            CHECK IF FIELDS ARE EMPTTY
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                if (result.next()) {
//                    THEN PROCEED TO DASHBOARD FORM

                    GetData.username = username.getText();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();

//                    TO HIDE THE LOGIN FORM
                    loginBtn.getScene().getWindow().hide();
                    //LINK YOUR DASHBOARD 
                    Parent root = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    root.setOnMousePressed((MouseEvent event) -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });

                    root.setOnMouseDragged((MouseEvent event) -> {
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });

                    stage.initStyle(StageStyle.TRANSPARENT);

                    stage.setScene(scene);
                    stage.show();

                } else {
                    // THEN ERROR MESSAGE WILL APPEAR
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void close() {
        System.exit(0);
    }

    //LETS NAME THE COMPONENTS ON LOGIN FORM : )
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
