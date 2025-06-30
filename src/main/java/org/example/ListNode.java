package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ListNode {

    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }


    public static void main(String[] args) {
        ListNode node4 = new ListNode(4);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        ListNode node = swapPairs(node1);
    }


        public static ListNode swapPairs(ListNode head) {
            ListNode newHead = null;

            if(head == null || head.next == null) return head;

            ListNode prev = null;
            ListNode current = head; // 1
            ListNode next = head.next;  // 2

            newHead = next;

            while(current != null && next != null) {
                ListNode temp = next.next; // 3 // null

                current.next = temp;  // 1 - 3 - 3 - null
                next.next = current;  // 2 - 1 -- 4 -- 3

                if(prev != null) prev.next = next; // 2 - 1 - 4 - 3

                prev = current; // 1 3
                current = temp; // 3 null
                next = temp != null ? temp.next : null; // 4
            }


            return newHead;
        }

}