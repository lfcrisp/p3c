package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTBlock;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * File.createTempFile不应该被用来创建目录
 * @author lifengchen
 * @date 2020-06-03 11:51:14
 */

public class FileCreateTempFileCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//Block/BlockStatement/Statement/StatementExpression/Expression/PrimaryExpression/PrimaryPrefix/Name[ends-with(@Image,\".createTempFile\")]";

    @Override
    public Object visit(ASTBlock node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.FileCreateTempFileCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

