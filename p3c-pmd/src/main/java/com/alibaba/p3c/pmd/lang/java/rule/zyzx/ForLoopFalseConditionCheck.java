package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTBlock;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 循环应该至少走一次.
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class ForLoopFalseConditionCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM1 = "//MethodDeclaration/Block/BlockStatement/Statement/ForStatement/ForInit/LocalVariableDeclaration/VariableDeclarator/VariableInitializer/Expression/PrimaryExpression/PrimaryPrefix/Literal";
    private static final String CHECKMETHODNUM2 = "//MethodDeclaration/Block/BlockStatement/Statement/ForStatement/Expression/RelationalExpression/PrimaryExpression/PrimaryPrefix/Literal";

    public Object visit(ASTClassOrInterfaceBodyDeclaration node, Object data) {
        try {
            List<Node> markerAnnotations1 = node.findChildNodesWithXPath(CHECKMETHODNUM1);
            List<Node> markerAnnotations2 = node.findChildNodesWithXPath(CHECKMETHODNUM2);
            if ( !markerAnnotations1.isEmpty() && !markerAnnotations2.isEmpty()){
                if(markerAnnotations1.get(0).getImage().equals(markerAnnotations2.get(0).getImage())){
                    addViolationWithMessage(data, node,
                            "java.zyzx.ForLoopFalseConditionCheck.rule.msg", null);
                }
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM1 + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

