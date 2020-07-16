package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBody;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 非public方法不要注解Transactional,调用时spring 会抛出异常.
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class TransactionalMethodVisibilityCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//ClassOrInterfaceBodyDeclaration[./Annotation/MarkerAnnotation/Name[@Image='Transactional']][./MethodDeclaration[not(@Public='true')]]";

    @Override
    public Object visit(ASTClassOrInterfaceBody node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.TransactionalMethodVisibilityCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

