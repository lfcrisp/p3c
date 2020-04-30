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
}
