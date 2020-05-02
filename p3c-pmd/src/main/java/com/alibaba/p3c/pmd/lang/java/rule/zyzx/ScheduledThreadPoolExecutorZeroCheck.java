package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTBlock;
import net.sourceforge.pmd.lang.java.ast.ASTBlockStatement;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * java.util.concurrent.ScheduledThreadPoolExecutor由属性corePoolSize指定线程池大小，如果设置为0表示线程执行器无线程可用且不做任何事.
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class ScheduledThreadPoolExecutorZeroCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "BlockStatement[./LocalVariableDeclaration//AllocationExpression[./ClassOrInterfaceType[@Image='ScheduledThreadPoolExecutor']]//Literal[@Image='0']]";
    private static final String CHECKOTHERMETHODNUM = "//BlockStatement[./Statement/StatementExpression//Name[contains(@Image,'setCorePoolSize')]]//Literal[@Image= '0']";

    public Object visit(ASTBlock node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            List<Node> childNodesWithXPath = node.findChildNodesWithXPath(CHECKOTHERMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                addViolationWithMessage(data, node,
                        "java.zyzx.ScheduledThreadPoolExecutorZeroCheck.rule.msg", null);
            }
            if ( !childNodesWithXPath.isEmpty() ){
                addViolationWithMessage(data, node,
                        "java.zyzx.ScheduledThreadPoolExecutorZeroCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

