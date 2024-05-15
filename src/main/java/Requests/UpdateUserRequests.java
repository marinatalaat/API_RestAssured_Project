package Requests;

import Helpers.JsonUtils;
import RequestBodyModels.CreateNewUserReqBody;
import ResponseModels.CreateOrUpdateUserResModel;
import Restwrapper.Headers;
import Restwrapper.RestWrapper;
import Enum.EndPoints;


import java.io.IOException;

public class UpdateUserRequests {

    static String filePath = "RequestBody/CreateNewUserReqBody.json";

    public static CreateOrUpdateUserResModel UpdateUserRequest(String UserId, String statusCode) throws IOException {
        CreateNewUserReqBody createNewUserReqModel = JsonUtils.mapJsonToJavaClass(CreateNewUserReqBody.class, filePath);

        return RestWrapper.restPut(EndPoints.users, Headers.generalHeaders(), UserId, createNewUserReqModel, CreateOrUpdateUserResModel.class, statusCode);
    }
}
