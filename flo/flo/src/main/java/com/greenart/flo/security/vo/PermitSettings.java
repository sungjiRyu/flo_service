package com.greenart.flo.security.vo;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "permission")
@Data
public class PermitSettings {
    String[] permitAllUrls;
}
