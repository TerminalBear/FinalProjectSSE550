public class Node {
    Character operator;
    Double operand;
    Node left, right;

    Node(char operator) {
        this.operator = operator;
        this.operand = null;
        left = right = null;
    }

    Node(double operand) {
        this.operand = operand;
        this.operator = null;
        left = right = null;
    }
}
