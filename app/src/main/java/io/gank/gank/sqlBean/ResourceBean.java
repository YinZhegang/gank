package io.gank.gank.sqlBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yinzhegang on 2019/3/25.
 */
@Entity
public class ResourceBean {
    @Id
    private Long id;
    private String url;
    private String create;
    private String desc;
    private String published;
    private String source;
    private String type;
    private String who;
    @Generated(hash = 650952075)
    public ResourceBean(Long id, String url, String create, String desc,
            String published, String source, String type, String who) {
        this.id = id;
        this.url = url;
        this.create = create;
        this.desc = desc;
        this.published = published;
        this.source = source;
        this.type = type;
        this.who = who;
    }
    @Generated(hash = 1663390416)
    public ResourceBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getCreate() {
        return this.create;
    }
    public void setCreate(String create) {
        this.create = create;
    }
    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getPublished() {
        return this.published;
    }
    public void setPublished(String published) {
        this.published = published;
    }
    public String getSource() {
        return this.source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getWho() {
        return this.who;
    }
    public void setWho(String who) {
        this.who = who;
    }

   
}
