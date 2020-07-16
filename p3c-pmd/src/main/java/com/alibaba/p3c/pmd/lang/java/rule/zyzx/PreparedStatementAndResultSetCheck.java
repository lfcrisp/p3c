package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.*;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * PreparedStatement与ResultSet参数设置与获取数据由序号1开始而非0.
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class PreparedStatementAndResultSetCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "Block[./BlockStatement/LocalVariableDeclaration[./Type[@TypeImage='ResultSet' or @TypeImage='PreparedStatement']]]//PrimaryPrefix/Literal[@Image='0']";

    @Override
    public Object visit(ASTMethodDeclaration node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if (markerAnnotations.size() == 0 || markerAnnotations.isEmpty()){
                return super.visit(node, data);
            }else {
                addViolationWithMessage(data, node,
                        "java.zyzx.PreparedStatementAndResultSetCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

