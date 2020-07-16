package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBody;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 非线程安全的域不应该静态化。注：可改为静态方法返回对象
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class StaticMultithreadedUnsafeFieldsCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUMSIMPLEDATEFORMAT = "//ClassOrInterfaceBodyDeclaration/FieldDeclaration[@Static='true']/Type/ReferenceType/ClassOrInterfaceType[@Image='SimpleDateFormat']";
    private static final String CHECKMETHODNUMCALENDAR = "//ClassOrInterfaceBodyDeclaration/FieldDeclaration[@Static='true']/Type/ReferenceType/ClassOrInterfaceType[@Image='Calendar']";

    @Override
    public Object visit(ASTClassOrInterfaceBody node, Object data) {
        try {
            List<Node> markerAnnotationsSimpleDateFormat = node.findChildNodesWithXPath(CHECKMETHODNUMSIMPLEDATEFORMAT);
            List<Node> markerAnnotationsCalendar = node.findChildNodesWithXPath(CHECKMETHODNUMCALENDAR);
            if ( !markerAnnotationsSimpleDateFormat.isEmpty() || !markerAnnotationsCalendar.isEmpty()){
                    addViolationWithMessage(data, node,
                            "java.zyzx.StaticMultithreadedUnsafeFieldsCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUMCALENDAR + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

