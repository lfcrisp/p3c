package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTBlockStatement;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBody;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 可变字段不应为“public static”
 * @author lifengchen
 * @date 2020-06-03 11:51:14
 */

public class PublicStaticMutableMembersCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//ClassOrInterfaceDeclaration[@Image='Watermelon']/ClassOrInterfaceBody/ClassOrInterfaceBodyDeclaration//FieldDeclaration/VariableDeclarator[./VariableDeclaratorId[contains(@Image,'strings')]]";

    public Object visit(ASTClassOrInterfaceBody node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                addViolationWithMessage(data, node,
                        "java.zyzx.PublicStaticMutableMembersCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

