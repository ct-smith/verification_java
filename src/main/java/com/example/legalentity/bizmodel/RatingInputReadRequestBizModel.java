package com.example.legalentity.bizmodel;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Rating情報取得処理ビジネスモデルクラス。
 * 
 * @author wild idea
 */
@Accessors(chain = true)
@Data
public class RatingInputReadRequestBizModel {

    /** システムID */
    private String systemId;

    /** 銘柄コード */
    private String issueCode;

    /** 世代(全世代) */
    private String all;

    /** 世代(現行) */
    private String current;

    /** 本日(システム日付) */
    private LocalDateTime appliedDate;
}
