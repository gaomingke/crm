package com.kaishengit.mapper;

import com.kaishengit.pojo.ProgressFile;

import java.util.List;

public interface ProgressFileMapper {
    /**
     * 保存
     * @param progressFile
     */
    void save(ProgressFile progressFile);

    /**
     * 根据进度Id查询关联文件
     * @param progressId
     * @return
     */
    List<ProgressFile> findByProgressId(Integer progressId);

    /**
     * 根据客户ID，获取关联的文件
     * @param id
     * @return
     */
    List<ProgressFile> findByCustId(Integer id);
}
