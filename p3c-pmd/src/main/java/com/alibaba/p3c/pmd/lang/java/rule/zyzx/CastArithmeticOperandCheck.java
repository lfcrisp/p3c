package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 数字操作在操作或赋值前要转化,对整数执行算术运算时，结果将始终为整数。 可以通过自动类型转换将该结果分配给long，double或float，
 * 但是以int或long形式开始的结果可能不会达到您的期望。例如，如果将int除法的结果分配给浮点变量，则在分配之前精度将丢失。
 * 同样，如果将乘法结果分配给long，则在分配之前它可能已经溢出。无论哪种情况，结果都不会是预期的。 取而代之的是，
 * 在进行操作之前，至少应将一个操作数强制转换或提升为最终类型。
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class CastArithmeticOperandCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//MethodDeclaration[./MethodDeclarator[@Image='Watermelon']]/Block/BlockStatement[./LocalVariableDeclaration/Type/PrimitiveType[@Image='long']][./LocalVariableDeclaration/VariableDeclarator/VariableDeclaratorId[@Image='millisInYear']]";

    @Override
    public Object visit(ASTClassOrInterfaceBodyDeclaration node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty()){
                    addViolationWithMessage(data, node,
                            "java.zyzx.CastArithmeticOperandCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

