package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTTryStatement;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 保证锁能够释放.
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class LocksNotUnlockedCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//MethodDeclaration/Block[./BlockStatement/Statement/StatementExpression//Name[contains(@Image,'.lock')]]//IfStatement/Statement/Block[//Name[contains(@Image,'.unloc')]]";

    public Object visit(ASTMethodDeclaration node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.LocksNotUnlockedCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

