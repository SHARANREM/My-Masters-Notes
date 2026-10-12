
import java.util.Scanner;

public class BTree_11 {
    static class Node {
        int t, n;
        boolean leaf;
        int[] keys;
        Node[] child;

        Node(int t, boolean leaf) {
            this.t = t;
            this.leaf = leaf;
            keys = new int[2 * t - 1];
            child = new Node[2 * t];
        }

        int findKey(int k) {
            int i = 0;
            while (i < n && keys[i] < k) i++;
            return i;
        }

        boolean contains(int k) {
            int i = findKey(k);
            if (i < n && keys[i] == k) return true;
            return !leaf && child[i].contains(k);
        }

        void insertNonFull(int k) {
            int i = n - 1;
            if (leaf) {
                while (i >= 0 && keys[i] > k) {
                    keys[i + 1] = keys[i--];
                }
                keys[i + 1] = k;
                n++;
            } else {
                while (i >= 0 && keys[i] > k) i--;
                i++;
                if (child[i].n == 2 * t - 1) {
                    splitChild(i, child[i]);
                    if (keys[i] < k) i++;
                }
                child[i].insertNonFull(k);
            }
        }

        void splitChild(int i, Node y) {
            Node z = new Node(t, y.leaf);
            z.n = t - 1;

            for (int j = 0; j < t - 1; j++)
                z.keys[j] = y.keys[j + t];

            if (!y.leaf)
                for (int j = 0; j < t; j++)
                    z.child[j] = y.child[j + t];

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
                if (!leaf) child[i].traverse();
                System.out.print(keys[i] + " ");
            }
            if (!leaf) child[i].traverse();
        }

        void remove(int k) {
            int i = findKey(k);

            if (i < n && keys[i] == k) {
                if (leaf) removeFromLeaf(i);
                else removeFromNonLeaf(i);
            } else {
                if (leaf) {
                    System.out.println("Value not found.");
                    return;
                }

                boolean last = i == n;
                if (child[i].n < t) fill(i);

                if (last && i > n) child[i - 1].remove(k);
                else child[i].remove(k);
            }
        }

        void removeFromLeaf(int i) {
            for (int j = i + 1; j < n; j++)
                keys[j - 1] = keys[j];
            n--;
        }

        void removeFromNonLeaf(int i) {
            int k = keys[i];

            if (child[i].n >= t) {
                int p = getPred(i);
                keys[i] = p;
                child[i].remove(p);
            } else if (child[i + 1].n >= t) {
                int s = getSucc(i);
                keys[i] = s;
                child[i + 1].remove(s);
            } else {
                merge(i);
                child[i].remove(k);
            }
        }

        int getPred(int i) {
            Node p = child[i];
            while (!p.leaf) p = p.child[p.n];
            return p.keys[p.n - 1];
        }

        int getSucc(int i) {
            Node p = child[i + 1];
            while (!p.leaf) p = p.child[0];
            return p.keys[0];
        }

        void fill(int i) {
            if (i > 0 && child[i - 1].n >= t) borrowFromPrev(i);
            else if (i < n && child[i + 1].n >= t) borrowFromNext(i);
            else if (i < n) merge(i);
            else merge(i - 1);
        }

        void borrowFromPrev(int i) {
            Node c = child[i], s = child[i - 1];

            for (int j = c.n - 1; j >= 0; j--)
                c.keys[j + 1] = c.keys[j];

            if (!c.leaf)
                for (int j = c.n; j >= 0; j--)
                    c.child[j + 1] = c.child[j];

            c.keys[0] = keys[i - 1];
            if (!c.leaf) c.child[0] = s.child[s.n];

            keys[i - 1] = s.keys[s.n - 1];
            c.n++;
            s.n--;
        }

        void borrowFromNext(int i) {
            Node c = child[i], s = child[i + 1];
            c.keys[c.n] = keys[i];

            if (!c.leaf) c.child[c.n + 1] = s.child[0];

            keys[i] = s.keys[0];

            for (int j = 1; j < s.n; j++)
                s.keys[j - 1] = s.keys[j];

            if (!s.leaf)
                for (int j = 1; j <= s.n; j++)
                    s.child[j - 1] = s.child[j];

            c.n++;
            s.n--;
        }

        void merge(int i) {
            Node c = child[i], s = child[i + 1];
            c.keys[t - 1] = keys[i];

            for (int j = 0; j < s.n; j++)
                c.keys[j + t] = s.keys[j];

            if (!c.leaf)
                for (int j = 0; j <= s.n; j++)
                    c.child[j + t] = s.child[j];

            for (int j = i + 1; j < n; j++)
                keys[j - 1] = keys[j];

            for (int j = i + 2; j <= n; j++)
                child[j - 1] = child[j];

            c.n += s.n + 1;
            n--;
        }
    }

    static class BTree {
        Node root;
        int t;

        BTree(int t) { this.t = t; }

        void insert(int k) {
            if (root != null && root.contains(k)) {
                System.out.println("Duplicate value not allowed.");
                return;
            }

            if (root == null) {
                root = new Node(t, true);
                root.keys[0] = k;
                root.n = 1;
                return;
            }

            if (root.n == 2 * t - 1) {
                Node r = new Node(t, false);
                r.child[0] = root;
                r.splitChild(0, root);
                int i = r.keys[0] < k ? 1 : 0;
                r.child[i].insertNonFull(k);
                root = r;
            } else root.insertNonFull(k);
        }

        void delete(int k) {
            if (root == null) {
                System.out.println("Tree is empty.");
                return;
            }

            if (!root.contains(k)) {
                System.out.println("Value not found.");
                return;
            }

            root.remove(k);
            if (root.n == 0)
                root = root.leaf ? null : root.child[0];
        }

        void traverse() {
            if (root == null) System.out.println("Tree is empty.");
            else {
                root.traverse();
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t;

        do {
            System.out.print("Enter minimum degree (t >= 2): ");
            t = s.nextInt();
            if (t < 2) System.out.println("Minimum degree must be at least 2.");
        } while (t < 2);

        BTree tree = new BTree(t);

        while (true) {
            System.out.println("\n1.Insert  2.Delete  3.Traverse  4.Exit");
            System.out.print("Enter choice: ");
            int ch = s.nextInt();

            switch (ch) {
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
                    s.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
