package com.study.flowable;

import java.util.List;

public class Soulution {

    public static void main(String[] args) {
        ListNode head=new ListNode(4);
        head.next=new ListNode(3);
        head.next.next=new ListNode(2);
        head.next.next.next=new ListNode(1);
        new Soulution().insertionSortList(head);
        System.out.print("测试");
    }

    public ListNode insertionSortList(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode headPoint=new ListNode(-1);
        headPoint.next=head;
        ListNode LastSorted=head,curr=head.next;
        while (curr!=null){
            if(curr.val>=LastSorted.val){
                LastSorted=curr;
            }else{
                ListNode prev=headPoint;
                while(prev.next.val<=curr.val){
                    prev=prev.next;
                }
                LastSorted.next=curr.next;
                curr.next=prev.next;
                prev.next=curr;
            }
            curr=LastSorted.next;
        }
        return headPoint.next;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}