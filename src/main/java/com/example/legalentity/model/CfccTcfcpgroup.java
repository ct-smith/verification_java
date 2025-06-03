package com.example.legalentity.model;

import lombok.Data;
// import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Accessors(chain = true)
@Data
// @EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CfccTcfcpgroup {

    private String cpGroupId;

    private String cpGroupNameSEng;
}
