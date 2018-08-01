package com.coinwind.bifeng.base;

import java.io.Serializable;

public class TaskBean implements Serializable {
    /**
     * shengyu_num : 25
     * img : http://coinwind.oss-cn-qingdao.aliyuncs.com/20180625195009_771.png
     * subject : 1
     * is_jp : 0
     * share_score : null
     * need_check : 1
     * type : 4
     * title :  Huobi Token 升级为火币全球生态通证
     * content :    <p style="font-family:"color:#374B5D;">
     * 尊敬的用户：
     * </p>
     * <p style="text-indent:24px;font-family:"color:#374B5D;">
     * 2018年2月，火币集团正式发布了火币全球通用积分 Huobi Token (HT)，并于3月份成立了以 HT 为核心的火币全球生态基金，标志着火币全球生态化战略的正式开启。2018年6月，Huobi Chain 全球公链领袖竞选计划宣告启动，这意味着在 2020 年 Huobi Chain 公链上线之际，Huobi Token 将升级成为基于 Huobi Chain 的全球通证。三个月以来，火币生态在全球范围内开展产业布局，寻找能够与火币正向协同的生态伙伴，不断用合作共赢产生的力量突破自身的界限，并投资了20余家优秀的区块链企业，包含交易平台、资讯平台、钱包、矿池、孵化器、资产管理机构等不同的业态，初步迈出了火币生态落地全球战略的第一步。<br />
     *     投资并不是生态伙伴与我们合作的终点，相反，投资是我们为用户创造价值的起点。正如传统股权架构中，当集团子业务及其投资的业务有进展时，集团亦将分享其发展带来的红利一样，火币希望所有投资过的生态火伴，在业务上能够与火币产生更强的协同作用，真正成为火币生态系统中的一部分，也让 HT的持有者能够享受到火币生态发展的整体红利。
     * </p>
     * <p style="text-indent:24px;font-family:"color:#374B5D;">
     * 所以，火币集团于今日正式启动对 Huobi Token 的升级计划：从“火币全球通用积分”，升级成为“火币全球生态通证”。我们决定：<br />
     * 1. 火币集团内的子业务线（包含子交易所、矿池、钱包、资讯等），未来均计划发行基于 Huobi Token 的生态子通证。在独立生态子通证发行时，所有火币平台上HT的持有者将直接按比例得到相应的生态子通证奖励，并将持续享有子业务线持续发展所带来的收益奖励。<br />
     * 2. 火币生态投资的所有生态火伴，其业务将与火币集团的业务深度结合，其 Token 亦可成为基于 Huobi Token 的生态子通证。火币生态将拿出所持有的生态火伴的 Token 赠送给所有火币平台上HT的持有者，并且希望生态火伴分享其业务发展所带来的部分收益奖励给到HT的持有者。<br />
     * 3. 火币自主数字资产交易所HADAX将开启火币生态专区，来提供HT与生态子通证的相互兑换。同时，目前已经启动面向火币全球生态基金投资的一期生态火伴的征集报名工作，视情况决定一期生态子通证的名单。
     * </p>
     * <p style="text-indent:24px;font-family:"color:#374B5D;">
     * 用户们的支持与鼓励是我们前行的最大动力。火币集团将一如既往的继续聚焦在切实创造价值，并且能够引领整个区块链行业的创新上面，真正推动行业健康可持续发展，并且给用户带来切实的收益。<br />
     * 具体开放生态专区的时间，请您近期关注火币 HADAX 公告。
     * </p>
     * <p style="text-indent:24px;font-family:"color:#374B5D;">
     * <div style="text-align:right;">
     * 火币集团
     * </div>
     * <div style="text-align:right;">
     * 2018年6月22日
     * </div>
     * </p>
     * <p style="text-align:justify;font-family:"">
     * <br />
     * </p>
     * score : 35
     * current_score : 900
     * logo : http://coinwind.oss-cn-qingdao.aliyuncs.com/20180705105034_942.jpeg
     * all_score : 970
     * exhort : 转发币圈，谢谢大家！
     * id : 1011215003916697600
     * ques_content : null
     * public_img : null
     * head_img : http://coinwind.oss-cn-qingdao.aliyuncs.com/20180718180448_848.jpg
     * end_time : 2018-07-31 19:44:21
     * gz_name : 火币
     * is_close : 0
     * label : 交易所,公告
     * url : null
     * is_top : 1
     * start_time : 2018-06-25 19:44:18
     * user_id : 1005374422548217856
     * lrrq : 2018-07-05 10:51:01
     * nick_name : 晓东
     * is_essence : 1
     * public_num : null
     * user_star : null
     * task_type : 4
     */

    private int shengyu_num;
    private String img;
    private String subject;
    private String is_jp;
    private Integer share_score;
    private String need_check;
    private String type;
    private String title;
    private String content;
    private int score;
    private int current_score;
    private String logo;
    private int all_score;
    private String exhort;
    private String id;
    private Object ques_content;
    private Object public_img;
    private String head_img;
    private String end_time;
    private String gz_name;
    private String is_close;
    private String label;
    private String url;
    private String is_top;
    private String start_time;
    private String user_id;
    private String lrrq;
    private String nick_name;
    private String is_essence;
    private Object public_num;
    private Object user_star;
    private String task_type;
    private String task_intro;

    public String getTask_intro() {
        return task_intro;
    }

    public void setTask_intro(String task_intro) {
        this.task_intro = task_intro;
    }

    public int getShengyu_num() {
        return shengyu_num;
    }

    public void setShengyu_num(int shengyu_num) {
        this.shengyu_num = shengyu_num;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIs_jp() {
        return is_jp;
    }

    public void setIs_jp(String is_jp) {
        this.is_jp = is_jp;
    }

    public Integer getShare_score() {
        return share_score;
    }

    public void setShare_score(Integer share_score) {
        this.share_score = share_score;
    }

    public String getNeed_check() {
        return need_check;
    }

    public void setNeed_check(String need_check) {
        this.need_check = need_check;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCurrent_score() {
        return current_score;
    }

    public void setCurrent_score(int current_score) {
        this.current_score = current_score;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getAll_score() {
        return all_score;
    }

    public void setAll_score(int all_score) {
        this.all_score = all_score;
    }

    public String getExhort() {
        return exhort;
    }

    public void setExhort(String exhort) {
        this.exhort = exhort;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getQues_content() {
        return ques_content;
    }

    public void setQues_content(Object ques_content) {
        this.ques_content = ques_content;
    }

    public Object getPublic_img() {
        return public_img;
    }

    public void setPublic_img(Object public_img) {
        this.public_img = public_img;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getGz_name() {
        return gz_name;
    }

    public void setGz_name(String gz_name) {
        this.gz_name = gz_name;
    }

    public String getIs_close() {
        return is_close;
    }

    public void setIs_close(String is_close) {
        this.is_close = is_close;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIs_top() {
        return is_top;
    }

    public void setIs_top(String is_top) {
        this.is_top = is_top;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLrrq() {
        return lrrq;
    }

    public void setLrrq(String lrrq) {
        this.lrrq = lrrq;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getIs_essence() {
        return is_essence;
    }

    public void setIs_essence(String is_essence) {
        this.is_essence = is_essence;
    }

    public Object getPublic_num() {
        return public_num;
    }

    public void setPublic_num(Object public_num) {
        this.public_num = public_num;
    }

    public Object getUser_star() {
        return user_star;
    }

    public void setUser_star(Object user_star) {
        this.user_star = user_star;
    }

    public String getTask_type() {
        return task_type;
    }

    public void setTask_type(String task_type) {
        this.task_type = task_type;
    }

    @Override
    public String toString() {
        return "TaskBean{" +
                "shengyu_num=" + shengyu_num +
                ", img='" + img + '\'' +
                ", subject='" + subject + '\'' +
                ", is_jp='" + is_jp + '\'' +
                ", share_score=" + share_score +
                ", need_check='" + need_check + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", score=" + score +
                ", current_score=" + current_score +
                ", logo='" + logo + '\'' +
                ", all_score=" + all_score +
                ", exhort='" + exhort + '\'' +
                ", id='" + id + '\'' +
                ", ques_content=" + ques_content +
                ", public_img=" + public_img +
                ", head_img='" + head_img + '\'' +
                ", end_time='" + end_time + '\'' +
                ", gz_name='" + gz_name + '\'' +
                ", is_close='" + is_close + '\'' +
                ", label='" + label + '\'' +
                ", url='" + url + '\'' +
                ", is_top='" + is_top + '\'' +
                ", start_time='" + start_time + '\'' +
                ", user_id='" + user_id + '\'' +
                ", lrrq='" + lrrq + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", is_essence='" + is_essence + '\'' +
                ", public_num=" + public_num +
                ", user_star=" + user_star +
                ", task_type='" + task_type + '\'' +
                ", task_intro='" + task_intro + '\'' +
                '}';
    }
}
