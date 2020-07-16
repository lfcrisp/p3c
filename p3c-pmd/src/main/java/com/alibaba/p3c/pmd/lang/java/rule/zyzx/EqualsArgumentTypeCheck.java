package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * equals（Object obj）应该测试参数类型.
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class EqualsArgumentTypeCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//MethodDeclaration[./MethodDeclarator[@Image='equals']]/Block/BlockStatement/Statement[./IfStatement[@Else='false']]";
    private static final String CHECKMETHODNUM_BEGIN = "//MethodDeclaration[./MethodDeclarator[@Image='equals']]";

    @Override
    public Object visit(ASTClassOrInterfaceBodyDeclaration node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM_BEGIN);
            List<Node> rightMethod = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( markerAnnotations.size() == 1){
                if(rightMethod.isEmpty()){
                    addViolationWithMessage(data, node,
                            "java.zyzx.EqualsArgumentTypeCheck.rule.msg", null);
                }
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

