package com.fas.fcdsystem.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.view.LiteDeviceDelegatingViewResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
public class TilesConfig {

    @Bean
    public TilesConfigurer tilesConfigurer() {
        final TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions("/WEB-INF/tiles.xml");
        configurer.setCheckRefresh(true);
        return configurer;
    }

    @Bean
    public TilesViewResolver tilesViewResolver() {
        final TilesViewResolver tilesViewResolver = new TilesViewResolver();
        tilesViewResolver.setViewClass(TilesView.class);
//        tilesViewResolver.setOrder(0);
        return tilesViewResolver;
    }

    @Bean
    public LiteDeviceDelegatingViewResolver liteDeviceDelegatingViewResolver() {
        LiteDeviceDelegatingViewResolver liteDeviceDelegatingViewResolver = new LiteDeviceDelegatingViewResolver(tilesViewResolver());
        liteDeviceDelegatingViewResolver.setMobilePrefix("/mobile");
        liteDeviceDelegatingViewResolver.setTabletPrefix("/mobile");
        liteDeviceDelegatingViewResolver.setEnableFallback(true);
        liteDeviceDelegatingViewResolver.setOrder(0);
        return liteDeviceDelegatingViewResolver;
    }

    @Bean
    public ViewResolver jspViewResolver() {
        UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
        urlBasedViewResolver.setViewClass(JstlView.class);
        urlBasedViewResolver.setPrefix("/WEB-INF/jsp/");
        urlBasedViewResolver.setSuffix(".jsp");
        return urlBasedViewResolver;
    }

    @Bean
    public LiteDeviceDelegatingViewResolver liteDeviceDelegatingViewResolverJSP() {
        LiteDeviceDelegatingViewResolver liteDeviceDelegatingViewResolver = new LiteDeviceDelegatingViewResolver(jspViewResolver());
        liteDeviceDelegatingViewResolver.setMobilePrefix("mobile/");
        liteDeviceDelegatingViewResolver.setTabletPrefix("mobile/");
        liteDeviceDelegatingViewResolver.setEnableFallback(true);
        liteDeviceDelegatingViewResolver.setOrder(1);
        return liteDeviceDelegatingViewResolver;
    }
}