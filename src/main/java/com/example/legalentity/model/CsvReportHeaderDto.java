package com.example.legalentity.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 共通ヘッダDTOクラス
 * 
 * @author wild idea
 */
@Data
@Accessors(chain = true)
public class CsvReportHeaderDto {

    /** タイトル */
    private String title;
    private String reportId;
    private String userId;
    private String operationMode;
    private String operationDate;
    private String printId;
    private String jobId;
    private String sectionCode;
}
