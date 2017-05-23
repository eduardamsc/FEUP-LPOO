# FEUP-LPOO-Project2

*********************
##Architecture Design

![UML Diagram](https://github.com/eduardamsc/FEUP-LPOO-Project2/blob/Final-Project/UML.png)

>**Menu** Class entitled to executing the game, exiting the app or offering help.

>**Level** Class which generates each new level.

>**Board** Class that creates a board to fill with gems.

>**Gem** Class with all the operations related to the existence of gems, such as moving and checking if a movement is possible.

>**Test** Class which tests the game's logic.

*********************
##Packages Diagram

![Packages Diagram](https://github.com/eduardamsc/FEUP-LPOO-Project2/blob/Final-Project/PackagesDiagram.png)

*********************
##GUI

![mockup main menu](https://github.com/eduardamsc/FEUP-LPOO-Project2/blob/Final-Project/MockUps.pdf)

>**Start Menu**

>Play Button – Starts game;

>Help Button – Explains what the game is about and how it is played;

>Exit Button – Exits the app if the user is sure that is what he wants.

*********************	
##Test Design

>Start game test:
Tests whether the game starts with 0 score and 0 time passed.

>Gem movement test:
Tests whether the gem the user is trying to move can move to that position.

>Consequent movement test:
Tests whether, when a gem moves to a certain position, if the gem in that position switches correctly with that one.

>Match test:
Tests whether the movement done causes a match among gems all over the board.

>Playable test:
Tests whether there are still available movements left to play.
