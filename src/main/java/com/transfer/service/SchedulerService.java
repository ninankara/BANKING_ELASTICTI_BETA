package com.transfer.service;

import com.transfer.dao.DaoCustBase;
import com.transfer.dao.DaoTrnfHis;
import com.transfer.entity.TrnfHis;
import com.transfer.module.ExtKakaoApiNotifInput;
import com.transfer.module.ExtKakaoApiNotifOutput;
import com.transfer.util.RestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SchedulerService {
    @Autowired public DaoTrnfHis daoTrnfHis;
    @Autowired public DaoCustBase daoCustBase;
    private final RestTemplate restTemplate;
    private final RestTemplateBuilder restTemplateBuilder;


    //run thread everySeconds
    @Scheduled(fixedDelay=1000)
    public String findUnfinishedTrsc () throws URISyntaxException {

        //Find the transaction which haven't been received for 23H
        List<TrnfHis> trnfHisList = daoTrnfHis.findUnfinishedTrsc();
        if (trnfHisList.size()>0){
            List<String> receiver = new ArrayList<String>();
            for (TrnfHis trnfHis : trnfHisList){
                //Add all of the receiver UUID for all of unfinished transaction
                receiver.add(daoCustBase.getUuid(trnfHis.getRecvAcctNo()));
            }
            ExtKakaoApiNotifInput.templateObject message = new ExtKakaoApiNotifInput.templateObject();
            message.setObject_type("text");
            message.setText("간편이체를 받아주세요");
            message.setLink(null);
            message.setButton_title(null);
            //TODO Send data to kakao MSG API
            ExtKakaoApiNotifOutput output =  restTemplate.postMessage(receiver,message);
            log.debug("### send notification for {} receiver from {} unfinished transaction", output.getReceiver_uuids().size(), receiver.size());
        }

        return "";

    }

    //run thread everySeconds
    @Scheduled(fixedDelay=1000)
    public String findTimeoutTrsc () throws URISyntaxException {

        //Find the transaction which haven't been received for 23H
        List<TrnfHis> trnfHisList = daoTrnfHis.findTimeoutTrsc();
        if (trnfHisList.size()>0){
            int i =0;
            List<String> sender = new ArrayList<String>();
            List<String> receiver = new ArrayList<String>();
            for (TrnfHis trnfHis : trnfHisList){
                //Add all of the receiver UUID for all of unfinished transaction
                sender.add(daoCustBase.getUuid(trnfHis.getSendAcctNo()));
                receiver.add(daoCustBase.getUuid(trnfHis.getRecvAcctNo()));
            }
            ExtKakaoApiNotifInput.templateObject message = new ExtKakaoApiNotifInput.templateObject();
            message.setObject_type("text");
            message.setText("간편이체를 자동취소");
            message.setLink(null);
            message.setButton_title(null);
            //TODO : send notification for both receiver and sender
            ExtKakaoApiNotifOutput outputSend =  restTemplate.postMessage(sender,message);
            ExtKakaoApiNotifOutput outputRec =  restTemplate.postMessage(receiver,message);
            String retVal;
            log.debug("### send notification for {} sender from {} unfinished transaction", outputSend.getReceiver_uuids().size(), sender.size());
            log.debug("### send notification for {} receiver from {} unfinished transaction", outputRec.getReceiver_uuids().size(), receiver.size());

        }
        return ""; //임시
    }
}
