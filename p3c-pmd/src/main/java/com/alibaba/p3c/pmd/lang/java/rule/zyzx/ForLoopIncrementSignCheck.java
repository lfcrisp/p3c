package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTStatement;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 检查for循环下标递增或递减正确,错误的写法将使循环运行比预期的次数多很多，从而可能导致意外的行为。
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class ForLoopIncrementSignCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "ForStatement[./ForUpdate//PostfixExpression[@Image='--']]";

    @Override
    public Object visit(ASTStatement node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.ForLoopIncrementSignCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

