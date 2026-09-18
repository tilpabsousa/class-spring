package com.example.demo.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * Redireciona a raiz ("/") para a página do Swagger UI,
 * assim quem acessa a aplicação já cai direto na documentação.
 */
@Configuration
class SwaggerRedirectConfig : WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addRedirectViewController("/", "/swagger-ui/index.html")
    }
}
