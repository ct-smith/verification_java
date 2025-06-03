package com.example.legalentity.bizmodel;

import com.example.legalentity.model.CfccTcfratingWithBLOBs;

// import java.util.List;

// import io.micrometer.common.util.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RatingInputRecord extends CfccTcfratingWithBLOBs {

    /** 更新区分 */
    private String updateCategory;

    /**
     * 変更区分をチェックし、変更有無を返却する
     * 
     * @return 変更有無判断結果
     */
    public boolean isChanged() {
        // if (StringUtils.hasText(updateCategory)) {
        // return List.of("ADD", "UPDATE", "DELETE").contains(updateCategory) ? true :
        // false;
        // } else {
        // return false;
        // }
        return true;
    }
}
