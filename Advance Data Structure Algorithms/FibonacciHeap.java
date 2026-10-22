
import java.util.ArrayList;
import java.util.List;

public class FibonacciHeap {

    public static class Node {
        int key;
        int degree;
        boolean mark;

        Node parent;
        Node child;
        Node left;
        Node right;

        private FibonacciHeap owner;

        Node(int key, FibonacciHeap owner) {
            this.key = key;
            this.owner = owner;
            this.left = this;
            this.right = this;
        }

        public int getKey() {
            return key;
        }
    }

    private Node min;
    private int size;

    public boolean isEmpty() {
        return min == null;
    }

    public int size() {
        return size;
    }

    // 1. INSERT
    public Node insert(int key) {
        Node x = new Node(key, this);

        addRoot(x);
        size++;

        return x;
    }

    // 2. FIND MINIMUM
    public Integer findMin() {
        return min == null ? null : min.key;
    }

    // 3. MERGE TWO HEAPS
    // The input heaps are consumed by this operation.
    public static FibonacciHeap merge(
            FibonacciHeap h1, FibonacciHeap h2) {

        if (h1 == null || h2 == null || h1 == h2) {
            throw new IllegalArgumentException(
                "Provide two different, non-null heaps.");
        }

        FibonacciHeap result = new FibonacciHeap();

        if (h1.min == null) {
            result.min = h2.min;
        } else if (h2.min == null) {
            result.min = h1.min;
        } else {
            Node a = h1.min;
            Node b = h2.min;

            // Concatenate circular doubly linked root lists.
            Node aRight = a.right;
            Node bLeft = b.left;

            a.right = b;
            b.left = a;

            bLeft.right = aRight;
            aRight.left = bLeft;

            result.min = a.key <= b.key ? a : b;
        }

        result.size = h1.size + h2.size;

        // Transfer node ownership to the result.
        for (Node root : roots(result.min)) {
            transferOwnership(root, result);
        }

        h1.min = null;
        h1.size = 0;

        h2.min = null;
        h2.size = 0;

        return result;
    }

    private static void transferOwnership(
            Node root, FibonacciHeap heap) {

        for (Node node : treeNodes(root)) {
            node.owner = heap;
        }
    }

    // 4. EXTRACT MINIMUM
    public Integer extractMin() {
        Node z = min;

        if (z == null) {
            return null;
        }

        // Promote all children of z to the root list.
        if (z.child != null) {
            List<Node> children = roots(z.child);

            for (Node x : children) {
                removeFromList(x);

                x.parent = null;
                x.mark = false;

                addRoot(x);
            }

            z.child = null;
            z.degree = 0;
        }

        // Remove z from the root list.
        if (z.right == z) {
            min = null;
        } else {
            Node next = z.right;
            removeFromList(z);
            min = next;
        }

        size--;

        if (min != null) {
            consolidate();
        }

        detach(z);
        z.owner = null;

        return z.key;
    }

    // Consolidate trees having equal degrees.
    private void consolidate() {
        List<Node> oldRoots = roots(min);
        List<Node> degreeTable = new ArrayList<>();

        for (Node w : oldRoots) {
            // This root may already have become a child.
            if (w.parent != null) {
                continue;
            }

            Node x = w;
            int d = x.degree;

            while (true) {
                ensureCapacity(degreeTable, d + 1);

                Node y = degreeTable.get(d);

                if (y == null) {
                    break;
                }

                if (x.key > y.key) {
                    Node temp = x;
                    x = y;
                    y = temp;
                }

                degreeTable.set(d, null);

                // Smaller key remains the parent.
                link(y, x);

                d = x.degree;
            }

            ensureCapacity(degreeTable, d + 1);
            degreeTable.set(d, x);
        }

        // Rebuild the root list.
        min = null;

        for (Node x : degreeTable) {
            if (x != null && x.parent == null) {
                x.left = x;
                x.right = x;
                addRoot(x);
            }
        }
    }

    // Link y under x.
    private void link(Node y, Node x) {
        removeFromList(y);

        y.parent = x;
        y.mark = false;

        if (x.child == null) {
            x.child = y;
            y.left = y;
            y.right = y;
        } else {
            insertBefore(x.child, y);
        }

        x.degree++;
    }

    // 5. DECREASE KEY
    public void decreaseKey(Node x, int newKey) {
        checkNode(x);

        if (newKey > x.key) {
            throw new IllegalArgumentException(
                "New key must not exceed the current key.");
        }

        x.key = newKey;

        Node parent = x.parent;

        if (parent != null && x.key < parent.key) {
            cut(x, parent);
            cascadingCut(parent);
        }

        if (min == null || x.key < min.key) {
            min = x;
        }
    }

    // Cut x from its parent and move it to the root list.
    private void cut(Node x, Node parent) {
        if (parent.child == x) {
            parent.child =
                (x.right == x) ? null : x.right;
        }

        removeFromList(x);
        parent.degree--;

        x.parent = null;
        x.mark = false;

        addRoot(x);
    }

    // Perform cascading cuts when necessary.
    private void cascadingCut(Node y) {
        Node parent = y.parent;

        if (parent == null) {
            return;
        }

        if (!y.mark) {
            y.mark = true;
        } else {
            cut(y, parent);
            cascadingCut(parent);
        }
    }

    // 6. DELETE A SPECIFIC NODE
    public void delete(Node x) {
        checkNode(x);

        // Cut the node from its parent, if necessary.
        if (x.parent != null) {
            Node parent = x.parent;
            cut(x, parent);
            cascadingCut(parent);
        }

        // Remove x from the root list.
        if (x == min) {
            if (x.right == x) {
                min = null;
            } else {
                Node next = x.right;
                removeFromList(x);
                min = next;
            }
        } else {
            removeFromList(x);
        }

        // Promote x's children.
        if (x.child != null) {
            List<Node> children = roots(x.child);

            for (Node child : children) {
                removeFromList(child);

                child.parent = null;
                child.mark = false;

                addRoot(child);
            }
        }

        size--;

        if (min != null) {
            consolidate();
        }

        detach(x);
        x.owner = null;
    }

    // Add a node to the root list.
    private void addRoot(Node x) {
        x.parent = null;

        if (min == null) {
            x.left = x;
            x.right = x;
            min = x;
        } else {
            insertBefore(min, x);

            if (x.key < min.key) {
                min = x;
            }
        }
    }

    // Insert x immediately before reference.
    private static void insertBefore(
            Node reference, Node x) {

        x.left = reference.left;
        x.right = reference;

        reference.left.right = x;
        reference.left = x;
    }

    // Remove x from a circular doubly linked list.
    private static void removeFromList(Node x) {
        x.left.right = x.right;
        x.right.left = x.left;

        x.left = x;
        x.right = x;
    }

    // Snapshot a circular list before modifying it.
    private static List<Node> roots(Node start) {
        List<Node> result = new ArrayList<>();

        if (start == null) {
            return result;
        }

        Node current = start;

        do {
            result.add(current);
            current = current.right;
        } while (current != start);

        return result;
    }

    private static void ensureCapacity(
            List<Node> list, int capacity) {

        while (list.size() < capacity) {
            list.add(null);
        }
    }

    private static void detach(Node x) {
        x.parent = null;
        x.child = null;
        x.degree = 0;
        x.mark = false;
        x.left = x;
        x.right = x;
    }

    private void checkNode(Node x) {
        if (x == null || x.owner != this) {
            throw new IllegalArgumentException(
                "Node does not belong to this heap.");
        }
    }

    private static List<Node> treeNodes(Node root) {
        List<Node> result = new ArrayList<>();
        if (root != null) {
            collectTree(root, result);
        }
        return result;
    }

    private static void collectTree(
            Node start, List<Node> result) {

        for (Node node : roots(start)) {
            result.add(node);

            if (node.child != null) {
                collectTree(node.child, result);
            }
        }
    }

    // 7. DISPLAY THE HEAP
    public void display() {
        if (min == null) {
            System.out.println("Heap is empty.");
            return;
        }

        System.out.println(
            "Minimum = " + min.key + ", Size = " + size);
        System.out.println("Root list:");

        for (Node root : roots(min)) {
            displayTree(root, "");
        }
    }

    private void displayTree(Node node, String indent) {
        System.out.println(
            indent + node.key
            + " (degree=" + node.degree
            + (node.mark ? ", marked" : "") + ")");

        if (node.child != null) {
            for (Node child : roots(node.child)) {
                displayTree(child, indent + "  ");
            }
        }
    }

    // 8. TEST ALL OPERATIONS
    public static void main(String[] args) {
        FibonacciHeap heap = new FibonacciHeap();

        Node seven = heap.insert(7);
        heap.insert(3);
        heap.insert(10);
        Node eight = heap.insert(8);
        heap.insert(2);

        System.out.println("After insertions:");
        heap.display();

        System.out.println("\nFind minimum: " + heap.findMin());

        System.out.println(
            "\nExtract minimum: " + heap.extractMin());
        heap.display();

        System.out.println("\nDecrease 8 to 1:");
        heap.decreaseKey(eight, 1);
        heap.display();

        System.out.println("\nDelete node 7:");
        heap.delete(seven);
        heap.display();

        FibonacciHeap other = new FibonacciHeap();
        other.insert(4);
        other.insert(12);

        heap = FibonacciHeap.merge(heap, other);

        System.out.println("\nAfter merging heaps:");
        heap.display();

        System.out.println("\nExtract all remaining values:");

        Integer value;

        while ((value = heap.extractMin()) != null) {
            System.out.print(value + " ");
        }

        System.out.println();
    }
}
