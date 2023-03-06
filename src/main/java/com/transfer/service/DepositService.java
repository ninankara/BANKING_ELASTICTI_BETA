package com.transfer.service;

import com.transfer.dao.DaoApiMsgHis;
import com.transfer.dao.DaoCustBal;
import com.transfer.dao.DaoCustBase;
import com.transfer.dao.DaoTrnfHis;
import com.transfer.entity.ApiMsgHis;
import com.transfer.entity.CustBal;
import com.transfer.entity.TrnfHis;
import com.transfer.model.*;
import com.transfer.module.ExtKakaoApiNotifInput;
import com.transfer.module.ExtKakaoApiNotifOutput;
import com.transfer.util.RestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepositService {
    @Autowired public DaoTrnfHis daoTrnfHis;
    @Autowired public DaoCustBase daoCustBase;
    @Autowired public DaoCustBal daoCustBal;
    @Autowired public DaoApiMsgHis daoApiMsgHis;
    private final RestTemplate restTemplate;

    public BalCheckOutput balCheck(BalCheckInput input) throws URISyntaxException {
        BalCheckOutput output = new BalCheckOutput();
        Long refNo =  insertApiMsgHis("balCheck", input.getAcctNo(), LocalDateTime.now(), input.toString());

        output.setAcctNo(input.getAcctNo());
        output.setDateTime(LocalDateTime.now());

        try {
            CustBal bal = daoCustBal.getBal(input.getAcctNo());
            output.setBal(bal.getBal());
            output.setStatus    ("00");
        } catch (Exception E){
            output.setStatus    ("ER");
            output.setDesc      ("Balance can't be found");
        }
        return output;
    }

    public TrscReqOutput trscReq(TrscReqInput input) throws URISyntaxException {
        TrscReqOutput output =  new TrscReqOutput();
        Long refNo =  insertApiMsgHis("trscReq", input.getAcctNo(), input.getDateTime(), input.toString());
        Double bal = daoCustBal.getBal(Long.valueOf(input.getAcctNo())).getBal();
        //insert trnfHis
        TrnfHis trnfHis = new TrnfHis();
        trnfHis.setRefNo        (refNo);
        trnfHis.setSendAcctNo   (Long.valueOf(input.getAcctNo()));
        trnfHis.setRecvAcctNo   (Long.valueOf(input.getRecvNo()));
        trnfHis.setTrscDate     (input.getDateTime());
        trnfHis.setAmt          (input.getAmt());
        trnfHis.setRmk          (input.getRmk());
        trnfHis.setBalAfTrsc    (bal - input.getAmt());
        trnfHis.setRequestTime  (LocalDateTime.now());
        trnfHis.setSts          ("WT");


        output.setAcctNo(input.getAcctNo());
        output.setDateTime(LocalDateTime.now());
        try{
            daoTrnfHis.save(trnfHis);
            //send message to receiver
            List<String> receiver = new ArrayList<String>();
            receiver.add(daoCustBase.getUuid(trnfHis.getRecvAcctNo()));
            ExtKakaoApiNotifInput.templateObject message = new ExtKakaoApiNotifInput.templateObject();
            message.setObject_type("text");
            message.setText("받아주세요");
            message.setLink(null);
            message.setButton_title("받기");
            //TODO Send data to kakao MSG API
           // ExtKakaoApiNotifOutput msgOutput =  restTemplate.postMessage(receiver,message);
            
            output.setStatus    ("00");
        } catch (Exception E){
            output.setStatus("ER");
            output.setDesc("Error when inserting Data");
        }
        //TODO : posting - not in the task scope

        return output;
    }

    public TrscConfirmOutput trscConfirm(TrscConfirmInput input) throws URISyntaxException {
        TrscConfirmOutput output =  new TrscConfirmOutput();
        //reversao is performed by Admin (0)
        Long refNo =  insertApiMsgHis("trscConfirm", 0L, input.getDateTime(), input.toString());

        TrnfHis trnfHis = new TrnfHis();
        try{
            trnfHis = daoTrnfHis.findByRefNo(Long.valueOf(input.getRefNo()));

        } catch (Exception E) {
            output.setStatus("ER");
            output.setDesc("Error finding the transaction");
        }

        output.setDateTime(LocalDateTime.now());

        if (trnfHis.getRefNo() != 0){
            if (trnfHis.getSts().equals("00")){
                output.setStatus("ER");
                output.setDesc("이미 입금처리 완료한 요청입니다");
            }
            else{
                try{
                    trnfHis.setSts("00");
                    trnfHis.setConfirmationTime(input.getDateTime());
                    daoTrnfHis.save(trnfHis);

                    //update Balance sender and receiver
                    CustBal sendBal = daoCustBal.getBal(trnfHis.getSendAcctNo());
                    sendBal.setBal(sendBal.getBal() - trnfHis.getAmt());
                    daoCustBal.save(sendBal);
                    CustBal recvBal = daoCustBal.getBal(trnfHis.getRecvAcctNo());
                    recvBal.setBal(recvBal.getBal() + trnfHis.getAmt());
                    daoCustBal.save(recvBal);
                } catch (Exception E){
                    output.setStatus("ER");
                    output.setDesc("Error confirming the transaction");
                }
                //TODO : posting - not in the task scope
            }
        }
        
        

        return output;
    }

    public CheckHisOutput checkHis(CheckHisInput input) throws URISyntaxException {
        CheckHisOutput output = new CheckHisOutput();

        Long refNo =  insertApiMsgHis("checkHis", input.getAcctNo(), input.getDateTime(), input.toString());

        output.setAcctNo(input.getAcctNo());
        output.setDateTime(LocalDateTime.now());
        try {
            List<TrnfHis> trnfHisList = daoTrnfHis.findTrscHis(Long.valueOf(input.getAcctNo()),input.getStrDt(),input.getEndDt());
            List<CheckHisOutput.listTrsc> listTrscs = new ArrayList<CheckHisOutput.listTrsc>();
            for (TrnfHis trnfHis : trnfHisList){
                CheckHisOutput.listTrsc listTrsc = new CheckHisOutput.listTrsc();
                listTrsc.setAmt         (trnfHis.getAmt());
                listTrsc.setReceiver    (trnfHis.getRecvAcctNo().toString());
                listTrsc.setRemark      (trnfHis.getRmk());
                listTrsc.setTrscDate    (trnfHis.getTrscDate());
                listTrscs.add(listTrsc);
            }
            output.setListTrsc  (listTrscs);
            output.setTotal     (listTrscs.size());
            output.setStatus    ("00");
        } catch (Exception E){
            output.setStatus("ER");
            output.setDesc("Error Finding History");
        }
        return output;
    }

    //insert API msg and create ref no
    public Long insertApiMsgHis (String apiName, Long acctNo, LocalDateTime dateTime, String msg){
        //count data in Api Msg His
        List<ApiMsgHis> apiMsgHisList = daoApiMsgHis.findAll();

        ApiMsgHis apiMsgHis = new ApiMsgHis();
        apiMsgHis.setDateTime   (dateTime);
        apiMsgHis.setAcctNo     (acctNo);
        apiMsgHis.setApiName    (apiName);
        apiMsgHis.setRefNo      (Long.valueOf(apiMsgHisList.size()+1));
        apiMsgHis.setMsg        (msg);


        daoApiMsgHis.save(apiMsgHis);
        return Long.valueOf(apiMsgHisList.size()+1);
    }
}
