package com.example.demo.mapper;

import com.example.demo.entity.AddBank;
import com.example.demo.entity.Bank;
import com.example.demo.entity.Exam;
import com.example.demo.entity.Tiku;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BankMapper {


    //    以下为一组
//查询全部题库数量
    @Select("select count(*) from allbank where uuid!=#{uuid}")
    int selectAllCount(@Param("uuid") String uuid);

    //查询全部题库信息
    @Select("SELECT bankid,bankname,banktime,tinum FROM allbank where uuid!=#{uuid} LIMIT #{page},10")
//    Bank selectAllBank(@Param("page") int page);
    List<Bank> selectAllBank(@Param("page") int page, @Param("uuid") String uuid);


    //    以下为一组
//查询每个用户未删除题库数量
    @Select("select count(*) from selfbank where isdel=1 and uuid=#{uuid}")
    int selectSelfCount(@Param("uuid") String uuid);

    //    查询未删除题库
    @Select("SELECT firstid,bankid,bankname,banktime,tinum FROM selfbank where isdel=1 and uuid=#{uuid} LIMIT #{page},10")
    List<Bank> selectSelfBank(@Param("uuid") String uuid, @Param("page") int page);


    //从全部题库中插入个人题库
//    先查询是否已经存在
    @Select("SELECT COUNT(*) from selfbank WHERE uuid=#{uuid} and bankid=#{bankid}")
    int selectIsInSelf(@Param("uuid") String uuid, @Param("bankid") String bankid);

    //    插入数据
    @Insert("INSERT INTO selfbank (uuid,bankid,bankname,banktime,tinum) VALUES (#{uuid},#{bankid},#{bankname},#{banktime},#{tinum})")
    int insertAllToSelf(@Param("uuid") String uuid, @Param("bankid") String bankid, @Param("bankname") String bankname, @Param("banktime") String banktime, @Param("tinum") String tinum);


    //    删除个人题库数据
    @Delete("DELETE FROM selfbank WHERE firstid=#{firstid}")
    int deleteSelfData(@Param("firstid") String firstid);

    //插入题库
//    插入题目
    @Insert("INSERT INTO tiku (ttype,tabout,optionA,optionB,optionC,optionD,optionE,optionF,optionG,optionH,trueABCD,analysis,bankid) " +
            "VALUES (#{ttype},#{tabout},#{optionA},#{optionB},#{optionC},#{optionD},#{optionE},#{optionF},#{optionG},#{optionH},#{trueABCD},#{analysis},#{bankid})")
//    int insertTi(@Param("ttype") String ttype,@Param("tabout") String tabout,@Param("optionA") String optionA,@Param("optionB") String optionB,@Param("optionC") String optionC,
//                 @Param("optionD") String optionD,@Param("optionE") String optionE,@Param("optionF") String optionF,@Param("optionG") String optionG,@Param("optionH") String optionH,
//                 @Param("trueABCD") String trueABCD,@Param("analysis") String analysis);
    int insertTi(Object tiku);


    //    插入题库编号
    @Insert("INSERT INTO allbank (uuid,bankname,banktime,tinum) VALUES(#{uuid},#{bankname},#{banktime},#{tinum})")
    @Options(useGeneratedKeys = true, keyProperty = "bankid")
//    int insertBank(@Param("uuid") String uuid,@Param("bankname") String bankname,@Param("banktime") String banktime,@Param("tinum") String tinum);
    int insertBank(Bank addbank);


    //背题
    @Select("select * from tiku where bankid=#{bankid}")
    List<Tiku> practice(@Param("bankid") String bankid);


    //插入考试记录
    @Insert("INSERT INTO exam (\n" +
            "\texamid,\n" +
            "\tbankid,\n" +
            "\tuuid,\n" +
            "\tzong,\n" +
            "\tdanzong,\n" +
            "\tdan,\n" +
            "\tdandui,\n" +
            "\tduozong,\n" +
            "duo,\n" +
            "duodui,examtime) VALUES (#{examid},\n" +
            "    #{bankid},\n" +
            "    #{uuid},\n" +
            "    #{zong},\n" +
            "    #{danzong},\n" +
            "    #{dan},\n" +
            "    #{dandui},\n" +
            "    #{duozong},\n" +
            "    #{duo},\n" +
            "    #{duodui},#{examtime})")
    int insertExam(Exam exam);

    //查询考试记录
    @Select("SELECT count(*) FROM exam where uuid=#{uuid}")
    int selectExamCount(@Param("uuid") String uuid);

    @Select("SELECT * FROM examlog where uuid=#{uuid} LIMIT #{page},10")
    List<Exam> selectExam(@Param("uuid") String uuid, @Param("page") int page);


}

