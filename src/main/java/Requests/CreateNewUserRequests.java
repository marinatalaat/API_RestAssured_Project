package Requests;

import Helpers.JsonUtils;
import RequestBodyModels.CreateNewUserReqBody;
import ResponseModels.CreateOrUpdateUserResModel;
import Restwrapper.Headers;
import Restwrapper.RestWrapper;
import Enum.EndPoints;


import java.io.IOException;

public class CreateNewUserRequests {
    static String filePath = "RequestBody/CreateNewUserReqBody.json";

    public static CreateOrUpdateUserResModel createNewUserRequest(String statusCode) throws IOException {
        CreateNewUserReqBody createNewUserReqModel = JsonUtils.mapJsonToJavaClass(CreateNewUserReqBody.class, filePath);
        return RestWrapper.restPost(EndPoints.users, Headers.generalHeaders(), createNewUserReqModel, CreateOrUpdateUserResModel.class, statusCode);
    }

}
