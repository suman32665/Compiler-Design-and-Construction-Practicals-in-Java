import java.util.Scanner;

/**
 * Created by suman maharjan on 26/08/2016.
 * RE=((ab*c)|(def)+ |(a*d+e))+")
 * Checks if the string is accepted or rejected by the above RE
 * Using transition table of DFA
 */
public class Program2 {
    public static void main(String[] args) {
        String states[];
        String outputForA[];
        String outputForB[];
        String outputForC[];
        String outputForD[];
        String outputForE[];
        String outputForF[];
        String acceptingState[];
        String text;

        Scanner input=new Scanner(System.in);

        states=new String[]{"q0", "q1", "q2", "q3", "q4", "q5", "q6", "q7", "q8", "q9", "q10"}; //initialing state array
        outputForA=new String[]{"q1", "q8", "q10", "q1", "q10", "q1", "q1", "q10", "q8", "q1", "q10"};  //initiatilizing array for each input
        outputForB=new String[]{"q10", "q2", "q2", "q10", "q10", "q10", "q10", "q10", "q10", "q10", "q10"};
        outputForC=new String[]{"q10", "q3", "q3", "q10", "q10", "q10", "q10", "q10", "q10", "q10", "q10"};
        outputForD=new String[]{"q4", "q7", "q10", "q4","q7", "q4", "q4", "q7", "q7", "q4", "q10"};
        outputForE=new String[]{"q10", "q10", "q10", "q10", "q5", "q10", "q10", "q9", "q10", "q10", "q10"};
        outputForF=new String[]{"q10", "q10", "q10", "q10", "q10", "q6", "q10", "q10", "q10", "q10", "q10"};
        acceptingState=new String[]{"q3", "q5", "q6", "q9"}; //initializing accepting states

        System.out.println("Enter Input for RE= ((ab*c)|(def)+ |(a*d+e))+");
        do {
            text = input.nextLine();
            String currentState = "q0";

            int index = 0;
            for (int i = 0; i < text.length(); i++) {   //loop until all character of text is read
                for (int j = 0; j < states.length; j++) {   //loop until index of current state is found
                    if (states[j].matches(currentState)) {
                        index = j;
                        break;
                    }
                }
                if (text.charAt(i) == 'a') {    //condition for input a
                    currentState = outputForA[index];
                }
                if (text.charAt(i) == 'b') {    //condition for input b
                    currentState = outputForB[index];
                }
                if (text.charAt(i) == 'c') {    //condition for input c
                    currentState = outputForC[index];
                }
                if (text.charAt(i) == 'd') {    //condition for input d
                    currentState = outputForD[index];
                }
                if (text.charAt(i) == 'e') {    //condition for input e
                    currentState = outputForE[index];
                }
                if (text.charAt(i) == 'f') {    //condition for input f
                    currentState = outputForF[index];
                }
                //System.out.println("Current State="+currentState);
            }
            boolean accept = false;
            for (int i = 0; i < acceptingState.length; i++) {       //check if final state is among accepting state
                if (acceptingState[i].matches(currentState)) {
                    accept = true;
                }
            }
            if (accept) {
                System.out.println("The String is accepted!!!");
            } else {
                System.out.println("The String is rejected!!!");
            }
        }while (!text.matches(""));
    }
}
