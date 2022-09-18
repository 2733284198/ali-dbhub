package com.alibaba.dataops.server.domain.core.api.test;

import com.alibaba.dataops.server.tools.base.wrapper.param.PageQueryParam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 测试的分页查询
 *
 * @author Jiaju Zhuang
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class TestPageQueryParam extends PageQueryParam {

    /**
     * 姓名
     */
    public String name;
}
