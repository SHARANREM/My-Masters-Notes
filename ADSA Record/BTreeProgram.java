import java.util.Scanner;

public class BTreeProgram {

    static class Node {
        int t, n;
        boolean leaf;
        int[] keys;
        Node[] child;

        Node(int t, boolean leaf) {
            this.t = t;
            this.leaf = leaf;
            this.keys = new int[2 * t - 1];
            this.child = new Node[2 * t];
            this.n = 0;
        }

        int findKey(int k) {
            int idx = 0;
            while (idx < n && keys[idx] < k) idx++;
            return idx;
        }

        void insertNonFull(int k) {
            int i = n - 1;
            if (leaf) {
                while (i >= 0 && keys[i] > k) { keys[i + 1] = keys[i]; i--; }
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
            Node z = new Node(y.t, y.leaf);
            z.n = t - 1;
            for (int j = 0; j < t - 1; j++) z.keys[j] = y.keys[j + t];
            if (!y.leaf) for (int j = 0; j < t; j++) z.child[j] = y.child[j + t];
            y.n = t - 1;
            for (int j = n; j >= i + 1; j--) child[j + 1] = child[j];
            child[i + 1] = z;
            for (int j = n - 1; j >= i; j--) keys[j + 1] = keys[j];
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
            int idx = findKey(k);
            if (idx < n && keys[idx] == k) {
                if (leaf) removeFromLeaf(idx);
                else removeFromNonLeaf(idx);
            } else {
                if (leaf) { System.out.println("Key not found."); return; }
                boolean flag = (idx == n);
                if (child[idx].n < t) fill(idx);
                if (flag && idx > n) child[idx - 1].remove(k);
                else child[idx].remove(k);
            }
        }

        void removeFromLeaf(int idx) {
            for (int i = idx + 1; i < n; i++) keys[i - 1] = keys[i];
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
            Node cur = child[idx];
            while (!cur.leaf) cur = cur.child[cur.n];
            return cur.keys[cur.n - 1];
        }

        int getSucc(int idx) {
            Node cur = child[idx + 1];
            while (!cur.leaf) cur = cur.child[0];
            return cur.keys[0];
        }

        void fill(int idx) {
            if (idx != 0 && child[idx - 1].n >= t) borrowFromPrev(idx);
            else if (idx != n && child[idx + 1].n >= t) borrowFromNext(idx);
            else merge(idx != n ? idx : idx - 1);
        }

        void borrowFromPrev(int idx) {
            Node c = child[idx], s = child[idx - 1];
            for (int i = c.n - 1; i >= 0; i--) c.keys[i + 1] = c.keys[i];
            if (!c.leaf) for (int i = c.n; i >= 0; i--) c.child[i + 1] = c.child[i];
            c.keys[0] = keys[idx - 1];
            if (!c.leaf) c.child[0] = s.child[s.n];
            keys[idx - 1] = s.keys[s.n - 1];
            c.n++; s.n--;
        }

        void borrowFromNext(int idx) {
            Node c = child[idx], s = child[idx + 1];
            c.keys[c.n] = keys[idx];
            if (!c.leaf) c.child[c.n + 1] = s.child[0];
            keys[idx] = s.keys[0];
            for (int i = 1; i < s.n; i++) s.keys[i - 1] = s.keys[i];
            if (!s.leaf) for (int i = 1; i <= s.n; i++) s.child[i - 1] = s.child[i];
            c.n++; s.n--;
        }

        void merge(int idx) {
            Node c = child[idx], s = child[idx + 1];
            c.keys[t - 1] = keys[idx];
            for (int i = 0; i < s.n; i++) c.keys[i + t] = s.keys[i];
            if (!c.leaf) for (int i = 0; i <= s.n; i++) c.child[i + t] = s.child[i];
            for (int i = idx + 1; i < n; i++) keys[i - 1] = keys[i];
            for (int i = idx + 2; i <= n; i++) child[i - 1] = child[i];
            c.n += s.n + 1;
            n--;
        }
    }

    static class BTree {
        Node root;
        int t;

        BTree(int t) { this.t = t; }

        void traverse() { if (root != null) root.traverse(); System.out.println(); }

        void insert(int k) {
            if (root == null) {
                root = new Node(t, true);
                root.keys[0] = k;
                root.n = 1;
            } else if (root.n == 2 * t - 1) {
                Node s = new Node(t, false);
                s.child[0] = root;
                s.splitChild(0, root);
                int i = s.keys[0] < k ? 1 : 0;
                s.child[i].insertNonFull(k);
                root = s;
            } else {
                root.insertNonFull(k);
            }
        }

        void delete(int k) {
            if (root == null) return;
            root.remove(k);
            if (root.n == 0) root = root.leaf ? null : root.child[0];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter minimum degree (t): ");
        BTree bt = new BTree(sc.nextInt());

        int ch;
        do {
            System.out.println("\n1.Insert  2. Delete  3. Display  4. Exit");
            System.out.print("Choice: ");
            ch = sc.nextInt();
            if (ch == 1) {
                System.out.print("Value to insert: ");
                bt.insert(sc.nextInt());
            } else if (ch == 2) {
                System.out.print("Value to delete: ");
                bt.delete(sc.nextInt());
            } else if (ch == 3) {
                System.out.print("Tree: ");
                bt.traverse();
            }
        } while (ch != 4);
        sc.close();
    }
}