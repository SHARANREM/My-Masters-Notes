import java.util.Scanner;

public class BTree_11 {

    static class Node {
        int t;
        int n;
        boolean leaf;
        int[] keys;
        Node[] child;

        Node(int t, boolean leaf) {
            this.t = t;
            this.leaf = leaf;
            this.n = 0;
            keys = new int[2 * t - 1];
            child = new Node[2 * t];
        }

        int findKey(int k) {
            int idx = 0;

            while (idx < n && keys[idx] < k)
                idx++;

            return idx;
        }

        void insertNonFull(int k) {
            int i = n - 1;

            if (leaf) {
                while (i >= 0 && keys[i] > k) {
                    keys[i + 1] = keys[i];
                    i--;
                }

                if (i >= 0 && keys[i] == k)
                    return;

                keys[i + 1] = k;
                n++;
            } else {
                while (i >= 0 && keys[i] > k)
                    i--;

                i++;

                if (child[i].n == 2 * t - 1) {
                    splitChild(i, child[i]);

                    if (keys[i] == k)
                        return;

                    if (keys[i] < k)
                        i++;
                }

                child[i].insertNonFull(k);
            }
        }

        void splitChild(int i, Node y) {
            Node z = new Node(y.t, y.leaf);

            z.n = t - 1;

            for (int j = 0; j < t - 1; j++)
                z.keys[j] = y.keys[j + t];

            if (!y.leaf) {
                for (int j = 0; j < t; j++)
                    z.child[j] = y.child[j + t];
            }

            y.n = t - 1;

            for (int j = n; j >= i + 1; j--)
                child[j + 1] = child[j];

            child[i + 1] = z;

            for (int j = n - 1; j >= i; j--)
                keys[j + 1] = keys[j];

            keys[i] = y.keys[t - 1];
            n++;
        }

        void traverse() {
            int i;

            for (i = 0; i < n; i++) {
                if (!leaf)
                    child[i].traverse();

                System.out.print(keys[i] + " ");
            }

            if (!leaf)
                child[i].traverse();
        }

        void remove(int k) {
            int idx = findKey(k);

            if (idx < n && keys[idx] == k) {
                if (leaf)
                    removeFromLeaf(idx);
                else
                    removeFromNonLeaf(idx);
            } else {
                if (leaf) {
                    System.out.println("Value not found.");
                    return;
                }

                boolean flag = (idx == n);

                if (child[idx].n < t)
                    fill(idx);

                if (flag && idx > n)
                    child[idx - 1].remove(k);
                else
                    child[idx].remove(k);
            }
        }

        void removeFromLeaf(int idx) {
            for (int i = idx + 1; i < n; i++)
                keys[i - 1] = keys[i];

            n--;
        }

        void removeFromNonLeaf(int idx) {
            int k = keys[idx];

            if (child[idx].n >= t) {
                int pred = getPred(idx);
                keys[idx] = pred;
                child[idx].remove(pred);
            } else if (child[idx + 1].n >= t) {
                int succ = getSucc(idx);
                keys[idx] = succ;
                child[idx + 1].remove(succ);
            } else {
                merge(idx);
                child[idx].remove(k);
            }
        }

        int getPred(int idx) {
            Node current = child[idx];

            while (!current.leaf)
                current = current.child[current.n];

            return current.keys[current.n - 1];
        }

        int getSucc(int idx) {
            Node current = child[idx + 1];

            while (!current.leaf)
                current = current.child[0];

            return current.keys[0];
        }

        void fill(int idx) {
            if (idx != 0 && child[idx - 1].n >= t)
                borrowFromPrev(idx);
            else if (idx != n && child[idx + 1].n >= t)
                borrowFromNext(idx);
            else {
                if (idx != n)
                    merge(idx);
                else
                    merge(idx - 1);
            }
        }

        void borrowFromPrev(int idx) {
            Node current = child[idx];
            Node sibling = child[idx - 1];

            for (int i = current.n - 1; i >= 0; i--)
                current.keys[i + 1] = current.keys[i];

            if (!current.leaf) {
                for (int i = current.n; i >= 0; i--)
                    current.child[i + 1] = current.child[i];
            }

            current.keys[0] = keys[idx - 1];

            if (!current.leaf)
                current.child[0] = sibling.child[sibling.n];

            keys[idx - 1] = sibling.keys[sibling.n - 1];

            current.n++;
            sibling.n--;
        }

        void borrowFromNext(int idx) {
            Node current = child[idx];
            Node sibling = child[idx + 1];

            current.keys[current.n] = keys[idx];

            if (!current.leaf)
                current.child[current.n + 1] = sibling.child[0];

            keys[idx] = sibling.keys[0];

            for (int i = 1; i < sibling.n; i++)
                sibling.keys[i - 1] = sibling.keys[i];

            if (!sibling.leaf) {
                for (int i = 1; i <= sibling.n; i++)
                    sibling.child[i - 1] = sibling.child[i];
            }

            current.n++;
            sibling.n--;
        }

        void merge(int idx) {
            Node current = child[idx];
            Node sibling = child[idx + 1];

            current.keys[t - 1] = keys[idx];

            for (int i = 0; i < sibling.n; i++)
                current.keys[i + t] = sibling.keys[i];

            if (!current.leaf) {
                for (int i = 0; i <= sibling.n; i++)
                    current.child[i + t] = sibling.child[i];
            }

            for (int i = idx + 1; i < n; i++)
                keys[i - 1] = keys[i];

            for (int i = idx + 2; i <= n; i++)
                child[i - 1] = child[i];

            current.n += sibling.n + 1;
            n--;
        }
    }

    static class BTree {
        Node root;
        int t;

        BTree(int t) {
            this.t = t;
            root = null;
        }

        void insert(int k) {
            if (root == null) {
                root = new Node(t, true);
                root.keys[0] = k;
                root.n = 1;
                return;
            }

            if (root.findKey(k) < root.n &&
                    root.keys[root.findKey(k)] == k) {
                System.out.println("Duplicate value not allowed.");
                return;
            }

            if (root.n == 2 * t - 1) {
                Node newRoot = new Node(t, false);

                newRoot.child[0] = root;
                newRoot.splitChild(0, root);

                int i = 0;

                if (newRoot.keys[0] < k)
                    i++;

                newRoot.child[i].insertNonFull(k);

                root = newRoot;
            } else {
                root.insertNonFull(k);
            }
        }

        void delete(int k) {
            if (root == null) {
                System.out.println("Tree is empty.");
                return;
            }

            int idx = root.findKey(k);

            if (idx == root.n || root.keys[idx] != k) {
                // The value may exist in a child, so let remove()
                // perform the complete search.
            }

            root.remove(k);

            if (root.n == 0) {
                if (root.leaf)
                    root = null;
                else
                    root = root.child[0];
            }
        }

        void traverse() {
            if (root == null) {
                System.out.println("Tree is empty.");
                return;
            }

            root.traverse();
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int degree;

        do {
            System.out.print("Enter minimum degree (t >= 2): ");
            degree = s.nextInt();

            if (degree < 2)
                System.out.println("Minimum degree must be at least 2.");

        } while (degree < 2);

        BTree tree = new BTree(degree);

        int choice;

        do {
            System.out.println("\n===== B-Tree Operations =====");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Traverse");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = s.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter value to insert: ");
                    tree.insert(s.nextInt());
                    break;

                case 2:
                    System.out.print("Enter value to delete: ");
                    tree.delete(s.nextInt());
                    break;

                case 3:
                    System.out.print("B-Tree: ");
                    tree.traverse();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);

        s.close();
    }
}
