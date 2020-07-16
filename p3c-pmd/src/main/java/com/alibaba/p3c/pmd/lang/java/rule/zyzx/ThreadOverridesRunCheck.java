package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceBodyDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTStatement;
import org.jaxen.JaxenException;

import java.util.List;

/**
 * 线程类应该重写run方法
 * @author lifengchen
 * @date 2020-04-26 11:51:14
 */

public class ThreadOverridesRunCheck extends AbstractAliRule {
    private static final String CHECKMETHODNUM_TH = "ClassOrInterfaceDeclaration[./ExtendsList/ClassOrInterfaceType[@Image='Thread']]";
    private static final String CHECKMETHODNUM_THREAD = "ClassOrInterfaceDeclaration[./ExtendsList/ClassOrInterfaceType[@Image='Thread']]/ClassOrInterfaceBody[./ClassOrInterfaceBodyDeclaration//Name[@Image='Override']][./ClassOrInterfaceBodyDeclaration//MethodDeclarator[@Image='run']]";
    private static final String CHECKMETHODNUM_RU = "ClassOrInterfaceDeclaration[./ImplementsList/ClassOrInterfaceType[@Image='Runnable']]";
    private static final String CHECKMETHODNUM_RUNNABLE = "ClassOrInterfaceDeclaration[./ImplementsList/ClassOrInterfaceType[@Image='Runnable']]/ClassOrInterfaceBody[./ClassOrInterfaceBodyDeclaration//Name[@Image='Override']][./ClassOrInterfaceBodyDeclaration//MethodDeclarator[@Image='run']]";

    @Override
    public Object visit(ASTClassOrInterfaceBodyDeclaration node, Object data) {
        try {
            List<Node> markerAnnotationsTh = node.findChildNodesWithXPath(CHECKMETHODNUM_TH);
            List<Node> markerAnnotationsRu = node.findChildNodesWithXPath(CHECKMETHODNUM_RU);
            List<Node> markerAnnotationsThread = node.findChildNodesWithXPath(CHECKMETHODNUM_THREAD);
           List<Node> markerAnnotationsRunnable = node.findChildNodesWithXPath(CHECKMETHODNUM_RUNNABLE);
            if ( !markerAnnotationsTh.isEmpty()){
                if(markerAnnotationsThread.isEmpty()){
                    addViolationWithMessage(data, node,
                            "java.zyzx.ThreadOverridesRunCheck.rule.msg", null);
                }
            }
            if ( !markerAnnotationsRu.isEmpty()){
                if(markerAnnotationsRunnable.isEmpty()){
                    addViolationWithMessage(data, node,
                            "java.zyzx.ThreadOverridesRunCheck.rule.msg", null);
                }
            }
        } catch (JaxenException e) {
            throw new RuntimeException("XPath expression " + CHECKMETHODNUM_THREAD + " failed: " + e.getLocalizedMessage(), e);
        }
        return super.visit(node, data);
    }
}

