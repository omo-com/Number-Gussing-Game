/*
 * Michael & Oliver
 * 12/01/17
 * Perks class
 * Allows account to recieve perks
 */
public class Perk{
  private String perkName;//Creates perk name
  private int levelRequired;//Creates minimum level required to receive perk
  private int perkNum;//Creates perk index
  public Perk(String nam, int lvl, int index){
    //Constructs perk from name, required level, and index number
    perkName=nam;
    levelRequired=lvl;
    perkNum=index;
  }
  public boolean setPerkName(String name){
    //Sets Perk name
    perkName=name;
    return true;
  }
  public boolean setLevelRequired(int lvl){
    //Sets required level for perk
    levelRequired=lvl;
    return true;
  }
  public boolean setPerkNum(int num){
    //Sets number index for perk
    perkNum=num;
    return true;
  }
  public String getPerkName(){
    //Returns perk name
    return perkName;
  }
  public int getLevelRequired(){
    //Returns perk level
    return levelRequired;
  }
  public int getPerkNum(){
    //Returns perk index
    return perkNum;
  }
  public String toString(){
    //Returns attributes
    return getPerkName()+" "+getLevelRequired()+" "+getPerkNum();
  }
}