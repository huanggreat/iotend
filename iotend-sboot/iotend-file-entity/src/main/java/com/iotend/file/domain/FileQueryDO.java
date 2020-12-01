package com.iotend.file.domain;


import com.iotend.file.entity.File;
import lombok.Data;

/**
 * 文件查询 DO
 *
 * @author huang
 * @date 2019/05/07
 */
@Data
public class FileQueryDO extends File {
    private File parent;

}