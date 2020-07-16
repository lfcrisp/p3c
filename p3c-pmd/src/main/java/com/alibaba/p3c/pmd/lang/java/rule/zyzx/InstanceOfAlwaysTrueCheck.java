package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTStatement;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 该条件执行块从未被执行
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class InstanceOfAlwaysTrueCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "IfStatement/Expression/PrimaryExpression/PrimaryPrefix/Literal/BooleanLiteral[@True='false']";

    @Override
    public Object visit(ASTStatement node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.InstanceOfAlwaysTrueCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

