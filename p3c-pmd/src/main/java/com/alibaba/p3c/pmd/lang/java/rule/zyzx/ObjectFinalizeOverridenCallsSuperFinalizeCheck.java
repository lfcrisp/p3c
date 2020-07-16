package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTBlock;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * super.finalize()强烈建议在此方法实现的最后调用，以防父实现也必须处理一些系统资源。
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class ObjectFinalizeOverridenCallsSuperFinalizeCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "MethodDeclaration[./MethodDeclarator[@Image='finalize']]/Block/BlockStatement[last()]/Statement[./StatementExpression//PrimaryPrefix[@SuperModifier='true']]/StatementExpression[./PrimaryExpression/PrimarySuffix[@Image='finalize']]";
    private static final String CHECKMETHODNUM_BEGIN = "MethodDeclaration[./MethodDeclarator[@Image='finalize']]";

    @Override
    public Object visit(ASTClassOrInterfaceBodyDeclaration node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM_BEGIN);
            List<Node> rightMethod = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() && rightMethod.isEmpty() ){
                addViolationWithMessage(data, node,
                        "java.zyzx.ObjectFinalizeOverridenCallsSuperFinalizeCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

