package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTLiteral;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 加密RSA算法应始终包含OAEP（最优非对称加密填充）
 * @author lifengchen
 * @date 2020-06-03 11:51:14
 */

public class RSAUsesOAEPCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//ClassOrInterfaceDeclaration[@Image='Watermelon']/ClassOrInterfaceBody/ClassOrInterfaceBodyDeclaration/FieldDeclaration[./Type/ReferenceType/ClassOrInterfaceType[@Image='Cipher']]/VariableDeclarator/VariableInitializer/Expression/PrimaryExpression/PrimarySuffix/Arguments//Literal[contains(@Image,'RSA') and not(contains(@Image,'OAEP'))]";

    @Override
    public Object visit(ASTLiteral node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.RSAUsesOAEPCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

