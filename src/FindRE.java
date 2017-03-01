import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by suman maharjan on 12/09/2016.
 * This program checks if the given is accepted or rejected by the given regular expression in FindRE.txt
 */
public class FindRE {
    public static void main(String[] args) {

        String states[];
        String OutputFor0[];
        String OutputFor1[];
        String acceptingState[];
        String RE;
        String language;
        String text="";

        String fileName="D:\\whitespaces\\src\\FindRE.txt";
        String line=null;
        Scanner input=new Scanner(System.in);
        System.out.println("Enter any String:");
        language=input.nextLine();

        try{
            FileReader fileReader=new FileReader(fileName);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            while ((line=bufferedReader.readLine())!=null){
                text+=line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] splitByEnd=text.split("end");      //splits information of each RE
        for (int i=0; i<splitByEnd.length;i++){
            String[] splitByhash=splitByEnd[i].split("#");//splits RE into states,OutputFor0,OutputFor1,acceptingState
            RE=splitByhash[0];
            states=splitByhash[1].split(",");
            OutputFor0=splitByhash[2].split(",");
            OutputFor1=splitByhash[3].split(",");
            acceptingState=splitByhash[4].split(",");

            String currentState="q0";   //initialising current state
            for (int j=0; j<language.length();j++){ //scanning the input string
                if (language.charAt(j)=='0'){
                    currentState=OutputFor0[indexOfString(currentState, states)];//checking for input value 0
                }else{
                    if (language.charAt(j)=='1'){
                        currentState=OutputFor1[indexOfString(currentState, states)];//checking for output value 0
                    }
                }
            }
            boolean accept=false;
            for (int j=0; j<acceptingState.length;j++){//checking if accepting states contains the final state of input string
                if (acceptingState[j].matches(currentState)){
                    accept=true;
                    break;
                }
            }
            if (accept==true){
                System.out.println("The String is accepted by " + RE);
            }
//            else{
//                System.out.println("The String is rejected by " + RE);
//            }

        }
    }

    public static int indexOfString(String s1, String[] s2)
    {
        int i;
        for (i=0; i<s2.length;i++){
            if (s2[i].matches(s1)){
                break;
            }
        }
        return i;
    }
}
