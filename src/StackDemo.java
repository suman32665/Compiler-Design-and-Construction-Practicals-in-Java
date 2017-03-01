import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by suman maharjan on 30/10/2016.
 */

public class StackDemo {

    static void showpush(Stack st, int a) {
        st.push(new Integer(a));
        System.out.println("push(" + (char)a + ")");
        System.out.println("stack: " + st);
    }

    static void showpop(Stack st) {
        System.out.print("pop -> ");
        int a = (Integer) st.pop();
        System.out.println((char)a);
        System.out.println("stack: " + st);
    }

    public static void main(String args[]) {
        Stack st = new Stack();
        System.out.println("stack: " + st);
        showpush(st, '(');
        try {
            showpop(st);
        }catch (EmptyStackException e) {
            System.out.println("empty stack");
        }
    }
}