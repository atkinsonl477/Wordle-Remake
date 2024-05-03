## Welcome!

This is my final project for CSCE-314. We were tasked with recreating wordle, and I tried to replicate it as close as possible while meeting the requirements of the assignment.
The GUI elements were built with JavaFX & SceneBuilder, and some styles were explicitly edited with Css files. The controllers and Main were built on Java.

# NOTE:
I wasnt able to upload all dependencies because github doesnt let me upload files larger than 25 megabytes, but I promise it works (see Youtube Link)

# Features:
    - Two Scenes: Menu and Game Scene, both with dynamic Labels and Buttons.
    - Save Feature: Ability to create and load existing saves. Ease of access with dropdown menu.
    - On-Screen Keyboard: User can both type on screen and on their keyboard.
    - Random word: A new game will always give a new word not yet used on the current save.
    - Statistics: The game keeps track of previous played games and allows for the user to see general statistics about their gameplay


# Random Note
In my opinion, the fact that javaFX doesnt let you assign a node in an array of nodes in the Controller is frustrating. Maybe there is some sort of restriction out of the creators' hand
that forces them to design it this way, but it made the implementation of this assigment look much more messy. I had to individually initialize every label used (59 individual Labels/Buttons
on the Game scene alone). Thankfully accessing them isn't as much of a pain by using a hashmap, but I would have been able to avoid all of these steps if JavaFX would just let me use arrays
the way I want to.

# Youtube Link:
https://youtu.be/3VXWr57HNxA

# Structure of Save files
The save file allows for the menu scene to display the users save data, and prevents previously used words to be used in the game scene. The file is formatted as following:

    XX XX XX XX XX XX      6 numbers indicating guess distribution
    XX                     1 number indicating loss count
    XX                     1 number indicating current streak
    XX                     1 number indicating max streak
    XX XX XX ....          n numbers indicating indexes of words previously used, where n is the amount of games played
