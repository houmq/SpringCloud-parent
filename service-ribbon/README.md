##Common rules
    ##RoundRobinRule
    * 此规则只是通过循环选择服务器。它通常用作默认规则或更高级规则的后备。

    ##AvailabilityFilteringRule
    * 此规则将跳过被视为“电路跳闸”或具有高并发连接数的服务器。
        默认情况下，如果RestClient最近三次无法与其建立连接，则实例会跳闸。一旦实例电路跳闸，它将在电路被认为再次关闭之前保持这种状态30秒。
        然而，如果它继续连接失败，它将再次“电路跳闸”并且它变为“电路闭合”的等待时间将指数地增加到连续故障的数量。
    * 配合说明
        # 连续连接失败阈值使服务器处于电路跳闸状态，默认为3
        niws.loadbalancer.<clientName>.connectionFailureCountThreshold=3
        # 无论指数增加，实例可以保持“不可用”状态的最大周期，默认为30
        niws.loadbalancer.<clientName>.circuitTripMaxTimeoutSeconds=30
        # 并发连接的阈值计数跳过服务器，默认为Integer.MAX_INT
        <clientName>.<clientConfigNameSpace>.ActiveConnectionsLimit

    ##WeightedResponseTimeRule
    *  对于此规则，每个服务器根据其平均响应时间给予权重。响应时间越长，重量就越小。该规则随机选择服务器，其中可能性由服务器的权重决定。
    * 配置说明
        <clientName>.<clientConfigNameSpace>.NFLoadBalancerRuleClassName=com.netflix.loadbalancer.WeightedResponseTimeRule











