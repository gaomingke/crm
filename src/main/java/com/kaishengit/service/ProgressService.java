package com.kaishengit.service;

import com.google.common.collect.Maps;
import com.kaishengit.mapper.CustomerMapper;
import com.kaishengit.mapper.ProgressFileMapper;
import com.kaishengit.mapper.ProgressMapper;
import com.kaishengit.pojo.Customer;
import com.kaishengit.pojo.Progress;
import com.kaishengit.pojo.ProgressFile;
import com.kaishengit.util.Page;
import com.kaishengit.util.QiniuUtil;
import com.kaishengit.util.ShiroUtil;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Named
@Transactional
public class ProgressService {

    @Inject
    private ProgressMapper progressMapper;
    @Inject
    private ProgressFileMapper progressFileMapper;
    @Inject
    private QiniuUtil qiniuUtil;
    @Inject
    private CustomerMapper customerMapper;

    /**
     * 根据首页显示数据
     * @param userid 所属用户ID
     * @param progress 进度
     * @param date 创建时间 【今天，昨天，本周...】
     * @param context 跟进内容
     * @param p 当前页码
     * @return
     */
    public Page<Progress> findProgressByPageAndParam(String userid, String progress, String date, String context, String p) {

        Map<String,Object> param = Maps.newHashMap();
        param.put("userid",userid);
        param.put("progress",progress);
        if(StringUtils.isNotEmpty(context)) {
            param.put("context", "%" + context + "%");
        }

        DateTime dateTime = new DateTime();
        if(StringUtils.isNotEmpty(date)) {
            if("今天".equals(date)) {
                param.put("startDate",dateTime.toString("yyyy-MM-dd"));
                param.put("endDate",dateTime.toString("yyyy-MM-dd"));
            } else if("昨天".equals(date)) {
                dateTime = dateTime.minusDays(1);
                param.put("startDate",dateTime.toString("yyyy-MM-dd"));
                param.put("endDate",dateTime.toString("yyyy-MM-dd"));
            } else if("本周".equals(date)) {
                LocalDate localDate = new LocalDate();

                localDate = localDate.withDayOfWeek(DateTimeConstants.MONDAY);
                param.put("startDate",localDate.toString("yyyy-MM-dd"));

                localDate = localDate.withDayOfWeek(DateTimeConstants.SUNDAY);
                param.put("endDate",localDate.toString("yyyy-MM-dd"));
            } else if("本月".equals(date)) {
                LocalDate localDate = new LocalDate();

                localDate = localDate.dayOfMonth().withMinimumValue();
                param.put("startDate",localDate.toString("yyyy-MM-dd"));

                localDate = localDate.dayOfMonth().withMaximumValue();
                param.put("endDate",localDate.toString("yyyy-MM-dd"));
            } else if("更早".equals(date)) {
                LocalDate localDate = new LocalDate();
                localDate = localDate.dayOfMonth().withMinimumValue();
                param.put("endDate",localDate.toString("yyyy-MM-dd"));
            }
        }

        int count = progressMapper.countByParam(param);
        Page<Progress> page = new Page<>(p,count,10);
        param.put("start",page.getStart());
        param.put("end",page.getSize());

        List<Progress> progressList = progressMapper.findByParam(param);
        page.setItems(progressList);

        return page;
    }

    /**
     * 新增跟进记录
     * @param progress 跟进对象
     * @param file 关联文件集合
     */
    public void saveNewProgress(Progress progress,MultipartFile[] file) {
        //更新客户的最近跟进信息
        Customer customer = customerMapper.findById(progress.getCustid());
        customer.setProgress(progress.getProgress());
        customer.setProgresstime(DateTime.now().toString("yyyy-MM-dd HH:mm"));
        customerMapper.update(customer);


        progress.setUserid(ShiroUtil.getCurrentUserId());
        progress.setCreatetime(DateTime.now().toString("yyyy-MM-dd HH:mm"));

        progressMapper.save(progress);

        //判断文件集合是否有文件
        if(file != null && file.length > 0) {
            for(MultipartFile multipartFile : file) {
                if(!multipartFile.isEmpty()) {
                    String key = null;
                    try {
                        key = qiniuUtil.uploadFile(multipartFile.getInputStream());
                    } catch (IOException e) {
                        throw new RuntimeException("获取inputstream异常");
                    }

                    ProgressFile progressFile = new ProgressFile();
                    progressFile.setCreatetime(DateTime.now().toString("yyyy-MM-dd HH:mm"));
                    progressFile.setUserid(ShiroUtil.getCurrentUserId());
                    progressFile.setCustid(progress.getCustid());
                    progressFile.setFilename(multipartFile.getOriginalFilename());
                    progressFile.setPath(key);
                    progressFile.setProgressid(progress.getId());
                    progressFileMapper.save(progressFile);
                }
            }
        }
    }

    /**
     * 根据客户Id，查询对应的跟进记录
     * @param id 客户ID
     * @return
     */
    public List<Progress> findProgressByCustId(Integer id) {
        return progressMapper.findByCustId(id);
    }

    /**
     * 根据客户ID，获取关联的文件
     * @param id
     * @return
     */
    public List<ProgressFile> findProgressFileByCustId(Integer id) {
        return progressFileMapper.findByCustId(id);
    }
}
