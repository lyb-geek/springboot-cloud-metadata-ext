## 本示例主要演示如何通过metaMap来扩展eureka的namespace隔离能力

实现核心点：

> 1、metaMap扩展

EurekaInstanceConfigBean填充metaMap的namesapce

> 2、在eureka的服务发现功能上，新增namespace约束

通过扩展com.netflix.niws.loadbalancer.DiscoveryEnabledNIWSServerList
以及切换org.springframework.cloud.netflix.ribbon.RibbonClients的默认配置

> 3、eureka服务端面板定制

通过重写templates/eureka/status.ftlh页面，
以及扩展org.springframework.cloud.netflix.eureka.server.EurekaController.populateApps方法实现