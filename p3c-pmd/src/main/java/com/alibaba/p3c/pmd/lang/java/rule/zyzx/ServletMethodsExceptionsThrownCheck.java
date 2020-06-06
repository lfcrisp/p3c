package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.ast.xpath.Attribute;
import net.sourceforge.pmd.lang.dfa.DataFlowNode;
import net.sourceforge.pmd.lang.java.ast.ASTBlock;
import net.sourceforge.pmd.lang.java.ast.ASTBlockStatement;
import net.sourceforge.pmd.lang.java.ast.ASTLiteral;
import org.jaxen.JaxenException;
import org.w3c.dom.Document;

import java.util.Iterator;
import java.util.List;

/**
 * 不应该从servlet方法抛出异常
 * @author lifengchen
 * @date 2020-06-03 11:51:14
 */

public class ServletMethodsExceptionsThrownCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//MethodDeclaration[./MethodDeclarator[contains(@Image,'do')] and ./NameList/Name[contains(@Image,'Exception')]]/Block";

    public Object visit(ASTBlockStatement node, Object data) {
        try {
            Boolean flag = true;
            String xPathNodeName = null;
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                Node nodeBlock = markerAnnotations.get(0);
                for (int i = 0; i < nodeBlock.jjtGetNumChildren() ; i++) {
                    xPathNodeName = nodeBlock.jjtGetChild(i).jjtGetChild(0).jjtGetChild(0).getXPathNodeName();
                    if ("TryStatement".equals(xPathNodeName)){
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    addViolationWithMessage(data, node,
                            "java.zyzx.ServletMethodsExceptionsThrownCheck.rule.msg", null);
                }
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

