package fil.car;

/**
 * Class Operation
 */
public class Operation {
    private int leftOperand;
    private char operator;
    private int rightOperand;

    /**
     * Constructor
     */
    public Operation() {
    }

    /**
     *Set the left operand for calcul
     * @param leftOperand (int)
     */
    public void setLeftOperand(int leftOperand) {
        this.leftOperand = leftOperand;
    }

    /**
     * Set the operantor for calcul
     * @param operator (Char)
     */
    public void setOperator(char operator) {
        this.operator = operator;
    }

    /**
     * Set the right operand for calcul
     * @param rightOperand (int)
     */
    public void setRightOperand(int rightOperand) {
        this.rightOperand = rightOperand;
    }

    /**
     * Calcul, with the appropriate operator, the operation
     * @return
     */
    public int calcul() {
        switch (operator) {
            case '+':
                return leftOperand + rightOperand;
            case '-':
                return leftOperand - rightOperand;
            case '/':
                return leftOperand / rightOperand;
            case '*':
                return leftOperand * rightOperand;
            default:
                return 0;
        }
    }
}
