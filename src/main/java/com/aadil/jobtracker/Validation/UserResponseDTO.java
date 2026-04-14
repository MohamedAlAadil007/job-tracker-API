package com.aadil.jobtracker.Validation;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
}