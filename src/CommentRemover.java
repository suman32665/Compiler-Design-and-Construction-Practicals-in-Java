import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by suman maharjan on 11/08/2016.
 * This program removes the comment of the given input program.
 */
public class CommentRemover {
    public static void main(String[] args) {
        String fileName="D:\\whitespaces\\src\\prgram_with_comments.txt";       //stores file name
        String line=null;                                                   //initializing null value
        boolean found=false;        //boolean value for ignoring multiple line comments
       try {
            FileReader fileReader = new FileReader(fileName);       //read text files in default coding
            BufferedReader bufferedReader = new BufferedReader(fileReader); //wraps file reader in bufferedreader
            while ((line = bufferedReader.readLine()) != null) {            //reads all the lines of text file
                if (line.contains("//") && line.contains("/*") && line.indexOf("//")<line.indexOf("/*")){   //check if line contains "//" and "/*" AND index of "//" is smaller than "/*"
                    String[] lineSplit=line.split("//");    //splits line by "//"
                    System.out.println(lineSplit[0]);       //prints contents before "//"
                }else{
                    if (line.contains("/*")){   //check if line contains "/*"
                        String lineSplit1=line.substring(0, line.indexOf("/*"));    //string from 0 to "/*" is stored
                        System.out.println(lineSplit1);
                        found=true;                             //boolean value is set true(has found"/*")
                    }
                    if (line.contains("*/")){   //check if line contains "*/"
                        String lineSplit1=line.substring(line.indexOf("*/")+2, line.length());  //string from "*/" to endofline is stored. +2 to exclude "*/".
                        System.out.println(lineSplit1);
                        found=false;        //boolean value is set false(has found "*/")
                        continue;
                    }
                    if (line.contains("//") && !found) {    //checks if "//" is found and boolean value is false
                        String[] lineSplit=line.split("//", 2);    //splits string by "//" into 2
                        System.out.println(lineSplit[0]);       //prints first one only
                        continue;
                    }
                    if (found){         //skips the line until the boolean is false
                        continue;
                    }else{
                        System.out.println(line);
                    }
                }

            }
        }
        catch (FileNotFoundException ex){
            System.out.println("Unable to open '"+fileName+"'");
        }
        catch (IOException ex){
            System.out.println("Error reading file '"+fileName+"'");
        }

    }

}
