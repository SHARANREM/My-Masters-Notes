class TreeNode {
    char val;
    TreeNode left, right;
    int height;

    TreeNode(char val) {
        this.val = val;
        this.height = 1;
    }
}

public class AVLTreeGenerator {

    private int height(TreeNode n) {
		return n == null ? 0 : n.height;
	}
    private int getBalance(TreeNode n) {
		return n == null ? 0 : height(n.left) - height(n.right);
    }

    private TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left, T2 = x.right;
        x.right = y; y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right, T2 = y.left;
        y.left = x; x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    public TreeNode insert(TreeNode node, char val) {
        if (node == null) return new TreeNode(val);
        if (val < node.val) node.left = insert(node.left, val);
        else if (val > node.val) node.right = insert(node.right, val);
        else return node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int balance = getBalance(node);

        if (balance > 1 && val < node.left.val) return rightRotate(node);
        if (balance < -1 && val > node.right.val) return leftRotate(node);
        if (balance > 1 && val > node.left.val) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && val < node.right.val) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public TreeNode delete(TreeNode root, char val) {
        if (root == null) return root;

        if (val < root.val) root.left = delete(root.left, val);
        else if (val > root.val) root.right = delete(root.right, val);
        else {
            if ((root.left == null) || (root.right == null)) {
                TreeNode temp = root.left != null ? root.left : root.right;
                if (temp == null) root = null;
                else root = temp;
            } else {
                TreeNode temp = minValueNode(root.right);
                root.val = temp.val;
                root.right = delete(root.right, temp.val);
            }
        }

        if (root == null) return root;

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0) return rightRotate(root);
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && getBalance(root.right) <= 0) return leftRotate(root);
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    private TreeNode minValueNode(TreeNode node) {
        TreeNode current = node;
        while (current.left != null) current = current.left;
        return current;
    }

    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public void preorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public void postorder(TreeNode root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val + " ");
    }

    public static void main(String[] args) {
        AVLTreeGenerator avl = new AVLTreeGenerator();
        TreeNode root = null;

        char[] values = {'A', 'B', 'D', 'E', 'C', 'F', 'G'};
        for (char v : values) root = avl.insert(root, v);

        System.out.println("Root: " + root.val);
        System.out.print("Preorder:  "); avl.preorder(root); System.out.println();
        System.out.print("Inorder:   "); avl.inorder(root); System.out.println();
        System.out.print("Postorder: "); avl.postorder(root); System.out.println();

        root = avl.delete(root, 'C');
        System.out.println("\nAfter deleting 'C':");
        System.out.print("Inorder:   "); avl.inorder(root); System.out.println();
    }
}