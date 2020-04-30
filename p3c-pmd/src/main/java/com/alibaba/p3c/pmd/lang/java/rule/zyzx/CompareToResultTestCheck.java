package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTIfStatement;

import java.util.List;


/**
 * 排查所有条件语句中调用compareTo方法返回的应用
 * compareTo可能返回不是具体的值（除0外），建议用 >0、<0、=0)
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class CompareToResultTestCheck extends AbstractAliRule {

  private static final String COMPARETO_METHODNAME = "Expression/EqualityExpression/PrimaryExpression/PrimaryPrefix/Name";
  private static final String COMPARETO_PRI_LITERAL = "Expression/EqualityExpression/PrimaryExpression/PrimaryPrefix/Literal";
  private static final String COMPARETO_UNA_LITERAL = "Expression/EqualityExpression/UnaryExpression/PrimaryExpression/PrimaryPrefix/Literal";

    @Override
    public Object visit(ASTIfStatement node, Object data) {
    try {
      List<Node> compareToName = node.findChildNodesWithXPath(COMPARETO_METHODNAME);
      List<Node> compareToPriLiteral = node.findChildNodesWithXPath(COMPARETO_PRI_LITERAL);
      List<Node> compareTUnaoLiteral = node.findChildNodesWithXPath(COMPARETO_UNA_LITERAL);
      if(compareToName != null && compareToName.size() > 0){

          String Literal = expressionParameter(compareToPriLiteral,compareTUnaoLiteral);
          if(compareToName.get(0).getImage().toLowerCase().endsWith("compareto") && !"0".equals(Literal)){
              this.addViolationWithMessage(data, compareToName.get(0),"java.zyzx.CompareToResultTestCheck.rule.msg",new Object[]{compareToName.get(0).getImage()});
          }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private String expressionParameter (List<Node> compareToPriLiteral,List<Node> compareTUnaoLiteral){
      String Literal = null;
        if (compareToPriLiteral != null && compareToPriLiteral.size() > 0){
            Literal = compareToPriLiteral.get(0).getImage();
        }
        if (compareTUnaoLiteral != null && compareTUnaoLiteral.size() > 0){
            Literal = compareTUnaoLiteral.get(0).getImage();
        }
        return Literal;
  }

}

