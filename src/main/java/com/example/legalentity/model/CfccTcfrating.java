package com.example.legalentity.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CfccTcfrating extends CfccTcfratingKey {

    private LocalDateTime appliedDateTo;

    private String userId;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private String recordStatus;

    private String rating;

    private String remarks;

}
