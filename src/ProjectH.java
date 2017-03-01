
import java.util.*;

/**
 * Created by suman maharjan on 20/12/2016.
 * The input to the program is a context­free grammar and a string. Outputs
 reverse of the right most derivation of the string from the given grammar.
 a. Computation of LR (1) Sets of Items.
 b. Computation of Canonical LR (1) parsing table.
 c. Simulation of Canonical LR parser on the given string.
 Before you start, please take into account following issues:
 ● The theoretical knowledge is very necessary.
 ● Motivate your choice in your report and explain any transformation
 you had to apply to your grammar to make it fit the parser’s
 constraints
 ● You have to demonstrate each module clearly while you are submitting
 your report.
 ● You can choose the language of your own interest however you have to
 justify the motivation you have while using that particular language.
 ● Interactive demonstration of your project will increase your chance to
 obtain higher marks.
 *
 */
public class ProjectH {
    public static void add_list(List list1,List list2, List terminals){ //function adds additional grammar of symbol that after dot

        for (int i=0; i<list1.size();i++){
            String string=list1.get(i).toString();
            String symbolAfterDot=check_symbolAfterDot(string.substring(string.indexOf(".")+1,string.indexOf(".")+2 ));
            List symbolAfterDotProductions=find_production(list2,symbolAfterDot);
            //   System.out.println(symbolAfterDotProductions);
            for (Object symbolafterdotproduction:symbolAfterDotProductions){
                String split[]=symbolafterdotproduction.toString().split(",");
                String split1[]=list1.get(i).toString().split(",");

                //split[0]=split[0]+","+split1[1];
                if (split1[0].indexOf(".")+2==split1[0].length()){
                    split[0]=split[0]+","+split1[1];
                }else{
                    if (terminals.contains(split1[0].substring(split1[0].indexOf(".")+2,split1[0].indexOf(".")+3))){
                        split[0]=split[0]+","+split1[0].substring(split1[0].indexOf(".")+2,split1[0].indexOf(".")+3);
                    }
                }

                if (split[0].substring(0,1).matches(symbolAfterDot) && !check_listcontents(list1, split[0])){
                    list1.add(split[0]);
                }
            }
        }
    }
    public static List<String> find_production(List productions, String symbolAfterDot) //it find productions of symbol that is after dot
    {
        List symbolAfterDot_productions=new ArrayList();
        for (Object production: productions){
            String split[]=production.toString().split("->");
            if (split[0].matches(symbolAfterDot)){
                symbolAfterDot_productions.add(production);
            }
        }
        return symbolAfterDot_productions;
    }
    public static String check_symbolAfterDot(String symbolAfterDot)//it checks the symbol that is after dot
    {
        if (symbolAfterDot.matches("i")){
            return "id";
        }else{
            if (symbolAfterDot.matches(":")){
                return ":=";
            }else{
                return symbolAfterDot;
            }
        }
    }
    public static void display_closure(List I)//displays the closure i.e. I0, I1, I2.....
    {
        int j=0;
        for (Object i:I){
            j++;
            System.out.println(j+". ["+i.toString()+"],");
        }
    }
    public static boolean check_listcontents(List list, String string)//checks if the list contains the given string
    {
        for (Object object:list){
            if (object.toString().contains(string)){
                return true;
            }
        }
        return false;
    }
    public static void find_ListOfSymbolAfterDot(List I, List listOf_symbolAfterDot)//it finds the symbols that is after dot in a list
    {
        listOf_symbolAfterDot.clear();
        for (int i=0; i<I.size();i++){
            int index=I.get(i).toString().indexOf(".");
            if (I.get(i).toString().charAt(index+1)!=',') {
                listOf_symbolAfterDot.add(check_symbolAfterDot( I.get(i).toString().substring(index+1, index+2)));
            }else{
                I.remove(i);
                i--;
            }
        }
    }
    public static int countRHSContent(List terminals, List nonterminals, String rhs)//it counts the terminals and nonterminals in the given string
    {
        int count=0;
        for (int i=0; i<terminals.size();i++){
            if (rhs.contains(terminals.get(i).toString())){
                count++;
            }
        }
        for (int i=0; i<nonterminals.size();i++){
            if (rhs.contains(nonterminals.get(i).toString())){
                count++;
            }
        }
        return count;
    }
    public static void displayParseTable(String gotoTable[][], String actionTable[][], List terminals, List non_terminals)//displays the parsing table
    {
        System.out.println("\n");
        System.out.println("States\t\t\tAction\t\t\t\t\t\t\tGoto");
        System.out.println("\t\t "+terminals.get(0)+"\t\t"+terminals.get(1)+"\t\t"+terminals.get(2)+"\t\t"+terminals.get(3)+"\t\t"+terminals.get(4)+"\t\t"+non_terminals.get(0)+"\t\t"+non_terminals.get(1)+"\t\t"+non_terminals.get(2)+"\t\t"+non_terminals.get(3));
        for (int i=0; i<gotoTable.length;i++){
            String s= " "+i+"\t\t ";
            for (int j=0; j<terminals.size();j++){
                s=s+actionTable[i][j]+"      ";
            }
            for (int j=0; j<non_terminals.size();j++){
                s=s+gotoTable[i][j]+"       ";
            }
            System.out.println(s);
        }
    }
    public static List find_transition(List transition, String state)//find the transitions of a given state
    {
        List list=new ArrayList();
        for (int i=0; i<transition.size();i++){
            String[] split=transition.get(i).toString().split("->");
            if (split[0].matches(state)){
                list.add(transition.get(i));
            }
        }
        return list;
    }

    public static String findNextState(String string)//finds rhs state of a given transition
    {
        String[] split=string.split("->");
        return split[1];
    }
    public static boolean checkDotAtLast(List I)//check if the list contains grammar with dot at last
    {
        for (int i=0; i<I.size();i++){
            String[] split=I.get(i).toString().split(",");
            if (split[0].endsWith(".")){
                return true;
            }
        }
        return false;
    }
    public static List findDotAtLast(List I)//finds list of grammar with dot at last
    {
        List list=new ArrayList();
        for (int i=0; i<I.size();i++){
            String[] split=I.get(i).toString().split(",");
            if (split[0].endsWith(".")){
                list.add(I.get(i));
            }
        }
        return list;
    }
    public static void main(String[] args) {
        String start_symbol = "S'";
        List terminals = new ArrayList();
        terminals.add("$");
        terminals.add(";");
        terminals.add("id");
        terminals.add(":=");
        terminals.add("+");

        List non_terminals = new ArrayList();
        non_terminals.add("S'");
        non_terminals.add("S");
        non_terminals.add("A");
        non_terminals.add("E");

        List productions = new ArrayList();
        productions.add("S'->S");
        productions.add("S->S;A");
        productions.add("S->A");
        productions.add("A->E");
        productions.add("A->id:=E");
        productions.add("E->E+id");
        productions.add("E->id");

        List firstSet_lhs = new ArrayList();
        List firstSet_rhs = new ArrayList();

        Collections.reverse(productions);
        Iterator iterator = productions.iterator();

        while (iterator.hasNext()) {
            String grammar = (String) iterator.next();
            String split[] = grammar.split("->");
            firstSet_lhs.add(split[0]);


            for (Object Terminal : terminals) {
                String terminal = (String) Terminal;
                if (split[1].contains(terminal) && split[1].indexOf(terminal) == 0) {
                    firstSet_rhs.add(terminal);
                    break;
                }
            }
            for (Object Non_Terminal : non_terminals) {
                String non_terminal = (String) Non_Terminal;
                if (split[1].contains(non_terminal) && split[1].indexOf(non_terminal) == 0) {
                    firstSet_rhs.add(firstSet_rhs.get(firstSet_rhs.indexOf(non_terminal) + 1));
                    break;
                }
            }
        }
        Collections.reverse(productions);
        Collections.reverse(firstSet_lhs);
        Collections.reverse(firstSet_rhs);
        System.out.println("Productions\n" + productions + "\n");
        System.out.println("First" + firstSet_lhs + " -> " + firstSet_rhs);
        System.out.println("\n");

        List I0 = new ArrayList();

        String production = (String) productions.get(0);
        String split[] = production.split("->");
        String production_lhs = split[0];
        String production_rhs = split[1];

        I0.add(production_lhs + "->." + production_rhs + "," + "$");

        List listOf_symbolAfterDot = new ArrayList();
        for (int i = 0; i < I0.size(); i++) { //calculating state I0
            String lookAhead = "";
            String split1[] = I0.get(i).toString().split("->");
            String split2[] = split1[1].split(",");
            String symbolAfterDot = split2[0].substring(split2[0].indexOf(".") + 1, split2[0].indexOf(".") + 2);

            listOf_symbolAfterDot.add(check_symbolAfterDot(symbolAfterDot));

            List symbolAfterDotProductions = find_production(productions, symbolAfterDot);
            for (Object symbolafterdotproductions : symbolAfterDotProductions) {
                split = symbolafterdotproductions.toString().split("->");
                production_lhs = split[0];
                production_rhs = split[1];
                if (split2[0].indexOf(".") + 2 == split2[0].length()) {
                    lookAhead = split2[1];
                } else {
                    if (terminals.contains(split2[0].substring(split2[0].indexOf(".") + 2, split2[0].indexOf(".") + 3))) {
                        lookAhead = split2[0].substring(split2[0].indexOf(".") + 2, split2[0].indexOf(".") + 3);
                    }
                }
                //System.out.println(production_lhs+"->."+production_rhs+","+lookAhead);
                if (I0.contains(production_lhs + "->." + production_rhs + "," + lookAhead)) {
                    break;
                } else {
                    I0.add(production_lhs + "->." + production_rhs + "," + lookAhead);
                }
            }
        }
        System.out.println("I0=CLOSURE({[S'->.S,$]})");
        display_closure(I0);

        //System.out.println(listOf_symbolAfterDot);
        List I1 = new ArrayList();
        List I2 = new ArrayList();
        List I3 = new ArrayList();
        List I4 = new ArrayList();
        List I = new ArrayList();
        for (int i = 0; i < listOf_symbolAfterDot.size(); i++) { //calculating state I1, I2, I3, I4
            if (listOf_symbolAfterDot.get(i).equals("S")) {
                // System.out.println("suman");
                String string = (String) I0.get(i);
                string = string.replace(".S", "S.");
                I1.add(string);
                I.add(string);
            }
            if (listOf_symbolAfterDot.get(i).equals("A")) {
                String string = (String) I0.get(i);
                string = string.replace(".A", "A.");
                I2.add(string);
                I.add(string);
            }
            if (listOf_symbolAfterDot.get(i).equals("E")) {
                String string = (String) I0.get(i);
                string = string.replace(".E", "E.");
                I3.add(string);
                I.add(string);
            }
            if (listOf_symbolAfterDot.get(i).equals("id")) {
                String string = (String) I0.get(i);
                string = string.replaceAll(".id", "id.");
                I4.add(string);
                I.add(string);
            }
        }
        System.out.println("\nI1");
        display_closure(I1);
        System.out.println("\nI2");
        display_closure(I2);
        System.out.println("\nI3");
        display_closure(I3);
        System.out.println("\nI4");
        display_closure(I4);
        find_ListOfSymbolAfterDot(I, listOf_symbolAfterDot);
        //System.out.println(listOf_symbolAfterDot);
        // System.out.println(I);
        List I5 = new ArrayList();
        List I6 = new ArrayList();
        List I7 = new ArrayList();

        for (int i = 0; i < listOf_symbolAfterDot.size(); i++) {//calcuting state I5, I6, I7
            if (listOf_symbolAfterDot.get(i).equals(";")) {
                String string = I.get(i).toString();
                string = string.replace(".;", ";.");
                I5.add(string);
            }
            if (listOf_symbolAfterDot.get(i).equals("+")) {
                String string = I.get(i).toString();
                string = string.replace(".+", "+.");
                I6.add(string);
            }
            if (listOf_symbolAfterDot.get(i).equals(":=")) {
                String string = I.get(i).toString();
                string = string.replace(".:=", ":=.");
                I7.add(string);
            }

        }
        add_list(I5, I0, terminals);
        add_list(I6, I0, terminals);
        add_list(I7, I0, terminals);

        System.out.println("\nI5");
        display_closure(I5);
        System.out.println("\nI6");
        display_closure(I6);
        System.out.println("\nI7");
        display_closure(I7);

        List I8 = new ArrayList();
        List I9 = new ArrayList();
        List I10 = new ArrayList();
        List I11 = new ArrayList();
        find_ListOfSymbolAfterDot(I5, listOf_symbolAfterDot);
//        System.out.println(listOf_symbolAfterDot);
        //calculating state I8, I9,
        for (int i = 0; i < listOf_symbolAfterDot.size(); i++) {
            if (listOf_symbolAfterDot.get(i).equals("A")) {
                String string = I5.get(i).toString();
                string = string.replace(".A", "A.");
                I8.add(string);
            }
        }
        find_ListOfSymbolAfterDot(I6, listOf_symbolAfterDot);
        for (int i = 0; i < listOf_symbolAfterDot.size(); i++) {
            if (listOf_symbolAfterDot.get(i).equals("id")) {
                String string = I6.get(i).toString();
                string = string.replace(".id", "id.");
                I9.add(string);
            }
        }
        find_ListOfSymbolAfterDot(I7, listOf_symbolAfterDot);
        //calculating state I10, I11
        for (int i = 0; i < listOf_symbolAfterDot.size(); i++) {
            if (listOf_symbolAfterDot.get(i).equals("E")) {
                String string = I7.get(i).toString();
                string = string.replace(".E", "E.");
                I10.add(string);
            }
            if (listOf_symbolAfterDot.get(i).equals("id")) {
                String string = I7.get(i).toString();
                string = string.replace(".id", "id.");
                I11.add(string);
            }
        }
        System.out.println("\nI8");
        display_closure(I8);
        System.out.println("\nI9");
        display_closure(I9);
        System.out.println("\nI10");
        display_closure(I10);
        System.out.println("\nI11");
        display_closure(I11);

        List transition = new ArrayList();//making list of transition of each states
        List consume = new ArrayList<>();
        transition.add("I0->I1");
        consume.add("S");
        transition.add("I0->I2");
        consume.add("A");
        transition.add("I0->I3");
        consume.add("E");
        transition.add("I0->I4");
        consume.add("id");
        transition.add("I1->I5");
        consume.add(";");
        transition.add("I3->I6");
        consume.add("+");
        transition.add("I4->I7");
        consume.add(":=");
        transition.add("I5->I8");
        consume.add("A");
        transition.add("I5->I3");
        consume.add("E");
        transition.add("I5->I4");
        consume.add("id");
        transition.add("I6->I9");
        consume.add("id");
        transition.add("I7->I10");
        consume.add("E");
        transition.add("I7->I11");
        consume.add("id");
        transition.add("I10->I6");
        consume.add("+");

        String[][] actionTable = new String[12][terminals.size()];
        String[][] gotoTable = new String[12][non_terminals.size()];
        for (int i = 0; i < actionTable.length; i++) {
            for (int j = 0; j < actionTable[i].length; j++) {
                actionTable[i][j] = "-";
            }
            for (int j = 0; j < gotoTable[i].length; j++) {
                gotoTable[i][j] = "-";
            }
        }

        for (int i = 0; i < actionTable.length; i++) {//this block create Parsing table
            String state = "I" + i;
            List transition_I = find_transition(transition, state);
//            System.out.println(transition_I);
            for (int j = 0; j < transition_I.size(); j++) {
                int index = transition.indexOf(transition_I.get(j));
                String consume_I = consume.get(index).toString();
                if (terminals.contains(consume_I)) {//it creates action table
                    int indexOfTerminal = terminals.indexOf(consume_I);
                    actionTable[i][indexOfTerminal] = "S" + findNextState(transition_I.get(j).toString());
                } else {
                    if (non_terminals.contains(consume_I)) {//it create goto table
                        int indexOfNonterminal = non_terminals.indexOf(consume_I);
                        gotoTable[i][indexOfNonterminal] = findNextState(transition_I.get(j).toString());
                    }
                }
            }
            List listDotAtLast = new ArrayList();
            if (i == 0) {
                if (checkDotAtLast(I0)) {
                    listDotAtLast = findDotAtLast(I0);
                }
            }
            if (i == 1) {
                if (checkDotAtLast(I1)) {
                    listDotAtLast = findDotAtLast(I1);
                }
            }
            if (i == 3) {
                if (checkDotAtLast(I3)) {
                    listDotAtLast = findDotAtLast(I3);
                }
            }
            if (i == 2) {
                if (checkDotAtLast(I2)) {
                    listDotAtLast = findDotAtLast(I2);
                }
            }
            if (i == 4) {
                if (checkDotAtLast(I4)) {
                    listDotAtLast = findDotAtLast(I4);
                }
            }
            if (i == 5) {
                if (checkDotAtLast(I5)) {
                    listDotAtLast = findDotAtLast(I5);
                }
            }
            if (i == 6) {
                if (checkDotAtLast(I6)) {
                    listDotAtLast = findDotAtLast(I6);
                }
            }
            if (i == 7) {
                if (checkDotAtLast(I7)) {
                    listDotAtLast = findDotAtLast(I7);
                }
            }
            if (i == 8) {
                if (checkDotAtLast(I8)) {
                    listDotAtLast = findDotAtLast(I8);
                }
            }
            if (i == 9) {
                if (checkDotAtLast(I9)) {
                    listDotAtLast = findDotAtLast(I9);
                }
            }
            if (i == 10) {
                if (checkDotAtLast(I10)) {
                    listDotAtLast = findDotAtLast(I10);
                }
            }
            if (i == 11) {
                if (checkDotAtLast(I11)) {
                    listDotAtLast = findDotAtLast(I11);
                }
            }

            for (int j = 0; j < listDotAtLast.size(); j++) {        //this block handles reducing
                String[] splitt = listDotAtLast.get(j).toString().split(",");
                String s = splitt[0].replace(".", "");
                if (splitt[1].contentEquals("$") && s.matches(productions.get(0).toString())) {
                    actionTable[i][terminals.indexOf("$")] = "AC";
                } else {
                    int reduceNo = productions.indexOf(s) + 1;
                    actionTable[i][terminals.indexOf(splitt[1])] = "R" + reduceNo;
                }
            }
        }

        displayParseTable(gotoTable, actionTable, terminals, non_terminals);

        List input_string=new ArrayList();
        input_string.add("id");
        input_string.add(";");
        input_string.add("id");
        input_string.add("$");

        Stack<String> stringStack=new Stack<>();
        stringStack.push("0");
        System.out.println("\nINPUT STRING:"+input_string);
        System.out.println("STACK IMPLEMENTATION");
        int number=0;
        for (int i=0; i<input_string.size();i++){//parsing a given string
            System.out.println("Input:\t"+input_string.get(i).toString());
            if (terminals.contains(input_string.get(i).toString())){
                String string1=actionTable[Integer.parseInt(stringStack.peek())][terminals.indexOf(input_string.get(i))];
                if (string1.contains("S")){
                    stringStack.push(input_string.get(i).toString());
                    String splitt[]=string1.split("I");
                    stringStack.push(splitt[1]);
                }
                if (string1.contains("R")){
                    int index=Integer.parseInt(string1.replace("R",""));
                    String string2=productions.get(index-1).toString();
                    //System.out.println(string2);
                    String splitt[]=string2.split("->");
                    int count=countRHSContent(terminals,non_terminals,splitt[1]) * 2;
                    for (int j=0; j<count;j++){
                        stringStack.pop();
                    }
                    int state=Integer.parseInt(stringStack.peek());
                    stringStack.push(splitt[0]);
                    String s=gotoTable[state][non_terminals.indexOf(splitt[0])];
                    s=s.replace("I","");
                    stringStack.push(s);
                    i--;
                }
                if (string1.contains("AC")){
                    stringStack.push(input_string.get(i).toString());
                    stringStack.push("ACCEPT");
                }
            }
            number++;
            System.out.println(number+". "+stringStack);
        }
        }
}
