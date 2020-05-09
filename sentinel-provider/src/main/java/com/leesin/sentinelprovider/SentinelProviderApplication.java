package com.leesin.sentinelprovider;

import com.alibaba.csp.sentinel.cluster.ClusterStateManager;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Collections;

@SpringBootApplication
public class SentinelProviderApplication {

    public static void main(String[] args) throws IOException {
        //表示当前的节点是集群客户端
        ClusterStateManager.applyState(ClusterStateManager.CLUSTER_CLIENT);
        //        initFlowRule();
        SpringApplication.run(com.leesin.sentinelprovider.SentinelProviderApplication.class, args);
        System.in.read();
    }

    //简单的方式
    //限流的规则
    private static void initFlowRule() {
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("com.leesin.sentinelprovider:sayHello(java.lang.String)");
        flowRule.setCount(10);
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
        //只有sentinel-web过来才能请求通过，设置限流的来源
        flowRule.setLimitApp("sentinel-web");
        FlowRuleManager.loadRules(Collections.singletonList(flowRule));
    }
}
