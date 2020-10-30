package com.elimen.ins.broker.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 行政区划表
 * </p>
 *
 * @author elimen
 * @since 2020-10-30
 */
public class DistrictCode extends Model<DistrictCode> {

    private static final long serialVersionUID = 1L;

    /**
     * 行政区划代码
     */
    private String code;

    /**
     * 名字
     */
    private String name;

    /**
     * 等级:1-省级;2-地级市;3-区/县;4-乡/镇
     */
    private String level;

    /**
     * 类型:1-省;2-自治区;3-直辖市;4-特别行政区;5-地级市;6-地区;7-自治州;8-盟;9-市辖区;10-县;11- 县级市;12-自治县;13-旗;14-自治旗;15-特区;16-林区
     */
    private String type;

    /**
     * 简称
     */
    private String abname;

    /**
     * 所属行政区划代码
     */
    private String pid;

    /**
     * 所属行政区划名字
     */
    private String pname;

    /**
     * 备注
     */
    private String note;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getAbname() {
        return abname;
    }

    public void setAbname(String abname) {
        this.abname = abname;
    }
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    protected Serializable pkVal() {
        return this.code;
    }

    @Override
    public String toString() {
        return "DistrictCode{" +
            "code=" + code +
            ", name=" + name +
            ", level=" + level +
            ", type=" + type +
            ", abname=" + abname +
            ", pid=" + pid +
            ", pname=" + pname +
            ", note=" + note +
        "}";
    }
}
