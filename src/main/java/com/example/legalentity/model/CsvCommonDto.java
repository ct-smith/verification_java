package com.example.legalentity.model;

import java.util.LinkedHashMap;
import java.util.List;

import lombok.Data;

@Data
public class CsvCommonDto {

    /** ヘッダ情報 */
    private CsvReportHeaderDto header;

    /** サブヘッダ・明細リスト保持用マップ */
    private LinkedHashMap<AbstractCsvReportSubHeaderDto, List<CsvReportHeaderDto>> reportCollection;

}
