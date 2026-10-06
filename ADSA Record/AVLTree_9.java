import java.util.Scanner;

class TreeNode {
    int data;
    TreeNode left, right;
    int height;

    TreeNode(int data) {
        this.data = data;
        this.height = 1;
    }
}

public class AVLTree_9 {

    static int height(TreeNode n) {
        return n == null ? 0 : n.height;
    }

    static int getBalance(TreeNode n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    static TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    static TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    static TreeNode insert(TreeNode root, int data) {
        if (root == null)
            return new TreeNode(data);

        if (data < root.data)
            root.left = insert(root.left, data);
        else if (data > root.data)
            root.right = insert(root.right, data);
        else
            return root;

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        if (balance > 1 && data < root.left.data)
            return rightRotate(root);

        if (balance < -1 && data > root.right.data)
            return leftRotate(root);

        if (balance > 1 && data > root.left.data) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && data < root.right.data) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    static TreeNode minValueNode(TreeNode root) {
        TreeNode current = root;

        while (current.left != null)
            current = current.left;

        return current;
    }

    static TreeNode delete(TreeNode root, int data) {
        if (root == null)
            return root;

        if (data < root.data)
            root.left = delete(root.left, data);
        else if (data > root.data)
            root.right = delete(root.right, data);
        else {
            if (root.left == null || root.right == null) {
                TreeNode temp;

                if (root.left != null)
                    temp = root.left;
                else
                    temp = root.right;

                if (temp == null)
                    root = null;
                else
                    root = temp;
            } else {
                TreeNode temp = minValueNode(root.right);
                root.data = temp.data;
                root.right = delete(root.right, temp.data);
            }
        }

        if (root == null)
            return root;

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
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

        TreeNode root = null;

        System.out.print("Enter the Number of Nodes: ");
        int n = s.nextInt();

        System.out.println("Enter " + n + " Values:");

        for (int i = 0; i < n; i++)
            root = insert(root, s.nextInt());

        System.out.println("\nPreorder Traversal:");
        preorder(root);

        System.out.println("\nInorder Traversal:");
        inorder(root);

        System.out.println("\nPostorder Traversal:");
        postorder(root);

        System.out.print("\nEnter value to delete: ");
        int value = s.nextInt();

        root = delete(root, value);

        System.out.println("\nAfter Deletion:");

        System.out.println("Preorder Traversal:");
        preorder(root);

        System.out.println("\nInorder Traversal:");
        inorder(root);

        System.out.println("\nPostorder Traversal:");
        postorder(root);

        s.close();
    }
}