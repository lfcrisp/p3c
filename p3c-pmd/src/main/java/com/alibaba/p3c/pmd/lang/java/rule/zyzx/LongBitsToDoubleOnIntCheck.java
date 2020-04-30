package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTLiteral;
import net.sourceforge.pmd.lang.java.ast.ASTName;
import net.sourceforge.pmd.lang.java.ast.ASTPrimaryPrefix;
import net.sourceforge.pmd.lang.java.ast.ASTVariableInitializer;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * Double.longBitsToDouble返回给定的位所代表的double值,需要一个64位的long类型参数.
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class LongBitsToDoubleOnIntCheck extends AbstractAliRule {

    private static final String LONGBITSTOTYPE = "Expression/PrimaryExpression/PrimaryPrefix";
    private static final String PARAMETERTYPE = "Expression/PrimaryExpression/PrimarySuffix/Arguments/ArgumentList/Expression/PrimaryExpression/PrimaryPrefix";

    public Object visit(ASTVariableInitializer node, Object data) {
        try {
            List<Node> longBitsType = node.findChildNodesWithXPath(LONGBITSTOTYPE);
            List<Node> parameterType = node.findChildNodesWithXPath(PARAMETERTYPE);
            if (longBitsType == null || longBitsType.isEmpty() || parameterType == null || parameterType.isEmpty()) {
                return super.visit(node, data);
            }
            ASTPrimaryPrefix expressionLongBits = (ASTPrimaryPrefix)longBitsType.get(0);
            ASTPrimaryPrefix expressionParameter = (ASTPrimaryPrefix)parameterType.get(0);
            //if ("double.longbitstodouble".equals(expressionLongBits.jjtGetChild(0).getImage().toLowerCase()) && isIntVariable(expressionParameter) || isIntLiteral(expressionParameter)){
            if ("double.longbitstodouble".equals(expressionLongBits.jjtGetChild(0).getImage().toLowerCase()) && isIntVariable(expressionParameter)){
                addViolationWithMessage(data, node,
                        "java.zyzx.LongBitsToDoubleOnIntCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + PARAMETERTYPE + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }

    private boolean isIntVariable(ASTPrimaryPrefix node) {
        ASTName name = node.getFirstChildOfType(ASTName.class);
        return name != null && "int".equals(name.getType().getName());
    }

    /*private boolean isIntLiteral(ASTPrimaryPrefix node) {
        ASTLiteral literal = node.getFirstChildOfType(ASTLiteral.class);
        return literal != null && literal.isIntLiteral();
    }*/

}

