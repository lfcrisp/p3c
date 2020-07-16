package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTBlock;
import net.sourceforge.pmd.lang.java.ast.ASTTryStatement;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * finally块中使用return, break, throw等跳转语句，会阻止在try catch中抛出的未处理异常的传播.
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class ReturnInFinallyCheck extends AbstractAliRule {

    private static final String RETURNNUM = "//FinallyStatement//Statement/ReturnStatement";
    private static final String CHECKNUM = "//FinallyStatement//Statement/BreakStatement";
    private static final String THROWNUM = "//FinallyStatement//Statement/ThrowStatement";

    @Override
    public Object visit(ASTTryStatement node, Object data) {
        try {
            List<Node> returnNum = node.findChildNodesWithXPath(RETURNNUM);
            List<Node> checkNum = node.findChildNodesWithXPath(CHECKNUM);
            List<Node> throwNum = node.findChildNodesWithXPath(THROWNUM);
            if ( !returnNum.isEmpty() || !checkNum.isEmpty() || !throwNum.isEmpty()){
                    addViolationWithMessage(data, node,
                            "java.zyzx.ReturnInFinallyCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + RETURNNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

