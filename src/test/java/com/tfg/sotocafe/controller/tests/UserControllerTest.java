package com.tfg.sotocafe.controller.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tfg.sotocafe.controllers.impl.UserControllerImpl;
import com.tfg.sotocafe.services.UserService;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@InjectMocks
    UserControllerImpl userController;
     
    @Mock
    UserService userService;
    
    @Test
    public void testAddUser() 
    {
        
    }
}
