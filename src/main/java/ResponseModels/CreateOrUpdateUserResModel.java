package ResponseModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrUpdateUserResModel {

        public String name;
        public String job;
        public String id;
        public String  createdAt;
        public String updatedAt;

}
