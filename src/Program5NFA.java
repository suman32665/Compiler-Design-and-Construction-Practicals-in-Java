import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by suman maharjan on 01/09/2016.
 * RE=(a|b)*abb
 * Checks if the string is accepted or rejected by the given above RE
 * Using transition table of NFA
 */
public class Program5NFA {

    public static void main(String[] args) {
        String[] currentState=new String[20];
        String[] currentStates=new String[20];
        currentState[0]="q0";
        String acceptingState = "q3";
        String text;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter String for (a|b)*abb :");
        text = input.nextLine();
        int length=1;
        for (int i=0; i<text.length();i++){     //scans the input text
            int index=0;
            for (int j=0; j<length;j++){    //checks if the currentState contains "," and split
                String[] initialState=CheckAndSplit(currentState[j]);
                for (int k=0; k<initialState.length;k++){
                    currentStates[index]=initialState[k];   //storing splitted string in currentStates array
                    index++;
                }
            }
            length=index;
            //System.out.println("Current States:");
            for (int j=0; j<index;j++){         //checks outputs for each currentStates for the respective input
                //System.out.println(currentStates[j]);
                currentState[j]=CheckNFA(text.charAt(i), currentStates[j]); //new states are stored in currentState
            }
        }
        boolean accept=false;
        //System.out.println("Current States:");
        for (int i=0; i<length;i++){        //checks if currentState matches accepting state
            //System.out.println(currentState[i]);
            if (currentState[i].matches(acceptingState)){
                accept=true;
                break;
            }
        }
        if (accept){
            System.out.println("The String is accepted.");
        }else{
            System.out.println("The String is rejected.");
        }
    }
    public static String CheckNFA(char input, String initialState)
    {
        String states[];
        String outputForA[];
        String outputForB[];

        states = new String[]{"q0", "q1", "q2", "q3"};
        outputForA = new String[]{"q0,q1", "NULL", "NULL", "NULL"};
        outputForB = new String[]{"q0", "q2", "q3", "NULL"};
        int index=0;
        if (initialState=="NULL"){      //returns null if state value is null
            return "NULL";
        }
        for (int i=0; i<states.length;i++){ //finds index of the given state
            if (states[i].matches(initialState)){
                index=i;
                break;
            }
        }
        if (input=='a'){        //returns output from outputForA array if input is 'a'
            return outputForA[index];
        }else{
            if (input=='b'){    ////returns output from outputForB array if input is 'b'
                return outputForB[index];
            }
        }
        return null;
    }
    public static String[] CheckAndSplit(String output) {   //returns splitted strings in a array
        if (output.contains(",")) {
            String splittedOutput[]=output.split(",");
            return splittedOutput;
        } else {
            String splittedOutput[]= new String[1];
            splittedOutput[0]=output;
            return splittedOutput;
        }
    }
}
