package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTBlockStatement;
import net.sourceforge.pmd.lang.java.ast.ASTPrimaryExpression;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * runFinalizersOnExit本质上是不安全的,根据Oracle Javadoc：这可能会导致在活动对象上调用终结器，而其他线程正在同时操纵这些对象，从而导致行为不稳定或死锁,因不安全而被弃用.
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class RunFinalizersCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "Statement/StatementExpression/PrimaryExpression/PrimaryPrefix/Name[contains(@Image,'runFinalizersOnExit')]";

    @Override
    public Object visit(ASTBlockStatement node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty()){
                addViolationWithMessage(data, node,
                        "java.zyzx.RunFinalizersCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

