package com.java8_in_action.chap14_functional_programming_techniques;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by sofia on 12/23/16.
 */
public class PatternMatching {

    static class Expr {}

    static class Number extends Expr {
        int val;

        public Number(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return ""+val;
        }
    }

    static class BinaryOp extends Expr {
        String opName;
        Expr left, right;

        public BinaryOp(String opName, Expr left, Expr right) {
            this.opName = opName;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "("+left+" "+opName+" "+right+")";
        }
    }

    static <T> T MyIf(boolean b, Supplier<T> trueCase, Supplier<T> falseCase) {
        return b ? trueCase.get() : falseCase.get();
    }

    static interface TriFunction<S, T, U, R> {
        R apply(S s, T t, U u);
    }

    static <T> T patternMatchExpr(
            Expr e,
            TriFunction<String, Expr, Expr, T> binaryOpCase,
            Function<Integer, T> numCase,
            Supplier<T> defaultCase) {

        if (e instanceof BinaryOp) {
            return binaryOpCase.apply(((BinaryOp)e).opName, ((BinaryOp)e).left, ((BinaryOp)e).right);
        } else if (e instanceof Number) {
            return numCase.apply(((Number)e).val);
        } else {
            return defaultCase.get();
        }
    }


    public static void simplify() {
        TriFunction<String, Expr, Expr, Expr> binaryOpCase = (opName, left, right) -> {
            if ("+".equals(opName)) {
                if (left instanceof Number && ((Number)left).val == 0) {
                    return right;
                }
                if (right instanceof Number && ((Number)right).val == 0) {
                    return left;
                }
            }
            if ("*".equals(opName)) {
                if (left instanceof Number && ((Number)left).val == 1) {
                    return right;
                }
                if (right instanceof Number && ((Number)right).val == 1) {
                    return left;
                }
            }
            return new BinaryOp(opName, left, right);
        };

        Function<Integer, Expr> numCase = val -> new Number(val);
        Supplier<Expr> defaultCase = () -> new Number(0);

        Expr e = new BinaryOp("+", new Number(5), new Number(0));
        Expr match = patternMatchExpr(e, binaryOpCase, numCase, defaultCase);

        if (match instanceof Number) {
            System.out.println("Number: "+match);
        } else if (match instanceof BinaryOp) {
            System.out.println("BinaryOp: "+match);
        }
    }

    public static Integer evaluate(Expr e) {
        Function<Integer, Integer> numCase = val -> val;
        Supplier<Integer> defaultCase = () -> 0;

        TriFunction<String, Expr, Expr, Integer> binaryOpCase = (opName, left, right) -> {
            if ("+".equals(opName)) {
                if (left instanceof Number && right instanceof Number) {
                    return ((Number)left).val + ((Number)right).val;
                }
                if (left instanceof Number && right instanceof BinaryOp) {
                    return ((Number)left).val + evaluate(right);
                }
                if (right instanceof Number && left instanceof BinaryOp) {
                    return ((Number)right).val + evaluate(left);
                }
                if (left instanceof BinaryOp && right instanceof BinaryOp) {
                    return evaluate(left) + evaluate(right);
                }
            }
            if ("*".equals(opName)) {
                if (left instanceof Number && right instanceof Number) {
                    return ((Number)left).val * ((Number)right).val;
                }
                if (left instanceof Number && right instanceof BinaryOp) {
                    return ((Number)left).val * evaluate(right);
                }
                if (right instanceof Number && left instanceof BinaryOp) {
                    return ((Number)right).val * evaluate(left);
                }
                if (left instanceof BinaryOp && right instanceof BinaryOp) {
                    return evaluate(left) * evaluate(right);
                }
            }
            return defaultCase.get();
        };

        return patternMatchExpr(e, binaryOpCase, numCase, defaultCase);
    }


    public static void main(String... args) {
        simplify();

        Expr e = new BinaryOp("+", new Number(5), new BinaryOp("*", new Number(3), new Number(4)));
        Integer result = evaluate(e);
        System.out.println(e+" = "+result);
    }

}
