# Number-Gussing-Game

This Number Guessing game was made 21/1/2017 using the coding application DrJava.

Features:
- Interactive graphic user interface (GUI)
- Account System
- Leveling System
- Single player and Two player options
- 3 different difficulties

Using Object-oriented programing. we have created accounts to track your progress and experience as you play the game.
When starting you must create an account, or you may use one of the following example accounts given in the Account.txt file.

Ex. Username: olive

Password: olive

1. Make sure you hit *Enter* when logging in for the username and password, otherwise the login button will not be enabled.
If the username or password does not match, a notification will appear displaying an error. Players can make new accounts and profiles.
2. Once logged in, the user can check their profile, start a game as one play or 2 player, or sign out.                                          
  i)If 2 players is selected, the second play must log in as well and the screen will be sent back to log in panel.

3. When picking a game, there are 3 diffculties. The ? button at the top right will tell you the difference in diffculties.                    
    i. Hard Mode (Leader Board Enabled) will make the use play the hardest game available. Leader Board Enabled means the game will record whether or not the user has achieved their own personal high score relative to other player's highest score in the game mode. High scores are determined through minimal amount of guesses. After the game, the leader board page will appear and display the results from players accounts. If an account has not been ranked on the leaderboard, their score by defult is 999 (lowest score possible in theory).

4. Playing the game.                                                                                                                          
  i)The user must enter an integer number within the range of the numbers given. If a number is enter outside the range, the program will prompt the use to try again with a real number. The same follows in a letter or character is entered.                                
  ii) First, a number must be guessed. The second number will then allow the system to compare the distancce between the first guess and second guess relative to the hidden number you are guessing for.                                                                           
  Ex) If the hidden number is 7, and the first guess is 10, second is 8. The program will say you are warmer since your second guess (current) is closer than your first (previous). 
  If hidden umber is 7, guess was 9, then next guess is 2. The program will say you are colder since the distance from 2 to 7, is greater than the distance from 9 to 7.                                                                                                       
  iii) When there are two players playing, both players are guessing their own *UNIQUE* number. In addition, after one player has made their guess, the player's input text log will be disabled until the next player enters their guess for their turn and so on.               
  iv)In order for one of the players to win, they must win on the same amount of tries. This means, if player one (firstp layer to start the game) guesses their number, player two has a chance to tie by guessing the number on their next turn. Else, player one wins.
  If Player two guesses the number, they win automaticly since player one has already guessed.                                             
  v) When the game starts, the user can choose to leave the game (Exit... button), but experience will not be gained and scores will not count for leader board rankings. Additionally, you may request the rules with the ? button.
  
  
Issues of certain useless classes:                                                                                                        
When we made this game, this was part of our final project. The 1500 lines of code and commenting required alot of effort to finish the main game controls on time. Intially, to fulfill the requirements for the project, we made ideas for achievements, perks and items that can be obtain as you play the game. The workload was found to be overbearing and we left it as just a simple number guessing game. Thus, the leveling system is not used to its full potential our project was submitted as such.

The game is still a functioning number guessing game with its own GUI, however the addition perks, items and achievements are left unfinished.
