package wordle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Random;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;



// #565758 = Active border color

public class Controller {



        // Styles for each letter on the grid
        public final String NORMAL = "-fx-border-color: #3a3a3c;" + 
                                     "-fx-border-insets: 4;" + 
                                     "-fx-border-width: 2;" + 
                                     "-fx-border-radius: 3";
        public final String CORRECT = "-fx-background-color: #618c55;" + 
                                      "-fx-background-insets: 4;" + 
                                      "-fx-background-radius: 3";
        public final String ACTIVE = "-fx-border-color: #565758;" + 
                                     "-fx-border-insets: 4;" + 
                                     "-fx-border-width: 2;" + 
                                     "-fx-border-radius: 3";
        public final String INCORRECT = "-fx-background-color: #3a3a3c;" + 
                                        "-fx-background-insets: 4;" + 
                                        "-fx-background-radius: 3";
        public final String WRONGPLACE = "-fx-background-color: #b1a04c;" + 
                                        "-fx-background-insets: 4;" + 
                                        "-fx-background-radius: 3";


        // Styles for each letter on keyboard
        public final String NORMAL_KEYBOARD = "-fx-background-color: #818384;";
        public final String CORRECT_KEYBOARD = "-fx-background-color: #618c55;";
        public final String INCORRECT_KEYBOARD = "-fx-background-color: #3a3a3c;";
        public final String WRONGPLACE_KEYBOARD = "-fx-background-color: #b1a04c;";


        // Hide newGame button when game is happening
        public final String VISIBLE_BUTTON = "-fx-background-color: #121213; -fx-border-color: #3a3a3c; -fx-border-radius: 2; -fx-opacity: 1;";
        public final String INVISIBLE_BUTTON = "-fx-background-color: #121213; -fx-border-color: #3a3a3c; -fx-border-radius: 2; -fx-opacity: 0;";




        
        // Hashmap to simplify updating labels        
        HashMap<String, Label> map = new HashMap<String, Label>();
        HashMap<String, Button> buttMap = new HashMap<String, Button>();

        String[] wordList;
        int finishedRows = 0;
        int finishedLetters = 0;
        char[][] shadArr = new char[6][5];
        String secretWord;

        // I hate that I have to do this
	@FXML public Label l00 = new Label();
        @FXML public Label l01 = new Label();
        @FXML public Label l02 = new Label();
        @FXML public Label l03 = new Label();
        @FXML public Label l04 = new Label();
        @FXML public Label l10 = new Label();
        @FXML public Label l11 = new Label();
        @FXML public Label l12 = new Label();
        @FXML public Label l13 = new Label();
        @FXML public Label l14 = new Label();
        @FXML public Label l20 = new Label();
        @FXML public Label l21 = new Label();
        @FXML public Label l22 = new Label();
        @FXML public Label l23 = new Label();
        @FXML public Label l24 = new Label();
        @FXML public Label l30 = new Label();
        @FXML public Label l31 = new Label();
        @FXML public Label l32 = new Label();
        @FXML public Label l33 = new Label();
        @FXML public Label l34 = new Label();
        @FXML public Label l40 = new Label();
        @FXML public Label l41 = new Label();
        @FXML public Label l42 = new Label();
        @FXML public Label l43 = new Label();
        @FXML public Label l44 = new Label();
        @FXML public Label l50 = new Label();
        @FXML public Label l51 = new Label();
        @FXML public Label l52 = new Label();
        @FXML public Label l53 = new Label();
        @FXML public Label l54 = new Label();
        @FXML public Button Lq = new Button();
        @FXML public Button Lw = new Button();
        @FXML public Button Le = new Button();
        @FXML public Button Lr = new Button();
        @FXML public Button Lt = new Button();
        @FXML public Button Ly = new Button();
        @FXML public Button Lu = new Button();
        @FXML public Button Li = new Button();
        @FXML public Button Lo = new Button();
        @FXML public Button Lp = new Button();
        @FXML public Button La = new Button();
        @FXML public Button Ls = new Button();
        @FXML public Button Ld = new Button();
        @FXML public Button Lf = new Button();
        @FXML public Button Lg = new Button();
        @FXML public Button Lh = new Button();
        @FXML public Button Lj = new Button();
        @FXML public Button Lk = new Button();
        @FXML public Button Ll = new Button();
        @FXML public Button Lz = new Button();
        @FXML public Button Lx = new Button();
        @FXML public Button Lc = new Button();
        @FXML public Button Lv = new Button();
        @FXML public Button Lb = new Button();
        @FXML public Button Ln = new Button();
        @FXML public Button Lm = new Button();



        /*
        @FXML public Label playedRounds = new Label();
        @FXML public Label winPercentage = new Label();
        @FXML public Label currentStreak = new Label();
        @FXML public Label maxStreak = new Label();
    
        @FXML public Label l1 = new Label();
        @FXML public Label l2 = new Label();
        @FXML public Label l3 = new Label();
        @FXML public Label l4 = new Label();
        @FXML public Label l5 = new Label();
        @FXML public Label l6 = new Label();
        */


        @FXML public Button resetButton = new Button();
        @FXML public Button menuButton = new Button();
        @FXML public Button newGameButton = new Button();
        @FXML public Label warningLabel = new Label();

       



        // Save file information
        public String saveFile;
        public int[] distributions = new int[6];
        int pR;
        int wP;
        int cS;
        int mS;

        ArrayList<Integer> usedWordList = new ArrayList<Integer>();



        
        

	@FXML public void initialize() {try{
		

                //Set values of the hashmap, aint readin allat
                if (true) {
                        map.put("l00", l00);
                        map.put("l01", l01);
                        map.put("l02", l02);
                        map.put("l03", l03);
                        map.put("l04", l04);
                        map.put("l10", l10);
                        map.put("l11", l11);
                        map.put("l12", l12);
                        map.put("l13", l13);
                        map.put("l14", l14);
                        map.put("l20", l20);
                        map.put("l21", l21);
                        map.put("l22", l22);
                        map.put("l23", l23);
                        map.put("l24", l24);
                        map.put("l30", l30);
                        map.put("l31", l31);
                        map.put("l32", l32);
                        map.put("l33", l33);
                        map.put("l34", l34);
                        map.put("l40", l40);
                        map.put("l41", l41);
                        map.put("l42", l42);
                        map.put("l43", l43);
                        map.put("l44", l44);
                        map.put("l50", l50);
                        map.put("l51", l51);
                        map.put("l52", l52);
                        map.put("l53", l53);
                        map.put("l54", l54);
                        buttMap.put("q", Lq);
                        buttMap.put("w", Lw);
                        buttMap.put("e", Le);
                        buttMap.put("r", Lr);
                        buttMap.put("t", Lt);
                        buttMap.put("y", Ly);
                        buttMap.put("u", Lu);
                        buttMap.put("i", Li);
                        buttMap.put("o", Lo);
                        buttMap.put("p", Lp);
                        buttMap.put("a", La);
                        buttMap.put("s", Ls);
                        buttMap.put("d", Ld);
                        buttMap.put("f", Lf);
                        buttMap.put("g", Lg);
                        buttMap.put("h", Lh);
                        buttMap.put("j", Lj);
                        buttMap.put("k", Lk);
                        buttMap.put("l", Ll);
                        buttMap.put("z", Lz);
                        buttMap.put("x", Lx);
                        buttMap.put("c", Lc);
                        buttMap.put("v", Lv);
                        buttMap.put("b", Lb);
                        buttMap.put("n", Ln);
                        buttMap.put("m", Lm);

                        /* 
                        map.put("l1", l1);
                        map.put("l2", l2);
                        map.put("l3", l3);
                        map.put("l4", l4);
                        map.put("l5", l5);
                        map.put("l6", l6);
                        */

                }

  
		Scanner readText = new Scanner(new File("text-files/words.txt"));
		wordList = new String[2310];
		int index = 0;
		while (readText.hasNext()) {
			wordList[index] = readText.next();
			// System.out.println(wordList[index] + " " + index);
			index++;
		}
                readText.close();
                Arrays.sort(wordList);
                


               
                
                newGame();
                
		// l44.setStyle(CORRECT);
                // l23.setStyle(WRONGPLACE);

                // l12.setStyle(ACTIVE);
                

                // Set save file location
                Scanner saveRead;

                try {
                saveRead = new Scanner(new File("text-files/currSave.txt"));
                } catch (Exception e) {System.out.println("Something went wrong"); return;}

                saveFile = "text-files/" + saveRead.next();
                saveRead.close();


                
                readFile();
                // updateMenu();


                
                
		
	} catch (FileNotFoundException e){System.out.println("Something went wrong");}}

        @FXML public void newGame() {
  
                Random rand = new Random();
                int index = rand.nextInt(2310);
                


                // Potentially dangerous (certainly not)
                while (true) {
                        boolean wordUsed = false;
                        for(int i : usedWordList) {
                                if (index == i) {
                                        wordUsed = true;
                                        break;
                                }
                                
                        }
                        if (!wordUsed) break;

                        index = rand.nextInt(2310);
                }

                secretWord = wordList[index];
                usedWordList.add(index);  
                
                System.out.println("The secret word this round is: " + secretWord);
                clearBoard();
                hideButton(newGameButton);

        }

        @FXML public void onScreenKeyboardPress(ActionEvent e) {
                Object src = e.getSource();
                

                for (char c = 'a'; c < 'z'; c++) {
                        // System.out.println(c);
                        if (src.equals(buttMap.get(c + ""))) {
                                KeyEvent keyPress = new KeyEvent(null, (c + ""), (c + ""), KeyCode.F, false, false, false, false);
                                onKeyPressed(keyPress);

                        }
                }

                // System.out.println(src.toString());

        }

	@FXML public void onKeyPressed(KeyEvent key) {
                if (finishedRows == 6) return;
                warningLabel.setStyle(INVISIBLE_BUTTON);
                if (key.getCode().equals(KeyCode.SPACE)) {System.out.println("This is not why its ending prematurely"); return;}

                // Check if enter was pressed, attempt word if all letters are filled AND word is in the list
                if (key.getCode().equals(KeyCode.ENTER)) {
                        // System.out.println("Enter was pressed");
                        if (finishedLetters == 5 && finishedRows != 6) {
                                // System.out.println("Attempting word!");
                                String attemptWord = "" + shadArr[finishedRows][0] + shadArr[finishedRows][1] + shadArr[finishedRows][2] + shadArr[finishedRows][3] + shadArr[finishedRows][4];
                                
                                // Binary search would work better here but I dont feel like making that so deal with it
                                for(int i = 0; i < 2310; i++) {
                                        
                                        int compare = attemptWord.compareTo(wordList[i]);
                                        // word is in list
                                        if (compare == 0) {guessWord(key); return;}

                                        // Word is passed what is possible
                                        if (compare < 0) break;
                                }


                                

                        }

                        warningLabel.setStyle(VISIBLE_BUTTON);
                        return;

                }
                if (key.getCode().equals(KeyCode.BACK_SPACE)) {
                        
                        if (finishedLetters > 0) {
                                String currLabel = "l" + finishedRows + (finishedLetters - 1);
                                map.get(currLabel).setText("");
                                map.get(currLabel).setStyle(NORMAL);
                                finishedLetters--;
                        }
                        return;
                        
                }

		char ch1 = key.getText().toUpperCase().charAt(0);
                // System.out.println(ch1);
                if (Character.isLetter(ch1)) {
                        //Check to see if last letter is done
                        if(finishedLetters == 5) return;
                        
                        String currLabel = "l" + finishedRows + finishedLetters;
                        

                        map.get(currLabel).setText("" + ch1);
                        map.get(currLabel).setStyle(ACTIVE);
                        
                        shadArr[finishedRows][finishedLetters] = key.getText().toLowerCase().charAt(0);
                        finishedLetters++;
                } 
                
                // System.out.println("Key pressed");
		return;
	}

        void guessWord(KeyEvent key) {
                // Array to keep track of index of letters that have already been used
                ArrayList<Integer> usedIndexes = new ArrayList<Integer>();
                boolean[] usedLetters = new boolean[5];
                boolean correctGuess = true;
                
                // First pass checks for only correct letters
                for(int i = 0; i < 5; i++) {
                        // System.out.println(shadArr[finishedRows][i] + " " + secretWord.charAt(i));
                        
                        // see if correct letter is present
                        if (shadArr[finishedRows][i] == secretWord.charAt(i))  {
                                // System.out.println("CORRECT LETTER IN PLACE");
                                map.get("l" + finishedRows + i).setStyle(CORRECT);
                                usedLetters[i] = true;
                                usedIndexes.add(i);
                        }
                        else {correctGuess = false;}
                }

                // Second pass allows for misplaced letters
                for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                                if (i == j || usedLetters[i] == true) continue;
                                if(shadArr[finishedRows][i] == secretWord.charAt(j)) {
                                        boolean letterUsed = false;
                                        for(int k = 0; k < usedIndexes.size(); k++) {
                                                if (usedIndexes.get(k) == j) {
                                                        letterUsed = true;
                                                        
                                                }
                                        }
                                        // Misplaced Letter
                                        if (!letterUsed) {
                                                usedIndexes.add(j);
                                                map.get("l" + finishedRows + i).setStyle(WRONGPLACE);
                                                usedLetters[i] = true;
                                                // System.out.println("Wrong place!");
                                        } 
 
                                } 
                        }
                }

                // Final third pass for incorrect letters
                for(int i = 0; i < 5; i++) {
                        if (!usedLetters[i]) map.get("l" + finishedRows + i).setStyle(INCORRECT);
                }


                
                updateKeyboard();

                
                
                if (correctGuess) {
                        System.out.println("Correct guess!");
                        

                        
                        pR++;
                        cS++;
                        if (cS > mS) mS = cS;
                        distributions[finishedRows]++;

                        

                        try{updateSave();} catch(Exception e) {System.out.println("Something went wrong");}
                        showButton(newGameButton);
                        resetButton.setDisable(true);
                        // try {openMenu();} catch(Exception e) {}

                }

                finishedRows++;
                finishedLetters = 0;

                if (finishedRows == 6 && !correctGuess) {
                        System.out.println("You lost");

                        
                        cS = 0;
                        pR++;
                        try{updateSave();} catch(Exception e) {System.out.println("Something went wrong");}
                        try {openMenu();} catch(Exception e) {}
                }

                
                      

        }

        public void clearBoard() {
                for(int i = 0; i < 5; i++) {
                        for (int j = 0; j < 6; j++) {
                                String currLabel = "l" + j + i;
                                map.get(currLabel).setText("");
                                map.get(currLabel).setStyle(NORMAL);
                                shadArr[j][i] = ' ';
                        }
                        
                        // word[i] = ' ';
                }

                for (char c = 'a'; c <= 'z'; c++) {
                        buttMap.get("" + c).setStyle(NORMAL_KEYBOARD);
                }


                finishedLetters = 0;
                finishedRows = 0;
                resetButton.setDisable(false);
        }

        void updateKeyboard() {
                for (int i = 0; i < 5; i++) {
                        String style = map.get("l" + finishedRows + i).getStyle();
                        String currKeyStyle = buttMap.get(shadArr[finishedRows][i] + "").getStyle();
                        if (style == CORRECT) {
                                buttMap.get(shadArr[finishedRows][i] + "").setStyle(CORRECT_KEYBOARD);
                        }
                        else if (style == WRONGPLACE && currKeyStyle != CORRECT_KEYBOARD) {
                                buttMap.get(shadArr[finishedRows][i] + "").setStyle(WRONGPLACE_KEYBOARD);
                        }
                        else if (currKeyStyle != CORRECT_KEYBOARD && currKeyStyle != WRONGPLACE_KEYBOARD) {
                                buttMap.get(shadArr[finishedRows][i] + "").setStyle(INCORRECT_KEYBOARD);
                        }
                }
        }

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
                if (pR == 0) {saveRead.close(); mS = 0; return;}
                wP = roundsWon * 100 / (roundsWon + roundsLost);
                cS = saveRead.nextInt();
                mS = saveRead.nextInt();
        


                // Check for used word indexes
                while (saveRead.hasNextInt()) {
                        usedWordList.add(saveRead.nextInt());
                }

                saveRead.close();
                return;
            } catch (FileNotFoundException e){System.out.println("Something went wrong");}}
        
        @FXML public void openMenu() throws Exception{
                
                // try{Thread.sleep(2000);} catch (Exception e) {};
                Stage stage = (Stage) menuButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));

                Scene scene = new Scene(root, 600, 900);

                

                stage.setScene(scene);
                stage.show();
                

                

        }

        void updateSave() throws Exception{
                FileWriter myWriter = new FileWriter(saveFile, false);
                int roundsWon = 0;
                for (int i = 0; i < 6; i++) {
                        myWriter.write(distributions[i] + " ");
                        roundsWon += distributions[i];
                }
                int roundsLost = pR - roundsWon;
                myWriter.write("\n" + roundsLost + " ");
                myWriter.write("\n" + cS);
                myWriter.write("\n" + mS + "\n");

                for(int i : usedWordList) {
                        myWriter.write(i + " ");
                }

                


                // try{TimeUnit.SECONDS.sleep(2);} catch (Exception e) {}
                myWriter.close();



        }

        void hideButton(Button butt) {
                butt.setStyle(INVISIBLE_BUTTON);
                butt.setDisable(true);
        }
        void showButton(Button butt) {
                butt.setStyle(VISIBLE_BUTTON);
                butt.setDisable(false);
        }


        /* 
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
                if (maxVal == 0) return;
                
                
                double slope = MAX_WIDTH;
        
                for (int i = 0; i < 6; i++) {
        
                    
        
        
                    
                    map.get("l" + (i + 1)).setText(distributions[i] + "  ");
                    if (index == i) {
                        map.get("l" + (i + 1)).setStyle(BEST_STYLING);
                    }
                    else {
                        // System.out.println(distributions[i] + " < " + maxVal);
                        double width = slope * (1.0 * distributions[i]/maxVal);
                        if (width < MIN_WIDTH) width = MIN_WIDTH;
                        // System.out.println(i + " " + width);
                        String newStyle = NORMAL_STYLING + 
                                          "-fx-max-width: " + width + ";" + 
                                          "-fx-min-width: " + width + ";";
                        map.get("l" + (i + 1)).setStyle(newStyle);
                    }
        
                }
            }
        */
}
