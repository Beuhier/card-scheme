//package com._3line.gravity.freedom.zohodesk.model;

package com.interview.nine.cardscheme.zohodesk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Access {
        @JsonProperty(value = "access_token")
        private String access_token;

        @JsonProperty(value = "refresh_token")
        private String refresh_token;

        @JsonProperty(value = "api_domain")
        private String api_domain;

        @JsonProperty(value = "token_type")
        private String token_type;

        @JsonProperty(value = "expires_in")
        private double expires_in;

        private LocalDateTime expiry_date;
}
