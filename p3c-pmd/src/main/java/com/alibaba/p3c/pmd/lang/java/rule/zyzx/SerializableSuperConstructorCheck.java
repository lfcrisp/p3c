package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBody;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 序列化的类的非序列化父类应有一个无参构造器.
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class SerializableSuperConstructorCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//ClassOrInterfaceBodyDeclaration/ClassOrInterfaceDeclaration[@Image='Watermelon'][./ExtendsList/ClassOrInterfaceType[@Image='Fruit']][./ImplementsList/ClassOrInterfaceType[@Image='Serializable']]";

    public Object visit(ASTClassOrInterfaceBody node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.SerializableSuperConstructorCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

