package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.*;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 不要对数值类型的MIN_VALUE值或返回值为此值进行Math.abs与取反操作，因为不会起作用。
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class AbsOnNegativeCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "Block/BlockStatement/Statement/IfStatement/Expression/RelationalExpression/PrimaryExpression[./PrimarySuffix/Arguments/ArgumentList/Expression/PrimaryExpression/PrimaryPrefix/Name[contains(@Image,'.hashCode')]]/PrimaryPrefix/Name[contains(@Image,'.abs')]";

    @Override
    public Object visit(ASTMethodDeclaration node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.AbsOnNegativeCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

