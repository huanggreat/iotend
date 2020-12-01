package com.iotend.file.domain;


import com.iotend.file.enumeration.DataType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huang
 * @create 2018-09-06 10:10
 * @desc 文件类型数量、
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileStatisticsDO {
    /**
     * 文件类型 IMAGE、DOC等
     */
    private DataType dataType;
    /**
     * 时间类型 （按月、周、天？）
     */
    private String dateType;
    /**
     * 文件数量
     */
    private Integer num;
    /**
     * 文件大小
     */
    private Long size;
}
