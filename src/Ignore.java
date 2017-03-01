import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 * Created by suman maharjan on 04/08/2016.
 * Program that removes of whitepsaces of the given input text
 */
public class Ignore {
    public static void main(String[] args) {

        String fileName="D:\\whitespaces\\src\\ignore.txt"; //name of a file to open that contains text
        String line=null;       //initialing null value
        String text="";         //variable to store all text contents
        try{
            FileReader fileReader=new FileReader(fileName);     //read text files in default coding
            BufferedReader bufferedReader=new BufferedReader(fileReader);   //wraps text file in BufferedReader
            while ((line=bufferedReader.readLine())!=null){     //reads line by line
                text+=line;                     //stores the line in text by concatening
            }
            text=text.replaceAll("\\s", "");        //replaces the spaces in text and stores in text
            System.out.println(text);               //prints the text
        }
        catch (FileNotFoundException ex){
            System.out.println("Unable to open file '" +fileName+"'");
        }
        catch (IOException ex){
            System.out.println("Error reading file '"+fileName+"'");
        }
        //System.out.println(text);
    }
}
