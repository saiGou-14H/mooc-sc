package com.org.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 班级表
 *
 * </p>
 *
 * @author Jie
 * @since 2022-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_class")
public class MClass implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班级ID，雪花ID后8位
     */
    @TableId("id")
    private Long id;

    /**
     * 教师id,对应用户表
     */
    @TableField("tea_id")
    private Long teaId;

    /**
     * 班级名称
     */
    @TableField("name")
    private String name;

    /**
     * 班级人数
     */
    @TableField("numbers")
    private Integer numbers;

    /**
     * 默认值：0已删除：1
     */
    @TableField("deleted")
    private Boolean deleted;


}

