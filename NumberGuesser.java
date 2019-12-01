/*
 * Oliver Mo & Michael Karimizadeh Productions
 * 21/1/2017
 * Number Guessing Game
 * This game incorporates both GUI and the lessons learned this year to create an interactive video game
 * You are allowed to both create account and modify them based on your actions within the game
 * There is a 1 player option as well as a head to head 2 player option
 * There is a leaderboard for hard mode in both single and multiplayer that arranges account based on their lowest score
 * In order for the program to register your input when entering account info you must press enter at the end of typing
 * 
 * The purpose of this game is to have the player guess a number that the computer creates based on the difficulty selected
 * In two player head to head the goal is to guess the mystery number is less tries than your opponent
 * Goodluck!
 * 
 * Test Username: miggil 
 * Test Password: miggil
 * ^Feel free to use this premade account for testing
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
public class NumberGuesser extends JFrame
{
  static int input;//Creates variable to hold user guess
  static int input1;//Creates variable to hold another user guess
  static int input2;//Creates variable to hold yet another user guess
  static String finish = "";//Creates variable to hold user choice at the end
  static int e = 9;//Creates variable to hold difficulty choice
  static int f = 0;//Creates variable to hold number of player choice
  int d = 9;//Changes random number based on difficulty selected
  
  private JFrame frame;
  private JPanel panel = new JPanel();
  private JLabel space = new JLabel(" ");
  private JButton signInButton, signUpButton, signOutButton, back, account, single, two, easy, normal, hard, info;
  private JLabel title, playerOneName, playerTwoName;
  protected JTextField textField, textField1, userText, passText, fNameText, lNameText, ageText;
  protected JTextArea textArea, textArea1;
  private final static String newLine = "\n";
  private int enablerSign, userEnable, passEnable, fNameEnable, lNameEnable, ageEnable, win1, win2, lastdiff1, lastdiff2, score1, score2, x, y, playerOneSignedIn, playerTwoSignedIn;
  private String user, pass, fName, lName, age;
  private double rand1, rand2;
  private boolean test, test2;
  private int numOfAccount=-1;//Holds total number of accounts
  ArrayList <Account> accounts = new ArrayList<Account>();//Creates array list of accounts
  boolean tester;//Holds testing boolean
  private String firstPlayerUser;//Stores username of first player
  private String secondPlayerUser;//Stores username of second player
  public NumberGuesser()
  {
    frame = new JFrame("Number Guesser");//Creates new frame
    panel.setLayout(new GridBagLayout());//Sets Layout to GridBagLayout
    setContentPane(panel);
    setSize(500, 500);//Sets the size of the frame
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Close JFrame when pressing x button
    signIn();//Add signIn() Components
    setVisible(true);//Makes content in frame visible
    setResizable(false);//Make the window not resizable
  }
  public void signIn()//Makes a start screen for palyers to sign in
  {
    try{
      FileInputStream fs = new FileInputStream("Account.txt");//Creates stream of input from txt file
      FileInputStream fs1 = new FileInputStream("Account.txt");//Creates second stream of input from txt file
      DataInputStream in = new DataInputStream(fs);//Creates data set to be used
      DataInputStream in1 = new DataInputStream(fs1);//Creates second data set to be used
      BufferedReader br = new BufferedReader(new InputStreamReader(in));//Reads set of data to be processed
      BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));//Reads second set of data to be processed
      String sLine,sLine1;//Creates temporary string holders
      numOfAccount=-1;
      while ((sLine = br.readLine()) != null)   {//Counts number of lines in file
        numOfAccount+=1;//Counts number of accounts
      }
      br1.readLine();//Skips first line of file
      int counter=0;
      while ((sLine1 = br1.readLine()) != null)   {//Creates objects for each line
        String[] part = sLine1.replaceAll("^[,\\s]+", "").split("[,\\s]+");//Regex for splitting string into appropriate array of parts
        accounts.add(new Account(part[0],part[1],Integer.parseInt(part[2]),Integer.parseInt(part[3]),part[4],part[5],Integer.parseInt(part[6]),Integer.parseInt(part[7])));//Creates object from object array
      }
    }
    catch(Exception eio){
    }
    playerOneSignedIn = 0;//Set varible to zero. Used later to identify if player one signs in, or is signed out to so the program brings user to different pages.
    enablerSign = 0;//Set to zero, When userEnable + passEnable equals 2, this Enables the log in button.
    userEnable = 0;//Set to zero, Identifies whether the user input a valid username
    passEnable = 0;//Set to zero, Identifies whether the user input a valid password
    GridBagConstraints c = new GridBagConstraints();//For the panel layout, GridBagLayout. This creates a grid to add components such as buttons, labels, textfields, etc.
    title = new JLabel("Sign In");
    title.setFont(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 50));//Set fond of JLabel title
    c.gridx = 0;
    c.gridy = 0;
    panel.add(title, c);//Add title to the panel at cordinates c
    userText = new JTextField("Username", 30);
    c.weightx = 0;
    c.gridx = 0;
    c.gridy = 1;
    panel.add(userText, c);//Add userText to the panel at cordinates c
    userText.addActionListener(new ActionListener()//Username Textfield action. Disables Log In button if a invalid Username is entered
                                 {    
      public void actionPerformed(ActionEvent evt) {
        user = userText.getText();
        firstPlayerUser=user;
        if(userEnable == 0 && !(user.equals("Username")))//If the Username textfield  does not contain "Username", increase userEnable
        {
          userEnable = 1;;
        }
        if(user.equals("Username"))//If the person re-inputs "Username", resets userEnabled and disables log in button
        {
          userEnable = 0;
          signInButton.setEnabled(false);
        }
        enablerSign = userEnable + passEnable;//Adds userEnabled and pass enabled
        if(enablerSign ==2)//If enablerSign is 2, the Log in button is enabled
        {
          signInButton.setEnabled(true);
        }
      }
    });  
    passText = new JTextField("Password", 30);
    c.weightx = 0;
    c.gridx = 0;
    c.gridy = 2;
    panel.add(passText, c);//Add passText to the panel at cordinates c
    passText.addActionListener(new ActionListener()//Password Textfield action. Disables Log In button if a invalid Password is entered
                                 {    
      public void actionPerformed(ActionEvent evt) {
        pass = passText.getText();//Sets pass equal to the text Field when pressed enter
        if(passEnable == 0 && !(pass.equals("Password")))//If the Password textfield  does not contain "Password", increase passEnable
        {
          passEnable = 1;
        }
        if(pass.equals("Password"))//If the person re-inputs "Password", resets passEnabled and disables log in button
        {
          passEnable = 0;
          signInButton.setEnabled(false);
        }
        enablerSign = userEnable + passEnable;//Adds userEnabled and pass enabled
        if(enablerSign ==2)//If enablerSign is 2, the Log in button is enabled
        {
          signInButton.setEnabled(true);
        }   
      }
    }); 
    tester = false;//Checks if an account matches Account.txt
    signInButton = new JButton("Log In");
    c.weightx = 0.5;
    c.ipady = 10;
    c.gridx = 0;
    c.gridy = 4;
    panel.add(signInButton, c);//Add signInButton to the panel at cordinates c
    signInButton.setEnabled(false);//Set signInButton to disabled
    signInButton.addActionListener(new ActionListener()//Sign In button action. Brings user to playerSelection() page
                                     {    
      public void actionPerformed(ActionEvent e) {
        for(int i=0;i<numOfAccount;i++){//Searches for account object
          if(accounts.get(i).getUser().equals(user)&&accounts.get(i).getPass().equals(pass))
            tester=true;//Turns true if correct password and username are found
        }
        if(tester==true){
          panel.removeAll();//Removes current panel and all objects on panel
          setContentPane(panel);//Reseting the panel
          panel.setLayout(null);//Sets layout to null, requires custom placements of objects (buttons, labels, textfields .etc)
          setSize(500, 500);//Sets the size of the new window
          playerOneSignedIn = 1;//Set to 1, checks that player one signed in. Used to confirm what the back button does in signUp()
          playerSelection();//Add sign up components
          setVisible(true);//Sets the panel visible
        }
        else{
          JOptionPane.showMessageDialog(null, "Incorrect login/password, please try again.", "Game", JOptionPane.INFORMATION_MESSAGE);//Creates Pop up explaining the buttons
        }
      }
    }); 
    signUpButton = new JButton("New to the game?");
    c.gridx = 0;
    c.gridy = 5;
    panel.add(signUpButton, c);//Add signUpButton to the panel at cordinates c
    signUpButton.addActionListener(new ActionListener()//Sign Up button action. Brings user to signUp(), page to make an account
                                     {    
      public void actionPerformed(ActionEvent e) {
        panel.removeAll();//Removes current panel and all objects on panel
        setContentPane(panel);//Reseting the panel
        panel.setLayout(new GridBagLayout());//Make panel layout GridBagLayout
        setSize(500, 500);//Sets the size of the new window
        signUp();//Add sign up components
        setVisible(true);//Sets the panel visible
      }
    }); 
  }
  public void signUp()//Makes a screen for both player to register into the game
  {
    enablerSign = 0;//Set all enable varibles to zero, decides wheither Regester button is enabled
    userEnable = 0;
    passEnable = 0;
    fNameEnable = 0;
    lNameEnable = 0;
    ageEnable = 0;
    GridBagConstraints c = new GridBagConstraints();//For GridBagLayout
    title = new JLabel("Sign Up");
    title.setFont(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 50));//set Font
    c.gridx = 0;
    c.gridy = 0;
    panel.add(title, c);
    userText = new JTextField("Username", 30);
    c.gridx = 0;
    c.gridy = 1;
    panel.add(userText, c);
    userText.addActionListener(new ActionListener()//Username Textfield action. Disables sign up button if a invalid Username is entered
                                 {    
      public void actionPerformed(ActionEvent evt) {
        user = userText.getText();//Sets user equal to the text Field when pressed enter
        if(userEnable == 0 && !(user.equals("Username")))//If the Username textfield  does not contain "Username", increase userEnable
        {
          userEnable = 1;
        }
        if(user.equals("Username"))//If the person re-inputs "Username", resets userEnabled and disables Sign up button
        {
          userEnable = 0;
          signUpButton.setEnabled(false);//Disables Sign up button
        }
        enablerSign = userEnable + passEnable + fNameEnable + lNameEnable + ageEnable;//Adds all int enabled varibles 
        if(enablerSign == 5)//If enablerSign is 5, the sign up button is enabled
        {
          signUpButton.setEnabled(true);//enables Sign up button
        }
      }
    }); 
    passText = new JTextField("Password", 30);   
    c.gridx = 0;
    c.gridy = 2;
    panel.add(passText, c);
    passText.addActionListener(new ActionListener()//Password Textfield action. Disables sign up button if a invalid Password is entered
                                 {    
      public void actionPerformed(ActionEvent evt) {
        pass = passText.getText();//Sets pass equal to the text Field when pressed enter
        if(passEnable == 0 && !(pass.equals("Password")))//If the Password textfield  does not contain "Password", increase passEnable
        {
          passEnable = 1;
        }
        if(pass.equals("Password"))//If the person re-inputs "Password", resets passEnabled and disables Sign up button
        {
          passEnable = 0;
          signUpButton.setEnabled(false);//Disables Sign up button
        }
        enablerSign = userEnable + passEnable + fNameEnable + lNameEnable + ageEnable;//Adds all int enabled varibles 
        if(enablerSign == 5)//If enablerSign is 5, the sign up button is enabled
        {
          signUpButton.setEnabled(true);//enables Sign up button
        }
      }
    }); 
    fNameText = new JTextField("First Name", 30);
    c.gridx = 0;
    c.gridy = 3;
    panel.add(fNameText, c);
    fNameText.addActionListener(new ActionListener()//First Name Textfield action. Disables sign up button if a invalid First Name is entered
                                  {    
      public void actionPerformed(ActionEvent evt) {
        fName = fNameText.getText();//Sets fName equal to the text Field when pressed enter
        if(fNameEnable == 0 && !(fName.equals("First Name")))//If the First Name textfield  does not contain "First Name", increase fNameEnable
        {
          fNameEnable = 1;
        }
        if(fName.equals("First Name"))//If the person re-inputs "First Name", resets fNameEnabled and disables Sign up button
        {
          fNameEnable = 0;
          signUpButton.setEnabled(false);
        }
        enablerSign = userEnable + passEnable + fNameEnable + lNameEnable + ageEnable;//Adds all int enabled varibles 
        if(enablerSign == 5)//If enablerSign is 5, the Sign up button is enabled
        {
          signUpButton.setEnabled(true);
        }
      }
    }); 
    lNameText = new JTextField("Last Name", 30);
    c.gridx = 0;
    c.gridy = 4;
    panel.add(lNameText, c);
    lNameText.addActionListener(new ActionListener()//Last Name Textfield action. Disables sign up button if a invalid Last Name is entered
                                  {    
      public void actionPerformed(ActionEvent evt) {
        lName = lNameText.getText();//Sets lName equal to the text Field when pressed enter
        if(lNameEnable == 0 && !(lName.equals("Last Name")))//If the Last Name textfield  does not contain "Last Name", increase lNameEnable
        {
          lNameEnable = 1;
        }
        if(lName.equals("Last Name"))//If the person re-inputs "Last Name", resets lNameEnabled and disables Sign up button
        {
          lNameEnable = 0;
          signUpButton.setEnabled(false);
        }
        enablerSign = userEnable + passEnable + fNameEnable + lNameEnable + ageEnable;//Adds all int enabled varibles 
        if(enablerSign == 5)//If enablerSign is 5, the Sign up button is enabled
        {
          signUpButton.setEnabled(true);
        }
      }
    }); 
    ageText = new JTextField("Age", 30);
    c.gridx = 0;
    c.gridy = 5;
    panel.add(ageText, c);
    ageText.addActionListener(new ActionListener()//Age Textfield action. Disables sign up button if a invalid Age is entered
                                {    
      public void actionPerformed(ActionEvent evt) {
        age = ageText.getText();//Sets age equal to the text Field when pressed enter
        if(ageEnable == 0 && !(age.equals("Age")))//If the Age textfield  does not contain "Age", increase ageEnable
        {
          ageEnable = 1;
        }
        if(age.equals("Age"))//If the person re-inputs "Age", resets ageEnabled and disables Sign up button
        {
          ageEnable = 0;
          signUpButton.setEnabled(false);
        }
        enablerSign = userEnable + passEnable + fNameEnable + lNameEnable + ageEnable;//Adds all int enabled varibles 
        if(enablerSign == 5)//If enablerSign is 5, the Sign up button is enabled
        {
          signUpButton.setEnabled(true);
        }
      }
    }); 
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 6;
    panel.add(space, c);//Adds seperation between regiester button and age textfield
    signUpButton = new JButton("Register");
    c.weightx = 0;
    c.gridx = 0;
    c.gridy = 7;
    panel.add(signUpButton, c); 
    signUpButton.setEnabled(false);
    signUpButton.addActionListener(new ActionListener()//Sign Up button action. Brings user to a new page
                                     {
      public void actionPerformed(ActionEvent e) {
        accounts.add(new Account(fName,lName,Integer.parseInt(age),999,user,pass,0,0));//Adds new account to object array
        numOfAccount+=1;
        try{
          PrintWriter writer = new PrintWriter("Account.txt");//Rewrites file with new information
          writer.println("FirstName LastName Age Score Username Password Level Exp");
          for(int i=0;i<numOfAccount;i++){
            writer.println(accounts.get(i).toString());//Displays all attributes
          }
          writer.close();
        } catch (IOException eio) {
        }
        panel.removeAll();//Removes current panel and all objects on panel
        setContentPane(panel);//Reseting the panel
        setSize(500, 500);//Sets the size of the new window
        if(playerOneSignedIn ==1 && playerTwoSignedIn == 1)//If player One is already signed in, and the two player button in playerSelection() is pressed, brings user to signInPlayerTwo()
        {
          signInPlayerTwo();//Add signInPlayerTwo components
        }
        else
        {
          signIn();//Add sign in components
        }
        setVisible(true);//Sets the panel visible
      }
    }); 
    back = new JButton("Back");
    c.anchor = GridBagConstraints.PAGE_END;
    c.gridx = 0;
    c.gridy = 8;
    panel.add(back, c);
    back.addActionListener(new ActionListener()//Back button action. Changes window it brings user back to depending 
                             {    
      public void actionPerformed(ActionEvent e) {
        panel.removeAll();//Removes current panel and all objects on panel
        setContentPane(panel);//Reseting the panel
        panel.setLayout(null);//Sets layout to null, requires custom placements of objects (buttons, labels, textfields .etc)
        setSize(500, 500);//Sets the size of the new window
        if(playerOneSignedIn ==1 && playerTwoSignedIn == 1)
        {
          panel.setLayout(new GridBagLayout());
          signInPlayerTwo();//Add signInPlayerTwo()
        }
        else if(playerOneSignedIn ==0)
        {
          panel.setLayout(new GridBagLayout());
          signIn();//Add signIn() for palyer one
        }
        setVisible(true);//Sets the panel visible
      }
    }); 
  }
  public void singlePlay()//Makes a single player GUI for Game
  {
    lastdiff1 = 9999;//Holds the difference between the current guess and the previous guess
    lastdiff2 = 9999;//Holds the difference between the current guess and the previous guess
    rand1 = Math.random();//Random number the user is supposed to search for
    rand2 = Math.random();//Random number the user is supposed to search for
    test = true;//Tester to see if scenarios should run or not
    score1 = 0;//User 1 score
    x = (int) (rand1 * d);//Creates the actual numbers the player 1 has to guess
    y = (int) (rand2 * d);//Creates the actual numbers the player 2 has to guess
    ///
    back = new JButton("Exit Game");
    back.setBounds(0,0, 65,40);//Set location, then size of button (x,y) (width,length)
    panel.add(back);
    back.addActionListener(new ActionListener()//Sign Up button action. Brings user to a new page
                             {    
      public void actionPerformed(ActionEvent e) {
        int leave = JOptionPane.showConfirmDialog(null, "Would you like to exit the game?\n            Score will not count!", "Game", JOptionPane.YES_NO_OPTION);//Creates a pop up. Clicking Yes returns an integer of 0, No returns 1
        if(leave == 0)//Yes
        {
          panel.removeAll();//Removes current panel and all objects on panel
          setContentPane(panel);//Reseting the panel
          panel.setLayout(null);//Sets layout to null, requires custom placements of objects (buttons, labels, textfields .etc)
          setSize(500, 500);//Sets the size of the new window
          playerSelection();//Add playerSelection components
          setVisible(true);//Sets the panel visible
          leave = 2;//Changing Leave to a different value other than 1 or 0
        }
      }
    }); 
    playerOneName = new JLabel(firstPlayerUser);
    playerOneName.setFont(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 20));//set Font
    FontMetrics fm = getFontMetrics(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 20));//For getting Length of string
    int strWidth = fm.stringWidth(firstPlayerUser);
    playerOneName.setBounds(getWidth()/2- strWidth/2, 15, 380,40);//Places player one name in the center over scroll pane
    panel.add(playerOneName);//Add Player One's name
    info = new JButton("?");
    info.setFont(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 10));
    info.setBounds(455,0, 40,40);//Set location (x, y), then size of button (width, length)
    panel.add(info);//Add button
    info.addActionListener(new ActionListener()//Info button action. Creates a pop up for user
                             {    
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Instructions:\n\nThe aim of the Random Number Game is to guess a\nrandom number in the least amount of guesses possible.\n\nMake a guess (must be an integer) between 0 and " + d + "\n\nThe second guess it will tell you if you\nare warmer (nearer) or colder (further away) from\nthe random number compared to your last valid guess.\n\nPerks are enabled only in One Player on Hard Mode", "Game", JOptionPane.INFORMATION_MESSAGE);//Creates Pop up explaining the buttons
      }
    });
    textField = new JTextField();
    textArea = new JTextArea(5, 20);//Set textArea and tempoary size
    textArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(textArea);
    //Add Components to this panel.
    scrollPane.setBounds(20,50,460,325);//x,y location. width length dimensons
    panel.add(scrollPane);//Creates a ScrollPane to display text
    textArea.append("I'm thinking of an a integer between 0 and " + d + " inclusively, enter a guess: ");//Tells User, at the start, what to do on scrollPane 
    textField.setBounds(20,400,350,30);
    panel.add(textField);
    textField.addActionListener(new ActionListener()//Sign Up button action. Brings user to a new page
                                  {    
      public void actionPerformed(ActionEvent evt) {
        String text = textField.getText();//Set text to textfield when pressed enter
        if(test ==true)
        {
          try {//Loops if something other than a number is given           
            textArea.append(text);//Outputs the input as a String on to scrollPane
            input = Integer.parseInt(textField.getText());//If input cannot be converted to int. Catches error and outputs it on the GUI
            if(input != x && (input <= d) && (input >= 0))//If guess is wrong
            {
              textArea.append(newLine + "Wrong, guess another integer between 0 and " + d + " inclusively: ");
              test = false;
              score1++;//Add to number of player's tries
            }
            else if (input == x) {//If guess is correct
              score1++;
              textArea.append(newLine + "Well done! The number is: " + x + ". You did it in " + score1 + " tries!");
              textField.setEnabled(false);//Disables Text Field
              JOptionPane.showMessageDialog(null, "You won in " + score1 + " tries!", "Game", JOptionPane.INFORMATION_MESSAGE);//Creates a pop up message for winning  
              int j=999;
              for(int i=0;i<numOfAccount;i++){
                if(firstPlayerUser.equals(accounts.get(i).getUser()))//Runs if correct index of account is found
                  j=i;
              }
              if(score1<accounts.get(j).getScore()){//Sets high score of account
                accounts.get(j).setScore(score1);
              }
              try{
                PrintWriter writer = new PrintWriter("Account.txt");//Rewrites to file with new information
                writer.println("FirstName LastName Age Score Username Password Level Exp");
                for(int i=0;i<numOfAccount;i++){
                  writer.println(accounts.get(i).toString());
                }
                writer.close();
              } catch (IOException eio) {
              }
              int p=999;
              for(int i=0;i<numOfAccount;i++){
                if(firstPlayerUser.equals(accounts.get(i).getUser()))//Runs if the index of the account is found
                  p=i;//Sets index
              }
              accounts.get(p).setExp(accounts.get(p).getExp()+10);
              while(accounts.get(p).getExp()>=100){
                if(accounts.get(p).getExp()>=100){
                  accounts.get(p).setLevel(accounts.get(p).getLevel()+1);
                  accounts.get(p).setExp(accounts.get(p).getExp()-100);
                }
              }
              try{
                PrintWriter writer = new PrintWriter("Account.txt");//Rewrites account file
                writer.println("FirstName LastName Age Score Username Password Level Exp");
                for(int i=0;i<numOfAccount;i++){
                  writer.println(accounts.get(i).toString());//Displays all attributes
                }
                writer.close();
              } catch (IOException eio) {
              }
              panel.removeAll();//Removes current panel and all objects on panel
              setContentPane(panel);//Reseting the panel
              panel.setLayout(null);//Sets layout to null, requires custom placements of objects (buttons, labels, textfields .etc)
              setSize(500, 500);//Sets the size of the new window
              playerSelection();//Add playerSelection components
              setVisible(true);//Sets the panel visible
            }
          }
          catch (Exception er) {
            textArea.append(newLine + "Very funny, do that again except with a number thanks: "); //Tell it's not a number.
            input = 0;//Reset input to equal 0 so it does not run the invalid input code below
            System.out.println ("Error: " + er.getMessage ());//Prints error
            System.out.println ("Error: " + er.toString ()+"\n");//Prints error
          }
        }
        else if( test == false)
        {
          try {//Loops if something other than a number is given
            textArea.append(text);//Outputs the input as a String
            input = Integer.parseInt(textField.getText());//If input cannot be converted into int. Catches error and outputs it on the GUI
            if(input != x && (input <= d) && (input >= 0))
            {
              if (((Math.abs(input - x)) >= lastdiff1) && (test == false)) {//Run if their guess is worse than their previous guess
                textArea.append(newLine + "Colder or the same as last time, however.....");
              } 
              else if (test == false) {//Tuns if their guess is better than their previous guess
                textArea.append(newLine + "Warmer, however......");
              }
              textArea.append(newLine + "Wrong, guess another integer between 0 and " + d + " inclusively: ");
              
              score1++;//Add to number of player's tries
            }
            else if (input == x) {//If guess is correct
              score1++;
              textArea.append(newLine + "Well done! The number is: " + x + ". You did it in " + score1 + " tries!");
              textField.setEnabled(false);//Disables Text Field 
              JOptionPane.showMessageDialog(null, "You won in " + score1 + " tries!" , "Game", JOptionPane.INFORMATION_MESSAGE);//Creates a pop up message for winning
              int p=999;
              for(int i=0;i<numOfAccount;i++){
                if(firstPlayerUser.equals(accounts.get(i).getUser()))//Runs if the index of the account is found
                  p=i;//Sets index
              }
              accounts.get(p).setExp(accounts.get(p).getExp()+10);
              while(accounts.get(p).getExp()>=100){
                if(accounts.get(p).getExp()>=100){
                  accounts.get(p).setLevel(accounts.get(p).getLevel()+1);
                  accounts.get(p).setExp(accounts.get(p).getExp()-100);
                }
              }
              try{
                PrintWriter writer = new PrintWriter("Account.txt");//Rewrites account file
                writer.println("FirstName LastName Age Score Username Password Level Exp");
                for(int i=0;i<numOfAccount;i++){
                  writer.println(accounts.get(i).toString());//Displays all attributes
                }
                writer.close();
              } catch (IOException eio) {
              }
              if(d==1000){
                int j=999;
                for(int i=0;i<numOfAccount;i++){
                  if(firstPlayerUser.equals(accounts.get(i).getUser()))//Runs if the index of the account is found
                    j=i;//Sets index
                }
                if(score1<accounts.get(j).getScore()){//Sets new high score for account
                  accounts.get(j).setScore(score1);
                }
                try{
                  PrintWriter writer = new PrintWriter("Account.txt");//Rewrites account file
                  writer.println("FirstName LastName Age Score Username Password Level Exp");
                  for(int i=0;i<numOfAccount;i++){
                    writer.println(accounts.get(i).toString());//Displays all attributes
                  }
                  
                  writer.close();
                } catch (IOException eio) {
                }
              }
              panel.removeAll();//Removes current panel and all objects on panel
              setContentPane(panel);//Reseting the panel
              panel.setLayout(null);//Sets layout to null, requires custom placements of objects (buttons, labels, textfields .etc)
              setSize(500, 500);//Sets the size of the new window
              if(d ==1000)//Shows leaderboard for only Hard mode two player
              {
                setSize(305, 260);//Sets the size of the new window
                leaderBoard();
              }
              else
              {
                playerSelection();//Add playerSelection components
              }
              setVisible(true);//Sets the panel visible
            } 
            lastdiff1 = Math.abs(input - x);//Calculates the difference between the guess and the number
          } 
          catch (Exception er) {
            textArea.append(text);//Outputs the input as a String
            textArea.append(newLine + "Very funny, do that again except with a number thanks: "); //Tell it's not a number.
            input =0;//Reset input to equal 0 so it does not run the invalid input code below
            System.out.println ("Error: " + er.getMessage ());//Prints error
            System.out.println ("Error: " + er.toString ()+"\n");//Prints error
          }
        }
        if ((input > d) || (input < 0)) {//Runs if their input is invalid
          {
            try {//Loops if something other than a number is given
              if(test == false)
              {
                textArea.append(text);//Outputs the input as a String
              }
              textArea.append(newLine + "Very funny, I'll ignore that. Guess an integer between 0 and " + d + " inclusively: ");
            } 
            catch (Exception er) {
              textArea.append(newLine + "Very funny, do that again except with a number thanks: "); //Tell it's not a number.
              System.out.println ("Error: " + er.getMessage ());//Prints error
              System.out.println ("Error: " + er.toString ()+"\n");//Prints error
            }
          }
          //Make sure the new text is visible, even if there
          //was a selection in the text area.
          textField.selectAll();
          textArea.setCaretPosition(textArea.getDocument().getLength());
        }
      }
    }); 
  }
  public void twoPlay()//Makes the two player Game
  {
    lastdiff1 = 9999;//Holds the difference between the current guess and the previous guess
    lastdiff2 = 9999;//Holds the difference between the current guess and the previous guess
    rand1 = Math.random();//Random number the user is supposed to search for
    rand2 = Math.random();//Random number the user is supposed to search for
    test = true;//Tester to see if scenarios for player one should run or not
    test2 = true;//Tester to see if scenarios for player two should run or not
    score1 = 0;//Player 1 score
    score2 = 0;//Player 2 score
    win1 = 0;//Win check for player 1
    win2 = 0;//Win check for palyer 2
    input1 = 0;//Textfield input for player one
    input2 = 0;//TextFeidl input for palyer two
    x = (int) (rand1 * d);//Creates the actual numbers the player 1 has to guess
    y = (int) (rand2 * d);//Creates the actual numbers the player 2 has to guess   
    back = new JButton("Exit Game");
    back.setBounds(0,0, 65,40);//Set location, then size of button (x,y) (width,length)
    panel.add(back);
    back.addActionListener(new ActionListener()//Sign Up button action. Brings user to a new page
                             {    
      public void actionPerformed(ActionEvent e) {
        int leave = JOptionPane.showConfirmDialog(null, "Would you like to exit the game?\n            Score will not count!", "Game", JOptionPane.YES_NO_OPTION);//Creates a pop up. Clicking Yes returns an integer of 0, No returns 1
        if(leave == 0)//Yes
        {
          panel.removeAll();//Removes current panel and all objects on panel
          setContentPane(panel);//Reseting the panel
          panel.setLayout(null);//Sets layout to null, requires custom placements of objects (buttons, labels, textfields .etc)
          setSize(500, 500);//Sets the size of the new window
          playerSelection();//Add playerSelection components
          setVisible(true);//Sets the panel visible
          leave = 2;//Changing Leave to a different value other than 1 or 0
        }
      }
    });
    playerOneName = new JLabel(firstPlayerUser);
    playerOneName.setFont(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 20));//set Font
    FontMetrics fm = getFontMetrics(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 20));//For getting Length of string
    int strWidth = fm.stringWidth(firstPlayerUser);
    playerOneName.setBounds(250- strWidth/2, 15, 380,40);//Places player one name in the center over scroll pane
    panel.add(playerOneName);//Add Player One's name
    playerTwoName = new JLabel(secondPlayerUser);
    playerTwoName.setFont(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 20));//set Font
    fm = getFontMetrics(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 20));//For getting Length of string
    strWidth = fm.stringWidth(secondPlayerUser);
    playerTwoName.setBounds(750- strWidth/2, 15, 380,40);//Places player two name in the center over scroll pane
    panel.add(playerTwoName);//Add Player Two's name
    info = new JButton("?");
    info.setFont(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 10));
    info.setBounds(955,0, 40,40);//Set location (x, y), then size of button (width, length)
    panel.add(info);//Add button
    info.addActionListener(new ActionListener()//Info button action
                             {    
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Instructions:\n\nThe aim of the Random Number Game is to guess a \nrandom number in the least amount of guesses possible.\n\nMake a guess (must be an integer) between 0 and " + d + "\n\nThe second guess it will tell you if you\nare warmer (nearer) or colder (further away) from\nthe random number compared to your last valid guess.\n\nGet items by geting warmer 4 times in a row \nand hinder your opponent or speed up your guessing!", "Game", JOptionPane.INFORMATION_MESSAGE);//Creates Pop up explaining the buttons
      }
    });
    //Creats Player One's interface
    textField = new JTextField();
    textArea = new JTextArea(5, 20);
    textArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(textArea);
    //Add Components to this panel.
    scrollPane.setBounds(20,50,460,325);//x,y location. width length dimensons
    panel.add(scrollPane);
    textArea.append("I'm thinking of an a integer between 0 and " + d + " inclusively, enter a guess: ");//Tells User, at the start, what to do
    textField.setBounds(20,400,350,30);
    panel.add(textField);
    textField.addActionListener(new ActionListener()//Player One's textfield
                                  {    
      public void actionPerformed(ActionEvent evt) {
        String text = textField.getText();
        if(test ==true)
        {
          try {//Loops if something other than a number is given           
            textArea.append(text);//Outputs the input1 as a String
            input1 = Integer.parseInt(textField.getText());//If input1 cannot be converted to int. Catches error and outputs it on the GUI    
            if(input1 != x && (input1 <= d) && (input1 >= 0))
            {
              textArea.append(newLine + "Wrong, guess another integer between 0 and " + d + " inclusively: ");
              lastdiff1 = Math.abs(input1 - x);//Calculates the difference between the guess and the number
              test = false;
              score1++;//Add to number of player Ones tries
              textField.setEnabled(false);//Disables Player One's Text Field (Current textField)
              textField1.setEnabled(true);//Enables Player Two's Text Field
            }
            else if (input1 == x) {//If guess is correct
              score1++;//Add to number of player Ones tries
              textArea.append(text);//Outputs the input2 as a String
              textArea.append(newLine + "Well done! The number is: " + x + ". You did it in " + score1 + " try!");
              win1 = 1;//Adds to win counter for player one
              textField.setEnabled(false);//Disables Player One's Text Field (Current textField)
              textField1.setEnabled(true);//Enables Player Two's Text Field
            } 
          }
          catch (Exception er) {
            textArea.append(newLine + "Very funny, do that again except with a number thanks: "); //Tell it's not a number.
            input1 = 0;//Reset input1 to equal 0 so it does not run the invalid input1 code below
            System.out.println ("Error: " + er.getMessage ());//Prints error
            System.out.println ("Error: " + er.toString ()+"\n");//Prints error
          }
        }
        else if( test == false)
        {
          try {//Loops if something other than a number is given
            input1 = Integer.parseInt(textField.getText());//If input1 cannot be converted into int. Catches error and outputs it on the GUI
            if(input1 != x && (input1 <= d) && (input1 >= 0))
            {
              textArea.append(text);//Outputs the input1 as a String
              if(win2 !=1)//If player two guessed right, won't output the following needlessly if player one guessed wrong
              {
                if (((Math.abs(input1 - x)) >= lastdiff1) && (test == false)) {//Run if their guess is worse than their previous guess
                  textArea.append(newLine + "Colder or the same as last time, however.....");
                } 
                else if (test == false) {//Tuns if their guess is better than their previous guess
                  textArea.append(newLine + "Warmer, however......");
                }
                textArea.append(newLine + "Wrong, guess another integer between 0 and " + d + " inclusively: ");
                textField.setEnabled(false);//Disables Player One's Text Field (Current textField)
                textField1.setEnabled(true);//Enables Player Two's Text Field
              }
              score1++;//Add to number of player Ones tries
            }
            else if (input1 == x) {//If guess is correct
              score1++;
              textArea.append(text);//Outputs the input2 as a String
              textArea.append(newLine + "Well done! The number is: " + x + ". You did it in " + score1 + " tries!");
              win1 = 1;//Adds to win counter for player one
              textField.setEnabled(false);//Disables Player One's Text Field (Current textField)
              textField1.setEnabled(true);//Enables Player Two's Text Field
            }  
            lastdiff1 = Math.abs(input1 - x);//Calculates the difference between the guess and the number
          } 
          catch (Exception er) {
            textArea.append(text);//Outputs the input1 as a String
            textArea.append(newLine + "Very funny, do that again except with a number thanks: "); //Tell it's not a number.
            input1 =0;//Reset input1 to equal 0 so it does not run the invalid input1 code below
            System.out.println ("Error: " + er.getMessage ());//Prints error
            System.out.println ("Error: " + er.toString ()+"\n");//Prints error
          }
        }
        if ((input1 > d) || (input1 < 0)) {//Runs if their input1 is invalid
          {
            try {//Loops if something other than a number is given
              if(test == false)
              {
                textArea.append(text);//Outputs the input1 as a String
              }
              textArea.append(newLine + "Very funny, I'll ignore that. Guess an integer between 0 and " + d + " inclusively: ");
            } 
            catch (Exception er) {
              textArea.append(newLine + "Very funny, do that again except with a number thanks: "); //Tell it's not a number.
              System.out.println ("Error: " + er.getMessage ());//Prints error
              System.out.println ("Error: " + er.toString ()+"\n");//Prints error
            }
          }
        }
        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        textField.selectAll();
        textArea.setCaretPosition(textArea.getDocument().getLength());
      }
    });//End of Player One Code
    //Creates Player Two's Interface
    textField1 = new JTextField();
    textArea1 = new JTextArea(5, 20);
    textArea1.setEditable(false);
    JScrollPane scrollPane1 = new JScrollPane(textArea1);
    scrollPane1.setBounds(520,50,460,325);//x,y location. width length dimensons
    panel.add(scrollPane1);
    textArea1.append("I'm thinking of an a integer between 0 and " + d + " inclusively, enter a guess: ");//Tells User, at the start, what to do
    textField1.setBounds(520,400,350,30);
    panel.add(textField1);
    textField1.setEnabled(false);
    textField1.addActionListener(new ActionListener()//Player two's Text Field
                                   {    
      public void actionPerformed(ActionEvent evt) {
        String text2 = textField1.getText();
        if(test2 ==true)
        {
          try {//Loops if something other than a number is given           
            textArea1.append(text2);//Outputs the input2 as a String
            input2 = Integer.parseInt(textField1.getText());//If input2 cannot be converted to int. Catches error and outputs it on the GUI
            if(input2 != y && (input2 <= d) && (input2 >= 0))
            {
              if(win1 !=1)//If player One guessed right the first try, won't output the following needlessly if player 2 gets the guess wrong on first try
              {
                textArea1.append(newLine + "Wrong, guess another integer between 0 and " + d + " inclusively: ");
                lastdiff2 = Math.abs(input2 - y);//Calculates the difference between the guess and the number
                test2 = false;
                textField.setEnabled(true);//Enables Player one's Text Field
                textField1.setEnabled(false);//Disables Player Two's Text Field (Current textField)
              }
              score2++;//Adds to player Two's try counter
            }
            else if (input2 == y) {//If guess is correct
              score2++;//Add to number of player two tries
              textArea1.append(text2);//Outputs the input2 as a String
              textArea1.append(newLine + "Well done! The number is: " + y + ". You did it in " + score2 + " try!");
              win2 = 1;//Adds to win counter for player two
              textField.setEnabled(true);//Enables Player one's Text Field
              textField1.setEnabled(false);//Disables Player Two's Text Field (Current textField)
            } 
          }
          catch (Exception er) {
            textArea1.append(newLine + "Very funny, do that again except with a number thanks: "); //Tell it's not a number.
            input2 = 0;//Reset input2 to equal 0 so it does not run the invalid input2 code below
            System.out.println ("Error: " + er.getMessage ());//Prints error
            System.out.println ("Error: " + er.toString ()+"\n");//Prints error
          }
        }
        else if( test2 == false)
        {
          try {//Loops if something other than a number is given
            input2 = Integer.parseInt(textField1.getText());//If input2 cannot be converted into int. Catches error and outputs it on the GUI
            if(input2 != y && (input2 <= d) && (input2 >= 0))
            {
              textArea1.append(text2);//Outputs the input2 as a String
              if(win1 !=1)//If player One guessed right on a later try, won't output the following needlessly if player 2 gets the guess wrong
              {
                if (((Math.abs(input2 - y)) >= lastdiff2) && (test2 == false)) {//Run if their guess is worse than their previous guess
                  textArea1.append(newLine + "Colder or the same as last time, however.....");
                } 
                else if (test2 == false) {//Tuns if their guess is better than their previous guess
                  textArea1.append(newLine + "Warmer, however......");
                }
                textArea1.append(newLine + "Wrong, guess another integer between 0 and " + d + " inclusively: ");
                textField.setEnabled(true);//Enables Player one's Text Field
                textField1.setEnabled(false);//Disables Player Two's Text Field (Current textField)
              }
              score2++;//Add to number of player two tries
            }
            else if (input2 == y) {//If guess is correct
              score2++;//Add to number of player two tries
              textArea1.append(text2);//Outputs the input2 as a String
              textArea1.append(newLine + "Well done! The number is: " + y + ". You did it in " + score2 + " tries!");
              textField.setEnabled(true);//Enables Player one's Text Field
              textField1.setEnabled(false);//Disables Player Two's Text Field (Current textField)
              win2 = 1;   //Adds to win counter for player two        
            } 
            lastdiff2 = Math.abs(input2 - y);//Calculates the difference between the guess and the number
          } 
          catch (Exception er) {
            textArea1.append(text2);//Outputs the input2 as a String
            textArea1.append(newLine + "Very funny, do that again except with a number thanks: "); //Tell it's not a number.
            input2 =0;//Reset input2 to equal 0 so it does not run the invalid input2 code below
            System.out.println ("Error: " + er.getMessage ());//Prints error
            System.out.println ("Error: " + er.toString ()+"\n");//Prints error
          }
        }
        if ((input2 > d) || (input2 < 0)) {//Runs if their input2 is invalid
          {
            try {//Loops if something other than a number is given
              if(test2 == false)
              {
                textArea1.append(text2);//Outputs the input2 as a String
              }
              textArea1.append(newLine + "Very funny, I'll ignore that. Guess an integer between 0 and " + d + " inclusively: ");
            } 
            catch (Exception er) {
              textArea1.append(newLine + "Very funny, do that again except with a number thanks: "); //Tell it's not a number.
              System.out.println ("Error: " + er.getMessage ());//Prints error
              System.out.println ("Error: " + er.toString ()+"\n");//Prints error
            }
          }
        }
        //Make sure the new text2 is visible, even if there
        //was a selection in the text area.
        textField1.selectAll();
        textArea1.setCaretPosition(textArea1.getDocument().getLength());
        if(win2 == 1 && win1 ==1)//Checks if player two won first, then if player One won the next turn. Results in tie
        {
          textField.setEnabled(false);//Disables Player Two's Text Field
          JOptionPane.showMessageDialog(null, "Both players tied with in " + score1 + " trie(s)!\n\nPlayer two will be logged out", "Game", JOptionPane.INFORMATION_MESSAGE); //Creates a pop up message for winning
          int p=999;
          for(int i=0;i<numOfAccount;i++){
            if(firstPlayerUser.equals(accounts.get(i).getUser()))//Runs if the index of the account is found
              p=i;//Sets index
          }
          accounts.get(p).setExp(accounts.get(p).getExp()+20);
          while(accounts.get(p).getExp()>=100){
            if(accounts.get(p).getExp()>=100){
              accounts.get(p).setLevel(accounts.get(p).getLevel()+1);
              accounts.get(p).setExp(accounts.get(p).getExp()-100);
            }
          }
          try{
            PrintWriter writer = new PrintWriter("Account.txt");//Rewrites account file
            writer.println("FirstName LastName Age Score Username Password Level Exp");
            for(int i=0;i<numOfAccount;i++){
              writer.println(accounts.get(i).toString());//Displays all attributes
            }
            writer.close();
          } catch (IOException eio) {
          }
          int q=999;
          for(int i=0;i<numOfAccount;i++){
            if(secondPlayerUser.equals(accounts.get(i).getUser()))//Runs if the index of the account is found
              q=i;//Sets index
          }
          accounts.get(q).setExp(accounts.get(q).getExp()+20);
          while(accounts.get(q).getExp()>=100){
            if(accounts.get(q).getExp()>=100){
              accounts.get(q).setLevel(accounts.get(q).getLevel()+1);
              accounts.get(q).setExp(accounts.get(q).getExp()-100);
            }
          }
          try{
            PrintWriter writer = new PrintWriter("Account.txt");//Rewrites account file
            writer.println("FirstName LastName Age Score Username Password Level Exp");
            for(int i=0;i<numOfAccount;i++){
              writer.println(accounts.get(i).toString());//Displays all attributes
            }
            writer.close();
          } catch (IOException eio) {
          }
          if(d==1000){
            int j=999;
            for(int i=0;i<numOfAccount;i++){
              if(firstPlayerUser.equals(accounts.get(i).getUser()))//Finds correct account index of player 1
                j=i;
            }
            if(score1<accounts.get(j).getScore()){
              accounts.get(j).setScore(score1);//Sets account high score
            }
            int k=999;
            for(int i=0;i<numOfAccount;i++){
              if(secondPlayerUser.equals(accounts.get(i).getUser()))//Finds correct account index of player 2
                k=i;
            }
            if(score2<accounts.get(k).getScore()){
              accounts.get(k).setScore(score2);//Sets account high score
            }
            try{
              PrintWriter writer = new PrintWriter("Account.txt");//Rewrites file using new information
              writer.println("FirstName LastName Age Score Username Password Level Exp");
              for(int i=0;i<numOfAccount;i++){
                writer.println(accounts.get(i).toString());
              }
              writer.close();
            } catch (IOException eio) {
            }
          }
          panel.removeAll();//Removes current panel and all objects on panel
          setContentPane(panel);//Reseting the panel
          panel.setLayout(null);//Sets layout to null, requires custom placements of objects (buttons, labels, textfields .etc)
          setSize(500, 500);//Sets the size of the new window
          if(d ==1000)//Shows leaderboard for only Hard mode two player
          {
            setSize(305, 260);//Sets the size of the new window
            leaderBoard();
          }
          else
          {
            playerSelection();//Add playerSelection components
          }
          setVisible(true);//Sets the panel visible
        }
        else if(win1 == 1)//If Player Two was unable to guess their number, player one wins
        {
          textArea1.append(newLine + "Too Bad, your number was: " + y + newLine + "You used "+ score2 + " tries!");//Outputs Player two's number and amount of tries
          textField.setEnabled(false);//Disables Player One's Text Field
          JOptionPane.showMessageDialog(null, "Player One won in " + score1 + " trie(s)!\n\nPlayer two will be logged out", "Game", JOptionPane.INFORMATION_MESSAGE);//Creates a pop up message for winning
          int p=999;
          for(int i=0;i<numOfAccount;i++){
            if(firstPlayerUser.equals(accounts.get(i).getUser()))//Runs if the index of the account is found
              p=i;//Sets index
          }
          accounts.get(p).setExp(accounts.get(p).getExp()+20);
          while(accounts.get(p).getExp()>=100){
            if(accounts.get(p).getExp()>=100){
              accounts.get(p).setLevel(accounts.get(p).getLevel()+1);
              accounts.get(p).setExp(accounts.get(p).getExp()-100);
            }
          }
          try{
            PrintWriter writer = new PrintWriter("Account.txt");//Rewrites account file
            writer.println("FirstName LastName Age Score Username Password Level Exp");
            for(int i=0;i<numOfAccount;i++){
              writer.println(accounts.get(i).toString());//Displays all attributes
            }
            writer.close();
          } catch (IOException eio) {
          }
          
          if(d==1000){
            int j=999;
            for(int i=0;i<numOfAccount;i++){
              if(firstPlayerUser.equals(accounts.get(i).getUser()))//Finds account index of player 1
                j=i;
            }
            if(score1<accounts.get(j).getScore()){
              accounts.get(j).setScore(score1);//Sets new high score
            }
            try{
              PrintWriter writer = new PrintWriter("Account.txt");//Rewrites file using new information
              writer.println("FirstName LastName Age Score Username Password Level Exp");
              for(int i=0;i<numOfAccount;i++){
                writer.println(accounts.get(i).toString());
              }
              writer.close();
            } catch (IOException eio) {
            }
          }
          panel.removeAll();//Removes current panel and all objects on panel
          setContentPane(panel);//Reseting the panel
          panel.setLayout(null);//Sets layout to null, requires custom placements of objects (buttons, labels, textfields .etc)
          setSize(500, 500);//Sets the size of the new window
          if(d ==1000)//Shows leaderboard for only Hard mode two player
          {
            setSize(305, 260);//Sets the size of the new window
            leaderBoard();
          }
          else
          {
            playerSelection();//Add playerSelection components
          }
          setVisible(true);//Sets the panel visible
        }
        else if(win2 == 1)
        {
          textArea.append(newLine + "Too Bad, your number was: " + x + newLine + "You used "+ score1 + " tries!");//Outputs Player One's number and amount of tries
          textField1.setEnabled(false);//Disables Player Two's Text Field
          JOptionPane.showMessageDialog(null, "Player Two won in " + score2 + " trie(s)!\n\nPlayer two will be logged out", "Game", JOptionPane.INFORMATION_MESSAGE);//Creates a pop up message for winning
          int p=999;
          for(int i=0;i<numOfAccount;i++){
            if(secondPlayerUser.equals(accounts.get(i).getUser()))//Runs if the index of the account is found
              p=i;//Sets index
          }
          accounts.get(p).setExp(accounts.get(p).getExp()+20);
          while(accounts.get(p).getExp()>=100){
            if(accounts.get(p).getExp()>=100){
              accounts.get(p).setLevel(accounts.get(p).getLevel()+1);
              accounts.get(p).setExp(accounts.get(p).getExp()-100);
            }
          }
          try{
            PrintWriter writer = new PrintWriter("Account.txt");//Rewrites account file
            writer.println("FirstName LastName Age Score Username Password Level Exp");
            for(int i=0;i<numOfAccount;i++){
              writer.println(accounts.get(i).toString());//Displays all attributes
            }
            writer.close();
          } catch (IOException eio) {
          }
          if(d==1000){
            int k=999;
            for(int i=0;i<numOfAccount;i++){
              if(secondPlayerUser.equals(accounts.get(i).getUser()))//Finds account index of player two
                k=i;
            }
            if(score2<accounts.get(k).getScore()){
              accounts.get(k).setScore(score2);//Sets account high score
            }
            try{
              PrintWriter writer = new PrintWriter("Account.txt");//rewrites file with new info
              writer.println("FirstName LastName Age Score Username Password Level Exp");
              for(int i=0;i<numOfAccount;i++){
                writer.println(accounts.get(i).toString());
              }
              writer.close();
            } catch (IOException eio) {
            }
          }
          panel.removeAll();//Removes current panel and all objects on panel
          setContentPane(panel);//Reseting the panel
          panel.setLayout(null);//Sets layout to null, requires custom placements of objects (buttons, labels, textfields .etc)
          setSize(500, 500);//Sets the size of the new window
          if(d ==1000)//Shows leaderboard for only Hard mode two player
          {
            setSize(305, 260);//Sets the size of the new window
            leaderBoard();
          }
          else
          {
            playerSelection();//Add playerSelection components
          }
          setVisible(true);//Sets the panel visible
        }
      }
    });//End of player two 
  }
  public void profile()//Player One's Profile
  {
    back = new JButton("Back");
    back.setBounds(0,0, 65,40);//Set location, then size of button (x,y) (width,length)
    panel.add(back);
    back.addActionListener(new ActionListener()//back button action. Brings user to playerSelection()
                             {    
      public void actionPerformed(ActionEvent e) {
        panel.removeAll();//Removes current panel and all objects on panel
        setContentPane(panel);//Reseting the panel
        panel.setLayout(null);//Sets layout to null, requires custom placements of objects (buttons, labels, textfields .etc)
        setSize(500, 500);//Sets the size of the new window
        playerSelection();//Add player slection components
        setVisible(true);//Sets the panel visible
      }
    });
    Font f = new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 25);
    FontMetrics fm = getFontMetrics(f);//For getting Length of string
    int strWidth = fm.stringWidth(firstPlayerUser);
    title = new JLabel(firstPlayerUser);//Player one's Username
    title.setBounds(getWidth()/2 -strWidth/2,10, 420,100);//Set location, then size of button (x,y) (width,length)
    title.setFont(f);
    panel.add(title);
    int p=999;
    for(int i=0;i<numOfAccount;i++){
      if(firstPlayerUser.equals(accounts.get(i).getUser()))//Runs if the index of the account is found
        p=i;//Sets index
    }
    title = new JLabel(accounts.get(p).getFName());//Player one's first name
    title.setBounds(65,75, 300,75);//Set location, then size of button (x,y) (width,length)
    title.setFont(f);
    panel.add(title);
    title = new JLabel(accounts.get(p).getLName());//Player one's last name
    title.setBounds(65,150, 300,75);//Set location, then size of button (x,y) (width,length)
    title.setFont(f);
    panel.add(title);
    String stringAge=Integer.toString(accounts.get(p).getAge());
    title = new JLabel("Age: "+stringAge);//Player one's age
    title.setBounds(65,225, 300,75);//Set location, then size of button (x,y) (width,length)
    title.setFont(f);
    panel.add(title);
    String stringLevel=Integer.toString(accounts.get(p).getLevel());
    title = new JLabel("Level: " + stringLevel);//Player one's Level
    title.setBounds(65,300, 300,75);//Set location, then size of button (x,y) (width,length)
    title.setFont(f);
    panel.add(title);
    String stringExp=Integer.toString(accounts.get(p).getExp());
    title = new JLabel("Exp: " + stringExp+"/100");//Player one's experience ()
    title.setBounds(65,375, 300,75);//Set location, then size of button (x,y) (width,length)
    title.setFont(f);
    panel.add(title);
  }
  public void playerSelection()//Main Selection Screen
  {
    playerTwoSignedIn = 0;//Whenever user is at playerSelection, Player two will be considered not signed in
    account = new JButton("Profile");  
    account.setFont(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 50));     
    account.setBounds(75,75, 350,200);//Set location, then size of button (x,y) (width,length)
    panel.add(account);
    account.addActionListener(new ActionListener()//Account button action. Brings user to profile()
                                {
      public void actionPerformed(ActionEvent e) {
        panel.removeAll();//Removes current panel and all objects on panel
        setContentPane(panel);//Reseting the panel
        panel.setLayout(null);//Sets layout to null, requires custom placements of objects (buttons, labels, textfields .etc)
        setSize(500, 500);//Sets the size of the new window
        profile();//Add profile components
        setVisible(true);//Sets the panel visible
      }
    });
    single = new JButton("One Player");
    single.setFont(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 16));
    single.setBounds(75,300, 150,150);//Set location, then size of button (x,y) (width,length)
    panel.add(single);//adding Single player button
    single.addActionListener(new ActionListener()//One Player button action. Brings user to player one level() (to chose diffculity)
                               {
      public void actionPerformed(ActionEvent e) {
        panel.removeAll();//Removes current panel and all objects on panel
        setContentPane(panel);//Reseting the panel
        panel.setLayout(null);//Sets layout to null, requires custom placements of objects (buttons, labels, textfields .etc)
        setSize(500, 500);//Sets the size of the new window
        level();//Add Level components
        setVisible(true);//Sets the panel visible
      }
    });
    two = new JButton("Two Player");
    two.setFont(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 16));
    two.setBounds(275,300, 150,150);//Set location (x, y), then size of button (width, length)
    panel.add(two);//adding Two player button
    two.addActionListener(new ActionListener()//Two button action. Brings user to signInPlayerTwo() to sign in our register
                            {    
      public void actionPerformed(ActionEvent e) {
        panel.removeAll();//Removes current panel and all objects on panel
        setContentPane(panel);//Reseting the panel
        panel.setLayout(new GridBagLayout());
        setSize(500, 500);//Sets the size of the new window
        playerTwoSignedIn = 1;//Set playerTwoSignedIn to 1. Now, level diffculity will change
        signInPlayerTwo();//Add twoPlay components
        setVisible(true);//Sets the panel visible
      }
    });
    signOutButton = new JButton("Sign Out");//Add sign out button
    signOutButton.setFont(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 10));
    signOutButton.setBounds(420,0, 80,50);//Set location (x, y), then size of button (width, length)
    panel.add(signOutButton);
    signOutButton.addActionListener(new ActionListener()//Sign out button action. Signs player one out, bringing user to signIn()
                                      {    
      public void actionPerformed(ActionEvent e) {
        panel.removeAll();//Removes current panel and all objects on panel
        setContentPane(panel);//Reseting the panel
        panel.setLayout(new GridBagLayout());
        setSize(500, 500);//Sets the size of the new window
        playerOneSignedIn = 0;//Player one signed out varible
        signIn();//Add twoPlay components
        setVisible(true);//Sets the panel visible
      }
    });
  }
  public void level()//Player diffculity  
  {
    back = new JButton("Back");
    back.setBounds(0,0, 65,40);//Set location, then size of button (x,y) (width,length)
    panel.add(back);
    back.addActionListener(new ActionListener()//Back button action. Brings user to playerSelection() regardless if player two
                             {    
      public void actionPerformed(ActionEvent e) {
        panel.removeAll();//Removes current panel and all objects on panel
        setContentPane(panel);//Reseting the panel
        panel.setLayout(null);//Sets layout to null, requires custom placements of objects (buttons, labels, textfields .etc)
        setSize(500, 500);//Sets the size of the new window
        playerSelection();//Add player slection components
        setVisible(true);//Sets the panel visible
      }
    }); 
    info = new JButton("?");
    info.setFont(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 10));
    info.setBounds(455,0, 40,40);//Set location (x, y), then size of button (width, length)
    panel.add(info);//Add button
    info.addActionListener(new ActionListener()//Info button action
                             {    
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Select a diffculty\nEasy is 0 - 10 numbers\nNormal is 0 - 100 numbers\nHard is 0 - 1000 numbers", "Game", JOptionPane.INFORMATION_MESSAGE);//Creates Pop up explaining the buttons
      }
    });
    easy = new JButton("Easy");
    easy.setFont(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 20));
    easy.setBounds(50,200, 100, 100);//Set location (x, y), then size of button (width, length)
    panel.add(easy);
    easy.addActionListener(new ActionListener()//Easy button action.
                             {    
      public void actionPerformed(ActionEvent e) {
        panel.removeAll();//Removes current panel and all objects on panel
        setContentPane(panel);//Reseting the panel
        panel.setLayout(null);//Sets layout to null, requires custom placements of objects (buttons, labels, textfields .etc)
        d = 10;
        if (playerTwoSignedIn == 1)
        {
          setSize(1000, 500);//Sets the size of the new window
          twoPlay();
        }
        else
        {
          setSize(500, 500);//Sets the size of the new window
          singlePlay();//Add singlePlay components
        }
        setVisible(true);//Sets the panel visible
      }
    });
    normal = new JButton("Normal");
    normal.setFont(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 20));
    normal.setBounds(200,200, 100, 100);//Set location (x, y), then size of button (width, length)
    panel.add(normal);
    normal.addActionListener(new ActionListener()//Normal button action.
                               {    
      public void actionPerformed(ActionEvent e) {
        panel.removeAll();//Removes current panel and all objects on panel
        setContentPane(panel);//Reseting the panel
        panel.setLayout(null);//Sets layout to null, requires custom placements of objects (buttons, labels, textfields .etc)
        setSize(500, 500);//Sets the size of the new window
        d = 100;
        if (playerTwoSignedIn == 1)
        {
          setSize(1000, 500);//Sets the size of the new window
          twoPlay();//Adds twoPlay components
        }
        else
        {
          setSize(500, 500);//Sets the size of the new window
          singlePlay();//Add singlePlay components
        }
        setVisible(true);//Sets the panel visible
      }
    });
    title = new JLabel("(Leader Board Enabled)");//Adds message under Hard diffculity button
    title.setFont(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 9));
    title.setBounds(353, 300, 100, 30);//Set location (x, y), then size of button (width, length)
    panel.add(title);
    
    hard = new JButton("Hard");
    hard.setFont(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 20));
    hard.setBounds(350,200, 100, 100);//Set location (x, y), then size of button (width, length)
    panel.add(hard);
    hard.addActionListener(new ActionListener()//Hard button action.
                             {    
      public void actionPerformed(ActionEvent e) {
        panel.removeAll();//Removes current panel and all objects on panel
        setContentPane(panel);//Reseting the panel
        panel.setLayout(null);//Sets layout to null, requires custom placements of objects (buttons, labels, textfields .etc)
        setSize(500, 500);//Sets the size of the new window
        d = 1000;
        if (playerTwoSignedIn == 1)
        {
          setSize(1000, 500);//Sets the size of the new window
          twoPlay();//Adds twoPlay components
        }
        else
        {
          setSize(500, 500);//Sets the size of the new window
          singlePlay();//Add singlePlay components
        }
        setVisible(true);//Sets the panel visible
      }
    });
  }
  public void signInPlayerTwo()
  {
    enablerSign = 0;
    userEnable = 0;
    passEnable = 0;
    GridBagConstraints c = new GridBagConstraints();
    title = new JLabel("Sign In");
    title.setFont(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 50));
    c.gridx = 0;
    c.gridy = 0;
    panel.add(title, c);//Add title
    
    userText = new JTextField("Username", 30);
    c.weightx = 0;
    c.gridx = 0;
    c.gridy = 1;
    panel.add(userText, c);
    userText.addActionListener(new ActionListener()//Username Textfield action. Disables Log In button if a invalid Username is entered
                                 {    
      public void actionPerformed(ActionEvent evt) {
        user = userText.getText();
        secondPlayerUser= user;
        if(userEnable == 0 && !(user.equals("Username")))//If the Username textfield  does not contain "Username", increase userEnable
        {
          userEnable = 1;;
        }
        user = userText.getText();
        if(user.equals("Username"))//If the person re-inputs "Username", resets userEnabled and disables log in button
        {
          userEnable = 0;
          signInButton.setEnabled(false);
        }
        enablerSign = userEnable + passEnable;//Adds userEnabled and pass enabled
        if(enablerSign ==2)//If enablerSign is 2, the Log in button is enabled
        {
          signInButton.setEnabled(true);
        } 
      }
    }); 
    passText = new JTextField("Password", 30);
    c.weightx = 0;
    c.gridx = 0;
    c.gridy = 2;
    panel.add(passText, c);
    passText.addActionListener(new ActionListener()//Password Textfield action. Disables Log In button if a invalid Password is entered
                                 {    
      public void actionPerformed(ActionEvent evt) {
        pass = passText.getText();//Sets pass equal to the text Field when pressed enter
        if(passEnable == 0 && !(pass.equals("Password")))//If the Password textfield  does not contain "Password", increase passEnable
        {
          passEnable = 1;
        }
        pass = passText.getText();
        if(pass.equals("Password"))//If the person re-inputs "Password", resets passEnabled and disables log in button
        {
          passEnable = 0;
          signInButton.setEnabled(false);
        }
        enablerSign = userEnable + passEnable;//Adds userEnabled and pass enabled
        if(enablerSign ==2)//If enablerSign is 2, the Log in button is enabled
        {
          signInButton.setEnabled(true);
        }
      }
    });
    tester=false;
    signInButton = new JButton("Log In");
    c.weightx = 0.5;
    c.ipady = 10;
    c.gridx = 0;
    c.gridy = 4;
    panel.add(signInButton, c);
    signInButton.setEnabled(false);
    signInButton.addActionListener(new ActionListener()//Log in button action.
                                     {
      public void actionPerformed(ActionEvent e) {
        for(int i=0;i<numOfAccount;i++){//Searches for corresponding account username and pass
          if(accounts.get(i).getUser().equals(user)&&accounts.get(i).getPass().equals(pass))//Runs if correct index is found
            tester=true;//Makes tester true
        }
        if(firstPlayerUser.equals(user)){//Runs if the same account tries to be signed in as player two
          JOptionPane.showMessageDialog(null, "That user is already logged in sorry.", "Game", JOptionPane.INFORMATION_MESSAGE);//Creates Pop up explaining the buttons
        }
        else if(tester==true){
          panel.removeAll();//Removes current panel and all objects on panel
          setContentPane(panel);//Reseting the panel
          panel.setLayout(null);
          setSize(500, 500);//Sets the size of the new window
          level();//Add Level components;//Add sign up components
          setVisible(true);//Sets the panel visible
        }
        else{
          JOptionPane.showMessageDialog(null, "Incorrect login/password, please try again.", "Game", JOptionPane.INFORMATION_MESSAGE);//Creates Pop up explaining the buttons
        }
      }
    });
    signUpButton = new JButton("New to the game?");
    c.weightx = 0;
    c.gridx = 0;
    c.gridy = 5;
    panel.add(signUpButton, c);
    signUpButton.addActionListener(new ActionListener()//Sign Up button action. 
                                     {    
      public void actionPerformed(ActionEvent e) {
        panel.removeAll();//Removes current panel and all objects on panel
        setContentPane(panel);//Reseting the panel
        panel.setLayout(new GridBagLayout());
        setSize(500, 500);//Sets the size of the new window
        signUp();//Add sign up components
        setVisible(true);//Sets the panel visible
      }
    }); 
    back = new JButton("Back");
    c.anchor = GridBagConstraints.PAGE_END;
    c.gridx = 0;
    c.gridy = 6;
    panel.add(back, c);
    back.addActionListener(new ActionListener()//Back button action.
                             {    
      public void actionPerformed(ActionEvent e) {
        panel.removeAll();//Removes current panel and all objects on panel
        setContentPane(panel);//Reseting the panel
        panel.setLayout(null);//Sets layout to null, requires custom placements of objects (buttons, labels, textfields .etc)
        setSize(500, 500);//Sets the size of the new window
        playerSelection();//Add player slection components
        setVisible(true);//Sets the panel visible
      }
    }); 
  }
  public void leaderBoard() {//Only appears in Hard mode
    back = new JButton("back");
    back.setBounds(0,0,65,30);
    panel.add(back);
    back.addActionListener(new ActionListener()//Back button action.
                             {    
      public void actionPerformed(ActionEvent e) {
        panel.removeAll();//Removes current panel and all objects on panel
        setContentPane(panel);//Reseting the panel
        panel.setLayout(null);//Sets layout to null, requires custom placements of objects (buttons, labels, textfields .etc)
        setSize(500, 500);//Sets the size of the new window
        playerSelection();//Add player slection components
        setVisible(true);//Sets the panel visible
      }
    }); 
    JLabel board = new JLabel("Leader Board (Hard Mode)");
    board.setFont(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 16));//set Font
    FontMetrics fm = getFontMetrics(new Font(Font.DIALOG, Font.ITALIC-Font.ITALIC, 16));//For getting Length of string
    int strWidth = fm.stringWidth("Leader Board (Hard Mode)");
    board.setBounds(getWidth()/2-strWidth/2,10,300,60);
    panel.add(board);
    textArea = new JTextArea(5, 20);//Set textArea and tempoary size
    textArea.setEditable(false);
    JScrollPane nameScrollPane = new JScrollPane(textArea);
    nameScrollPane.setBounds(0,60,150,200);
    panel.add(nameScrollPane);
    textArea1 = new JTextArea(5, 20);//Set textArea and tempoary size
    textArea1.setEditable(false);
    JScrollPane scoreScrollPane = new JScrollPane(textArea1);
    scoreScrollPane.setBounds(150,60,150,200);
    panel.add(scoreScrollPane);
    int smallestIndex=0;
    for(int i=0;i<numOfAccount-1;i++){
      smallestIndex=i;
      for(int j=i+1;j<numOfAccount;j++){
        if(accounts.get(j).getScore()<accounts.get(smallestIndex).getScore()){
          smallestIndex=j;
        }
      }
      int tempScore=accounts.get(i).getScore();
      int tempAge=accounts.get(i).getAge();
      int tempLvl=accounts.get(i).getLevel();
      int tempExp=accounts.get(i).getExp();
      String tempFName=accounts.get(i).getFName();
      String tempLName=accounts.get(i).getLName();
      String tempUser=accounts.get(i).getUser();
      String tempPass=accounts.get(i).getPass();
      accounts.get(i).setScore(accounts.get(smallestIndex).getScore());
      accounts.get(i).setAge(accounts.get(smallestIndex).getAge());
      accounts.get(i).setLevel(accounts.get(smallestIndex).getLevel());
      accounts.get(i).setExp(accounts.get(smallestIndex).getExp());
      accounts.get(i).setFname(accounts.get(smallestIndex).getFName());
      accounts.get(i).setLname(accounts.get(smallestIndex).getLName());
      accounts.get(i).setUser(accounts.get(smallestIndex).getUser());
      accounts.get(i).setPass(accounts.get(smallestIndex).getPass());
      accounts.get(smallestIndex).setScore(tempScore);
      accounts.get(smallestIndex).setAge(tempAge);
      accounts.get(smallestIndex).setLevel(tempLvl);
      accounts.get(smallestIndex).setExp(tempExp);
      accounts.get(smallestIndex).setFname(tempFName);
      accounts.get(smallestIndex).setLname(tempLName);
      accounts.get(smallestIndex).setUser(tempUser);
      accounts.get(smallestIndex).setPass(tempPass);
    }
    try{
      PrintWriter writer = new PrintWriter("Account.txt");//Rewrites account file
      writer.println("FirstName LastName Age Score Username Password Level Exp");
      for(int i=0;i<numOfAccount;i++){
        writer.println(accounts.get(i).toString());//Displays all attributes
      }
      writer.close();
    } catch (IOException eio) {
    }
    textArea.append("Player:\n\n");
    for(int i=0;i<numOfAccount;i++){
      textArea.append(accounts.get(i).getUser()+"\n");
    }
    textArea1.append("High Score:\n\n");
    for(int i=0;i<numOfAccount;i++){
      textArea1.append(accounts.get(i).getScore()+"\n");
    }
  }
  public static void main(String[]args)
  {
    new NumberGuesser();
  }
}