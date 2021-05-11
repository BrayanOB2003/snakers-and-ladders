# snakers-and-ladders
This project is about the famous Snakes and Ladders game, which must create a matrix of n rows and m squares, with s snakes and e ladders, and the players will be represented with the following symbols * ! O X % $ # + & . For this project, the output will be displayed by console and the program will only use recursion.

First, the entry by the system will be, for example, 6 5 2 2 $&! which means that a 6x5 matrix must be created with 2 snakes, 2 ladders and 3 players represented by the symbols: $ & ! , the program receives the dimensions of the matrix and creates the row nodes of the first column and then in each row creates the rest of the columns specified by the user. Then the program will look for the first box and will start to list the boxes starting from number 1 making a recursive call: right and up, left and up, right and up and so on until the boxes run out. Then we will create the snakes and ladders, the snakes will be represented by a char starting from A, it will create them using a recursive call from right and down, with the help of Math.Random, for the ladders the same recursive call will be used but these will be represented with numbers starting from 1.

For the movement of the players, a linked list of nodes was created in each square of the board, and the function of how many squares to move does the program after the user types an enter without any messages, when doing this the program calculates a Math .Random, if the player falls into a snake, the program will look down the same symbol as the snake and take the player there, in the case of stairs, it will look up the number and take the player there. The first player to reach or pass through the last box will be the winner, and his score will be calculated as follows: number of times he rolled the dice * (number of rows * number of columns). At the end of the game, the winner will be asked for a nickname, and then save in a binary tree all the information on the board together with the winner's nickname, the binary tree will be ordered inversely, that is, the largest on the left and the smallest on the right .

On the other hand, if the player writes as input the word: "num" and an enter, the program will show how the board was when starting, and will wait for another enter to show the current positions of the players.

If the player types as input the word: "simul" and an enter, the game will start to play automatically with a lapse of 2 seconds per move.

If the player types as input the word: "menu" and an enter, the program cuts without ending the game and shows the menu options again.

the menu will have three options as follows:
1. New game
2. List of winning nicknames
3. Exit

This project is made in Java language, using recursion, binary trees and linked lists
This program only works with input parameters where the number of rows is an even number  > =  4.
