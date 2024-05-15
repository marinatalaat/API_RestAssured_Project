package RequestBodyModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateNewUserReqBody {

    public String name;
    public String job;
}
