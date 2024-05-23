package com.example.nghiahaui.model.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTagRequest {
    

    @NotNull(message = "Tên nhãn rỗng")
    @NotEmpty(message = "Tên nhãn rỗng")
    @Schema(description = "Tên nhãn",example="Beauty",required=true)
    private String name;
}
