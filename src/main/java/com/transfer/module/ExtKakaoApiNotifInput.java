package com.transfer.module;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExtKakaoApiNotifInput
{
    @Getter
    @Setter
    public static class templateObject {
        private String object_type;
        private String text;
        private String link;
        private String button_title;
    }
    // The format of kakao API notification
    @JsonProperty("receiver_uuids")
    private List<String> receiver_uuids;
    @JsonProperty("template_object")
    private templateObject template_object;
}
