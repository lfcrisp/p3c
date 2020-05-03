package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTBlock;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTStatement;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 集合不应该作为参数传递给它们自己的方法.
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class CollectionCallingItselfCheck extends AbstractAliRule {

    private static final String CHECKMETHOD = "Block[./BlockStatement/LocalVariableDeclaration//ClassOrInterfaceType[@Image='List']]";
    private static final String LISTNAME = "Block/BlockStatement/LocalVariableDeclaration[./Type//ClassOrInterfaceType[@Image='List']]/VariableDeclarator/VariableDeclaratorId";

    public Object visit(ASTMethodDeclaration node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHOD);
            if ( !markerAnnotations.isEmpty() ){
                String listName = node.findChildNodesWithXPath(LISTNAME).get(0).getImage();
                String CHECKMETHODNUM = "//BlockStatement[./Statement/StatementExpression/PrimaryExpression/PrimaryPrefix/Name[contains(@Image,'"+listName+"')]][./Statement/StatementExpression/PrimaryExpression/PrimarySuffix//Name[contains(@Image,'"+listName+"')]]";
                List<Node> checkMethodNum = node.findChildNodesWithXPath(CHECKMETHODNUM);
                if (!checkMethodNum.isEmpty()){
                    addViolationWithMessage(data, node,
                            "java.zyzx.CollectionCallingItselfCheck.rule.msg", null);
                }

            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + LISTNAME + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

