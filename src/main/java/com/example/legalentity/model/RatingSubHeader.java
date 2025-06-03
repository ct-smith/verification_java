package com.example.legalentity.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RatingSubHeader extends AbstractCsvReportSubHeaderDto {

    private String systemId;

    private String issueCode;

    private String issueCodeName;

    private String generation;

}
