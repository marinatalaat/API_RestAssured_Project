package TestCases;

import Requests.DeleteUserRequests;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Properties;

import static Helpers.PropertiesLoader.readPropertyFile;

public class DeleteExistingUser_TC {

    private static final Properties urlProps = readPropertyFile("ConfigData/CommonData.properties");


    @Test
    public void DeleteUser() throws IOException {
        Assert.assertEquals(String.valueOf(DeleteUserRequests.deleteUserRequest(urlProps.getProperty("userID"))), urlProps.getProperty("DeleteStatusCode"));
    }
}
