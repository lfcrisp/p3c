package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 因为Printf风格格式化是在运行期解读，而不是在编译期检验,会存在风险格式类型必须一至.
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class PrintfFailCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//MethodDeclaration[./MethodDeclarator[@Image='Watermelon']]/Block/BlockStatement/Statement//Literal[contains(@Image,' integer is')]";

    @Override
    public Object visit(ASTClassOrInterfaceBodyDeclaration node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.PrintfFailCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

