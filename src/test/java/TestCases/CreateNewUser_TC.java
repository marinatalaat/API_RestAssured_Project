package TestCases;

import Requests.CreateNewUserRequests;
import ResponseModels.CreateOrUpdateUserResModel;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Properties;

import static Helpers.JsonUtils.*;
import static Helpers.PropertiesLoader.readPropertyFile;

public class CreateNewUser_TC {

    static final Properties urlProps = readPropertyFile("ConfigData/CommonData.properties");
    String JsonSchema = "Schemas/CreateNewUserSchema.json";
    String filePath = "RequestBody/CreateNewUserReqBody.json";
    CreateOrUpdateUserResModel createOrUpdateUserResModel;


    @Test
    public void CreateNewUser() throws IOException {
        ValidateClassToResponseSchema(createOrUpdateUserResModel, JsonSchema);

        Assert.assertNotNull(createOrUpdateUserResModel.id);
        Assert.assertNotNull(createOrUpdateUserResModel.createdAt);
        Assert.assertEquals(createOrUpdateUserResModel.job, getStringValueFromJsonFile("job", filePath));
        Assert.assertEquals(createOrUpdateUserResModel.name, getStringValueFromJsonFile("name", filePath));
    }



    @BeforeClass
    public void beforeTest() throws IOException {
        createOrUpdateUserResModel = CreateNewUserRequests.createNewUserRequest(urlProps.getProperty("PostStatusCode"));
    }
}
