package com.example.legalentity;

import static com.example.legalentity.common.CommonConstants.PRT_MODE_APPLY;
import static com.example.legalentity.common.CommonConstants.RECORD_STATUS_DELETE;
// import static com.example.legalentity.common.CommonConstants.MSG_ID_CF10A007;
// import static com.example.legalentity.common.CommonConstants.MSG_ID_CF10A015;
// import static com.example.legalentity.common.CommonConstants.MSG_ID_CF10A030;
import static com.example.legalentity.common.CommonConstants.RECORD_STATUS_VALID;
import static com.example.legalentity.common.CommonConstants.SERVICE_NAME_APPLY;
import static com.example.legalentity.common.CommonConstants.STR_EMPTY;
import static com.example.legalentity.common.CommonConstants.WILD_CARD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.legalentity.bizmodel.RatingInputApplyChkRequestBizModel;
import com.example.legalentity.bizmodel.RatingInputBizModel;
import com.example.legalentity.bizmodel.RatingInputBondSelectResultBizModel;
import com.example.legalentity.bizmodel.RatingInputInitBizData;
import com.example.legalentity.bizmodel.RatingInputInitResultBizModel;
import com.example.legalentity.bizmodel.RatingInputPrintRequestBizModel;
import com.example.legalentity.bizmodel.RatingInputReadRequestBizModel;
import com.example.legalentity.bizmodel.RatingInputRecord;
import com.example.legalentity.common.CfSystemDateUtils;
import com.example.legalentity.common.CfTraceLog;
import com.example.legalentity.common.ScreenMode;
// import com.example.legalentity.common.TrBusinessException;
import com.example.legalentity.example.CfccTcfbondExample;
// import com.example.legalentity.common.SiComponentHolder;
import com.example.legalentity.example.CfccTcfcbsystemidExample;
import com.example.legalentity.example.CfccTcfratingExample;
import com.example.legalentity.mapper.CfccTcfbondMapper;
import com.example.legalentity.mapper.CfccTcfcbsystemidMapper;
import com.example.legalentity.mapper.CfccTcfratingMapper;
import com.example.legalentity.mapper.CfcctcfdatetimeMapper;
import com.example.legalentity.model.AbstractCsvReportSubHeaderDto;
import com.example.legalentity.model.CfccTcfbond;
import com.example.legalentity.model.CfccTcfdatetimeKey;
import com.example.legalentity.model.CfccTcfratingWithBLOBs;
import com.example.legalentity.model.CsvCommonDto;
import com.example.legalentity.model.CsvReportHeaderDto;
import com.example.legalentity.model.RatingDetail;
import com.example.legalentity.model.RatingSubHeader;
import com.example.legalentity.model.TrServerContext;

import lombok.RequiredArgsConstructor;

/**
 * Rating情報登録サービスクラス
 * 
 * 機能ID:CFIC0010
 * 
 * @author wild idea
 */
@RequiredArgsConstructor
@Service
@Transactional
public class RatingInputBizService {

    /** SYSTEM ID情報取得マッパー */
    private final CfccTcfcbsystemidMapper systemidMapper;

    /** DATE TIME情報取得マッパー */
    private final CfcctcfdatetimeMapper datetimeMapper;

    /** 債券現物銘柄情報取得マッパー */
    private final CfccTcfbondMapper bondMapper;

    /** Rating情報取得マッパー */
    private final CfccTcfratingMapper ratingMapper;

    /** SI外部提供クラス */
    // private final SiComponentHolder siComponent;

    /** CfTraceLogクラス */
    private final CfTraceLog trceLog;

    /** Rating情報登録帳票出力クラス */
    private final RatingPrint printer;

    private static final String ONLY_MODIFY = "1";
    private static final String REGIST_AND_PARTIAL_MODIFY = "2";
    private static final String REGIST_AND_MODIFY = "3";
    private static final String SYSTEM_SIGN_DEFAULT_DISPLAY = "1";
    private static final String SYSTEM_SIGN_DEFAULT_HIDDEN = "2";
    private static final String ORDER_BY = "SEQUENCE_SIGN ASC";
    private static final String ONLINE = "1";
    private static final String MATURITY_FLG_BEFORE = "1";
    private static final int SIZE_ONE = 1;
    private static final int SHIFT_ONE = 1;
    private static final char SELECT_LIKE_SYMBOL_PERCENT = '%';
    private static final String STR_TRUE = "True";
    private static final String MODIFY = "MODIFY";
    private static final String CANCEL = "CANCEL";
    private static final String CATEGORY_ADD = "ADD";
    private static final String CATEGORY_UPDATE = "UPDATE";
    private static final String CATEGORY_DELETE = "DELETE";
    private static final String GENERATION_ALL = "0";
    private static final int FIRST_INDEX = 0;

    /**
     * SystemIDテーブルのリストを、DateTimeテーブルからOnline日付を取得
     * 
     * @return 取得結果
     */
    public RatingInputInitResultBizModel init() {

        var result = new RatingInputInitResultBizModel();

        // 取得条件
        var example = new CfccTcfcbsystemidExample();
        example.createCriteria().andBondSignIn(List.of(ONLY_MODIFY, REGIST_AND_PARTIAL_MODIFY, REGIST_AND_MODIFY))
                .andSystemSign3In(List.of(SYSTEM_SIGN_DEFAULT_DISPLAY, SYSTEM_SIGN_DEFAULT_HIDDEN))
                .andRecordStatusEqualTo(RECORD_STATUS_VALID);
        example.setOrderByClause(ORDER_BY);

        // SyetemID情報取得
        var systemIdList = systemidMapper.selectByExample(example);

        // 取得件数が0件の場合
        if (systemIdList.isEmpty()) {
            // TrBusinessExceptionをスローする
            // throw new TrBusinessException(siComponent.getMessageSourceAccessor(),
            // MSG_ID_CF10A007);
        }

        // DateTime情報取得
        var datetimeData = datetimeMapper
                .selectByPrimaryKey(new CfccTcfdatetimeKey().setDateType(ONLINE));

        // データが存在しない場合、当日日付を取得する。データが存在する場合、OperationDateをセットする。
        var datetime = Objects.isNull(datetimeData) ? CfSystemDateUtils.getDateTime()
                : datetimeData.getOperationDate();

        // 取得したSystemID情報をセット
        var initDataList = new ArrayList<RatingInputInitBizData>();
        systemIdList.forEach(data -> {
            var initData = new RatingInputInitBizData();
            initData.setSystemId(data.getSystemId()).setCodeDisplay(data.getCodeDisplay())
                    .setSystemSign3(data.getSystemSign3());
            initDataList.add(initData);
        });

        // 結果を格納して返す
        return result.setRatingInputInitDatas(initDataList).setOperationDate(datetime);
    }

    /**
     * 指定した検索条件に該当する債券現物銘柄情報を取得する
     * 
     * @param systemId  システムID
     * @param issueCode 銘柄コード
     * @return 取得結果
     */
    public CfccTcfbond getBondData(String systemId, String issueCode) {

        // どちらにも入力がない場合
        if (!StringUtils.hasText(systemId) && !StringUtils.hasText(issueCode)) {
            // 空を返す
            return new CfccTcfbond();
        }

        // 取得条件
        var example = new CfccTcfbondExample();
        var criteria = example.createCriteria();
        criteria.andMaturityFlgEqualTo(MATURITY_FLG_BEFORE)
                .andRecordStatusEqualTo(RECORD_STATUS_VALID);

        // SystemIDに入力がある場合
        if (StringUtils.hasText(systemId)) {
            // 取得条件にSystemIdを追加
            criteria.andSystemIdqualTo(systemId);
        }

        // IssueCodeに入力がある場合
        if (StringUtils.hasText(issueCode)) {
            // 取得条件にissueCodeを追加
            criteria.andSystemIdqualTo(issueCode);
        }

        // 債券現物銘柄情報取得
        var bondData = bondMapper.selectByExample(example);

        // 該当データが0件の場合
        if (bondData.isEmpty()) {
            // TrBusinessExceptionをスローする
            // throw new TrBusinessException(siComponent, MSG_ID_CF10A015);

            // 該当データが複数件の場合
        } else if (bondData.size() != SIZE_ONE) {
            // 受け取った引数を返す
            var res = new CfccTcfbond();
            res.setIssueCode(issueCode).setSystemId(systemId);
            return res;

            // 該当データが1件の場合
        } else {
            // 取得データを返す
            return bondData.stream().findFirst().get();
        }

        return new CfccTcfbond();
    }

    /**
     * 指定した検索条件に該当する債券現物銘柄情報を取得する
     * 
     * @param systemId  システムID
     * @param issueCode 銘柄コード
     * @return 取得結果
     */
    public CfccTcfbond issueCodeGetBondData(String systemId, String issueCode) {

        // IssueCodeに入力がない場合
        if (StringUtils.hasText(issueCode)) {
            // 空を返す
            return new CfccTcfbond();
        }

        // 取得条件
        var example = new CfccTcfbondExample();
        var criteria = example.createCriteria();
        criteria.andIssueCodeEqualTo(issueCode).andMaturityFlgEqualTo(MATURITY_FLG_BEFORE)
                .andRecordStatusEqualTo(RECORD_STATUS_VALID);

        // SystemIDに入力がある場合
        if (StringUtils.hasText(systemId)) {
            // 取得条件にsystemIdを追加
            criteria.andSystemIdqualTo(systemId);
        }

        // 債券現物銘柄情報取得
        var bondData = bondMapper.selectByExample(example);

        // 該当データが存在しない場合
        if (bondData.isEmpty()) {
            // TrBusinessExceptionをスローする
            // throw new TrBusinessException(siComponent, MSG_ID_CF10A015);

            // 該当データが複数の場合
        } else if (bondData.size() > SIZE_ONE) {
            // TrBusinessExceptionをスローする
            // throw new TrBusinessException(siComponent, MSG_ID_CF10A030);
        }

        // 取得結果を返す
        return bondData.stream().findFirst().get();
    }

    /**
     * 指定した検索条件に該当する債券現物銘柄情報を取得する
     * 
     * @param systemId  システムID
     * @param issueCode 銘柄コード
     * @return 取得結果
     */
    public List<RatingInputBondSelectResultBizModel> bondSelectGetBondData(String systemId,
            String issueCode) {

        // 取得条件
        var example = new CfccTcfbondExample();
        var criteria = example.createCriteria();
        criteria.andMaturityFlgEqualTo(MATURITY_FLG_BEFORE)
                .andRecordStatusEqualTo(RECORD_STATUS_VALID);

        // SystemIDに入力がある場合
        if (StringUtils.hasText(systemId)) {
            // 取得条件を追加
            criteria.andSystemIdqualTo(systemId);
        }

        // IssueCodeに入力がある場合
        if (StringUtils.hasText(issueCode)) {
            // 末尾にワイルドカードがある場合
            if (issueCode.endsWith(String.valueOf(WILD_CARD))) {
                // '%'に置き換えLike条件を追加
                var keyBuilder = new StringBuilder(issueCode);
                keyBuilder.setCharAt(issueCode.length() - SHIFT_ONE, SELECT_LIKE_SYMBOL_PERCENT);
                criteria.andIssueCodeLike(keyBuilder.toString());

                // 末尾にワイルドカードがない場合
            } else {
                // 取得条件を追加
                criteria.andIssueCodeEqualTo(issueCode);
            }
        }

        // 債券現物銘柄情報取得
        var bondDataList = bondMapper.selectByExample(example);

        // 取得したデータを格納
        var result = new ArrayList<RatingInputBondSelectResultBizModel>();
        bondDataList.forEach(data -> {
            var model = new RatingInputBondSelectResultBizModel();
            model.setSystemId(data.getSystemId()).setIssueCode(data.getIssueCode())
                    .setIssueNameShort(data.getIssueNameShort())
                    .setCommodityType(data.getCommodityType())
                    .setDecimalSign(data.getDecimalSign())
                    .setTickDenomi(data.getTickDenomi())
                    .setSymbol(data.getSymbol()).setVendor(data.getVendor())
                    .setMkField(data.getMkField());
            result.add(model);
        });

        // 結果を返す
        return result;
    }

    /**
     * 指定した検索条件に該当するRating情報を取得する
     * 
     * @param searchKey Rating情報取得処理ビジネスモデルクラス
     * @return 取得結果
     */
    public List<CfccTcfratingWithBLOBs> readGetRatingData(
            RatingInputReadRequestBizModel searchKey) {

        // 債券現物銘柄データ存在チェック
        // 取得条件
        var bondExample = new CfccTcfbondExample();
        bondExample.createCriteria().andSystemIdqualTo(searchKey.getSystemId())
                .andIssueCodeEqualTo(searchKey.getIssueCode())
                .andMaturityFlgEqualTo(MATURITY_FLG_BEFORE)
                .andRecordStatusEqualTo(RECORD_STATUS_VALID);

        // 債券現物銘柄情報を取得し、該当データが0件の場合
        if (bondMapper.selectByExample(bondExample).isEmpty()) {
            // TrBusinessExceptionをスローする
            // throw new TrBusinessException(siComponent, MSG_ID_CF10A007);
        }

        var ratingExample = new CfccTcfratingExample();
        var ratingCriteria = ratingExample.createCriteria();
        // QueryモードかつGeneration = Currentの場合
        if (ScreenMode.QUERY == ScreenMode.fromValue(TrServerContext.getCtxBaseDto().getTrScreenMode())
                && STR_TRUE.equals(searchKey.getCurrent())) {
            // 取得条件
            ratingCriteria.andSystemIdqualTo(searchKey.getSystemId())
                    .andIssueCodeEqualTo(searchKey.getIssueCode())
                    .andRecordStatusEqualTo(RECORD_STATUS_VALID)
                    .andAppliedDateFromLessThanOrEqualTo(searchKey.getAppliedDate())
                    .andAppliedDateToGreaterThanOrEqualTo(searchKey.getAppliedDate());

            // Generation = Allの場合
        } else {
            // 取得条件
            ratingCriteria.andSystemIdqualTo(searchKey.getSystemId())
                    .andIssueCodeEqualTo(searchKey.getIssueCode())
                    .andRecordStatusEqualTo(RECORD_STATUS_VALID);
        }

        // Rating情報取得
        var ratingList = ratingMapper.selectByExampleWithBLOBs(ratingExample);

        // 該当データが0件の場合
        if (ratingList.isEmpty()) {
            // TrBusinessExceptionをスローする
            // throw new TrBusinessException(siComponent, MSG_ID_CF10A007);
        }

        // 取得結果を返す
        return ratingList;
    }

    /**
     * Rating情報レコードの変更確認を行う
     * 
     * @param bizModel Rating情報取得リクエストビズモデル
     */
    public void checkRatingRecord(RatingInputApplyChkRequestBizModel bizModel) {

        var mode = TrServerContext.getCtxBaseDto().getTrScreenMode();
        var recordList = bizModel.getRecordList();

        // モードがMODIFYもしくはCANCELの場合
        if (MODIFY.equals(mode) || CANCEL.equals(mode)) {
            // Key変更項目チェック
            var keyChanged = recordList.stream()
                    .anyMatch(record -> !bizModel.getSystemId().equals(record.getSystemId())
                            || !bizModel.getIssueCode().equals(record.getIssueCode()));

            // Key項目が変更されたレコードが存在する場合
            if (keyChanged) {
                // TrBusinessExceptionをスローする
                // throw new TrBusinessException(siComponent, MSG_ID_CF10A017);
            }

            // 変更チェック 追加、変更、削除されたレコードが存在しない場合
            var noChanged = recordList.stream().noneMatch(record -> record.isChanged());
            if (noChanged) {
                // TrBusinessExceptionをスローする
                // throw new TrBusinessException(siComponent, MSG_ID_CF10A012);
            }
        }

        // 新規登録レコード(変更区分："ADD")の期間重複チェック
        recordList.stream().filter(record -> CATEGORY_ADD.equals(record.getUpdateCategory()))
                .forEach(addRecord -> cheDuplicateTerm(addRecord));
    }

    /**
     * レコードの期間重複チェックを行う。<br>
     * t_CF_ratingテーブルを検索し、テーブル上に期間重複するレコードがないかチェックする。<br>
     * 期間重複するレコードが存在する場合、業務例外をスローする。
     * 
     * @param record レコード
     */
    private void cheDuplicateTerm(RatingInputRecord record) {

        // 取得条件
        var example = new CfccTcfratingExample();
        example.createCriteria().andSystemIdqualTo(record.getSystemId())
                .andIssueCodeEqualTo(record.getIssueCode())
                .andRecordStatusEqualTo(RECORD_STATUS_VALID)
                .andAppliedDateFromLessThanOrEqualTo(record.getAppliedDateTo())
                .andAppliedDateToGreaterThanOrEqualTo(record.getAppliedDateFrom());
        // Rating情報取得
        var result = ratingMapper.selectByExample(example);

        // 期間重複するレコードが存在する場合
        if (!result.isEmpty()) {
            // TrBusinessExceptionをスローする
            // throw new TrBusinessException(siComponent, MSG_ID_CF10A012);
        }
    }

    /**
     * 指定した検索条件に該当するRating情報を登録/修正する
     * 
     * @param bizModel Rating情報登録ビジネスモデルクラス
     * @return 取得結果
     */
    public List<CfccTcfratingWithBLOBs> apply(RatingInputBizModel bizModel) {

        List<CfccTcfratingWithBLOBs> resultList = new ArrayList<>();
        var baseDto = TrServerContext.getCtxBaseDto();
        var mode = ScreenMode.fromValue(baseDto.getTrScreenMode());
        var userId = baseDto.getTrUserId();

        switch (mode) {
            case NEW:
                bizModel.getRecordList().forEach(data -> {
                    // 更新区分がADDの場合
                    if (CATEGORY_ADD.equals(data.getUpdateCategory())) {
                        data.setUserId(userId);
                        // DB更新処理
                        insertRecord(data);
                    }
                });
                break;
            case MODIFY:
                bizModel.getRecordList().forEach(data -> {
                    // 更新区分がADDの場合
                    if (CATEGORY_ADD.equals(data.getUpdateCategory())) {
                        data.setUserId(data.getUserId());
                        // DB更新処理
                        insertRecord(data);
                    }

                    if (CATEGORY_UPDATE.equals(data.getUpdateCategory())) {
                        data.setUserId(userId);
                        // DB更新処理
                        updateRecord(data, RECORD_STATUS_VALID);
                    }
                });
                resultList = getValidRecord(bizModel);
                break;
            case CANCEL:
                bizModel.getRecordList().forEach(data -> {
                    // 更新区分がDELETEのデータの場合
                    if (CATEGORY_DELETE.equals(data.getUpdateCategory())) {
                        data.setUserId(userId);
                        // DB更新処理
                        updateRecord(data, RECORD_STATUS_DELETE);
                    }
                });
                break;
            default:
                break;
        }

        // プリントサービス
        printRatingData(toPrintBizModel(bizModel));

        // トレースログを出力する
        trceLog.writeTrceLog(SERVICE_NAME_APPLY);

        // 結果を返す
        return resultList;
    }

    /**
     * BizModelからPrintBizModelへ変換する。<br>
     * 変換時に銘柄名を取得しセットする。
     * 
     * @param bizModel Rating情報登録ビジネスモデルクラス
     * @return PrintBizモデル
     */
    private RatingInputPrintRequestBizModel toPrintBizModel(RatingInputBizModel bizModel) {

        var subHeadeer = new RatingSubHeader();
        var detailList = new ArrayList<CsvReportHeaderDto>();

        subHeadeer.setSystemId(bizModel.getSystemId()).setIssueCode(bizModel.getIssueCode())
                .setGeneration(GENERATION_ALL);

        var bond = issueCodeGetBondData(bizModel.getSystemId(), bizModel.getIssueCode());
        subHeadeer.setIssueCodeName(bond.getIssueNameShort());

        bizModel.getRecordList().forEach(data -> {
            var ratingDetail = new RatingDetail();
            ratingDetail.setAppliedDateFrom(data.getAppliedDateFrom())
                    .setAppliedDateTo(data.getAppliedDateTo())
                    .setRating(data.getRating())
                    .setRemarks(data.getRemarks());

            ratingDetail.setChgFlg(StringUtils.hasText(data.getUpdateCategory())
                    ? String.valueOf(data.getUpdateCategory().charAt(FIRST_INDEX))
                    : STR_EMPTY);

            detailList.add(ratingDetail);
        });

        return new RatingInputPrintRequestBizModel().setSubHeader(subHeadeer)
                .setRatingDetail(detailList).setPrintMode(PRT_MODE_APPLY);
    }

    /**
     * 指定した検索条件に該当する論理削除レコードを取得する
     * 
     * @param record 検索対象レコード
     * @return 取得結果
     */
    private List<CfccTcfratingWithBLOBs> getLogicalDeleteRecord(RatingInputRecord record) {

        var example = new CfccTcfratingExample();
        example.createCriteria().andSystemIdqualTo(record.getSystemId())
                .andIssueCodeEqualTo(record.getIssueCode())
                .andAppliedDateFromEqualTo(record.getAppliedDateFrom())
                .andRecordStatusEqualTo(RECORD_STATUS_DELETE);
        return ratingMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * Rating情報にレコードを登録する
     * 
     * @param record 登録するRating情報
     */
    private void insertRecord(RatingInputRecord record) {

        // 論理削除レコードを取得
        var logicalDeleteRecord = getLogicalDeleteRecord(record);

        // 論理削除レコードが存在しない場合
        if (logicalDeleteRecord.isEmpty()) {
            // 作成日、更新日、レコードステータスをセット
            record.setCreateDate(CfSystemDateUtils.getDateReturnsLocalDateTime())
                    .setUpdateDate(CfSystemDateUtils.getDateTime())
                    .setRecordStatus(RECORD_STATUS_VALID);
            // 新規登録処理
            ratingMapper.insert(record);

            // 論理削除レコードが存在する場合
        } else {
            // 作成日とタイムスタンプをセット(論理削除データの作成日、タイムスタンプ)
            var old = logicalDeleteRecord.stream().findFirst().get();
            record.setTimestamp(old.getTimestamp()).setCreateDate(old.getCreateDate());

            updateLogicalDeleteRecord(record, RECORD_STATUS_VALID);
        }
    }

    /**
     * Rating情報テーブルの論理削除レコードを更新する
     * 
     * @param record       更新するRating情報
     * @param recordStatus レコードステータス
     */
    private void updateLogicalDeleteRecord(RatingInputRecord record, String recordStatus) {

        // 更新日とレコードステータスをセット
        record.setUpdateDate(CfSystemDateUtils.getDateTime())
                .setRecordStatus(recordStatus);

        // 更新処理
        ratingMapper.updateWithVersionByPrimaryKeyWithBLOBs(record.getTimestamp(),
                record);
    }

    /**
     * Rating情報テーブルのレコードを更新する
     * 
     * @param record       更新するRating情報
     * @param recordStatus レコードステータス
     */
    private void updateRecord(RatingInputRecord record, String recordStatus) {

        // タイムスタンプチェック
        var tableRecord = checkTimestamp(record);

        // 作成日(テーブルレコードの作成日)と更新日、レコードステータスをセット
        record.setCreateDate(tableRecord.getCreateDate())
                .setUpdateDate(CfSystemDateUtils.getDateTime())
                .setRecordStatus(recordStatus);
        // 更新処理
        ratingMapper.updateWithVersionByPrimaryKeyWithBLOBs(record.getTimestamp(), record);
    }

    /**
     * Rating情報テーブルの更新前レコードと更新するレコードのタイムスタンプを比較する
     * 
     * @param record 更新するRating情報レコード
     * @return Rating情報テーブルの更新前レコード
     */
    private CfccTcfratingWithBLOBs checkTimestamp(RatingInputRecord record) {

        // 検索条件
        var example = new CfccTcfratingExample();
        example.createCriteria().andSystemIdqualTo(record.getSystemId())
                .andIssueCodeEqualTo(record.getIssueCode())
                .andAppliedDateFromEqualTo(record.getAppliedDateFrom())
                .andRecordStatusEqualTo(RECORD_STATUS_VALID);

        // テーブル検索
        var res = ratingMapper.selectByExampleWithBLOBs(example);
        // 更新前にレコードが存在しない、タイムスタンプが一致しない場合
        if (res.isEmpty()
                || !Arrays.equals(res.stream().findFirst().get().getTimestamp(),
                        record.getTimestamp())) {
            // TrBusinessExceptionをスローする
            // throw new TrBusinessException(siComponent, MSG_ID_CF10A013);
        }

        // 結果を返却
        return res.stream().findFirst().get();
    }

    /**
     * Rating情報テーブルの有効レコードリストを取得する
     * 
     * @param bizModel Rating情報登録ビジネスモデルクラス
     * @return 取得結果
     */
    private List<CfccTcfratingWithBLOBs> getValidRecord(RatingInputBizModel bizModel) {
        // 検索条件設定
        var example = new CfccTcfratingExample();
        example.createCriteria().andSystemIdqualTo(bizModel.getSystemId())
                .andIssueCodeEqualTo(bizModel.getIssueCode())
                .andRecordStatusEqualTo(RECORD_STATUS_VALID);
        // 結果を返却
        return ratingMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 指定したRating情報の帳票(Proof List)を出力する
     * 
     * @param model Rating情報登録処理ビジネスモデルクラス
     */
    public void printRatingData(RatingInputPrintRequestBizModel model) {

        // データ作成
        var printData = new CsvCommonDto();
        var reportCollection = new LinkedHashMap<AbstractCsvReportSubHeaderDto, List<CsvReportHeaderDto>>();

        // サブヘッダーに項目をセット
        var reqSubHeader = model.getSubHeader();
        var subHeadeer = new RatingSubHeader();
        subHeadeer.setSystemId(reqSubHeader.getSystemId())
                .setIssueCode(reqSubHeader.getIssueCode())
                .setIssueCodeName(reqSubHeader.getIssueCodeName())
                .setGeneration(reqSubHeader.getGeneration());

        var detailList = new ArrayList<CsvReportHeaderDto>();
        model.getRatingDetail().forEach(detailList::add);

        reportCollection.put(subHeadeer, detailList);
        printData.setReportCollection(reportCollection);

        printer.printOut(printData, model.getPrintMode());
    }
}
