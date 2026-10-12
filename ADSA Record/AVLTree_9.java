
import java.util.Scanner;

class TreeNode {
    int data, height = 1;
    TreeNode left, right;
    TreeNode(int d) { data = d; }
}

public class AVLTree_9 {
    static int h(TreeNode n) { return n == null ? 0 : n.height; }
    static int bal(TreeNode n) { return n == null ? 0 : h(n.left) - h(n.right); }
    static void update(TreeNode n) { n.height = 1 + Math.max(h(n.left), h(n.right)); }

    static TreeNode right(TreeNode y) {
        TreeNode x = y.left, t = x.right;
        x.right = y; y.left = t;
        update(y); update(x);
        return x;
    }

    static TreeNode left(TreeNode x) {
        TreeNode y = x.right, t = y.left;
        y.left = x; x.right = t;
        update(x); update(y);
        return y;
    }

    static TreeNode insert(TreeNode r, int d) {
        if (r == null) return new TreeNode(d);
        if (d < r.data) r.left = insert(r.left, d);
        else if (d > r.data) r.right = insert(r.right, d);
        else return r;
        update(r);
        int b = bal(r);
        if (b > 1) {
            if (d > r.left.data) r.left = left(r.left);
            return right(r);
        }
        if (b < -1) {
            if (d < r.right.data) r.right = right(r.right);
            return left(r);
        }
        return r;
    }

    static TreeNode min(TreeNode r) {
        while (r.left != null) r = r.left;
        return r;
    }

    static TreeNode delete(TreeNode r, int d) {
        if (r == null) return null;
        if (d < r.data) r.left = delete(r.left, d);
        else if (d > r.data) r.right = delete(r.right, d);
        else if (r.left == null || r.right == null)
            r = r.left != null ? r.left : r.right;
        else {
            TreeNode t = min(r.right);
            r.data = t.data;
            r.right = delete(r.right, t.data);
        }
        if (r == null) return null;
        update(r);
        int b = bal(r);
        if (b > 1) {
            if (bal(r.left) < 0) r.left = left(r.left);
            return right(r);
        }
        if (b < -1) {
            if (bal(r.right) > 0) r.right = right(r.right);
            return left(r);
        }
        return r;
    }

    static void preorder(TreeNode r) {
        if (r != null) {
            System.out.print(r.data + " ");
            preorder(r.left); preorder(r.right);
        }
    }

    static void inorder(TreeNode r) {
        if (r != null) {
            inorder(r.left); System.out.print(r.data + " "); inorder(r.right);
        }
    }

    static void postorder(TreeNode r) {
        if (r != null) {
            postorder(r.left); postorder(r.right); System.out.print(r.data + " ");
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        TreeNode root = null;

        while (true) {
            System.out.println("\n1.Create  \n2.Insert  \n3.Delete  \n4.Preorder\n5.Inorder  \n6.Postorder  \n7.Exit");
            System.out.print("Enter choice: ");
            int ch = s.nextInt();

            switch (ch) {
                case 1:
                case 2:
                    System.out.print("Enter value: ");
                    root = insert(root, s.nextInt());
                    System.out.println("Inserted");
                    break;
                case 3:
                    System.out.print("Enter value to delete: ");
                    root = delete(root, s.nextInt());
                    System.out.println("Deleted if present");
                    break;
                case 4: preorder(root); System.out.println(); break;
                case 5: inorder(root); System.out.println(); break;
                case 6: postorder(root); System.out.println(); break;
                case 7: s.close(); return;
                default: System.out.println("Invalid Choice");
            }
        }
    }
}
