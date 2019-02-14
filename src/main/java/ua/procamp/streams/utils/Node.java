package ua.procamp.streams.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {

    private Integer itemValue;
    private Node next;
    private Node prev;

    public Node(Node prev, Integer itemValue) {
        this.itemValue = itemValue;
        this.prev = prev;
    }

}
