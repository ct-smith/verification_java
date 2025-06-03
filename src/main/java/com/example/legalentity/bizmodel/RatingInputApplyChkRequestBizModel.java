package com.example.legalentity.bizmodel;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class RatingInputApplyChkRequestBizModel {

    // Rating情報リスト
    private List<RatingInputRecord> recordList;

    /** システムID */
    private String systemId;

    /** 銘柄コード */
    private String issueCode;
}
