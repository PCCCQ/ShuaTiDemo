package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.AddBank;
import com.example.demo.entity.Bank;
import com.example.demo.entity.Exam;
import com.example.demo.entity.Tiku;
import com.example.demo.mapper.BankMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankController<data> {
    @Autowired
    private BankMapper bankMapper;
    private Object data;

    //    查询全部题库接口
    @SneakyThrows
    @PostMapping("/api/allbank")
    public String allBank(@RequestBody Bank data) {
        try {
            List<Bank> bank = bankMapper.selectAllBank(data.getPage() * 10 - 10, data.getUuid());
            int c = bankMapper.selectAllCount(data.getUuid());
//            System.out.print(c);
            // 将获取的json数据封装一层，然后在给返回
            JSONObject result = new JSONObject();
            if (bank != null) {
                result.put("code", 200);
                result.put("data", bank);
                result.put("total", c);
            } else {
                result.put("code", 300);
            }

            return result.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("code", 400);
            result.put("data", "失败");
            return result.toJSONString();
        }
    }


    //    查询个人用户未删除题库
    @SneakyThrows
    @PostMapping("/api/selfbank")
    public String selfbank(@RequestBody Bank data) {
        try {
            List<Bank> bank = bankMapper.selectSelfBank(data.getUuid(), data.getPage() * 10 - 10);
            int n = bankMapper.selectSelfCount(data.getUuid());
//            System.out.print(n);
            // 将获取的json数据封装一层，然后在给返回
            JSONObject result = new JSONObject();
            if (bank != null) {
                result.put("code", 200);
                result.put("data", bank);
                result.put("total", n);
            } else {
                result.put("code", 300);
            }

            return result.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("code", 400);
            result.put("data", "失败");
            return result.toJSONString();
        }
    }

    //从全部题库添加到个人题库
//    查询个人用户未删除题库
    @SneakyThrows
    @PostMapping("/api/alltoself")
    public String alltoself(@RequestBody Bank data) {
        try {
            System.out.println(data);
            JSONObject result = new JSONObject();
            int n = bankMapper.selectIsInSelf(data.getUuid(), data.getBankid());
            if (n == 0) {
                int bank = bankMapper.insertAllToSelf(data.getUuid(), data.getBankid(), data.getBankname(), data.getBanktime(), data.getTinum());
                if (bank == 1) {
                    result.put("code", 200);
                    result.put("data", bank);
                    result.put("total", n);
                } else {
                    result.put("code", 300);
                    result.put("data", "加入失败，请重试");

                }

            } else {
                result.put("code", 350);
                result.put("data", "您的题库已存在");
            }

//            System.out.print(result);
            // 将获取的json数据封装一层，然后在给返回


            return result.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("code", 400);
            result.put("data", "失败，未知原因");
            return result.toJSONString();
        }
    }


    //    删除个人题库某一项数据
    @SneakyThrows
    @PostMapping("/api/deleteselfdata")
    public String deleteselfdata(@RequestBody Bank data) {
        try {
            int isSuccess = bankMapper.deleteSelfData(data.getFirstid());
//            System.out.print(isSuccess);
            // 将获取的json数据封装一层，然后在给返回
            JSONObject result = new JSONObject();
            if (isSuccess == 1) {
                result.put("code", 200);
                result.put("data", "删除成功");
            } else {
                result.put("code", 300);
                result.put("data", "删除失败");
            }

            return result.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("code", 400);
            result.put("data", "未知失败");
            return result.toJSONString();
        }
    }


    //    添加题库
    @SneakyThrows
    @PostMapping("/api/addbank")
    public String addbank(@RequestBody Bank data) {
        try {
            int isSuccess = bankMapper.insertBank(data);
            // 将获取的json数据封装一层，然后在给返回
            JSONObject result = new JSONObject();
            if (isSuccess == 1) {
                result.put("code", 200);
                result.put("data", data.getBankid());
            } else {
                result.put("code", 300);
                result.put("data", "添加失败");
            }

            return result.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("code", 400);
            result.put("data", "未知失败");
            return result.toJSONString();
        }
    }


    //    填加题库
    @SneakyThrows
//    @PostMapping("/api/uploadbank")
    @PostMapping("/api/addtimu")
    public String addtimu(@RequestBody AddBank addbank) {

        JSONObject result = new JSONObject();
        try {


            result.put("code", 200);
            result.put("data", addbank.getTinum());
            List p = addbank.getTimudata();
            for (int i = 0; i < addbank.getTinum(); i++) {

                Object objectTiku = p.get(i);
                System.out.println(objectTiku);

                int insertTi = bankMapper.insertTi(objectTiku);
                if (insertTi == 0) {
                    result.put("code", 205);
                    result.put("data", objectTiku);
                    continue;
                }
            }


            return result.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 400);
            result.put("data", "失败，未知原因");
            return result.toJSONString();
        }
    }


    //    背题接口
    @SneakyThrows
    @PostMapping("/api/practice")
    public String practice(@RequestBody Tiku data) {
        try {
            List<Tiku> practice = bankMapper.practice(data.getBankid());

            // 将获取的json数据封装一层，然后在给返回
            JSONObject result = new JSONObject();
            if (practice != null) {
                result.put("code", 200);
                result.put("data", practice);
            } else {
                result.put("code", 300);

            }

            return result.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("code", 400);
            result.put("data", "失败");
            return result.toJSONString();
        }
    }


    //插入考试记录
    @SneakyThrows
    @PostMapping("/api/addexam")
    public String addexam(@RequestBody Exam data) {
        try {
            int isSuccess = bankMapper.insertExam(data);
            // 将获取的json数据封装一层，然后在给返回
            JSONObject result = new JSONObject();
            if (isSuccess == 1) {
                result.put("code", 200);
                result.put("data", "成功");
            } else {
                result.put("code", 300);
                result.put("data", "添加失败");
            }

            return result.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("code", 400);
            result.put("data", "未知失败");
            return result.toJSONString();
        }
    }


    //查询考试记录
    @SneakyThrows
    @PostMapping("/api/examlog")
    public String examlog(@RequestBody Bank data) {
        try {
            List<Exam> exam = bankMapper.selectExam(data.getUuid(), data.getPage() * 10 - 10);
            int n = bankMapper.selectExamCount(data.getUuid());
//            System.out.print(n);
            // 将获取的json数据封装一层，然后在给返回
            JSONObject result = new JSONObject();
            if (exam != null) {
                result.put("code", 200);
                result.put("data", exam);
                result.put("total", n);
            } else {
                result.put("code", 300);
            }

            return result.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("code", 400);
            result.put("data", "失败");
            return result.toJSONString();
        }
    }


}
