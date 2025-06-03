package com.example.legalentity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.legalentity.bizmodel.SamplePrintRequestBizModel;
import com.example.legalentity.model.AbstractCsvReportSubHeaderDto;
import com.example.legalentity.model.CsvCommonDto;
import com.example.legalentity.model.CsvReportHeaderDto;
import com.example.legalentity.model.EmptySubHeader;

import lombok.RequiredArgsConstructor;

/**
 * Legal Entity情報登録サービスクラス
 * 
 * 機能ID:CFIC000101
 * 
 * @author wild idea
 */
@RequiredArgsConstructor
@Service
@Transactional
public class SamplePrintBizService {

    /** LegalEntity登録 帳票出力クラス */
    private final LegalEntityPrint printer;

    /**
     * LegalEntity登録を帳票(Proof List)出力する
     * 
     * @param model LegalEntity情報登録処理ビジネスモデルクラス
     */
    public void print(SamplePrintRequestBizModel model) {

        // データ作成
        var newData = new CsvCommonDto();
        var reportCollection = new LinkedHashMap<AbstractCsvReportSubHeaderDto, List<CsvReportHeaderDto>>();
        var detailList = new ArrayList<CsvReportHeaderDto>();

        reportCollection.put(new EmptySubHeader(), detailList);
        newData.setReportCollection(reportCollection);

        printer.printOut(null, null, null, null, null);
    }
}