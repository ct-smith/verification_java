package com.example.legalentity.bizmodel;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Rating情報取得処理ビジネスモデルクラス。
 * 
 * @author wild idea
 */
@Accessors(chain = true)
@Data
public class RatingInputInitBizData {

    /** 内部コード システムID */
    private String systemId;

    /** コード名称(略称) */
    private String codeDisplay;

    /** 個別システム使用サイン3(共通機能用) */
    private String systemSign3;
}
