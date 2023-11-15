public class PostOrderCalculator {
    public double calculatePostOrder(Node root) {
        if (root == null) {
            return 0;
        }

        if (root.operand != null) {
            return root.operand;
        }

        double left = calculatePostOrder(root.left);
        double right = calculatePostOrder(root.right);

        switch (root.operator) {
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
            case '/':
                if (right == 0) {
                    throw new IllegalArgumentException("Division by zero");
                }
                return left / right;
            default:
                throw new IllegalArgumentException("Invalid operator: " + root.operator);
        }
    }
}