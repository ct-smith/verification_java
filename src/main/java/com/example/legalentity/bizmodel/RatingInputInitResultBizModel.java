package com.example.legalentity.bizmodel;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Rating情報取得処理ビジネスモデルクラス。
 * 
 * @author wild idea
 */
@Accessors(chain = true)
@Data
public class RatingInputInitResultBizModel {

    /** SystemID情報モデル */
    private List<RatingInputInitBizData> ratingInputInitDatas;

    /** 処理日付 */
    private LocalDateTime operationDate;
}
