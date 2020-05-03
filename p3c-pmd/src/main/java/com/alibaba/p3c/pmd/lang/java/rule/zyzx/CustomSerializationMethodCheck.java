package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTStatement;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * Serializable类的编写者可以选择让Java的自动机制处理序列化和反序列化，也可以选择通过实现特定方法自行处理。
 * 但是，如果这些方法的签名与预期的不完全相同，则将忽略它们，并且默认的序列化机制将重新启用。
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class CustomSerializationMethodCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "ClassOrInterfaceDeclaration[@Image='Watermelon'][./ImplementsList/ClassOrInterfaceType[@Image='Serializable']]/ClassOrInterfaceBody/ClassOrInterfaceBodyDeclaration/MethodDeclaration[not(@Private='true')]";

    public Object visit(ASTClassOrInterfaceBodyDeclaration node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.CustomSerializationMethodCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

