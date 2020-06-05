package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTLiteral;
import net.sourceforge.pmd.lang.java.ast.ASTVariableDeclarator;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 密码等凭证应存储在代码之外的受严格保护的加密配置文件或数据库中。
 * @author lifengchen
 * @date 2020-06-03 11:51:14
 */

public class HardCodedCredentialsCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//VariableDeclarator[./VariableDeclaratorId[contains(@Image,\"password\")]]/VariableInitializer/Expression/PrimaryExpression/PrimaryPrefix/Literal[@StringLiteral='true']";

    public Object visit(ASTLiteral node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.HardCodedCredentialsCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

