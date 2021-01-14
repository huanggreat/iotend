package com.iotend.my.controller;

import com.alibaba.fastjson.JSON;
import com.iotend.my.api.MedInsDetailsService;
import com.iotend.my.api.MedInsService;
import com.iotend.my.model.MedIns;
import com.iotend.my.model.MedInsDetails;
import com.iotend.my.model.Nhsa;
import com.iotend.my.model.NhsaDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.iotend.my.utils.CommonUtils.sendGet;

@Controller
@RequestMapping({"/nhsa"})
@Slf4j
public class MedInsController {

    @Autowired
    MedInsService medInsService;

    @Autowired
    MedInsDetailsService medInsDetailsService;

    @RequestMapping("add")
    @Async
    @ResponseBody
    public String insertNhsa() throws InterruptedException {
        String host = "http://code.nhsa.gov.cn:8000/";
        String path = "hc/stdPublishData/getStdPublicDataList.html";
        for (int i = 1; i < 654; i++) {
            String jsonstr = sendGet(host+path,String.format("page=%s&rows=50&sord=asc&nd=%s&_search=false&releaseVersion=20200628",i,System.currentTimeMillis()));
            Nhsa nhsa= JSON.parseObject(jsonstr,Nhsa.class);
            log.info(String.format("===========>第：%s,统计:%s" ,i,nhsa.count));
            for (MedIns medIns:nhsa.rows
            ) {
                medInsService.add(medIns);
                Thread.sleep(30);
                log.info(JSON.toJSONString(medIns));
            }
            Thread.sleep(300);
        }
        return "insertNhsa finish!";
    }
    @RequestMapping("addetails")
    @Async
    @ResponseBody
    public String insertNhsaDetails() throws InterruptedException {
        String host = "http://code.nhsa.gov.cn:8000/";
        String path = "hc/stdPublishData/getStdPublicDataListDetail.html";
        List<MedIns> rowList = medInsService.findById(39393);
        log.info(String.format("总共多少条：%s",rowList.size()));
        for (MedIns medIns : rowList
        ) {
            String jsonstr = sendGet(host+path,String.format("specificationCode=%s&releaseVersion=%s&_search=false&nd=%s&rows=25&page=1&sord=asc",medIns.specificationCode,medIns.releaseVersion,System.currentTimeMillis()));
            log.info(jsonstr);
            NhsaDetails nhsaDetails= JSON.parseObject(jsonstr,NhsaDetails.class);
            log.info(String.format("==============>%s",medIns.id));
            for (MedInsDetails medInsDetails: nhsaDetails.rows
            ) {
                medInsDetailsService.add(medInsDetails);
                Thread.sleep(10);
                log.info(JSON.toJSONString(medInsDetails));
            }
            Thread.sleep(200);
        }
        return "insertNhsaDetails finish!";
    }
}
