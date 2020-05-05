package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTCompilationUnit;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * Servlet不应具有可变的实例字段.
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class ServletInstanceFieldCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//TypeDeclaration/ClassOrInterfaceDeclaration/ClassOrInterfaceBody/ClassOrInterfaceBodyDeclaration/ClassOrInterfaceDeclaration[./ExtendsList/ClassOrInterfaceType[@Image='HttpServlet' or @Image='Action']]/ClassOrInterfaceBody/ClassOrInterfaceBodyDeclaration/FieldDeclaration[@Static='false' and @Final='false']";

    public Object visit(ASTCompilationUnit node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.ServletInstanceFieldCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

