package com.example.legalentity.bizmodel;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Rating情報取得処理ビジネスモデルクラス
 * 
 * @author wild idea
 */
@Accessors(chain = true)
@Data
public class RatingInputBondSelectResultBizModel {

    /** システムID */
    private String systemId;

    /** 銘柄コード */
    private String issueCode;

    /** 銘柄略称 */
    private String issueNameShort;

    /** 商品区分 */
    private String commodityType;

    /** 10進数サイン */
    private String decimalSign;

    /** 呼称単位 */
    private String tickDenomi;

    /** TICK区分 */
    private String tick;

    /** 時価シンボル */
    private String symbol;

    /** 時価取得元ベンダー */
    private String vendor;

    /** 時価取得フィールド */
    private String mkField;
}
