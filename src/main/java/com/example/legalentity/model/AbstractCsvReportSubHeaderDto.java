package com.example.legalentity.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * サブヘッダDTO 抽象クラス
 * 
 * @author wild idea
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AbstractCsvReportSubHeaderDto extends CsvReportHeaderDto {

    public String[] toStringArray() {
        return null;
    }

}
