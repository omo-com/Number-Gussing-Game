/*
 * Michael & Oliver
 * 12/01/17
 * Achievement class
 * Allows account to recieve achievements
 */
public class Achievement{
  private String achieveName;//Creates achievement name
  private int experience;//Creates experience gained if achievement is recieved
  public Achievement(String nam, int ex){
    //Constructs achievement from name, and experience
    achieveName=nam;
    experience=ex;
  }
  public boolean setAchieve(String name){
    //Sets Achievement name
    achieveName=name;
    return true;
  }
  public boolean setExp(int ex){
    //Sets experience for achievement
    experience=ex;
    return true;
  }
  public String getName(){
    //Returns achievement name
    return achieveName;
  }
  public int getExp(){
    //Returns achievement exp
    return experience;
  }
  public String toString(){
    //Returns attributes
    return getName()+" "+getExp();
  }
}