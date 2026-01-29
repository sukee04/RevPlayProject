package com.example.RevPlay;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import com.example.RevPlay.DAO.UserDAO;
import com.example.RevPlay.model.LoginPage;

@ExtendWith(MockitoExtension.class)
class UserDAOTest {

    @Mock
    UserDAO userDAO;

    @Test
    void testUserLoginSuccess() {
        LoginPage login = new LoginPage("test@gmail.com", "12345");

        when(userDAO.getUserDetails(login)).thenReturn(login);

        LoginPage result = userDAO.getUserDetails(login);

        assertNotNull(result);
        assertEquals("test@gmail.com", result.email);
    }
    
    @Test
    void testUserLoginFailure() {
        LoginPage login = new LoginPage("wrong@gmail.com", "wrong");

        when(userDAO.getUserDetails(login)).thenReturn(null);

        LoginPage result = userDAO.getUserDetails(login);

        assertNull(result);
    }

}
