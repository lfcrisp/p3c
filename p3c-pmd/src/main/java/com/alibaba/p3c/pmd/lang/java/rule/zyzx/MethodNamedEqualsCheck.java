package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.*;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * equals作为方法名应该仅用于重写Object.equals(Object)来避免混乱.
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class MethodNamedEqualsCheck extends AbstractAliRule {

    private static final String MARKERANNOTATION = "Annotation/MarkerAnnotation/Name";
    private static final String METHODDECLARATOR = "MethodDeclaration";

    public Object visit(ASTClassOrInterfaceBodyDeclaration node, Object data) {
        try {
            List<Node> markerAnnotation = node.findChildNodesWithXPath(MARKERANNOTATION);
            List<Node> methodDeclarator = node.findChildNodesWithXPath(METHODDECLARATOR);
            if (methodDeclarator == null || methodDeclarator.isEmpty()) {
                return super.visit(node, data);
            }
            ASTMethodDeclaration methodDeclaratorNode = (ASTMethodDeclaration)methodDeclarator.get(0);
            String methodDeclaratorName = methodDeclaratorNode.getName();
            if ("equals".equals(methodDeclaratorName.toLowerCase()) ){
                //String markerAnnotationName = markerAnnotation.get(0).getImage();
                if (markerAnnotation == null || markerAnnotation.isEmpty()){
                    addViolationWithMessage(data, node,
                            "java.zyzx.MethodNamedEqualsCheck.rule.msg", null);
                }
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + METHODDECLARATOR + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

