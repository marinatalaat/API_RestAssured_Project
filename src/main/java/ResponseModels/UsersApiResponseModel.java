package ResponseModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersApiResponseModel {

        public int page;
        public int per_page;
        public int total;
        public int total_pages;
        public ArrayList<data> data;
        public Support support;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class data {
        public int id;
        public String email;
        public String first_name;
        public String last_name;
        public String avatar;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Support{
        public String url;
        public String text;
    }


}
