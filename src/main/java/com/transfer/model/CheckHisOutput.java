package com.transfer.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false) //lombok
public class CheckHisOutput implements Serializable {
    @Getter
    @Setter
    public static class listTrsc {
        LocalDateTime trscDate;
        String receiver;
        Double amt;
        String remark;
    }

    private static final long serialVersionUID = -1L;
    private String status;
    private String desc;
    private Long acctNo;
    private int total;
    private List<listTrsc> listTrsc;
    private LocalDateTime dateTime;

}
