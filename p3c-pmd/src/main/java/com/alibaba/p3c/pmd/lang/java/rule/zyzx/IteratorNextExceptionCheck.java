package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTAnnotation;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBody;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 当迭代中没有更多元素时，java.util.Iterator.next（）方法的任何实现都会引发NoSuchElementException异常。
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class IteratorNextExceptionCheck extends AbstractAliRule {

    private static final String CLASSORINTERFACE = "ImplementsList/ClassOrInterfaceType";
    private static final String IFSTATEMENTNAME = "ClassOrInterfaceBody/ClassOrInterfaceBodyDeclaration/MethodDeclaration/Block/BlockStatement/Statement/IfStatement/Expression/UnaryExpressionNotPlusMinus/PrimaryExpression/PrimaryPrefix/Name";
    private static final String RETURNSTATEMENTNAME = "ClassOrInterfaceBody/ClassOrInterfaceBodyDeclaration/MethodDeclaration/Block/BlockStatement/Statement/IfStatement/Statement/Block/BlockStatement/Statement/ThrowStatement/Expression/PrimaryExpression/PrimaryPrefix/AllocationExpression/ClassOrInterfaceType";

    public Object visit(ASTClassOrInterfaceDeclaration node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CLASSORINTERFACE);
            List<Node> ifStatementNames = node.findChildNodesWithXPath(IFSTATEMENTNAME);
            List<Node> returnStatementNames = node.findChildNodesWithXPath(RETURNSTATEMENTNAME);
            if (markerAnnotations != null && markerAnnotations.size() > 0 ) {
             if("Iterator".equals(markerAnnotations.get(0).getImage())){
                if ("hasNext".equals( ifStatementNames.get(0).getImage())){
                    if (returnStatementNames.isEmpty()){
                        addViolationWithMessage(data, node,
                                "java.zyzx.IteratorNextExceptionCheck.rule.msg", null);
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

