package com.example.nghiahaui.model.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    
    @NotNull(message="Usernamerỗng")
    @NotEmpty(message="Username rỗng")
    @Size(min=5,max=30,message="Username có từ 5-30 ký tự")
    @Schema(description = "Username",example="admin",required=true)
    private String username;

    @NotNull(message = "Mật khẩu rỗng")
    @NotEmpty(message = "Mật khẩu rỗng")
    @Size(min=6,max=30,message="Mật khẩu có từ 6-30 ký tự")
    @Schema(description = "Mật khẩu",example = "123456")
    private String password;
}
