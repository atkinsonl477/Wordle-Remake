package wordle;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.FileWriter;
import javafx.scene.control.ChoiceBox;




public class menuController {
    @FXML public Label playedRounds;
    @FXML public Label winPercentage;
    @FXML public Label currentStreak;
    @FXML public Label maxStreak;

    @FXML public Label l1;
    @FXML public Label l2;
    @FXML public Label l3;
    @FXML public Label l4;
    @FXML public Label l5;
    @FXML public Label l6;

    @FXML public Button gameButton;
    @FXML public Button wipeStats;
    @FXML public Button newSaveButton;

    @FXML public ChoiceBox<String> saveMenu = new ChoiceBox<String>();



    int pR;
    int wP;
    int cS;
    int mS;


    HashMap<String, Label> map = new HashMap<String, Label>();


    final String NORMAL_STYLING = "-fx-background-color: #3a3a3c;" + 
                                  "-fx-background-insets: 3;" + 
                                  "-fx-background-radius: 3;";

    final String BEST_STYLING = "-fx-background-color:  #618c55;" + 
                           "-fx-background-insets: 3;" + 
                           "-fx-background-radius: 3;" + 
                           "-fx-max-width: 420;" + // nice
                           "-fx-min-width: 420;";

  
    String saveFile;
    ArrayList<String> saves = new ArrayList<String>();

    int[] distributions = new int[6];


    void readFile() {try{
        Scanner saveRead = new Scanner(new File(saveFile));
        int roundsWon = 0;
        int roundsLost = 0;
        
        for(int i = 0; i < 6; i++) {
            distributions[i] = saveRead.nextInt();
            roundsWon += distributions[i];
        }
        roundsLost = saveRead.nextInt();
        
        pR = roundsWon + roundsLost;
        if (pR == 0) {
            wP = 0;
            cS = 0;
            mS = 0;
            saveRead.close(); 
            return;
        }
        wP = roundsWon * 100 / (roundsWon + roundsLost);
        cS = saveRead.nextInt();
        mS = saveRead.nextInt();

        saveRead.close();
        return;
    } catch (FileNotFoundException e){/*System.out.println(e.toString());*/}}

    void updateMenu() {
        
        playedRounds.setText(pR + "");
        winPercentage.setText(wP + "");
        currentStreak.setText(cS + "");
        maxStreak.setText(mS + "");

        

        int maxVal = 0;
        int index = -1;
        // Set variable length depending on wins for each guess count
        for(int i = 0; i < 6; i++) {
    
            if (distributions[i] > maxVal) {maxVal = distributions[i]; index = i;}
        }
        // System.out.println(index);


        final double MIN_WIDTH = 35;
        final double MAX_WIDTH = 420;
        
        // Avoid dividing by 0
        
        
        
        double slope = MAX_WIDTH;

        for (int i = 0; i < 6; i++) {

            


            
            map.get("l" + (i + 1)).setText(distributions[i] + "  ");
            if (index == i) {
                map.get("l" + (i + 1)).setStyle(BEST_STYLING);
            }
            else {
                // System.out.println(distributions[i] + " < " + maxVal);
                double width;
                if (maxVal == 0) width = 0; else
                width = slope * (1.0 * distributions[i]/maxVal);
                if (width < MIN_WIDTH) width = MIN_WIDTH;
                // System.out.println(i + " " + width);
                String newStyle = NORMAL_STYLING + 
                                  "-fx-max-width: " + width + ";" + 
                                  "-fx-min-width: " + width + ";";
                map.get("l" + (i + 1)).setStyle(newStyle);
            }

        }
    }


    @FXML public void initialize() {
        if (true) {
            map.put("l1", l1);
            map.put("l2", l2);
            map.put("l3", l3);
            map.put("l4", l4);
            map.put("l5", l5);
            map.put("l6", l6);


        }

        Scanner saveRead;

        try {
        saveRead = new Scanner(new File("text-files/currSave.txt"));
        } catch (Exception e) {System.out.println(e.toString()); return;}

        saveFile = saveRead.next();
        while (saveRead.hasNext()) {
            saves.add(saveRead.next());
            saveMenu.getItems().addAll(saves.get(saves.size() - 1));
        }
        saveMenu.setValue(saveFile);
        saveFile = "text-files/" + saveFile;
        saveRead.close();


        saveMenu.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            saveFile = "text-files/" + newValue;
            readFile();
            updateMenu();
            updateSaveInformation();
        });
        
        
        // saveMenu.show();

        readFile();
        updateMenu();


    }

    @FXML public void openGame() {
        Stage gameStage = (Stage) gameButton.getScene().getWindow();
        // Parent root;
        try {
        Parent root = FXMLLoader.load(getClass().getResource("wordle.fxml"));
        
        Scene scene = new Scene(root, 600, 900);
        gameStage.setScene(scene);
        gameStage.show();

        } catch (Exception e) {
            System.out.println("Something went wrong");
            System.out.println(e.toString());
            return;
        }

       



    }

    @FXML public void wipeSave() {try {
        
        FileWriter fileWrite = new FileWriter(saveFile, false);;
        fileWrite.write("0 0 0 0 0 0\n0\n0\n0");
        fileWrite.close();
        readFile();
        updateMenu();
        return;


    }catch (Exception e) {System.out.println("Cant find save file");}}

   void updateSaveInformation() {try{
    FileWriter fileWrite = new FileWriter("text-files/currSave.txt", false);
    fileWrite.write(saveFile.substring(11) + "\n");
    for (String s : saves) {
        fileWrite.write(s + " ");
    }
    fileWrite.close();
   }catch (Exception e) {System.out.println("Something went wrong");}}

   @FXML public void newSave() {try{
        int saveCount = saves.size() + 1;
        // FileWriter newSave = new FileWriter("text-files/save" + saveCount + ".txt");
        saveFile = "text-files/save" + saveCount + ".txt";
        saves.add("save" + saveCount + ".txt");
        saveMenu.getItems().addAll(saves.get(saves.size() - 1));
        saveMenu.setValue(saves.get(saves.size() - 1));
        wipeSave();
        updateSaveInformation();
        
        // updateMenu();



   }catch (Exception e){System.out.println(e.toString());}}

}
