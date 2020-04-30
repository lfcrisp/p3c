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
    private static final String MARKERANNOTATION = "Annotation/MarkerAnnotation/Name";
    private static final String METHODDECLARATOR = "MethodDeclaration";

    public Object visit(ASTClassOrInterfaceBody node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CLASSORINTERFACE);
            //List<Node> methodDeclarator = node.findChildNodesWithXPath(METHODDECLARATOR);
            if (markerAnnotations != null || markerAnnotations.size() > 0) {
                for (Node markerAnnotation:markerAnnotations) {
                    if (markerAnnotation != null){
                        Object o = visit(markerAnnotation, data);
                    }
                    String ss = markerAnnotation.getFirstChildOfType(ASTMarkerAnnotation.class).getAnnotationName();
                    if ("override".equals(markerAnnotation.getFirstChildOfType(ASTMarkerAnnotation.class).getAnnotationName().toLowerCase())){
                        System.out.println("hahahahahah");
                    }
                }
            }
            /*ASTMethodDeclaration methodDeclaratorNode = (ASTMethodDeclaration)markerAnnotation.get(0);
            String methodDeclaratorName = methodDeclaratorNode.getName();
            if ("equals".equals(methodDeclaratorName.toLowerCase()) ){
                //String markerAnnotationName = markerAnnotation.get(0).getImage();
                if (markerAnnotation == null || markerAnnotation.isEmpty()){
                    addViolationWithMessage(data, node,
                            "java.zyzx.EqualsOverridenWithHashCodeCheck.rule.msg", null);
                }
            }*/
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + METHODDECLARATOR + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

