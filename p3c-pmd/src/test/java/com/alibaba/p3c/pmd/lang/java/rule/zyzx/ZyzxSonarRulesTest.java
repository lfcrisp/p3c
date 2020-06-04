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

    @Test
    public void testShiftOnIntOrLongCheck(){
        Rule rule = this.findRule(RULESET,"ShiftOnIntOrLongCheck");
        runTests(rule,"ShiftOnIntOrLongCheck");
    }

    @Test
    public void testInvalidDateValuesCheck(){
        Rule rule = this.findRule(RULESET,"InvalidDateValuesCheck");
        runTests(rule,"InvalidDateValuesCheck");
    }

    @Test
    public void testReturnInFinallyCheck(){
        Rule rule = this.findRule(RULESET,"ReturnInFinallyCheck");
        runTests(rule,"ReturnInFinallyCheck");
    }

    @Test
    public void testLocksNotUnlockedCheck(){
        Rule rule = this.findRule(RULESET,"LocksNotUnlockedCheck");
        runTests(rule,"LocksNotUnlockedCheck");
    }

    @Test
    public void testForLoopFalseConditionCheck(){
        Rule rule = this.findRule(RULESET,"ForLoopFalseConditionCheck");
        runTests(rule,"ForLoopFalseConditionCheck");
    }

    @Test
    public void testCastArithmeticOperandCheck(){
        Rule rule = this.findRule(RULESET,"CastArithmeticOperandCheck");
        runTests(rule,"CastArithmeticOperandCheck");
    }

    @Test
    public void testParameterReassignedToCheck(){
        Rule rule = this.findRule(RULESET,"ParameterReassignedToCheck");
        runTests(rule,"ParameterReassignedToCheck");
    }

    @Test
    public void testThreadWaitCallCheck(){
        Rule rule = this.findRule(RULESET,"ThreadWaitCallCheck");
        runTests(rule,"ThreadWaitCallCheck");
    }

    @Test
    public void testMethodNamedHashcodeOrEqualCheck(){
        Rule rule = this.findRule(RULESET,"MethodNamedHashcodeOrEqualCheck");
        runTests(rule,"MethodNamedHashcodeOrEqualCheck");
    }

    @Test
    public void testAbsOnNegativeCheck(){
        Rule rule = this.findRule(RULESET,"AbsOnNegativeCheck");
        runTests(rule,"AbsOnNegativeCheck");
    }

    @Test
    public void testTransactionalMethodVisibilityCheck(){
        Rule rule = this.findRule(RULESET,"TransactionalMethodVisibilityCheck");
        runTests(rule,"TransactionalMethodVisibilityCheck");
    }

    @Test
    public void testNonSerializableWriteCheck(){
        Rule rule = this.findRule(RULESET,"NonSerializableWriteCheck");
        runTests(rule,"NonSerializableWriteCheck");
    }

    @Test
    public void testSerializableObjectInSessionCheck(){
        Rule rule = this.findRule(RULESET,"SerializableObjectInSessionCheck");
        runTests(rule,"SerializableObjectInSessionCheck");
    }

    @Test
    public void testStaticMultithreadedUnsafeFieldsCheck(){
        Rule rule = this.findRule(RULESET,"StaticMultithreadedUnsafeFieldsCheck");
        runTests(rule,"StaticMultithreadedUnsafeFieldsCheck");
    }

    @Test
    public void testPrintfFailCheck(){
        Rule rule = this.findRule(RULESET,"PrintfFailCheck");
        runTests(rule,"PrintfFailCheck");
    }

    @Test
    public void testUselessIncrementCheck(){
        Rule rule = this.findRule(RULESET,"UselessIncrementCheck");
        runTests(rule,"UselessIncrementCheck");
    }

    @Test
    public void testReflectionOnNonRuntimeAnnotationCheck(){
        Rule rule = this.findRule(RULESET,"ReflectionOnNonRuntimeAnnotationCheck");
        runTests(rule,"ReflectionOnNonRuntimeAnnotationCheck");
    }

    @Test
    public void testDuplicateConditionIfElseIfCheck(){
        Rule rule = this.findRule(RULESET,"DuplicateConditionIfElseIfCheck");
        runTests(rule,"DuplicateConditionIfElseIfCheck");
    }

    @Test
    public void testUnclosedResourcesCheck(){
        Rule rule = this.findRule(RULESET,"UnclosedResourcesCheck");
        runTests(rule,"UnclosedResourcesCheck");
    }

    @Test
    public void testSynchronizationOnStringOrBoxedCheck(){
        Rule rule = this.findRule(RULESET,"SynchronizationOnStringOrBoxedCheck");
        runTests(rule,"SynchronizationOnStringOrBoxedCheck");
    }

    @Test
    public void testSerializableSuperConstructorCheck(){
        Rule rule = this.findRule(RULESET,"SerializableSuperConstructorCheck");
        runTests(rule,"SerializableSuperConstructorCheck");
    }

    @Test
    public void testIgnoredStreamReturnValueCheck(){
        Rule rule = this.findRule(RULESET,"IgnoredStreamReturnValueCheck");
        runTests(rule,"IgnoredStreamReturnValueCheck");
    }

    @Test
    public void testObjectFinalizeCheck(){
        Rule rule = this.findRule(RULESET,"ObjectFinalizeCheck");
        runTests(rule,"ObjectFinalizeCheck");
    }

    @Test
    public void testObjectFinalizeOverloadedCheck(){
        Rule rule = this.findRule(RULESET,"ObjectFinalizeOverloadedCheck");
        runTests(rule,"ObjectFinalizeOverloadedCheck");
    }

    @Test
    public void testEqualsArgumentTypeCheck(){
        Rule rule = this.findRule(RULESET,"EqualsArgumentTypeCheck");
        runTests(rule,"EqualsArgumentTypeCheck");
    }

    @Test
    public void testServletInstanceFieldCheck(){
        Rule rule = this.findRule(RULESET,"ServletInstanceFieldCheck");
        runTests(rule,"ServletInstanceFieldCheck");
    }

    @Test
    public void testSillyEqualsCheck(){
        Rule rule = this.findRule(RULESET,"SillyEqualsCheck");
        runTests(rule,"SillyEqualsCheck");
    }

    @Test
    public void testFileCreateTempFileCheck(){
        Rule rule = this.findRule(RULESET,"FileCreateTempFileCheck");
        runTests(rule,"FileCreateTempFileCheck");
    }

    @Test
    public void testGetRequestedSessionIdCheck(){
        Rule rule = this.findRule(RULESET,"GetRequestedSessionIdCheck");
        runTests(rule,"GetRequestedSessionIdCheck");
    }

    @Test
    public void testPublicStaticFieldShouldBeFinalCheck(){
        Rule rule = this.findRule(RULESET,"PublicStaticFieldShouldBeFinalCheck");
        runTests(rule,"PublicStaticFieldShouldBeFinalCheck");
    }
}
