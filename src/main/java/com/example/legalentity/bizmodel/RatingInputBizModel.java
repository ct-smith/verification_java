package com.example.legalentity.bizmodel;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Rating情報取得処理ビジネスモデルクラス
 * 
 * @author wild idea
 */
@Accessors(chain = true)
@Data
public class RatingInputBizModel {

    // Rating情報リスト
    private List<RatingInputRecord> recordList;

    /** システムID */
    private String systemId;

    /** 銘柄コード */
    private String issueCode;
}
