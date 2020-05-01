package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;

import java.util.List;


/**
 * 使用Arrays.toString(args)和Arrays.hashCode(args)代替.
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class ArrayHashCodeAndToStringCheck extends AbstractAliRule {

  private static final String METHODNAME = "MethodDeclarator";
  private static final String PRI_NAME = "Block/BlockStatement/LocalVariableDeclaration/VariableDeclarator/VariableInitializer/Expression/PrimaryExpression/PrimaryPrefix/Name";

    @Override
    public Object visit(ASTMethodDeclaration node, Object data) {
    try {
      List<Node> methodName = node.findChildNodesWithXPath(METHODNAME);
      List<Node> primaryExpressionName = node.findChildNodesWithXPath(PRI_NAME);
      if(methodName != null && methodName.size() > 0){
          if(methodName.get(0).getImage().toLowerCase().endsWith("main")){
              if (!primaryExpressionName.isEmpty()){
                  String image = primaryExpressionName.get(0).getImage();
                  if ("args.toString".equals(image) || "args.hashCode".equals(image)){
                      this.addViolationWithMessage(data, methodName.get(0),"java.zyzx.ArrayHashCodeAndToStringCheck.rule.msg",new Object[]{methodName.get(0).getImage()});
                  }
              }
          }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}

