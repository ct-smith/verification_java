package com.example.legalentity.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
// import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
public class LegalEntityDetail extends AbstractCsvReportSubHeaderDto {

    // ヘッダ部
    /** タイトル */
    private String title;
    private String reportId;
    private String userId;
    private String operationMode;
    private String operationDate;
    private String printId;

    // 明細部
    private String legalEntityId;
    private String longEnglish;
    private String longEnglishChgd;
    private String longJapanese;
    private String shortEnglish;
    private String shortJapanese;
    private String industryCode;
    private String industryName;
    private String bisIndustryCode;
    private String bisIndustryName;
    private String countryCode;
    private String countryName;
    private String isdaMemberSign;
    private String agreementCode;
    private String agreementName;
    private String creditSecCode;
    private String creditSecName;
    private String cpCreditType;
    private String cpCreditTypeName;
    private String yenShikinCode;
    private String limitCheckSign;
    private String limitUpdateSign;
    private String bankCustCode;
    private String bankCustName;
    private String notionalCheckSign;
    private String productSwap;
    private String productMoney;
    private String productForex;
    private String productDomes;
    private String attention;
    private String remarks;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String cpGroupId;
    private String cpGroupShortEng;
    private String branchId;
    private String branchName;
}
