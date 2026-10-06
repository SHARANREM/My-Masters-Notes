import java.util.Scanner;

class TreeNode {
    int data;
    TreeNode left, right;

    TreeNode(int data) {
        this.data = data;
    }
}

public class SplayTree_10 {

    static TreeNode rightRotate(TreeNode x) {
        TreeNode y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    static TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    static TreeNode splay(TreeNode root, int data) {
        if (root == null || root.data == data)
            return root;

        if (data < root.data) {
            if (root.left == null)
                return root;

            if (data < root.left.data) {
                root.left.left = splay(root.left.left, data);
                root = rightRotate(root);
            } else if (data > root.left.data) {
                root.left.right = splay(root.left.right, data);

                if (root.left.right != null)
                    root.left = leftRotate(root.left);
            }

            return root.left == null ? root : rightRotate(root);
        } else {
            if (root.right == null)
                return root;

            if (data > root.right.data) {
                root.right.right = splay(root.right.right, data);
                root = leftRotate(root);
            } else if (data < root.right.data) {
                root.right.left = splay(root.right.left, data);

                if (root.right.left != null)
                    root.right = rightRotate(root.right);
            }

            return root.right == null ? root : leftRotate(root);
        }
    }

    static TreeNode insert(TreeNode root, int data) {
        if (root == null)
            return new TreeNode(data);

        root = splay(root, data);

        if (root.data == data)
            return root;

        TreeNode newNode = new TreeNode(data);

        if (data < root.data) {
            newNode.right = root;
            newNode.left = root.left;
            root.left = null;
        } else {
            newNode.left = root;
            newNode.right = root.right;
            root.right = null;
        }

        return newNode;
    }

    static void inorder(TreeNode root) {
        if (root == null)
            return;

        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        TreeNode root = null;

        System.out.print("Enter the Number of Nodes: ");
        int n = s.nextInt();

        System.out.println("Enter " + n + " Values:");

        for (int i = 0; i < n; i++)
            root = insert(root, s.nextInt());

        System.out.println("Inorder Traversal:");
        inorder(root);

        s.close();
    }
}