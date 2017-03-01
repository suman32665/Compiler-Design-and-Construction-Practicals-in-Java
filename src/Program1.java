import java.util.Scanner;

/**
 * Created by suman maharjan on 26/08/2016.
 * RE= (00|11)(0|1)* | (0|1)*(00|11)
 * Checks if the string is accepted or rejected by the above RE
 * Using transition table of DFA
 */
public class Program1 {
    public static void main(String[] args) {
        String states[];
        String outputFor0[];
        String outputFor1[];
        String acceptingState[];
        String text;

        Scanner input = new Scanner(System.in);
        states = new String[]{"q0", "q1", "q2", "q3", "q4", "q5", "q6", "q7"}; //initializing States array
        outputFor0 = new String[]{"q1", "q3", "q4", "q3", "q6", "q4", "q6", "q4"};  //initializing array for input 0
        outputFor1 = new String[]{"q2", "q5", "q3", "q3", "q5", "q7", "q5", "q7"};  //initializing array for input 1
        acceptingState = new String[]{"q3", "q6", "q7"};    //initializing array with accepting states

        System.out.println("Enter Input for RE=(00|11)(0|1)* | (0|1)*(00|11)");
        do {
            text = input.nextLine();

            String currentState = "q0";     //initializing initial state
            int index = 0;
            for (int i = 0; i < text.length(); i++) {   //loop until all text character is read
                for (int j = 0; j < states.length; j++) {   //loop until index of currentState is found
                    if (states[j].matches(currentState)) {
                        index = j;
                        break;
                    }
                }
                if (text.charAt(i) == '0') {        //condition for input 0
                    currentState = outputFor0[index];   //change current state for input 0

                } else {
                    if (text.charAt(i)=='1'){   //condition for input 1
                     currentState = outputFor1[index];  //change current state for input 1
                    }else{
                        System.out.println("Invalid Input");    //prints if input is invalid(other than 0 and 1
                        break;
                    }
                }
            }
            boolean accept = false; //initializing boolean value false
            for (int i = 0; i < acceptingState.length; i++) {   //loop to find final state in acceptingState[]
                if (acceptingState[i].matches(currentState)) {
                    accept = true;      //if final state is available in acceptingState change boolean value
                    break;
                }
            }
            if (accept) {
                System.out.println("The String is accepted!!!");
            } else {
                System.out.println("The String is rejected!!!");
            }
        }while (!text.matches(""));     //takes input until user inputs nothing
    }
}
