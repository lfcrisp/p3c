package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTBlock;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBody;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 用字符实例化StringBuilder或StringBuffer会产生误导，因为大多数Java开发人员都希望字符是StringBuffer的初始值。实际发生的是，字符的int表示用于确定StringBuffer的初始大小。
 * StringBuffer foo = new StringBuffer('x');   //错误 等同于 StringBuffer foo = new StringBuffer(120);
 * StringBuffer foo = new StringBuffer("x");  //正确示例
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class StringBufferAndBuilderWithCharCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "BlockStatement[./LocalVariableDeclaration//ClassOrInterfaceType[@Image='StringBuffer' or 'StringBuilder ']]//Literal[@CharLiteral='true']";

    public Object visit(ASTBlock node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.StringBufferAndBuilderWithCharCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

