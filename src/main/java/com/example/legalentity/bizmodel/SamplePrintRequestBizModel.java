package com.example.legalentity.bizmodel;

import com.example.legalentity.model.LegalEntityDetail;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Legal Entity情報登録ビジネスモデルクラス
 * 
 * 機能ID:CFIC000101
 * 
 * @author wild idea
 */
@Accessors(chain = true)
@Data
public class SamplePrintRequestBizModel {

    /** 帳票明細データ */
    private LegalEntityDetail newDetail;

    /** Print Mode */
    private Integer printMode;
}