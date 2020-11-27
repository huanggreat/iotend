package com.baidu.fsg.uid.worker;

import com.baidu.fsg.uid.utils.ValuedEnum;

/**
 * WorkerNodeType
 * <li>CONTAINER: Such as Docker
 * <li>ACTUAL: Actual machine
 *
 * @author yutianbao
 */
public enum WorkerNodeType implements ValuedEnum<Integer> {

    /**
     * 容器
     */
    CONTAINER(1),
    /**
     * 实际机器
     */
    ACTUAL(2);

    /**
     * Lock type
     */
    private final Integer type;

    /**
     * Constructor with field of type
     */
    WorkerNodeType(Integer type) {
        this.type = type;
    }

    @Override
    public Integer value() {
        return type;
    }

}
