package Requests;

import ResponseModels.CreateOrUpdateUserResModel;
import Restwrapper.Headers;
import Restwrapper.RestWrapper;
import Enum.EndPoints;

import java.io.IOException;

public class DeleteUserRequests {
    public static int deleteUserRequest(String userId) throws IOException {
        return RestWrapper.restDelete(EndPoints.users, Headers.generalHeaders(), userId);
    }
}
