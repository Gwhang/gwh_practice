package com.example.demo.pattern.interpreter;

/**
 * 解释器模式（Interpreter Pattern）属于行为模式，它定义一个语言的文法，并且建立一个解释器来解释该语言中的句子，
 * 这里的“语言”是指使用规定格式和语法的代码。
 *
 * 解释器模式的四个角色
 * AbstractExpression（抽象表达式）：声明一个抽象的解释操作，这个方法为抽象语法树中所有的节点所共享。
 * TerminalExpression（终结符表达式）：实现与文法中的终结符相关的解释操作。比如 c = a + b，其中 a 和 b 就是终结符，而对应 a 和 b 的解释器就是终结符表达式。
 * NonterminalExpression（非终结符表达式）：为文法中的非终结符实现解释操作。比如 c = a + b 中，+就是非终结符，解析+的解释器就是一个非终结符表达式。
 * Context（环境角色）：含有解释器之外的全局信息。一般情况下是用来存放文法中各个终结符所对应的具体指，比如 c = a + b，我们给 a 赋值为 1，给 b 赋值为 2。这些信息需要存放到环境角色中，大多数情况我们是使用 Map 来充当环境角色的。
 *
 * 解释器模式的注意事项和细节
 * 当有一个语言需要解释执行，可将该语言中的句子表示为一个抽象语法树，就可以考虑使用解释器模式，让程序具有良好的扩展性。
 * 应用场景：编译器、运算表达式计算、正则表达式、机器人等。
 * 使用解释器可能带来的问题：解释器模式会引起类膨胀、解释器模式采用递归调用方法，将会导致调试非常复杂、效率可能降低。
 */
public class Test {

    public static void main(String[] args) {
        //(a*b)/(a-b+15000)
        Context context = new Context();
        Variable a = new Variable();
        Variable b = new Variable();
        Constant c = new Constant(15000);

        context.addValue(a, 14);
        context.addValue(b, 10000);

        Expression expression = new Div(new Mul(a, b), new Add(new Sub(a, b), c));
        System.out.println("Result = "+expression.interpret(context));
    }

}
