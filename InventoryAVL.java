class Node {
    int val, h = 1; 
    Node left, right;
    Node(int v) { val = v; }
}

public class InventoryAVL {
    int height(Node n) { return n == null ? 0 : n.h; }
    int balance(Node n) { return n == null ? 0 : height(n.left) - height(n.right); }
    
    Node leftRotate(Node x) {
        Node y = x.right; Node T2 = y.left;
        y.left = x; x.right = T2;
        x.h = Math.max(height(x.left), height(x.right)) + 1;
        y.h = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    public Node insert(Node node, int val) {
        if (node == null) return new Node(val);
        if (val < node.val) node.left = insert(node.left, val);
        else node.right = insert(node.right, val);
        
        node.h = 1 + Math.max(height(node.left), height(node.right));
        int b = balance(node);
        
        if (b < -1 && val > node.right.val) return leftRotate(node);
        return node;
    }

    public static void main(String[] args) {
        InventoryAVL tree = new InventoryAVL();
        Node root = null;
        root = tree.insert(root, 10);
        root = tree.insert(root, 20);
        root = tree.insert(root, 30); // Triggers Left Rotation
        
        System.out.println("Balanced Root: " + root.val);
        System.out.println("Left Child: " + root.left.val);
        System.out.println("Right Child: " + root.right.val);
    }
}