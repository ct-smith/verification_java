package com.example.legalentity.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
public class EmptySubHeader extends AbstractCsvReportSubHeaderDto {

    @Override
    public String[] toStringArray() {
        return new String[0];
    }
}
