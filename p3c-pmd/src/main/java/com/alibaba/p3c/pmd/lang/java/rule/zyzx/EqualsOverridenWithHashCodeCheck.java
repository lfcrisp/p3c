package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.*;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * Equals和hashCode必须成对重写，只重写任何一个都是错误的。
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class EqualsOverridenWithHashCodeCheck extends AbstractAliRule {

    private static final String CLASSORINTERFACE = "ClassOrInterfaceBodyDeclaration";

    @Override
    public Object visit(ASTClassOrInterfaceBody node, Object data) {
        try {
            Boolean hasEquals = null;
            Boolean hasCode = null;
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CLASSORINTERFACE);
            if (markerAnnotations != null || markerAnnotations.size() > 0) {
                for (int i = 0; i < markerAnnotations.size(); i++) {
                    Node markerAnnotation = markerAnnotations.get(i);
                    ASTAnnotation astAnnotation = markerAnnotation.getFirstChildOfType(ASTAnnotation.class);
                    if (astAnnotation != null){
                        String firstChildOfType = astAnnotation.getType().getName();
                        if (firstChildOfType.toLowerCase().endsWith("override")){
                            String methodName = markerAnnotation.jjtGetChild(1).jjtGetChild(1).getImage();
                            if ("hashCode".equals(methodName) && hasCode == null){
                                hasCode = true;
                            }
                            if ("equals".equals(methodName) && hasEquals == null){
                                hasEquals = true;
                            }
                        }
                    }
                }
            }
            boolean result = false;
            if (hasCode == null){hasCode = false;}
            if (hasEquals == null){hasEquals =false;}
           if (hasCode.equals(hasEquals)){
                result = true;
            }
            if (!result){
                addViolationWithMessage(data, node,
                        "java.zyzx.EqualsOverridenWithHashCodeCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CLASSORINTERFACE + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);

        /*try {
            List<Node> nodeList = node.findChildNodesWithXPath(
                    "//ClassOrInterfaceBodyDeclaration[./Annotation[@AnnotationName = 'Override']]" +
                            "//MethodDeclaration[@MethodName = 'hashCode' or @MethodName = 'equals']");
            if (nodeList.size() == 1) {
                addViolationWithMessage(data, nodeList.get(0),
                        "java.set.EqualsOverridenWithHashCodeCheck.rule.msg");
            }
        } catch (JaxenException e) {
            e.printStackTrace();
        }
        return super.visit(node, data);*/

    }
}

