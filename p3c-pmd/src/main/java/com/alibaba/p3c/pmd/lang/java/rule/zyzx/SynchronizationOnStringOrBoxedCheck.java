package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 字符串和封装类不应该被用作锁定对象，因为它们被合并和重用。
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class SynchronizationOnStringOrBoxedCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//MethodDeclaration[./MethodDeclarator[@Image='Watermelon']]/Block/BlockStatement/Statement/SynchronizedStatement/Expression/PrimaryExpression/PrimaryPrefix/Name[@Image='sLock']";

    @Override
    public Object visit(ASTClassOrInterfaceBodyDeclaration node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.SynchronizationOnStringOrBoxedCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

