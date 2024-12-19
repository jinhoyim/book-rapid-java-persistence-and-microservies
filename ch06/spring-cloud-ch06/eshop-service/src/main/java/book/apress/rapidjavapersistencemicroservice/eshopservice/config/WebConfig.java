package book.apress.rapidjavapersistencemicroservice.eshopservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {

    @Bean
    @Primary
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    @LoadBalanced
    public RestTemplate loadBalancedRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    @Primary
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    @LoadBalanced
    public RestClient.Builder loadBalancedRestClientBuilder() {
        return RestClient.builder();
    }

//    @Bean("inventoryServiceClient")
//    @LoadBalanced
//    public RestClient loadBalancedRestClient(@Value("${api-services.inventory-service.url}") String url,
//                                             @LoadBalanced RestClient.Builder builder) {
//        return builder.baseUrl(url)
//                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                .requestFactory(getHttpRequestFactory())
//                .build();
//    }

//    private static HttpComponentsClientHttpRequestFactory getHttpRequestFactory() {
//        var factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setConnectTimeout(1000);
//        factory.setReadTimeout(1000);
//        return factory;
//    }
}
