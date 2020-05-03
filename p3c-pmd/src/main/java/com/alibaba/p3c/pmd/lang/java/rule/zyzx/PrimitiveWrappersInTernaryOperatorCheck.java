package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 不同的原始包装类如果没有明确的类转换不能用于三元操作中
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class PrimitiveWrappersInTernaryOperatorCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "ClassOrInterfaceDeclaration[@Image='Watermelon']/ClassOrInterfaceBody/ClassOrInterfaceBodyDeclaration/FieldDeclaration/VariableDeclarator[./VariableDeclaratorId[@Image='n']]";

    public Object visit(ASTClassOrInterfaceBodyDeclaration node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.PrimitiveWrappersInTernaryOperatorCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

