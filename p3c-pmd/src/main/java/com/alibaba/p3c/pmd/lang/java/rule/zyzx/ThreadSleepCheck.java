package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTStatement;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 如果在当前线程持有锁时调用Thread.sleep（...），则可能导致性能和可伸缩性问题，甚至导致死锁，因为持有锁的线程的执行被冻结。
 * 最好在监视对象上调用wait（...）以暂时释放锁并允许其他线程运行。
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class ThreadSleepCheck extends AbstractAliRule {

    private static final String CHECKMETHODNUM = "SynchronizedStatement[./Block//Name[contains(@Image,'.sleep')]]";

    public Object visit(ASTStatement node, Object data) {
        try {
            List<Node> markerAnnotations = node.findChildNodesWithXPath(CHECKMETHODNUM);
            if ( !markerAnnotations.isEmpty() ){
                    addViolationWithMessage(data, node,
                            "java.zyzx.ThreadSleepCheck.rule.msg", null);
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

