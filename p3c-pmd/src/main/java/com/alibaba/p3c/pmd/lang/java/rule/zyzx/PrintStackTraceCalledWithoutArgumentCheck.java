package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBody;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * Throwable.printStackTrace(...)会打印异常信息，但也会暴露敏感信息
 * @author lifengchen
 * @date 2020-06-03 11:51:14
 */

public class PrintStackTraceCalledWithoutArgumentCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//Block/BlockStatement/Statement/TryStatement/CatchStatement[./Block/BlockStatement/Statement/StatementExpression/PrimaryExpression/PrimaryPrefix/Name[ends-with(@Image,'.printStackTrace')]]";

    public Object visit(ASTClassOrInterfaceBody node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                addViolationWithMessage(data, node,
                        "java.zyzx.PrintStackTraceCalledWithoutArgumentCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

