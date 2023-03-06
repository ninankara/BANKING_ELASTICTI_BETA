package com.transfer.module;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExtKakaoApiNotifOutput
{

    // The format of kakao API notification
    @JsonProperty("receiver_uuids")
    private List<String> receiver_uuids;
}
