import java.io.BufferedReader;
        import java.io.FileNotFoundException;
        import java.io.FileReader;
        import java.io.IOException;
        import java.util.Scanner;

/**
 * Created by suman maharjan on 11/08/2016.
 * Program that removes recognizes keywords and tha identifiers.
 */
public class KeywordsAndIdentifiers {
    public static void main(String[] args) {
        String text;

        Scanner input = new Scanner(System.in);
        System.out.println("Enter text:");
        text = input.nextLine();            //input keywords, identifier
        String[]  textSplit=text.split(" ");    //splits text by space

        String fileName = "D:\\whitespaces\\src\\keywords.txt";     //name of file to open that stores keywords
        String line = null;         //initializing null value
        boolean value;          //boolean to differentiate keywords or others
        try {
            for (int i = 0; i < textSplit.length; i++) {    //loop continues to check all splitted input text
                value=true;                                 //initializing the boolean value to true
                FileReader fileReader = new FileReader(fileName);   //read text files in default coding
                BufferedReader bufferedReader = new BufferedReader(fileReader); //wraps text file in bufferedreader
                while ((line = bufferedReader.readLine()) != null) {    //reads all strings line by line
                    if (line.equals(textSplit[i])) {                    //compares splitted string with each keywords stored in text file.
                        System.out.println(textSplit[i]+"=keyword");    //prints it is keyword
                        value = false;                                  //boolean value is set false(no need to check for identifier constraints)
                        break;
                    }
                }
                if (value){                 //checks if boolean value is true(i.e. splitted string is not keyword)
                    if (textSplit[i].charAt(0)>='a' && textSplit[i].charAt(0)<='z' || textSplit[i].charAt(0)>='A' &&
                            textSplit[i].charAt(0)<='Z' || textSplit[i].charAt(0)=='_'){    //checks starting character a-z, A-Z and "_"
                        if (textSplit[i].matches("^[a-z0-9A-Z[_]]+")){        //checks contents of string if it contains other than a-z, A-Z, 0-9,"_"
                            System.out.println(textSplit[i] + "=identifier");   //prints it is an identifier
                        }else{
                            System.out.println(textSplit[i] + "=Neither keyword nor identifier");
                        }
                    }else{
                        System.out.println(textSplit[i] + "=Neither keyword nor identifier");
                    }
                }
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex){
            System.out.println("Error reading file '" + fileName + "'");
        }
    }
}