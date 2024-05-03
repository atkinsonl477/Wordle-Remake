package wordle;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    

    @Override
    public void start(Stage primaryStage) throws Exception{

        



        // Parent gameRoot = FXMLLoader.load(getClass().getResource("wordle.fxml"));
        Parent menuRoot = FXMLLoader.load(getClass().getResource("menu.fxml"));

        // Parent game = FXMLLoader.load(getClass().getResource("wordle.fxml"));
        primaryStage.setTitle("Wordle");
        Scene menuScene = new Scene(menuRoot, 600, 900);
        // Scene gameScene = new Scene(gameRoot, 600, 900);
        primaryStage.setScene(menuScene);

        // Button gameButton = new Button();

        primaryStage.show();

        
        
    }


    public static void main(String[] args) {
        launch(args);
    }
}