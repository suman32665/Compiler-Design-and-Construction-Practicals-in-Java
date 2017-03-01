import java.util.Scanner;

/**
 * Created by suman maharjan on 26/08/2016.
 * RE=((a|b)*(c|d)*)+ |(ab*c*d)")
 * Checks if the string is accepted or rejected by the above RE
 * Using transition table of DFA
 */
public class Program3 {
    public static void main(String[] args) {
        String states[];
        String outputForA[];
        String outputForB[];
        String outputForC[];
        String outputForD[];
        String acceptingState[];
        String text;

        Scanner input=new Scanner(System.in);

        states=new String[]{"q0", "q1", "q2"};  //initializing state array
        outputForA=new String[]{"q1", "q1", "q0"};  //initializing arrays for inputs
        outputForB=new String[]{"q1", "q1", "q0"};
        outputForC=new String[]{"q2", "q0", "q2"};
        outputForD=new String[]{"q2", "q0", "q2"};
        acceptingState=new String[]{"q0", "q1", "q2"};  //initializing array with accepting state

        System.out.println("Enter Input for RE= ((a|b)*(c|d)*)+ |(ab*c*d)");
        do {
            boolean invalidInput=false;
            text = input.nextLine();
            String currentState = "q0";     //initialize with q0
            int index = 0;
            for (int i = 0; i < text.length(); i++) {   //loop until all characters of text is read
                for (int j = 0; j < states.length; j++) {   //loop until index of current state is found
                    if (states[j].matches(currentState)) {
                        index = j;
                        break;
                    }
                }
                if (text.charAt(i) == 'a') {    //condition for input a
                    currentState = outputForA[index];
                }else{
                    if (text.charAt(i) == 'b') {    //condition for input b
                        currentState = outputForB[index];
                    }else{
                        if (text.charAt(i) == 'c') {    //condition for input c
                            currentState = outputForC[index];
                        }else{
                            if (text.charAt(i) == 'd') { //condition for input d
                                currentState = outputForD[index];
                            }else{
                                System.out.println("Invalid input"); //if input is invalid
                                invalidInput=true;
                            }
                        }
                    }
                }
            }
            boolean accept=false;
            for (int i=0; i<acceptingState.length; i++){    // check if currentstate is one of accepting state
                if (acceptingState[i].matches(currentState) && !invalidInput){
                    accept=true;
                    break;
                }
            }
            if (accept){        //if final state is among accepting state
                System.out.println("The String is accepted!!!");
            }else{
                System.out.println("The String is rejected!!!");
            }
        }while (!text.matches(""));
    }
}
