package com.peaksoft.lms.dto.requests.student;

import com.peaksoft.lms.enums.StudyFormat;
import com.peaksoft.lms.validation.EmailValid;
import com.peaksoft.lms.validation.PasswordValid;
import com.peaksoft.lms.validation.PhoneNumberValid;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    @NotNull(message = "First name must not be empty !")
    private String firstName;
    @NotNull(message = "Last name must not be empty !")
    private String lastName;
    @NotNull(message = "Phone number  must not be empty !")
    @PhoneNumberValid
    private String phoneNumber;
    @EmailValid
    @NotNull(message = "email must not be empty !")
    private String email;
    @PasswordValid
    @NotNull(message = "Password must not be empty !")
    private String password;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Study format must not be empty!")
    private StudyFormat studyFormat;
    @NotNull(message = "Group id must not be empty!")
    @Positive(message = "Group id must be positive!")
    private Long groupId;
}
