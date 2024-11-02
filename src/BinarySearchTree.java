public class BinarySearchTree {
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data=data;
        }
        public static Node insert(Node root,int value){
            if(root ==null){
                root=new Node(value);
                return root;
            }
            if(root.data<value){
                root.right=insert(root.right,value);
            }else{
                root.left=insert(root.left,value);
            }
            return root;
        }
        public static void inOrder(Node root){
            if(root==null){
                return;
            }
            inOrder(root.left);
            System.out.print(root.data+" ");
            inOrder(root.right);
        }
        public static boolean search(Node root,int key){
            if(root==null){
//                return false;
            }
            if(root.data<key){
                return search(root.right,key);
            }else if(root.data>key){
                return search(root.left,key);
            }else{
                return true;
            }
        }
        public static Node delete(Node root, int val) {
            if (root == null) {
                return null;
            }

            if (val < root.data) {
                root.left = delete(root.left, val);
            } else if (val > root.data) {
                root.right = delete(root.right, val);
            } else {
                // Node found - handle deletion cases

                // Case 1: No children (leaf node)
                if (root.left == null && root.right == null) {
                    return null;
                }

                // Case 2: One child
                else if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                }

                // Case 3: Two children
                Node iS = inorderSuccessor(root.right);
                root.data = iS.data;
                root.right = delete(root.right, iS.data);
            }
            return root;
        }
        public static Node inorderSuccessor(Node root){
            while (root.left !=null){
                root=root.left;
            }
            return root;
        }

        public static void printInRange(Node root, int x, int y) {
            if (root == null) {
                return;
            }

            if (x <= root.data && root.data <= y) {
                printInRange(root.left, x, y);
                System.out.print(root.data + " ");
                printInRange(root.right, x, y);
            } else if (root.data < x) {
                printInRange(root.right, x, y);
            } else {
                printInRange(root.left, x, y);
            }
        }
    }

    public static void main(String []args){
        int []values={8,5,3,1,4,6,10,11,14};

        Node root=null;
        for (int value : values) {
            root = Node.insert(root, value);
        }
        System.out.println();
        System.out.println("The inorder traversal is: ");
        Node.inOrder(root);
        System.out.println();
        Node.printInRange(root,2,9);
        Node.delete(root,1);
        System.out.println();
        Node.inOrder(root);
        if(Node.search(root,8)){
            System.out.println("Found");
        }else{
            System.out.println("Not found");
        }


    }


}
