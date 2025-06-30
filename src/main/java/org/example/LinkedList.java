class Node {
        int data;
        Node next;

        Node(int new_data) {
            data = new_data;
            next = null;
        }

    // This function prints the contents
    // of the linked list starting from the head
    static void printList(Node node) {
        while (node != null) {
            System.out.print(" " + node.data);
            node = node.next;
        }
    }

    public static void main(String[] args) {

        // Create a hard-coded linked list:
        // 1 -> 2 -> 3 -> 4 -> 5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        System.out.print("Given Linked list:");
        printList(head);

        head = reverseList(head);

        System.out.print("\nReversed Linked List:");
        printList(head);

        Node head1 = new Node(1);
        head1.next = new Node(3);
        head1.next.next = new Node(4);

        // Create a loop
        //head1.next.next.next = head1.next;

        if (detectLoop(head1))
            System.out.println("true");
        else
            System.out.println("false");
    }

    private static Node reverseList(Node head) {
        Node prev = null;
        Node current = head;

        while(current != null ) {
            Node next = current.next;

            current.next = prev;

            prev = current;
            current = next;
        }

        return prev;
    }

    private static boolean detectLoop(Node head) {
            Node fast = head;
            Node slow = head;

            while(fast != null && slow != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;

                if(fast == slow) return true;
            }

            return false;
    }
}
