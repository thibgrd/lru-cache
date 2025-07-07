public class Node {

    int key;
    int val;
    Node prev, next;
    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }

    public void detachNode() {
        if (prev != null) {
            prev.next = next;
        }
        if (next != null) {
            next.prev = prev;
        }
        prev = null;
        next = null;
    }
}