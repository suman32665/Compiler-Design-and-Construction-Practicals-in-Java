import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//Checks if there is left recursion or not of the given input grammar
/**
 * Created by suman maharjan on 19/11/2016.
 */
public class Lab5 {
    public String separateLeft(String line)     //returns left part of input
    {
        String array[]=line.split("=");
        return array[0];
    }
    public String separateRight(String line)        //returns right part of input
    {
        String array[]=line.split("=");
        return array[1];
    }
    public boolean checkRecursion(String left, String right)    //checks if the input consists left recursion
    {
        if (left.charAt(0)== right.charAt(0))
        {
            return true;
        }else{
            return false;
        }
    }
    public int countAlpha(String right[], String left)      //counts number that causes left recursion
    {
        int count=0;
        for (int i=0; i< right.length;i++){
            if (right[i].contains(left)){
                count++;
            }
        }
        return count;
    }
    public void step1(String beta_values[], String left)    //calculate first step and prints it i.e. A=BA'
    {
        String right=new String();
        for (int i=0; i<beta_values.length;i++){
            right=right+beta_values[i]+left+"'"+"/";
        }
        right=right.substring(0,right.length()-1);
        System.out.println(left+"="+right);

    }
    public void step2(String alpha_values[], String left)   //calculate second step and prints it i.e. A'=αA'/e
    {
        String right=new String();
        for (int i=0; i<alpha_values.length;i++){
            alpha_values[i]=alpha_values[i].replace(left, "");
            right=right+alpha_values[i]+left+"'/";
        }
        right=right+"e";
        System.out.println(left+"'="+right);

    }
    public static void main(String[] args) {
        Lab5 leftrecursion=new Lab5();
        String fileName="D:\\whitespaces\\src\\leftrecursion.txt";
        String line=null;
        String left=new String();
        String right=new String();

        try {
            FileReader fileReader=new FileReader(fileName);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
                while ((line= bufferedReader.readLine()) != null){          //read inputs from the text file(each line)
                    left=leftrecursion.separateLeft(line);              //splits left and right part of string
                    right=leftrecursion.separateRight(line);
                    if (leftrecursion.checkRecursion(left,right)){
                        System.out.println(line+" is a left recursion grammar");
                        String[] splittedRight=right.split("/");        //right part is splitted by /
                        int alpha_count=leftrecursion.countAlpha(splittedRight,left);
                        int beta_count=splittedRight.length-alpha_count;
                        String[] alpha_values=new String[alpha_count];
                        String[] beta_values=new String[beta_count];
                        for (int i=0; i<alpha_count;i++){
                            alpha_values[i]=splittedRight[i];
                        }
                        for (int i=0; i<beta_count;i++)
                        {
                            beta_values[i]=splittedRight[i+alpha_count];
                        }
                        leftrecursion.step1(beta_values, left);
                        leftrecursion.step2(alpha_values, left);
                        System.out.println("\n");
                    }else{
                        System.out.println(line+" is not a left recursion grammar");
                    }
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
