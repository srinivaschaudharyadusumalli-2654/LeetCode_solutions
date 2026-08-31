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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] answer = {-1, -1};
        if (head == null || head.next == null || head.next.next == null) {
            return answer;
        }
        ListNode prev = head;
        ListNode curr = head.next;
        int position = 1;
        int firstCritical = -1;
        int lastCritical = -1;
        int minDistance = Integer.MAX_VALUE;
        while (curr.next != null) {
            boolean isCritical =
                (curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val);
            if (isCritical) {
                if (firstCritical == -1) {
                    firstCritical = position;
                } else {
                    int distance = position - lastCritical;

                    minDistance = Math.min(minDistance, distance);
                }
                lastCritical = position;
            }
            prev = curr;
            curr = curr.next;
            position++;
        }
        if (firstCritical == lastCritical) {
            return answer;
        }
        int maxDistance = lastCritical - firstCritical;
        answer[0] = minDistance;
        answer[1] = maxDistance;
        return answer;
    }
}