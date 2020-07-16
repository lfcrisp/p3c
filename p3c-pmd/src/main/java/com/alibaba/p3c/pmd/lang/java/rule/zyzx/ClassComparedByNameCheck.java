package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTStatement;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 不要用类名称比较类是否相同，而用instanceof或者Class.isAssignableFrom()方法检查对象的基础类型。
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class ClassComparedByNameCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "IfStatement/Expression/PrimaryExpression[./PrimarySuffix[@Image='equals']][./PrimarySuffix/Arguments//Name[contains(@Image,'Class')]]";

    @Override
    public Object visit(ASTStatement node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.ClassComparedByNameCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

