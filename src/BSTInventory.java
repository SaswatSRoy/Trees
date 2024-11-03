import java.util.*;

public class BSTInventory {

    static class Node{
        int data;
        int id;
        Node left;
        Node right;

        public Node(int data,int id){
            this.data=data;
            this.id=id;
        }
        public static Node insert(Node root,int data,int id){
            if(root==null){
                root=new Node(data,id);
                return root;
            }
            if(root.data<data){
                root.right=insert(root.right,data,id);
            }else {
                root.left=insert(root.left,data,id);
            }
            return root;
        }
        public static Node searchById(Node root,int id){
            if(root==null){
                return null;
            }

            if(root.id==id){
                return root;
            }
            Node foundInLeft= searchById(root.left,id);
            if(foundInLeft !=null){
                return foundInLeft;
            }else{
                return searchById(root.right,id);
            }

        }
        public static Node delete(Node root,int id){
            if(root==null){
                return null;
            }

            if(root.id==id){
                if(root.left==null && root.right==null){
                    return null;
                }
                else if (root.left==null) {
                    return root.right;
                }else if(root.right==null) {
                    return root.left;
                }else {
                    Node successor = inorderSuccessor(root.right);
                    root.data = successor.data;
                    root.id = successor.id;
                    root.right = delete(root.right, successor.id);
                }
            }
            else if (id < root.data) {
                root.left = delete(root.left, id);
            } else {
                root.right = delete(root.right, id);
            }
            return root;
        }

        public static Node inorderSuccessor(Node root){
            while (root.left !=null){
                root=root.left;
            }
            return root;
        }
        public static void inOrder(Node root) {
            if (root == null) {
                return;
            }
            inOrder(root.left);
            System.out.print("ID: " + root.id + ", Data: " + root.data + " | ");
            inOrder(root.right);
        }
    }

    public static void main(String []args){
        Scanner scanner = new Scanner(System.in);
        Node root = null;

        System.out.print("Enter the number of nodes you want to add: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter data for node " + (i + 1) + ": ");
            int data = scanner.nextInt();
            System.out.print("Enter id for node " + (i + 1) + ": ");
            int id = scanner.nextInt();
            root = Node.insert(root, data, id);
        }

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Insert a node");
            System.out.println("2. Delete a node by ID");
            System.out.println("3. Search for a node by ID");
            System.out.println("4. Display in-order traversal");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter data: ");
                    int data = scanner.nextInt();
                    System.out.print("Enter id: ");
                    int id = scanner.nextInt();
                    root = Node.insert(root, data, id);
                    break;
                case 2:
                    System.out.print("Enter ID to delete: ");
                    int deleteId = scanner.nextInt();
                    Node nodeToDelete = Node.searchById(root, deleteId);
                    if (nodeToDelete != null) {
                        root = Node.delete(root, deleteId);
                        System.out.println("Node with ID " + deleteId + " deleted.");
                    } else {
                        System.out.println("Node with ID " + deleteId + " not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter ID to search: ");
                    int searchId = scanner.nextInt();
                    Node result = Node.searchById(root, searchId);
                    if (result != null) {
                        System.out.println("Node found - ID: " + result.id + ", Data: " + result.data);
                    } else {
                        System.out.println("Node with ID " + searchId + " not found.");
                    }
                    break;
                case 4:
                    System.out.println("In-order Traversal of BST:");
                    Node.inOrder(root);
                    System.out.println();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}



