package com.greenart.flo.vo.admin.response;

import com.greenart.flo.security.vo.TokenVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class AdminLoginResponseVO {
    private Boolean status;
    private String message;
    private HttpStatus code;
    private TokenVO token;
}
