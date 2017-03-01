import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//Removes left recursion from the given grammar
/**
 * Created by suman maharjan on 26/11/2016.
 */
public class Lab6 {
//    int lengthofnew=0;
    public String separateLeft(String line)     //returns left part of input
    {
        String array[] = line.split("=");
        return array[0];
    }

    public String separateRight(String line)        //returns right part of input
    {
        String array[] = line.split("=");
        return array[1];
    }

    public boolean checkRecursion(String left, String right)    //checks if the input consists left recursion
    {
        if (left.charAt(0) == right.charAt(0)) {
//            lengthofnew=lengthofnew+2;
            return true;
        } else {
//            lengthofnew++;
            return false;
        }
    }

    public int countAlpha(String right[], String left)      //counts number that causes left recursion
    {
        int count = 0;
        for (int i = 0; i < right.length; i++) {
            if (right[i].contains(left)) {
                count++;
            }
        }
        return count;
    }

    public String step1(String beta_values[], String left)    //calculate first step and prints it i.e. A=BA'
    {
        String right = new String();
        for (int i = 0; i < beta_values.length; i++) {
            right = right + beta_values[i] + left + "'" + "/";
        }
        right = right.substring(0, right.length() - 1);
        String string=left + "=" + right;
        return string;
//        System.out.println(left + "=" + right);

    }

    public String step2(String alpha_values[], String left)   //calculate second step and prints it i.e. A'=αA'/e
    {
        String right = new String();
        for (int i = 0; i < alpha_values.length; i++) {
            alpha_values[i] = alpha_values[i].replace(left, "");
            right = right + alpha_values[i] + left + "'/";
        }
        right = right + "e";
        String string=left + "'=" + right;
        return string;
//        System.out.println(left + "'=" + right);

    }
    public boolean check_variable(String variables[], String right)
    {
        boolean flag=true;
        for (int i=0; i<variables.length;i++){
            if (variables[i].charAt(0)==right.charAt(0)){
                flag=false;
                break;
            }
        }
        return flag;
    }
    public int find_index(String variables[], String s)
    {
        for (int i=0; i<variables.length;i++){
            if (s.matches(variables[i])){
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Lab6 leftrecursion = new Lab6();
        String fileName = "D:\\whitespaces\\src\\lab6.txt";
        String line = null;
        String left = new String();
        String right = new String();
        String[] no_leftRecursion=new String[5];
        int index=0;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {          //read inputs from the text file(each line)
                left = leftrecursion.separateLeft(line);              //splits left and right part of string
                right = leftrecursion.separateRight(line);
                if (leftrecursion.checkRecursion(left, right)) {
                    System.out.println(line + " is a left recursion grammar");
                    String[] splittedRight = right.split("/");        //right part is splitted by /
                    int alpha_count = leftrecursion.countAlpha(splittedRight, left);
                    int beta_count = splittedRight.length - alpha_count;
                    String[] alpha_values = new String[alpha_count];
                    String[] beta_values = new String[beta_count];
                    for (int i = 0; i < alpha_count; i++) {
                        alpha_values[i] = splittedRight[i];
                    }
                    for (int i = 0; i < beta_count; i++) {
                        beta_values[i] = splittedRight[i + alpha_count];
                    }
                    no_leftRecursion[index++]=leftrecursion.step1(beta_values, left);
                    no_leftRecursion[index++]=leftrecursion.step2(alpha_values, left);
                } else {
                    System.out.println(line + " is not a left recursion grammar");
                     no_leftRecursion[index++]=line;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nAfter Eliminating left recursion");
        for (int i=0; i<no_leftRecursion.length;i++){
            System.out.println(no_leftRecursion[i]);
        }
//      System.out.println(leftrecursion.lengthofnew);

        String[] first=new String[no_leftRecursion.length];
        String[] variables=new String[first.length];

        for (int i=0; i<variables.length;i++){
            variables[i]=leftrecursion.separateLeft(no_leftRecursion[i]);
        }

        for (int i=first.length-1; i>=0;i--){
            first[i]="";
            right=leftrecursion.separateRight(no_leftRecursion[i]);
            String split_right[]=right.split("/");
            for (int j=0; j<split_right.length;j++){
                if (leftrecursion.check_variable(variables, split_right[j])){
                    if (split_right[j].matches("id")){
                        first[i]=first[i]+split_right[j]+",";
                    }else{
                        first[i]=first[i]+Character.toString(split_right[j].charAt(0))+",";
                    }
                }else{
                    first[i]=first[i]+first[leftrecursion.find_index(variables, Character.toString(split_right[j].charAt(0)))]+",";
                }
            }
            first[i]=first[i].substring(0,first[i].length()-1);
        }
        System.out.println("\nFIRST");
        for (int i=0; i<first.length;i++){
            System.out.println("First("+variables[i]+")={"+first[i]+"}");
        }
        String follow[]=new String[first.length];
        for (int i=0; i<follow.length;i++){
            if (i==0){
                follow[i]="$,";
            }else{
                follow[i]="";
            }
            for (int j=0; j<no_leftRecursion.length;j++){
                right=leftrecursion.separateRight(no_leftRecursion[j]);
                if (right.contains(variables[i])){
                    if (variables[i].length()==2){
                        if (right.indexOf(variables[i].charAt(1))==right.length()-1){
                            follow[i]=follow[i]+follow[j]+",";
                        }
                    }else{
                        if (variables[i].length()==1){
                            if (right.charAt(right.indexOf(variables[i].charAt(0))+1)!=('/' & 39 )){
                                if (leftrecursion.check_variable(variables, Character.toString(right.charAt(right.indexOf(variables[i].charAt(0))+1)))){
                                    follow[i]=follow[i]+Character.toString(right.charAt(right.indexOf(variables[i].charAt(0))+1))+",";
                                }else{
                                    if (right.charAt(right.indexOf(variables[i].charAt(0))+2)==39){
                                        String string=right.charAt(right.indexOf(variables[i].charAt(0))+1)+"'";
                                        if (!follow[i].contains(first[leftrecursion.find_index(variables, string)]))
                                        follow[i]=follow[i]+first[leftrecursion.find_index(variables, string)]+","+follow[j]+",";
                                    }else{
                                        follow[i]=follow[i]+first[leftrecursion.find_index(variables, Character.toString(right.charAt(right.indexOf(variables[i].charAt(0))+1)))]+",";
                                    }
                                }
                            }
                        }
                    }
                }
            }
            follow[i]=follow[i].replace(",e", "");
            follow[i]=follow[i].substring(0,follow[i].length()-1);
        }
        System.out.println("\nFOLLOW");
        for (int i=0; i<follow.length;i++){
            System.out.println("Follow("+variables[i]+")={"+follow[i]+"}");
        }
    }
}
