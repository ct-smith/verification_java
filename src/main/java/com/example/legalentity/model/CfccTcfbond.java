package com.example.legalentity.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CfccTcfbond extends CfccTcfbondKey {

    private String issueNameShort;

    private String commodityType;

    private String decimalSign;

    private String tickDenomi;

    private String tick;

    private String symbol;

    private String vendor;

    private String mkField;

}
