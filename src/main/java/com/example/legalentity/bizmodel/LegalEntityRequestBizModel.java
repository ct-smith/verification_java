package com.example.legalentity.bizmodel;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * LegalEntity検索モデルクラス
 * 
 * @author wild idea
 */
@Accessors(chain = true)
@Data 
public class LegalEntityRequestBizModel {

    /** 国コード */
    private String countryCode;

    /** 地域コード */
    private String areaCode;

    /** 業種コード */
    private String industryCode;

    /** BIS業種コード */
    private String bisIndustryCode;

    /** 所管審査部門コード */
    private String creditSectionCode;

    /** 与信相手区分 */
    private String counterpartyCreditType;

    /** ISDAメンバー有無 */
    private String isdaMembersSign;

    /** 要注意先設定有無 */
    private String attentionSign;

    /** 作成日(始) */
    private LocalDateTime createDateFrom;

    /** 作成日(終) */
    private LocalDateTime createDateTo;

    /** 更新日(始) */
    private LocalDateTime updateDateFrom;

    /** 更新日(終) */
    private LocalDateTime updateDateTo;

}
