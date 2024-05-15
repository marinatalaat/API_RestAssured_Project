package Requests;

import ResponseModels.UsersApiResponseModel;
import Restwrapper.Headers;
import Restwrapper.RestWrapper;
import Enum.EndPoints;

import java.util.HashMap;

public class GetTheUserDataRequests {

    static String filePath = "Schemas/UsersResponseSchema.json";

    public static UsersApiResponseModel getAllUsersDataInFirstPage(String statusCode){
        return RestWrapper.restGet(EndPoints.users, Headers.generalHeaders(), UsersApiResponseModel.class, statusCode);
    }

    public static UsersApiResponseModel getAllUsersDataInSecondPage(String statusCode){
        HashMap<String, Integer> queryParams = new HashMap<String, Integer>() {{
            put("page", 2);
        }};
        return RestWrapper.restGetWithQueryParams(EndPoints.users, Headers.generalHeaders(), queryParams, UsersApiResponseModel.class, statusCode);
    }


}
