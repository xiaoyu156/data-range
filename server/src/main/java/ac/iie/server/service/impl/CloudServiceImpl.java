package ac.iie.server.service.impl;

import ac.iie.common.utils.SendHttpRequest;
import ac.iie.server.api.base.Constant;
import ac.iie.server.config.SystemConfig;
import ac.iie.server.service.ICloudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 云平台接口适配
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-14 17:44
 * @version: 1.0.0
 */
@Slf4j
@Service(value = "cloudService")
public class CloudServiceImpl implements ICloudService {

    private final SystemConfig systemConfig;

    @Autowired
    public CloudServiceImpl(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    @Override
    public String cloudService(String param, String addKey, String type) {

        /*
        根据接口key获取接口地址，拼接url
         */
        String url = systemConfig.getCloudTarget() + systemConfig.getCloudService().get(addKey);
        /*
        判断接口调用类型,返回结果
         */
        String result = null;
        if (Constant.POST_INTERFACE.equals(type)) {
            result = SendHttpRequest.sendPost(url, param);
        }
        if (Constant.GET_INTERFACE.equals(type)) {
            result = SendHttpRequest.sendGet(url, param);
        }
        log.info("------------------------------:"+result);
        return result;
    }

}
