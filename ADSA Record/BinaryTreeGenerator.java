class TreeNode {
    char val;
    TreeNode left;
    TreeNode right;

    TreeNode(char val) {
        this.val = val;
    }
}

public class BinaryTreeGenerator {
    private int preIndex = 0;

    public TreeNode buildTree(char[] preorder, char[] inorder) {
        return build(preorder, inorder, 0, inorder.length - 1);
    }

    private TreeNode build(char[] preorder, char[] inorder, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }

        char currVal = preorder[preIndex++];
        TreeNode node = new TreeNode(currVal);

        if (inStart == inEnd) {
            return node;
        }

        int inIndex = findIndex(inorder, inStart, inEnd, currVal);

        node.left = build(preorder, inorder, inStart, inIndex - 1);
        node.right = build(preorder, inorder, inIndex + 1, inEnd);

        return node;
    }

    private int findIndex(char[] arr, int start, int end, char value) {
        for (int i = start; i <= end; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

    private void itraverse(TreeNode root){
		if(root==null)
			return;
		itraverse(root.left);
		System.out.println(root.val+" ");
		itraverse(root.right);
	}

	private void pretraverse(TreeNode root){
			if(root==null)
				return;

			System.out.println(root.val+" ");
			pretraverse(root.left);
			pretraverse(root.right);
	}

	private void postraverse(TreeNode root){
				if(root==null)
					return;

				postraverse(root.left);
				postraverse(root.right);
				System.out.println(root.val+" ");
	}

    public static void main(String[] args) {
        char[] preorder = {'A', 'B', 'D', 'E', 'C', 'F', 'G'};
        char[] inorder = {'D', 'B', 'E', 'A', 'F', 'C', 'G'};

        BinaryTreeGenerator generator = new BinaryTreeGenerator();
        TreeNode root = generator.buildTree(preorder, inorder);

        System.out.println("Binary Tree generated with root: " + root.val);
        generator.postraverse(root);
    }
}