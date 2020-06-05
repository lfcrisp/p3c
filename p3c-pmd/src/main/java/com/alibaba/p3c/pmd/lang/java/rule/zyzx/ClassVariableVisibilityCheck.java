package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBody;
import net.sourceforge.pmd.lang.java.ast.ASTVariableDeclarator;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 类变量域应该是private,通过set，get进行操作
 * @author lifengchen
 * @date 2020-06-03 11:51:14
 */

public class ClassVariableVisibilityCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//ClassOrInterfaceDeclaration[@Image='Watermelon']/ClassOrInterfaceBody/ClassOrInterfaceBodyDeclaration/FieldDeclaration/VariableDeclarator[./VariableDeclaratorId[@Image='firstName']]";

    public Object visit(ASTVariableDeclarator node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.ClassVariableVisibilityCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

