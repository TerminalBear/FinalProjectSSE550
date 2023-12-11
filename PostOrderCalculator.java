import java.util.Stack;

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
                case '^':
               return Math.pow(left, right);
                case '~' :
                	 if (right == 0) {
                         throw new IllegalArgumentException(" cant't root a number by 0");
                     }
                	 return Math.pow(left, 1/right);
                
        }
    }
    public double calculatePostOrder(String[] tokens) {
        Stack<Double> stack = new Stack<>();

        for (String token : tokens) {
            if ("+-*/^~".contains(token)) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid postfix expression");
                }
         
                double rightOperand = stack.pop();
                double leftOperand = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(leftOperand + rightOperand);
                        break;
                    case "-":
                        stack.push(leftOperand - rightOperand);
                        break;
                    case "*":
                        stack.push(leftOperand * rightOperand);
                        break;
                    case "/":
                        if (rightOperand == 0) {
                            throw new IllegalArgumentException("Division by zero");
                        }
                        stack.push(leftOperand / rightOperand);
                        break;
                    case "^":
                    stack.push(Math.pow(leftOperand,rightOperand));
                
                    break;
                    case "~":
                    	   if (rightOperand == 0) {
                               throw new IllegalArgumentException("cant do root of zero");
                           }
                    	   stack.push(Math.pow(leftOperand,1/rightOperand));
                        
                    
                        break;


                }
            } else {
                stack.push(Double.parseDouble(token));
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid postfix expression");
        }

        return stack.pop();
    }
}
