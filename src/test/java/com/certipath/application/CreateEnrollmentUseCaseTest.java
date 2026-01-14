package com.certipath.application;

import com.certipath.application.exceptions.InvalidEnrollmentException;
import com.certipath.domain.Enrollment;
import com.certipath.domain.Route;
import com.certipath.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateEnrollmentUseCaseTest {

    // Pruebo el caso de uso de crear una inscripción en función de los IDs de usuario y ruta, que mockeo.

    // atributos de clase

    @Mock private UserPort userPort;
    @Mock private RoutePort routePort;

        // objetos mockeados

    User mockUser = new User("mock-user-123", "Mocked User");
    Route mockRoute = new Route("mock-route-456", "Mocked Route");

    // servicio a probar
    private EnrollmentUseCase enrollmentUseCase;

    @BeforeEach
            void setUp() {
        enrollmentUseCase = new EnrollmentUseCase(userPort, routePort);
    }

    @Test
    void testValidEnrollment() {
        // Given an existing user and an existing route are returned by the ports

        when(userPort.findUserById(anyString()))
                .thenReturn(Optional.of(mockUser));
        when(routePort.findRouteById(anyString()))
                .thenReturn(Optional.of(mockRoute));

        // When the enrollment use case is executed with existing IDs
        Enrollment enrollment = enrollmentUseCase.enroll("123", "256");

        // Then a new enrollment is created with the expected data
        assertThat(enrollment.getUser()).isEqualTo(mockUser);
        assertThat(enrollment.getRoute()).isEqualTo(mockRoute);
        assertThat(enrollment.getEnrolledAt()).isNotNull();
    }

    @ParameterizedTest
    @CsvSource(
            value = {"null, route-1",
                    "user-1, null"},
            nullValues = "null")
    void testNullIdEnrollment_throwsException(String userId, String routeId) {
        assertThatThrownBy(() ->
                enrollmentUseCase.enroll(userId, routeId)
        )
                .isInstanceOf(InvalidEnrollmentException.class)
                .hasMessage("User or route ID is null");
    }

    @ParameterizedTest
    @CsvSource(
            value = {"'', route-1",
            "'   ', route-1",
            "'\t', route-1",
            "'\n', route-1",
            "user-1, ''",
            "user-1, '    '",
            "user-1, '\t'",
            "user-1, '\n'"})
    void testEmptyIdEnrollment_throwsException(String userId, String routeId) {
        assertThatThrownBy(() ->
                enrollmentUseCase.enroll(userId, routeId)
        )
                .isInstanceOf(InvalidEnrollmentException.class)
                .hasMessage("User or route ID es empty");
    }

}
