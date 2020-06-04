package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.*;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * HttpServletRequest.getRequestedSessionId()返回的客户端浏览器会话id不要用，用HttpServletRequest.getSession().getId()
 * @author lifengchen
 * @date 2020-06-03 11:51:14
 */

public class GetRequestedSessionIdCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//Expression[./PrimaryExpression/PrimaryPrefix/Name[ends-with(@Image,'.getRequestedSessionId')]]";

    public Object visit(ASTExpression node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.GetRequestedSessionIdCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

