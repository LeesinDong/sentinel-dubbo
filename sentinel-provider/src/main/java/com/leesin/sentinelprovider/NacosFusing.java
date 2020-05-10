package com.leesin.sentinelprovider;

import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;

import java.util.ArrayList;

/**
 * @description:
 * @author: Leesin Dong
 * @date: Created in 2020/5/10 0010 8:17
 * @modified By:
 */
//熔断
public class NacosFusing implements InitFunc {
    @Override
    public void init() throws Exception {
        ArrayList<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule();
        rule.setResource("com.leesin.SentinelService");//只能针对于service级别
        //1s内处理5个请求
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        //请求的平均响应时间ms
        //随着上面定义的规则不一样，这里的count代表的含义也会不一样
        rule.setCount(10);
        //降级的窗口，降级多长时间，单位是s
        rule.setTimeWindow(5);
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }
}
