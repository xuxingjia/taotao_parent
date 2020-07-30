package com.study.demo.taotao_shoppingcart_web.utils.cas;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:CasConfig.properties")
public class CasConfig {

    @Value("${cas.server.host.url}")
    private String casServerHostUrl;

    @Value("${cas.server.host.login_url}")
    private String casServerHostLoginUrl;

    @Value("${cas.server.host.logout_url}")
    private String casServerHostLogoutUrl;

    @Value("${app.server.host.url}")
    private String appServerHostUrl;

    @Value("${app.login.url}")
    private String appLoginUrl;

    @Value("${app.logout.url}")
    private String appLogoutUrl;


    public String getCasServerHostUrl() {
        return casServerHostUrl;
    }

    public void setCasServerHostUrl(String casServerHostUrl) {
        this.casServerHostUrl = casServerHostUrl;
    }

    public String getCasServerHostLoginUrl() {
        return casServerHostLoginUrl;
    }

    public void setCasServerHostLoginUrl(String casServerHostLoginUrl) {
        this.casServerHostLoginUrl = casServerHostLoginUrl;
    }

    public String getCasServerHostLogoutUrl() {
        return casServerHostLogoutUrl;
    }

    public void setCasServerHostLogoutUrl(String casServerHostLogoutUrl) {
        this.casServerHostLogoutUrl = casServerHostLogoutUrl;
    }

    public String getAppServerHostUrl() {
        return appServerHostUrl;
    }

    public void setAppServerHostUrl(String appServerHostUrl) {
        this.appServerHostUrl = appServerHostUrl;
    }

    public String getAppLoginUrl() {
        return appLoginUrl;
    }

    public void setAppLoginUrl(String appLoginUrl) {
        this.appLoginUrl = appLoginUrl;
    }

    public String getAppLogoutUrl() {
        return appLogoutUrl;
    }

    public void setAppLogoutUrl(String appLogoutUrl) {
        this.appLogoutUrl = appLogoutUrl;
    }
}
