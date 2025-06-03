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
public class CfccTcfratingWithBLOBs extends CfccTcfrating {

    private byte[] timestamp;

    public CfccTcfratingWithBLOBs nextVersion(byte[] version) {
        this.timestamp = version;
        return this;
    }
}
