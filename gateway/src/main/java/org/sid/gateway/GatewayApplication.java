package org.sid.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ReactiveAdapter;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    //on faire d'une autre maniére la partie de config du gateway de l'application.yml qui est statique
    // donc a comenter la partie on changant le nom vers app.yml pour ne pas la mettre on considération
   /* @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route((r) -> r.path("/customers/**").uri("lb://CUSTOMER-SERVICE").id("r1"))
                .route((r) -> r.path("/products/**").uri("lb://PRODUCT-SERVICE").id("r2"))
                .build();
    }*/

    // puisque  les applications sçont enrestrer dans euruka donc on pas plus besoin de mettre l'addresse localhot' , il faut juste mentionner le nom de service
    //lb = load balancer


    // cette partie on va rendre tous est dynamqiue
    // on dit à cloud gateway a chaque fois que je recois une requete ,checher dans le url le nom de microservice

    @Bean
    DiscoveryClientRouteDefinitionLocator definitionLocator(ReactiveDiscoveryClient rdc,
                                                            DiscoveryLocatorProperties properties){

    return new DiscoveryClientRouteDefinitionLocator(rdc,properties);
    }

}
