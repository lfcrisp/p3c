package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTCompilationUnit;
import net.sourceforge.pmd.lang.java.ast.ASTNullLiteral;
import net.sourceforge.pmd.lang.java.ast.ASTStatement;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 不应该做的equals检查.
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class SillyEqualsCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//IfStatement/Expression/PrimaryExpression[./PrimaryPrefix/Name[contains(@Image,'.equals')]]/PrimarySuffix/Arguments/ArgumentList/Expression/PrimaryExpression/PrimaryPrefix/Literal/NullLiteral";

    public Object visit(ASTNullLiteral node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.SillyEqualsCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

