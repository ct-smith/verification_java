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
public class CfccTcfentity extends CfccTcfentityKey {

    private String hOLocation;

    private String typeOfIndustry;

    private String bisIndustry;

    private String cpCreditType;

    private String creditSecCode;

    private String isdaMember;

    private String agreement;

    private String yenShikinCode;

    private String limitCheckSign;

    private String limitUpdateSign;

    private String bankCust;

    private String notionalCheckSign;

    private String swapSign;

    private String moneySign;

    private String forexSign;

    private String domesticMoneySign;

    private String attentionSign;

    private String attention;

    private String entityNameSEng;

    private String entityNameSJpg;

    private String entityNameLEng;

    private String entityNameLJpn;

    private String remarks;

    private String temporarySign;

    private String userId;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private String recordStatus;

    private String rowId;

}
