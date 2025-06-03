package com.example.legalentity.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
public class RatingDetail extends RatingSubHeader {

    // ヘッダ部
    /** タイトル */
    private String title;
    private String reportId;
    private String userId;
    private String operationMode;
    private String operationDate;
    private String printId;

    // サブヘッダ部
    private String systemId;
    private String issueCode;
    private String issueCodeName;
    private String generation;

    // 明細部
    private String chgFlg;
    private String rating;
    private LocalDateTime appliedDateFrom;
    private LocalDateTime appliedDateTo;
    private String remarks;

}
