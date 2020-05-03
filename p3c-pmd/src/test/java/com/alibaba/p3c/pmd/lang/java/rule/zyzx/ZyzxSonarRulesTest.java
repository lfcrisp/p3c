/*
 * Copyright 1999-2017 Alibaba Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.p3c.pmd.lang.java.rule.zyzx;

import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.testframework.SimpleAggregatorTst;
import org.junit.Test;

/**
 * Test for set rules.
 *
 * @author lifengchen
 * @date 2020-04-30
 */
public class ZyzxSonarRulesTest extends SimpleAggregatorTst {

    private static final String RULESET = "java-zyzx-sonar";

    @Override
    public void setUp() {
        addRule(RULESET, "MethodParameterCountRule");
    }

    @Test
    public void testMethodParameterCountRule(){
        Rule rule = this.findRule(RULESET,"MethodParameterCountRule");
        runTests(rule,"MethodParameterCountRule");
    }

    @Test
    public void testCompareToResult(){
        Rule rule = this.findRule(RULESET,"CompareToResultTestCheck");
        runTests(rule,"CompareToResultTestCheck");
    }

    @Test
    public void testCompareToReturn(){
        Rule rule = this.findRule(RULESET,"CompareToReturnValueCheck");
        runTests(rule,"CompareToReturnValueCheck");
    }

    @Test
    public void testLongBitsToDoubleOnIntCheck(){
        Rule rule = this.findRule(RULESET,"LongBitsToDoubleOnIntCheck");
        runTests(rule,"LongBitsToDoubleOnIntCheck");
    }

    @Test
    public void testMethodNamedEqualsCheck(){
        Rule rule = this.findRule(RULESET,"MethodNamedEqualsCheck");
        runTests(rule,"MethodNamedEqualsCheck");
    }

    @Test
    public void testEqualsOverridenWithHashCodeCheck(){
        Rule rule = this.findRule(RULESET,"EqualsOverridenWithHashCodeCheck");
        runTests(rule,"EqualsOverridenWithHashCodeCheck");
    }

    @Test
    public void testArrayHashCodeAndToStringCheck(){
        Rule rule = this.findRule(RULESET,"ArrayHashCodeAndToStringCheck");
        runTests(rule,"ArrayHashCodeAndToStringCheck");
    }

    @Test
    public void testHasNextCallingNextCheck(){
        Rule rule = this.findRule(RULESET,"HasNextCallingNextCheck");
        runTests(rule,"HasNextCallingNextCheck");
    }

    @Test
    public void testIteratorNextExceptionCheck(){
        Rule rule = this.findRule(RULESET,"IteratorNextExceptionCheck");
        runTests(rule,"IteratorNextExceptionCheck");
    }

    @Test
    public void testPreparedStatementAndResultSetCheck(){
        Rule rule = this.findRule(RULESET,"PreparedStatementAndResultSetCheck");
        runTests(rule,"PreparedStatementAndResultSetCheck");
    }

    @Test
    public void testUnusedReturnedDataCheck(){
        Rule rule = this.findRule(RULESET,"UnusedReturnedDataCheck");
        runTests(rule,"UnusedReturnedDataCheck");
    }

    @Test
    public void testRunFinalizersCheck(){
        Rule rule = this.findRule(RULESET,"RunFinalizersCheck");
        runTests(rule,"RunFinalizersCheck");
    }

    @Test
    public void testScheduledThreadPoolExecutorZeroCheck(){
        Rule rule = this.findRule(RULESET,"ScheduledThreadPoolExecutorZeroCheck");
        runTests(rule,"ScheduledThreadPoolExecutorZeroCheck");
    }

    @Test
    public void testInnerClassOfNonSerializableCheck(){
        Rule rule = this.findRule(RULESET,"InnerClassOfNonSerializableCheck");
        runTests(rule,"InnerClassOfNonSerializableCheck");
    }

    @Test
    public void testStringBufferAndBuilderWithCharCheck(){
        Rule rule = this.findRule(RULESET,"StringBufferAndBuilderWithCharCheck");
        runTests(rule,"StringBufferAndBuilderWithCharCheck");
    }

    @Test
    public void testObjectFinalizeOverridenCallsSuperFinalizeCheck(){
        Rule rule = this.findRule(RULESET,"ObjectFinalizeOverridenCallsSuperFinalizeCheck");
        runTests(rule,"ObjectFinalizeOverridenCallsSuperFinalizeCheck");
    }

    @Test
    public void testThreadRunCheck(){
        Rule rule = this.findRule(RULESET,"ThreadRunCheck");
        runTests(rule,"ThreadRunCheck");
    }

    @Test
    public void testToStringReturningNullCheck(){
        Rule rule = this.findRule(RULESET,"ToStringReturningNullCheck");
        runTests(rule,"ToStringReturningNullCheck");
    }

    @Test
    public void testWaitInSynchronizeCheck(){
        Rule rule = this.findRule(RULESET,"WaitInSynchronizeCheck");
        runTests(rule,"WaitInSynchronizeCheck");
    }

    @Test
    public void testThreadSleepCheck(){
        Rule rule = this.findRule(RULESET,"ThreadSleepCheck");
        runTests(rule,"ThreadSleepCheck");
    }

    @Test
    public void testForLoopIncrementSignCheck(){
        Rule rule = this.findRule(RULESET,"ForLoopIncrementSignCheck");
        runTests(rule,"ForLoopIncrementSignCheck");
    }

    @Test
    public void testImmediateReverseBoxingCheck(){
        Rule rule = this.findRule(RULESET,"ImmediateReverseBoxingCheck");
        runTests(rule,"ImmediateReverseBoxingCheck");
    }

    @Test
    public void testThreadOverridesRunCheck(){
        Rule rule = this.findRule(RULESET,"ThreadOverridesRunCheck");
        runTests(rule,"ThreadOverridesRunCheck");
    }

    @Test
    public void testClassComparedByNameCheck(){
        Rule rule = this.findRule(RULESET,"ClassComparedByNameCheck");
        runTests(rule,"ClassComparedByNameCheck");
    }

    @Test
    public void testCollectionCallingItselfCheck(){
        Rule rule = this.findRule(RULESET,"CollectionCallingItselfCheck");
        runTests(rule,"CollectionCallingItselfCheck");
    }

    @Test
    public void testInstanceOfAlwaysTrueCheck(){
        Rule rule = this.findRule(RULESET,"InstanceOfAlwaysTrueCheck");
        runTests(rule,"InstanceOfAlwaysTrueCheck");
    }

    @Test
    public void testCustomSerializationMethodCheck(){
        Rule rule = this.findRule(RULESET,"CustomSerializationMethodCheck");
        runTests(rule,"CustomSerializationMethodCheck");
    }

    @Test
    public void testPrimitiveWrappersInTernaryOperatorCheck(){
        Rule rule = this.findRule(RULESET,"PrimitiveWrappersInTernaryOperatorCheck");
        runTests(rule,"PrimitiveWrappersInTernaryOperatorCheck");
    }

    @Test
    public void testIdenticalOperandOnBinaryExpressionCheck(){
        Rule rule = this.findRule(RULESET,"IdenticalOperandOnBinaryExpressionCheck");
        runTests(rule,"IdenticalOperandOnBinaryExpressionCheck");
    }

    @Test
    public void testCollectionInappropriateCallsCheck(){
        Rule rule = this.findRule(RULESET,"CollectionInappropriateCallsCheck");
        runTests(rule,"CollectionInappropriateCallsCheck");
    }
}
