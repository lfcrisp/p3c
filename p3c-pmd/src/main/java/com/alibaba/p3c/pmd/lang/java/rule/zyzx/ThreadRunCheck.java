package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTBlock;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * Thread.run（）方法的目的是在单独的专用线程中执行代码。 直接调用此方法没有意义，因为它导致其代码在当前线程中执行。为了获得预期的行为，请改为调用Thread.start（）方法。
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class ThreadRunCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "Block[./BlockStatement/LocalVariableDeclaration/Type/ReferenceType/ClassOrInterfaceType[@Image='Thread']]/BlockStatement/Statement//Name[contains(@Image,'.run')]";

    @Override
    public Object visit(ASTMethodDeclaration node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.ThreadRunCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

