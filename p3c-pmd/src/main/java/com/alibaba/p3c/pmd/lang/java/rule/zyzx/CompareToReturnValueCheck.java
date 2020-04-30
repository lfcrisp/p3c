package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;

import java.util.List;

/**
 * "compareTo" should not return "Integer.MIN_ VALUE"
 * compareTo只代表一个不等标识，不代表不等的程度，应返回-1,0,1标识即可
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class CompareToReturnValueCheck extends AbstractAliRule {

    private static final String COMPARETO_METHODNAME = "MethodDeclarator";
    private static final String COMPARETO_RETURN = "Block/BlockStatement/Statement/IfStatement/Statement/Block/BlockStatement/Statement/ReturnStatement/Expression/PrimaryExpression/PrimaryPrefix/Name";

    @Override
    public Object visit(ASTMethodDeclaration node, Object data) {
        try {
            List<Node> compareToMethod = node.findChildNodesWithXPath(COMPARETO_METHODNAME);
            List<Node> compareToReturn = node.findChildNodesWithXPath(COMPARETO_RETURN);
            if(checkListNotNull(compareToMethod,compareToReturn)){
                if(compareToReturn.get(0).getImage().toLowerCase().endsWith("integer.min_value") && "compareTo".equals(compareToMethod.get(0).getImage())){
                    this.addViolationWithMessage(data, compareToMethod.get(0),"java.zyzx.CompareToReturnValueCheck.rule.msg",new Object[]{compareToMethod.get(0).getImage()});
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Boolean checkListNotNull (List<Node> compareToMethod,List<Node> compareToReturn){
        return compareToMethod != null && compareToMethod.size() > 0 && compareToReturn != null && compareToReturn.size() > 0;
    }

}

