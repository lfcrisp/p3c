package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTBlock;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 正确使用日期.
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class InvalidDateValuesCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "BlockStatement[./Statement/StatementExpression/PrimaryExpression/PrimaryPrefix/Name[contains(@Image,'.setMonth')]]//Literal[@Image='12']";

    public Object visit(ASTBlock node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.InvalidDateValuesCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

