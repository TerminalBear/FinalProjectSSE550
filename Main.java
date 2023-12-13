import java.util.*;
import java.io.IOException;
import java.util.logging.*;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    static {
        try {
            FileHandler fileHandler = new FileHandler("exceptions.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        PreOrderCalculator preOrderCalculator = new PreOrderCalculator();
        PostOrderCalculator postOrderCalculator = new PostOrderCalculator();
        int mode = 0; // 0 for normal, 1 for pre-order, 2 for post-order

        while (true) {
            try {
                System.out.println("Enter an expression (e.g., 1 + 2), or 'q' to quit:");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("q")) {
                    break;
                }

                Node root;

                if (mode == 1) {
                    String[] tokens = input.split("\\s+");
                    Queue<String> tokenQueue = new LinkedList<>(Arrays.asList(tokens));
                    root = buildExpressionTreePreOrder(tokenQueue);

                    if (!tokenQueue.isEmpty()) {
                        throw new IllegalArgumentException("Invalid pre-order expression");
                    }
                } else if (mode == 2) {
                    String[] tokens = input.split("\\s+");
                    Stack<String> tokenStack = new Stack<>();
                    tokenStack.addAll(Arrays.asList(tokens));
                    root = buildExpressionTreePostOrder(tokenStack);

                    if (!tokenStack.isEmpty()) {
                        throw new IllegalArgumentException("Invalid post-order expression");
                    }
                } else {
                    root = buildExpressionTree(input);
                }

                switch (mode) {
                    case 0:
                        System.out.println("Result: " + calculator.calculate(root));
                        break;
                    case 1:
                        System.out.println("Pre-order: " + preOrderCalculator.calculatePreOrder(root));
                        break;
                    case 2:
                        System.out.println("Post-order: " + postOrderCalculator.calculatePostOrder(root));
                        break;
                }

                System.out.println("Enter 'n' for normal mode, 'p' for pre-order mode, 'po' for post-order mode:");
                String modeInput = scanner.nextLine();

                switch (modeInput.toLowerCase()) {
                    case "n":
                        mode = 0;
                        break;
                    case "p":
                        mode = 1;
                        break;
                    case "po":
                        mode = 2;
                        break;
                    default:
                        System.out.println("Invalid mode. Staying in current mode.");
                }
            } catch (IllegalArgumentException e) {
                LOGGER.log(Level.SEVERE, "An error occurred", e);
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }

        scanner.close();
    }

    private static Node buildExpressionTree(String expression) {
        String[] tokens = expression.split("\\s+");
        Stack<String> operatorStack = new Stack<>();
        Stack<Node> operandStack = new Stack<>();

        for (String token : tokens) {
            if (isOperator(token)) {
                while (!operatorStack.isEmpty() && isOperator(operatorStack.peek())) {
                    Node right = operandStack.pop();
                    Node left = operandStack.pop();
                    Node node = new Node(operatorStack.pop().charAt(0));
                    node.left = left;
                    node.right = right;
                    operandStack.push(node);
                }
                operatorStack.push(token);
            } else {
                operandStack.push(new Node(Double.parseDouble(token)));
            }
        }

        while (!operatorStack.isEmpty()) {
            Node right = operandStack.pop();
            Node left = operandStack.pop();
            Node node = new Node(operatorStack.pop().charAt(0));
            node.left = left;
            node.right = right;
            operandStack.push(node);
        }

        return operandStack.pop();
    }

    private static Node buildExpressionTreePreOrder(Queue<String> tokens) {
        if (tokens.isEmpty()) {
            return null;
        }

        String token = tokens.poll();

        Node node;

        if (isOperator(token)) {
            node = new Node(token.charAt(0));
            node.left = buildExpressionTreePreOrder(tokens);
            node.right = buildExpressionTreePreOrder(tokens);

            if (node.left == null || node.right == null) {
                throw new IllegalArgumentException("Invalid pre-order expression");
            }
        } else {
            node = new Node(Double.parseDouble(token));
        }

        return node;
    }

    private static Node buildExpressionTreePostOrder(Stack<String> tokens) {
        if (tokens.isEmpty()) {
            return null;
        }

        String token = tokens.pop();

        Node node;

        if (isOperator(token)) {
            node = new Node(token.charAt(0));
            node.right = buildExpressionTreePostOrder(tokens);
            node.left = buildExpressionTreePostOrder(tokens);

            if (node.left == null || node.right == null) {
                throw new IllegalArgumentException("Invalid post-order expression");
            }
        } else {
            node = new Node(Double.parseDouble(token));
        }

        return node;
    }

    private static boolean isOperator(String token) {
        return "+-*/^~SC".contains(token);
    }
}
