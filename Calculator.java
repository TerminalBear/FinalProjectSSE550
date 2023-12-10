public class Calculator {
    public double calculate(Node node) {
        if (node == null) {
            return 0;
        }

        if (node.operator == null) {
            return node.operand;
        }

        switch (node.operator) {
            case '+':
                return calculate(node.left) + calculate(node.right);
            case '-':
                return calculate(node.left) - calculate(node.right);
            case '*':
                return calculate(node.left) * calculate(node.right);
            case '^' :
            	return Math.pow(calculate(node.left),calculate(node.right));
            case '/':
     
                if (node.right.operand == 0) {
                    throw new IllegalArgumentException("Cannot divide by zero.");
                }
                return calculate(node.left) / calculate(node.right);
            default:
                throw new IllegalArgumentException("Invalid operator: " + node.operator);
        }
    }
    
}
