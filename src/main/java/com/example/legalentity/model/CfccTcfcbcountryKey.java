package com.example.legalentity.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Accessors(chain = true)
@Data
public class CfccTcfcbcountryKey {

    private String countryCode;
}
