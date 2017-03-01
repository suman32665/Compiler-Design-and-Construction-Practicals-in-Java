import java.util.Stack;
//FInding nullable,firstpos, lastpos and followpos of string (a|b)*abb#
/**
 * Created by suman maharjan on 27/10/2016.
 */
public class Lab4 {
    static String infixTopostfix(String infix_exp)
    {
        boolean flag=true;
        String postfix_exp=new String("");
        String operators=new String("|.*");
        Stack stack=new Stack();


        for (int i=0; i<infix_exp.length();i++){
            if (infix_exp.charAt(i)>='a' && infix_exp.charAt(i)<='z' || infix_exp.charAt(i)=='#'){
                postfix_exp=postfix_exp+infix_exp.charAt(i);
            }else{
                if (stack.empty() || infix_exp.charAt(i)=='('){
                    stack.push(new Integer(infix_exp.charAt(i)));
                }else{
                    if (infix_exp.charAt(i)=='*' || infix_exp.charAt(i)=='|' ||infix_exp.charAt(i)=='.'){
                        int a=(Integer) stack.peek();
                        if ((char)a == '('){
                            stack.push(new Integer(infix_exp.charAt(i)));
                        }else{
                            if (operators.indexOf(infix_exp.charAt(i)) <= operators.indexOf((char)a)){
                                stack.pop();
                                postfix_exp=postfix_exp+(char) a;
                                stack.push(new Integer(infix_exp.charAt(i)));
                            }else{
                                stack.push(new Integer(infix_exp.charAt(i)));
                            }
                        }
                    }else{
                        if (infix_exp.charAt(i)==')'){
                            while (flag){
                                int a=(Integer)stack.pop();
                                if ((char)a=='('){
                                    flag=false;
                                    break;
                                }
                                postfix_exp=postfix_exp+(char)a;
                            }
                        }
                    }
                }
            }
        }
        while (!stack.empty()){
            int a=(Integer)stack.pop();
            postfix_exp=postfix_exp+(char)a;
        }
        return postfix_exp;
    }

    public static void main(String[] args) {
        String regular_exp=new String("(a|b)*abb#");
        String infix_exp=new String("");

        for (int i=0; i<regular_exp.length();i++){
            if (regular_exp.charAt(i)=='*' || regular_exp.charAt(i)>='a' && regular_exp.charAt(i)<='z'){
                if (regular_exp.charAt(i+1)>='a' && regular_exp.charAt(i+1)<= 'z' || regular_exp.charAt(i+1)=='#'){
                    infix_exp=infix_exp+regular_exp.charAt(i)+'.';
                }else{
                    infix_exp=infix_exp+regular_exp.charAt(i);
                }
            }else{
                infix_exp=infix_exp+regular_exp.charAt(i);
            }
        }
        String postfix_exp=infixTopostfix(infix_exp);

 //       System.out.println(postfix_exp);

        int positions[]=new int[postfix_exp.length()];
        int flag=0;
        for (int i=0; i<postfix_exp.length();i++){
            if (postfix_exp.charAt(i)>='a' && postfix_exp.charAt(i)<='z' || postfix_exp.charAt(i)=='#'){
                flag++;
                positions[i]=flag;
            }else{
                positions[i]=0;
            }
//            System.out.println("positions["+i+"]=" + positions[i]);
        }

        boolean[] nullable=new boolean[postfix_exp.length()];
        int[][] firstpos=new int[postfix_exp.length()][];
        int[][] lastpos=new int[postfix_exp.length()][];

        for (int i=0; i<postfix_exp.length();i++){
            if (positions[i]!=0){
                firstpos[i]=new int[1];
                lastpos[i]=new int[1];
                firstpos[i][0]=positions[i];
                lastpos[i][0]=positions[i];
                nullable[i]=false;
            }else{
                if (postfix_exp.charAt(i)=='|'){
                    nullable[i]=nullable[i-1] || nullable[i-2];
                    firstpos[i]=new int[firstpos[i-1].length+firstpos[i-2].length];

                    for (int j=0; j<firstpos[i-1].length;j++){
                        firstpos[i][j]=firstpos[i-1][j];
                    }
                    int k=0;
                    for (int j=firstpos[i-1].length; j<firstpos[i].length;j++){
                        firstpos[i][j]=firstpos[i-2][k];
                        k++;
                    }
                    lastpos[i]=new int[lastpos[i-1].length+lastpos[i-2].length];
                    for (int j=0; j<lastpos[i-1].length; j++){
                        lastpos[i][j]=lastpos[i-1][j];
                    }
                    k=0;
                    for (int j=lastpos[i-1].length;j<lastpos[i].length;j++){
                        lastpos[i][j]=lastpos[i-2][k];
                        k++;
                    }
                }
                if (postfix_exp.charAt(i)=='*'){
                    nullable[i]=true;
                    firstpos[i]=new int[firstpos[i-1].length];
                    lastpos[i]=new int[lastpos[i-1].length];
                    for (int j=0; j<firstpos[i-1].length;j++){
                        firstpos[i][j]=firstpos[i-1][j];
                        lastpos[i][j]=lastpos[i-1][j];
                    }
                }
                if (postfix_exp.charAt(i)=='.'){
                    nullable[i]=nullable[i-1] && nullable[i-2];
                    if (nullable[i-2]){
                        firstpos[i]=new int[firstpos[i-1].length+firstpos[i-2].length];

                        for (int j=0; j<firstpos[i-1].length;j++){
                            firstpos[i][j]=firstpos[i-1][j];
                        }
                        int k=0;
                        for (int j=firstpos[i-1].length; j<firstpos[i].length;j++){
                            firstpos[i][j]=firstpos[i-2][k];
                            k++;
                        }
                    }else{
                        firstpos[i]=new int[firstpos[i-2].length];
                        for (int j=0; j<firstpos[i-2].length;j++){
                            firstpos[i][j]=firstpos[i-2][j];
                        }
                    }
                    if (nullable[i-1]){
                        lastpos[i]=new int[lastpos[i-1].length+lastpos[i-2].length];
                        for (int j=0; j<lastpos[i-1].length; j++){
                            lastpos[i][j]=lastpos[i-1][j];
                        }
                        int k=0;
                        for (int j=lastpos[i-1].length;j<lastpos[i].length;j++){
                            lastpos[i][j]=lastpos[i-2][k];
                            k++;
                        }
                    }else{
                        lastpos[i]=new int[lastpos[i-1].length];
                        for (int j=0; j<lastpos[i-1].length;j++){
                            lastpos[i][j]=lastpos[i-1][j];
                        }
                    }
                }
            }
        }
        System.out.println("n   nullable(n)     firstpos(n)     lastpos(n)");
        for (int i=0; i<postfix_exp.length();i++){
            String line=new String("");
            line=line+postfix_exp.charAt(i)+"     "+nullable[i]+"          {";
            for (int j=0; j<firstpos[i].length;j++){
                line=line+firstpos[i][j]+",";
            }
            line=line.substring(0, line.length()-1);
            line=line+"}           {";
            for (int j=0; j<lastpos[i].length;j++){
                line=line+lastpos[i][j]+",";
            }
            line=line.substring(0, line.length()-1);
            line=line+"}";
            System.out.println(line);
        }
        int count=0;
        for (int i=0; i<postfix_exp.length();i++){
            if (postfix_exp.charAt(i)=='*' || postfix_exp.charAt(i)=='.'){
                count++;
            }
        }
        int[][] followpos=new int[count+1][];
        count=0;
       // System.out.println(followpos.length);
       // while (count<followpos.length){
            for (int i=0; i<postfix_exp.length();i++){
                if (postfix_exp.charAt(i)=='*'){
                    for (int j=count; j<count+lastpos[i].length;j++){
                        followpos[j]=new int[firstpos[i].length];
                        for (int k=0; k<firstpos[i].length;k++){
                            followpos[j][k]=firstpos[i][k];
                        //    System.out.println(followpos[j][k]);
                        }
                    }
                }
                int[][] followpos_star=followpos.clone();
                if (postfix_exp.charAt(i)=='.'){
                    for (int j=count; j<(lastpos[i-2].length+count);j++){
                        int length;
                        if (followpos[j]!=null) {
                            length = followpos[j].length;
                        }else{
                            length=0;
                        }
                        followpos[j]=new int[firstpos[i-1].length + length];

                        if (length!=0){
                            for (int l=count; l<lastpos[i-2].length+count;l++){
                                 for (int k=0; k<length;k++){
                                    followpos[l][k]=followpos_star[l][k];
                                }
                             }
                        }
                        int l=0;
                        for (int k=length; k<firstpos[i-1].length+length;k++){
                            followpos[j][k]=firstpos[i-1][l];
                            l++;
                        }
                    }
                    count=count+lastpos[i-2].length;
                }
            }
        //}

        for (int i=1; i<followpos.length; i++){
            String line=new String("");
            line=line+ "Followpos("+i+")={";
            for (int j=0; j<followpos[i-1].length;j++){
                line=line+followpos[i-1][j]+",";
            }
            line=line.substring(0,line.length()-1);
            line=line+"}";
            System.out.println(line);
        }
        System.out.println("Followpos("+followpos.length+")=null");

    }
}
