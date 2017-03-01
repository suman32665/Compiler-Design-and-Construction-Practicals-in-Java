/**
 * Created by suman maharjan on 30/10/2016.
 */
public class Tree {
    public static Node root;
    public Tree(){
        this.root=null;
    }
    public void insert(char a)
    {
        Node newNode=new Node(a);
        if (root==null){
            root=newNode;
            return;
        }
    }
    public static void main(String[] args) {
        Tree t=new Tree();
    }
}
class Node{
    char data;
    Node left;
    Node right;
    public Node(char data){
        this.data=data;
        left=null;
        right=null;
    }
}
