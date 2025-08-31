package com.internship.management.mappers;

import com.internship.management.dto.UserResponseDto;
import com.internship.management.dto.registration.EnterpriseRegistrationRequestDto;
import com.internship.management.dto.registration.StudentRegistrationRequestDto;
import com.internship.management.dto.registration.TeacherRegistrationRequestDto;
import com.internship.management.entities.Enterprise;
import com.internship.management.entities.Logo;
import com.internship.management.entities.Student;
import com.internship.management.entities.Teacher;
import com.internship.management.entities.Users;
import com.internship.management.entities.VerificationToken;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-31T18:02:00+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.13 (OpenLogic)"
)
@Component
public class RegistrationMapperImpl implements RegistrationMapper {

    @Override
    public UserResponseDto toDto(Enterprise enterprise) {
        if ( enterprise == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( enterprise.getId() );
        userResponseDto.setName( enterprise.getName() );
        userResponseDto.setEmail( enterprise.getEmail() );
        if ( enterprise.getRole() != null ) {
            userResponseDto.setRole( enterprise.getRole().name() );
        }
        userResponseDto.setEmailVerified( enterprise.isEmailVerified() );

        return userResponseDto;
    }

    @Override
    public UserResponseDto toDto(Student student) {
        if ( student == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( student.getId() );
        userResponseDto.setName( student.getName() );
        userResponseDto.setEmail( student.getEmail() );
        if ( student.getRole() != null ) {
            userResponseDto.setRole( student.getRole().name() );
        }
        userResponseDto.setEmailVerified( student.isEmailVerified() );

        return userResponseDto;
    }

    @Override
    public UserResponseDto toDto(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( teacher.getId() );
        userResponseDto.setName( teacher.getName() );
        userResponseDto.setEmail( teacher.getEmail() );
        if ( teacher.getRole() != null ) {
            userResponseDto.setRole( teacher.getRole().name() );
        }
        userResponseDto.setEmailVerified( teacher.isEmailVerified() );

        return userResponseDto;
    }

    @Override
    public UserResponseDto toDto(Users user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( user.getId() );
        userResponseDto.setName( user.getName() );
        userResponseDto.setEmail( user.getEmail() );
        if ( user.getRole() != null ) {
            userResponseDto.setRole( user.getRole().name() );
        }
        userResponseDto.setEmailVerified( user.isEmailVerified() );

        return userResponseDto;
    }

    @Override
    public Student toEntity(StudentRegistrationRequestDto studentRequestDto, PasswordEncoder passwordEncoder) {
        if ( studentRequestDto == null ) {
            return null;
        }

        Student student = new Student();

        student.setName( studentRequestDto.getName() );
        student.setEmail( studentRequestDto.getEmail() );
        student.setFirstName( studentRequestDto.getFirstName() );
        student.setSector( studentRequestDto.getSector() );
        student.setDepartment( studentRequestDto.getDepartment() );
        List<String> list = studentRequestDto.getLanguages();
        if ( list != null ) {
            student.setLanguages( new ArrayList<String>( list ) );
        }
        student.setGithubLink( studentRequestDto.getGithubLink() );
        student.setLinkedinLink( studentRequestDto.getLinkedinLink() );

        student.setRole( com.internship.management.enums.Role.STUDENT );
        student.setPassword( passwordEncoder.encode(studentRequestDto.getPassword()) );

        return student;
    }

    @Override
    public Enterprise toEntity(EnterpriseRegistrationRequestDto enterpriseRequestDto, PasswordEncoder passwordEncoder) {
        if ( enterpriseRequestDto == null ) {
            return null;
        }

        Enterprise enterprise = new Enterprise();

        enterprise.setName( enterpriseRequestDto.getName() );
        enterprise.setEmail( enterpriseRequestDto.getEmail() );
        enterprise.setMatriculation( enterpriseRequestDto.getMatriculation() );
        enterprise.setSectorOfActivity( enterpriseRequestDto.getSectorOfActivity() );
        enterprise.setContact( enterpriseRequestDto.getContact() );
        enterprise.setLocation( enterpriseRequestDto.getLocation() );
        enterprise.setCity( enterpriseRequestDto.getCity() );
        enterprise.setCountry( enterpriseRequestDto.getCountry() );
        enterprise.setLogo( multipartFileToLogo( enterpriseRequestDto.getLogo(), passwordEncoder ) );

        enterprise.setRole( com.internship.management.enums.Role.ENTERPRISE );
        enterprise.setPassword( passwordEncoder.encode(enterpriseRequestDto.getPassword()) );

        return enterprise;
    }

    @Override
    public Teacher toEntity(TeacherRegistrationRequestDto teacherRequestDto, PasswordEncoder passwordEncoder) {
        if ( teacherRequestDto == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setName( teacherRequestDto.getName() );
        teacher.setEmail( teacherRequestDto.getEmail() );
        teacher.setFirstName( teacherRequestDto.getFirstName() );
        teacher.setDepartment( teacherRequestDto.getDepartment() );

        teacher.setRole( com.internship.management.enums.Role.TEACHER );
        teacher.setPassword( passwordEncoder.encode(teacherRequestDto.getPassword()) );

        return teacher;
    }

    @Override
    public VerificationToken verificationTokenUpdate(String code, Users user) {
        if ( code == null && user == null ) {
            return null;
        }

        VerificationToken verificationToken = new VerificationToken();

        if ( user != null ) {
            verificationToken.setUser( user );
            verificationToken.setId( user.getId() );
        }
        verificationToken.setCode( code );
        verificationToken.setExpirationDate( LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES) );

        return verificationToken;
    }

    @Override
    public VerificationToken updateToken(VerificationToken token, String newCode) {
        if ( newCode == null ) {
            return token;
        }

        token.setCode( newCode );

        token.setExpirationDate( LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES) );

        return token;
    }

    protected Logo multipartFileToLogo(MultipartFile multipartFile, PasswordEncoder passwordEncoder) {
        if ( multipartFile == null ) {
            return null;
        }

        Logo logo = new Logo();

        logo.setContentType( multipartFile.getContentType() );

        return logo;
    }
}
