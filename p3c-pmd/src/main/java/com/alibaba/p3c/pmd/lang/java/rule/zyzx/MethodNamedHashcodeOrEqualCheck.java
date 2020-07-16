package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBody;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 方法不应命名为“ toString”，“ hashCode”或“ equals”,如果需这样命名则必须加@Override。
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class MethodNamedHashcodeOrEqualCheck extends AbstractAliRule {

    private static final String EQUALS_CHECKMETHODNUM = "//ClassOrInterfaceBodyDeclaration[./MethodDeclaration/MethodDeclarator[@Image='equals']][not(./Annotation[@Image=''])]";
    private static final String TOSTRING_CHECKMETHODNUM = "//ClassOrInterfaceBodyDeclaration[./MethodDeclaration/MethodDeclarator[@Image='toString']][not(./Annotation[@Image=''])]";
    private static final String HASHCODE_CHECKMETHODNUM = "//ClassOrInterfaceBodyDeclaration[./MethodDeclaration/MethodDeclarator[@Image='hashCode']][not(./Annotation[@Image=''])]";

    @Override
    public Object visit(ASTClassOrInterfaceBody node, Object data) {
        try {
            List<Node> markerAnnotationsEquals = node.findChildNodesWithXPath(EQUALS_CHECKMETHODNUM);
            List<Node> markerAnnotationsToString = node.findChildNodesWithXPath(TOSTRING_CHECKMETHODNUM);
            List<Node> markerAnnotationsHashCode = node.findChildNodesWithXPath(HASHCODE_CHECKMETHODNUM);
            if ( !markerAnnotationsEquals.isEmpty() || !markerAnnotationsToString.isEmpty() || !markerAnnotationsHashCode.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.MethodNamedHashcodeOrEqualCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + EQUALS_CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

