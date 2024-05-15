package TestCases;

import Requests.UpdateUserRequests;
import ResponseModels.CreateOrUpdateUserResModel;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Properties;

import static Helpers.JsonUtils.ValidateClassToResponseSchema;
import static Helpers.JsonUtils.getStringValueFromJsonFile;
import static Helpers.PropertiesLoader.readPropertyFile;

public class UpdateExistingUser_TC {


    String JsonSchema = "Schemas/UpdateUserSchema.json";
    String filePath = "RequestBody/CreateNewUserReqBody.json";
    CreateOrUpdateUserResModel createOrUpdateUserResModel;
    static final Properties urlProps = readPropertyFile("ConfigData/CommonData.properties");


    @Test
    public void UpdateUser() throws IOException {
        ValidateClassToResponseSchema(createOrUpdateUserResModel, JsonSchema);

        Assert.assertNotNull(createOrUpdateUserResModel.updatedAt);
        Assert.assertEquals(createOrUpdateUserResModel.job, getStringValueFromJsonFile("job", filePath));
        Assert.assertEquals(createOrUpdateUserResModel.name, getStringValueFromJsonFile("name", filePath));
    }



    @BeforeClass
    public void beforeTest() throws IOException {
        createOrUpdateUserResModel = UpdateUserRequests.UpdateUserRequest(urlProps.getProperty("userID"), urlProps.getProperty("UpdateStatusCode"));}

}
