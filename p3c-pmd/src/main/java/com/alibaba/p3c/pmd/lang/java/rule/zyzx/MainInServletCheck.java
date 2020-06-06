package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBody;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclarator;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 程序中不应有main方法
 * @author lifengchen
 * @date 2020-06-03 11:51:14
 */

public class MainInServletCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//ClassOrInterfaceDeclaration[//MethodDeclaration/MethodDeclarator[@Image='main']]";

    public Object visit(ASTClassOrInterfaceDeclaration node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                addViolationWithMessage(data, node,
                        "java.zyzx.MainInServletCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

