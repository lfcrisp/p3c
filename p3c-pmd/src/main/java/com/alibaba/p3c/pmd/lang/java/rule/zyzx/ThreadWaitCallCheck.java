package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 在线程实例上可以使用方法wait（...），notify（）和notifyAll（），但这仅是因为Java中的所有类都扩展了Object并因此自动继承了这些方法。 但是有两个很好的理由不在线程上调用它们：
 *
 * 在内部，JVM依靠这些方法来更改线程的状态（BLOCKED，WAITING等），因此调用它们将破坏JVM的行为。
 *
 * 目前尚不清楚（可能甚至对于原始编码器而言）真正期望的是什么。 例如，它正在等待线程的执行被挂起，还是正在等待获取的对象监视器？
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class ThreadWaitCallCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "//MethodDeclaration[./MethodDeclarator[@Image='Watermelon']]/Block/BlockStatement/Statement/StatementExpression/PrimaryExpression/PrimaryPrefix/Name[@Image='myThread.wait']";

    @Override
    public Object visit(ASTClassOrInterfaceBodyDeclaration node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.ThreadWaitCallCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

