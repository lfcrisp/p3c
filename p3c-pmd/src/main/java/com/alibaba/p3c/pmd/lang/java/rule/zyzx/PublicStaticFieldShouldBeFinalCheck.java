package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.*;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * public static 域应该 final
 * @author lifengchen
 * @date 2020-06-03 11:51:14
 */

public class PublicStaticFieldShouldBeFinalCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//ClassOrInterfaceBody/ClassOrInterfaceBodyDeclaration/FieldDeclaration[@SyntacticallyFinal='false' and @SyntacticallyPublic='true']";

    @Override
    public Object visit(ASTClassOrInterfaceBody node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.PublicStaticFieldShouldBeFinalCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

