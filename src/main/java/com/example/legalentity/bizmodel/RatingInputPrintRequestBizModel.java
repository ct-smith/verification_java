package com.example.legalentity.bizmodel;

import java.util.List;

import com.example.legalentity.model.CsvReportHeaderDto;
import com.example.legalentity.model.RatingSubHeader;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Rating情報取得処理ビジネスモデルクラス。
 * 
 * @author wild idea
 */
@Accessors(chain = true)
@Data
public class RatingInputPrintRequestBizModel {

    /** サブヘッダー */
    private RatingSubHeader subHeader;

    /** 格付け詳細 */
    private List<CsvReportHeaderDto> ratingDetail;

    /** Print Mode */
    private Integer printMode;
}
