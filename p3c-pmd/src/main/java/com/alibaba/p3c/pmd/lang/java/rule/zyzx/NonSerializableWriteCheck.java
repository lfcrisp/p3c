package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBody;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 执行写操作的类要序列化，否则会抛出异常.
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class NonSerializableWriteCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//MethodDeclaration[./MethodDeclarator[@Image='Watermelon']]/Block/BlockStatement/Statement/StatementExpression/PrimaryExpression[./PrimaryPrefix/Name[contains(@Image,'.writeObject')]]/PrimarySuffix//Name[@Image='veg']";

    @Override
    public Object visit(ASTClassOrInterfaceBodyDeclaration node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.NonSerializableWriteCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

