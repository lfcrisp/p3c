package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTBlockStatement;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * Object.finalize()不要人为去调用.
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class ObjectFinalizeCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//Statement/StatementExpression/PrimaryExpression[./PrimaryPrefix[@ThisModifier='true']][./PrimarySuffix[@Image='finalize']]";

    @Override
    public Object visit(ASTBlockStatement node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.ObjectFinalizeCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

