/**
 * alibaba.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alibaba.dbhub.server.domain.core.impl;

import java.time.LocalDateTime;

import com.alibaba.dbhub.server.domain.api.model.Config;
import com.alibaba.dbhub.server.domain.api.param.SystemConfigParam;
import com.alibaba.dbhub.server.domain.api.service.ConfigService;
import com.alibaba.dbhub.server.domain.core.converter.ConfigConverter;
import com.alibaba.dbhub.server.domain.repository.entity.SystemConfigDO;
import com.alibaba.dbhub.server.domain.repository.mapper.SystemConfigMapper;
import com.alibaba.dbhub.server.tools.base.wrapper.result.ActionResult;
import com.alibaba.dbhub.server.tools.base.wrapper.result.DataResult;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jipengfei
 * @version : ConfigServiceImpl.java
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    @Autowired
    private ConfigConverter configConverter;

    @Override
    public ActionResult create(SystemConfigParam param) {
        SystemConfigDO systemConfigDO = configConverter.param2do(param);
        systemConfigDO.setGmtCreate(LocalDateTime.now());
        systemConfigDO.setGmtModified(LocalDateTime.now());
        systemConfigMapper.insert(systemConfigDO);
        return ActionResult.isSuccess();
    }

    @Override
    public ActionResult update(SystemConfigParam param) {
        SystemConfigDO systemConfigDO = configConverter.param2do(param);
        UpdateWrapper<SystemConfigDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("code", param.getCode());
        systemConfigMapper.update(systemConfigDO, updateWrapper);
        return ActionResult.isSuccess();
    }

    @Override
    public ActionResult createOrUpdate(SystemConfigParam param) {
        SystemConfigDO systemConfigDO = systemConfigMapper.selectOne(
            new UpdateWrapper<SystemConfigDO>().eq("code", param.getCode()));
        if (systemConfigDO == null) {
            return create(param);
        } else {
            return update(param);
        }
    }

    @Override
    public DataResult<Config> find(String code) {
        SystemConfigDO systemConfigDO = systemConfigMapper.selectOne(
            new UpdateWrapper<SystemConfigDO>().eq("code", code));
        return DataResult.of(configConverter.do2model(systemConfigDO));
    }

    @Override
    public ActionResult delete(String code) {
        systemConfigMapper.delete(new UpdateWrapper<SystemConfigDO>().eq("code", code));
        return ActionResult.isSuccess();
    }
}