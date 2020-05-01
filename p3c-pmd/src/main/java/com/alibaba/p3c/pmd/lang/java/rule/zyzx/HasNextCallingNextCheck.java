package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTAnnotation;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBody;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * Iterator.next（）使迭代器前进一项。 因此，在Iterator.hasNext（）中调用它会破坏Iterator，并导致生产中出现意外行为。
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class HasNextCallingNextCheck extends AbstractAliRule {

    private static final String CLASSORINTERFACE = "ClassOrInterfaceBodyDeclaration";
    private static final String IFSTATEMENTNAME = "ClassOrInterfaceBodyDeclaration/MethodDeclaration/Block/BlockStatement/Statement/IfStatement/Expression/EqualityExpression/PrimaryExpression/PrimaryPrefix/Name";

    public Object visit(ASTClassOrInterfaceBody node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CLASSORINTERFACE);
            List<Node> ifStatementNames = node.findChildNodesWithXPath(IFSTATEMENTNAME);
            if (markerAnnotations != null && markerAnnotations.size() > 0 && !ifStatementNames.isEmpty()) {
                for (int i = 0; i < markerAnnotations.size(); i++) {
                    Node markerAnnotation = markerAnnotations.get(i);
                    ASTAnnotation astAnnotation = markerAnnotation.getFirstChildOfType(ASTAnnotation.class);
                    if (astAnnotation != null){
                        String firstChildOfType = astAnnotation.getType().getName();
                        if (firstChildOfType.toLowerCase().endsWith("override")){
                            String methodName = markerAnnotation.jjtGetChild(1).jjtGetChild(1).getImage();
                            if("hasNext".equals(methodName) && "next".equals(ifStatementNames.get(0).getImage())){
                                addViolationWithMessage(data, node,
                                        "java.zyzx.HasNextCallingNextCheck.rule.msg", null);
                            }
                        }
                    }
                }
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CLASSORINTERFACE + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

