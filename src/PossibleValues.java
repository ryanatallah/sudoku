import java.util.*;
public class PossibleValues {
	public ArrayList<Integer> numSet;
    //declare initial numSet arraylist
    public PossibleValues(){
        ArrayList<Integer> tempSet=new ArrayList<Integer>();
        for (int i=1; i<10; i++){
            tempSet.add(i);
        }
        numSet=tempSet;
    }
    //remove a from the list of possible values
    //@param a the number to be removed from list of possibilities
    public void removeNum(int a){
        int index=numSet.indexOf(a);
        if (index>=0){
            numSet.remove(index);
        }
    }
    //@return the value at index b
    //@param b the index of the value to be returned
    public int getNum(int b){
        return numSet.get(b);
    }
    //@return number of possible values
    public int getLength(){
        return numSet.size();
    }
    //make the only possible value for the space be c
    //@param c the only possible value for space
    public void setNum(int c){
        numSet.clear();
        numSet.add(c);
    }
    public void printSet(){
    	System.out.println(numSet);
    }
}
