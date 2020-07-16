package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 在对象上调用toString（）或clone（）应该始终返回字符串或对象。 相反，返回null会违反该方法的隐式协定。
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class ToStringReturningNullCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "MethodDeclaration[./MethodDeclarator[@Image='toString' or @Image='clone']]//ReturnStatement//Literal/NullLiteral[@Image='']";

    @Override
    public Object visit(ASTClassOrInterfaceBodyDeclaration node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.ToStringReturningNullCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

