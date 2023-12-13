public class PreOrderCalculator {
    public double calculatePreOrder(Node node) {
        if (node == null) {
            return 0;
        }

        if (node.operator == null) {
            return node.operand;
        }

        double left = calculatePreOrder(node.left);
        double right = calculatePreOrder(node.right);

        switch (node.operator) {
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
            case '^':
            	Math.pow(left, right);
            case '~' :
            	  if (right == 0) {
                      throw new IllegalArgumentException("Cannot root a number by zero");
                  }
            	  Math.pow(left, 1/right);
            case '/':
                if (right == 0) {
                    throw new IllegalArgumentException("Cannot divide by zero");
                }
                return left / right;
           
            
            case 'S':
               return Math.sin(left);
            case 'C' :
                 	 
                 	 return Math.cos(left);
            default:
                throw new IllegalArgumentException("Invalid operator: " + node.operator);
        }
    }
}
