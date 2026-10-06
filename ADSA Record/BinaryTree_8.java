import java.util.Scanner;

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    TreeNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

public class BinaryTree_8 {

    static TreeNode root = null;

    static void insert(int data) {
        TreeNode newNode = new TreeNode(data);

        if (root == null) {
            root = newNode;
            return;
        }

        TreeNode[] queue = new TreeNode[100];
        int front = 0, rear = 0;

        queue[rear++] = root;

        while (front < rear) {
            TreeNode temp = queue[front++];

            if (temp.left == null) {
                temp.left = newNode;
                return;
            } else {
                queue[rear++] = temp.left;
            }

            if (temp.right == null) {
                temp.right = newNode;
                return;
            } else {
                queue[rear++] = temp.right;
            }
        }
    }

    static void preorder(TreeNode root) {
        if (root == null)
            return;

        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    static void inorder(TreeNode root) {
        if (root == null)
            return;

        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    static void postorder(TreeNode root) {
        if (root == null)
            return;

        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.print("Enter the Number of Nodes: ");
        int n = s.nextInt();

        System.out.println("Enter " + n + " Values:");

        for (int i = 0; i < n; i++)
            insert(s.nextInt());

        System.out.println("Preorder Traversal:");
        preorder(root);

        System.out.println("\nInorder Traversal:");
        inorder(root);

        System.out.println("\nPostorder Traversal:");
        postorder(root);

        s.close();
    }
}
