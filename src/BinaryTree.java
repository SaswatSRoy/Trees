import java.util.*;

public class BinaryTree {

    static class Node{
        int data;
        Node left;
        Node right;

        Node (int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }
    static class BT{
        static int index=-1;
        public static Node buildTree(int[] nodes){
            index++;
            if(nodes[index]==-1){
                return null;
            }

            Node newNode= new Node(nodes[index]);
            newNode.left=buildTree(nodes);
            newNode.right=buildTree(nodes);
            return newNode;

        }
        public static void preorder(Node root){
            if (root==null){
                return;
            }
            System.out.print(root.data+" ");
            preorder(root.left);
            preorder(root.right);

        }
        public static void inorder(Node root){
            if(root==null){
                return;
            }
            inorder(root.left);
            System.out.print(root.data+" ");
            inorder(root.right);
        }

        public static void postorder(Node root){
            if(root==null){
                return;
            }
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data+" ");
        }

        public static void LevelOrder(Node root){
            if(root==null){
                return;
            }

            Queue<Node>q= new LinkedList<>();

            q.add(root);
            q.add(null);

            while(!q.isEmpty()){
                Node currNode=q.remove();
                if(currNode==null){
                    System.out.println();
                    if(q.isEmpty()){
                        break;
                    }else{
                        q.add(null);
                    }
                }else{
                    System.out.print(currNode.data+" ");
                    if(currNode.left!=null){
                        q.add(currNode.left);
                    } if (currNode.right!=null) {
                        q.add(currNode.right);

                    }
                }

            }
        }

        public static int countNodes(Node root){
            if(root==null){
                return 0;
            }
            int leftNODE= countNodes(root.left);
            int rightNODE= countNodes(root.right);
            return leftNODE+rightNODE+1;
        }
        public static int sumNodes(Node root){
            if(root==null){
                return 0;
            }
            int leftSum= sumNodes(root.left);
            int rightSum= sumNodes(root.right);
            return leftSum+rightSum+root.data;
        }

        public static int height(Node root){
            if(root==null){
                return 0;
            }
            int leftH=height(root.left);
            int rightH=height(root.right);
            return Math.max(leftH,rightH)+1;
        }

        public static int diameter(Node root){
            if(root==null){
                return 0;
            }
            int diam1=diameter(root.left);
            int diam2=diameter(root.right);
            int diam3=height(root.left)+height(root.right)+1;
            return Math.max(diam3,Math.max(diam1,diam2));
        }

        static class TreeInfo{
            int height;
            int dia;

            TreeInfo(int height,int dia){
                this.height=height;
                this.dia=dia;
            }
        }
        public static TreeInfo diameterBetter(Node root){
            if(root==null){
                return new TreeInfo(0,0);
            }
            TreeInfo left= diameterBetter(root.left);
            TreeInfo right=diameterBetter(root.right);

            int myHeight=Math.max(left.height, right.height);

            int dia1= left.dia;
            int dia2=right.dia;
            int dia3=left.height+ right.height+1;

            int actualdia=Math.max(dia3,Math.max(dia1,dia2));

            return new TreeInfo(myHeight,actualdia);

        }

    }





    public static void main(String [] args){
        int[] nodes = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};

        BT tree=new BT();
        Node root = tree.buildTree(nodes);
        System.out.println("Preorder Traversal result:- ");
        BT.preorder(root);
        System.out.println();
        System.out.println("Inorder Traversal result:- ");
        BT.inorder(root);
        System.out.println();
        System.out.println("Postorder Traversal result:- ");
        BT.postorder(root);
        System.out.println();
        System.out.println("LevelOrder Traversal result:- ");
        BT.LevelOrder(root);
        System.out.println();
        System.out.print("The total number of nodes are: "+ BT.countNodes(root));
        System.out.println();
        System.out.print("The total sum of Nodes is: "+ BT.sumNodes(root));
        System.out.println();
        System.out.println("The height of the TREE is: "+ BT.height(root));
        System.out.println();
        System.out.println("The diameter of the TREE is: "+ BT.diameter(root));
        System.out.println();
        System.out.println("The diameter of the TREE(better) is: "+ BT.diameterBetter(root).dia);
    }
}
