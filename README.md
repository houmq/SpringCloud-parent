## SpringCloud

## Balancer Client 负载均衡器
        * 配置 restTemplate ben

        @LoadBalanced -该注解标明该restTemplate使用负载均衡策略 去掉该注解为指定调用
        @Bean
        RestTemplate restTemplate() {
            return new RestTemplate();
        }

        * 使用@Autowired 注入使用
        @Autowired
        private RestTemplate restTemplate;

        public String doOtherStuff() {
            String results = restTemplate.getForObject("调用地址", String.class);
            return results;
        }

    * WebClient 模式
        * 配置WebClient bean
        @Bean
        @LoadBalanced
        public WebClient.Builder loadBalancedWebClientBuilder() {
            return WebClient.builder();
        }

        * 注入使用
        @Autowired
        private WebClient.Builder webClientBuilder;

        public Mono<String> doOtherStuff() {
            return webClientBuilder.build().get().uri("http://stores/stores")
                            .retrieve().bodyToMono(String.class);
        }


## EurekaServer 忽略网络接口
    * 通配符模式
    spring:
      cloud:
        inetutils:
          ignoredInterfaces:
            - docker0
            - veth.*
    * 指定IP模式
    spring:
      cloud:
        inetutils:
          preferredNetworks:
            - 192.168
            - 10.0
    * 仅使用本地接口模式
    spring:
      cloud:
        inetutils:
          useOnlySiteLocalInterfaces: true















