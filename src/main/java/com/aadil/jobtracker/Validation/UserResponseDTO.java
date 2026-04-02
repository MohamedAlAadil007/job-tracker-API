package com.aadil.jobtracker.Validation;
import lombok.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
}