/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.val, b.val));
        for(ListNode node : lists){
            if(node != null){
                pq.add(node);
            }
        }
            ListNode dumm = new ListNode(0);
            ListNode curr = dumm;
            while(!pq.isEmpty()){
                ListNode no = pq.poll();
                curr.next = no;
                curr = curr.next;
                if(no.next != null){
                    pq.add(no.next);
                }
            }
                return dumm.next;
            }

}