package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.*;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * BufferedReader.readLine(), Reader.read()及子类中的相关方法都应该先存储再比较.
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class UnusedReturnedDataCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//Expression[./Expression/PrimaryExpression/PrimaryPrefix/Name[contains(@Image,'readLine')]]";

    public Object visit(ASTPrimaryExpression node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( markerAnnotations.isEmpty()){
                addViolationWithMessage(data, node,
                        "java.zyzx.UnusedReturnedDataCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

