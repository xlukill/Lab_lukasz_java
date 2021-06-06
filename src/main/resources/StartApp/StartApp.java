package StartApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApp extends Application {


    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException{


        scene = new Scene(loadFXML("/MainWindow"),640,480);
        stage.setScene(scene);
        stage.setTitle("App Fly");
        stage.show();
        stage.setResizable(false); // wylaczenie zmiany rozdzielczości okna

    }

    public static void setRoot(String name) throws IOException{ // ustawienie root'a
        scene.setRoot(loadFXML(name));
    }

    private static Parent loadFXML(String name) throws IOException{ // załadowanie pliku .fxml
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(name + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String [] args){
        launch();
    }

}

