package Controllers;

import DAO.UsersDAO;
import LogicalAspects.DataOperation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class MainWindowController {


    //FXML
    @FXML
    PasswordField passwordField;

    @FXML
    TextField loginField;

    DataOperation dataOperation;

    @FXML
    TextField registerName;

    @FXML
    PasswordField registerPassword;

    @FXML
    Label warning;



    @FXML
    public void initialize(){

    }

    @FXML
    public void logIn() throws Exception{ // log in function

        dataOperation = new DataOperation();

        //System.out.println(loginField.getText() + " " + passwordField.getText());

        if(dataOperation.userFind(loginField.getText().trim(),passwordField.getText().trim())){
            StartApp.StartApp.setRoot("/OperationPanel");
        }
        else{
            warning.setText("Błędne dane logowania.");
            warning.setTextFill(Color.RED);

        }
    }

    public void registerInAction(){ //register function
        UsersDAO usersDAO = new UsersDAO();

        usersDAO.registerUser(registerName.getText().trim(),passwordField.getText().trim());
    }


}
