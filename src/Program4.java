import java.util.Scanner;

/**
 * Created by suman maharjan on 30/08/2016.
 * RE=(a|b)* aaa
 * Checks if the string is accepted or rejected by the above RE
 * Using transition table of DFA
 */
public class Program4 {
    public static void main(String[] args) {
        String initialState="q0";
        String acceptingState="q3";
        String text;

        Scanner input=new Scanner(System.in);
        System.out.println("Enter a String for (a|b)* aaa):");
        text=input.nextLine();      //inputs string

        for (int i=0; i<text.length();i++){     //scan all the string characters
            initialState=move(initialState, text.charAt(i));
        }
        if (initialState.matches(acceptingState)){  //checks if the final state is in accepting state
            System.out.println("The String is accepted.");
        }else{
            if (initialState.matches("invalid")){
                System.out.println("The String is invalid.");
            }else{
                System.out.println("The String is rejected.");
            }
        }
    }

    public static String move(String s, char c){        //function that moves currentstate from one to another
        String states[];
        String OutputForA[];
        String OutputForB[];

        states=new String[]{"q0", "q1", "q2", "q3", "q4"};
        OutputForA=new String[]{"q1", "q2", "q3", "q3", "q1"};
        OutputForB=new String[]{"q4", "q0", "q0", "q0", "q0"};
        int index=0;
        for (int i=0; i<states.length;i++){     //finds index of current state
            if (states[i].matches(s)){
                index=i;
            }
        }
        if (c=='a'){            //according to input, value is returned from OutputArray
            return OutputForA[index];
        }else{
            if (c=='b'){
                return  OutputForB[index];
            }else{
                return "invalid";
            }
        }
    }
}
