/*
 * Michael & Oliver
 * 12/01/17
 * Account class
 * Allows user to manipulate their account
 */
public class Account{
  private String firstName;//Creates first name
  private String lastName;//Creates last name
  private int age;//Creates user age
  private int score;//Creates user score
  private String username;//Creates username
  private String password;//Creates password
  private int level;//Creates user level
  private int experience;//Creates experience counter
  private Perk[] perk=new Perk[5];//Creates account perks
  private Achievement[] achievement=new Achievement[10];//Creates account achievements
  private Item[] item=new Item[1];//Creates account perks
  public Account(String fn, String ln, int ag, int scor, String user, String pass,int lvl, int exp){
    //Constructs account from name, age, score, username, and password
    firstName=fn;
    lastName=ln;
    age=ag;
    score=scor;
    username=user;
    password=pass;
    level=lvl;
    experience=exp;
  }
  public boolean setScore(int scor){
    //Sets account score
    score=scor;
    return true;
  }
  public boolean setUser(String user){
    //Sets account username
    username=user;
    return true;
  }
  public boolean setPass(String pass){
    //Sets account password
    password=pass;
    return true;
  }
  public boolean setLevel(int lvl){
    //Sets account level
    level=lvl;
    return true;
  }
  public boolean setExp(int exp){
    //Sets account experience
    experience=exp;
    return true;
  }
  public boolean setFname(String fname){
    //Sets user's first name
    firstName=fname;
    return true;
  }
  public boolean setLname(String lname){
    //Sets user's last name
    lastName=lname;
    return true;
  }
  
  public boolean addPerks(Perk per){
    //Adds account perks
    int x=0;
    if(perk[x]!=null){//Runs if there is no perk
      x++;
    }
    perk[x]= per;//Sets perk
    return true;//Returns true if successful
  }   
  
  public boolean addAchieve(Achievement achieve){
    //Adds account Achievements
    int x=0;
    if(achievement[x]!=null){//Runs if there is no achievement
      x++;
    }
    achievement[x]= achieve;//Sets achievement
    return true;//Returns true if successful
  }   
  
  public boolean addItem(String nam, int func, int x){
    //Sets account item
    if(item[x]==null){//Runs if the type doesn't exist
      item[x] = new Item(nam, func);//Creates item
      return true;//Returns true if successful
    }
    else{
      return false;//Reutrns false if unsuccessful
    }
  }
  public boolean removeItem(int x){
    //Removes account item
    if(item[x]!=null){//Run if item exists
      item[x]=null;//Destroys item
      return true;//Returns true if successful
    }
    else{
      return true;//Returns false if unsuccessful
    }
  }
  public boolean setAge(int ag){
    //Sets user's age
    age=ag;
    return true;
  }
  public int getScore(){
    //Returns account score
    return score;
  }
  public int getExp(){
    //Returns account exp
    return experience;
  }
  public String getUser(){
    //Returns account username
    return username;
  }
  public String getPass(){
    //Returns account password
    return password;
  }
  public String getFName(){
    //Returns account first name
    return firstName;
  }
  public String getLName(){
    //Returns account last name
    return lastName;
  }
  public int getAge(){
    //Returns account age
    return age;
  }
  public int getLevel(){
    //Returns account level
    return level;
  }
  public Perk[] getPerk(){
    //Returns account perks
    return perk;
  }
  public Achievement[] getAchieve(){
    //Returns account achievements
    return achievement;
  }
  public String toString(){
    //Returns attributes
    return getFName()+" "+getLName()+" "+getAge()+" "+getScore()+" "+getUser()+" "+getPass()+" "+getLevel()+" "+getExp();
  }
}