package com.coinwind.bifeng.ui.my.bean;

import java.util.List;

/**
 * 我的钱包的bean
 */
public class WalletBean {

    /**
     * code : 1000
     * msg : 请求成功！
     * state : true
     * data : {"bfCssLog":{"totalRow":2,"pageNumber":1,"firstPage":true,"lastPage":false,"totalPage":2,"pageSize":1,"list":[{"fromm":null,"note":"每日签到","css":"+10","too":"1013989360678207488","lrrq":"2018-07-03 11:35:32","task_id":null,"id":"1013989590211493888","type":"c1","fencheng":null}]},"currentCss":98,"todayCss":0}
     */

    private int code;
    private String msg;
    private boolean state;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bfCssLog : {"totalRow":2,"pageNumber":1,"firstPage":true,"lastPage":false,"totalPage":2,"pageSize":1,"list":[{"fromm":null,"note":"每日签到","css":"+10","too":"1013989360678207488","lrrq":"2018-07-03 11:35:32","task_id":null,"id":"1013989590211493888","type":"c1","fencheng":null}]}
         * currentCss : 98
         * todayCss : 0
         */

        private BfCssLogBean bfCssLog;
        private int currentCss;
        private int todayCss;

        public BfCssLogBean getBfCssLog() {
            return bfCssLog;
        }

        public void setBfCssLog(BfCssLogBean bfCssLog) {
            this.bfCssLog = bfCssLog;
        }

        public int getCurrentCss() {
            return currentCss;
        }

        public void setCurrentCss(int currentCss) {
            this.currentCss = currentCss;
        }

        public int getTodayCss() {
            return todayCss;
        }

        public void setTodayCss(int todayCss) {
            this.todayCss = todayCss;
        }

        public static class BfCssLogBean {
            /**
             * totalRow : 2
             * pageNumber : 1
             * firstPage : true
             * lastPage : false
             * totalPage : 2
             * pageSize : 1
             * list : [{"fromm":null,"note":"每日签到","css":"+10","too":"1013989360678207488","lrrq":"2018-07-03 11:35:32","task_id":null,"id":"1013989590211493888","type":"c1","fencheng":null}]
             */

            private int totalRow;
            private int pageNumber;
            private boolean firstPage;
            private boolean lastPage;
            private int totalPage;
            private int pageSize;
            private List<ListBean> list;

            public int getTotalRow() {
                return totalRow;
            }

            public void setTotalRow(int totalRow) {
                this.totalRow = totalRow;
            }

            public int getPageNumber() {
                return pageNumber;
            }

            public void setPageNumber(int pageNumber) {
                this.pageNumber = pageNumber;
            }

            public boolean isFirstPage() {
                return firstPage;
            }

            public void setFirstPage(boolean firstPage) {
                this.firstPage = firstPage;
            }

            public boolean isLastPage() {
                return lastPage;
            }

            public void setLastPage(boolean lastPage) {
                this.lastPage = lastPage;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * fromm : null
                 * note : 每日签到
                 * css : +10
                 * too : 1013989360678207488
                 * lrrq : 2018-07-03 11:35:32
                 * task_id : null
                 * id : 1013989590211493888
                 * type : c1
                 * fencheng : null
                 */

                private Object fromm;
                private String note;
                private String css;
                private String too;
                private String lrrq;
                private Object task_id;
                private String id;
                private String type;
                private Object fencheng;

                public Object getFromm() {
                    return fromm;
                }

                public void setFromm(Object fromm) {
                    this.fromm = fromm;
                }

                public String getNote() {
                    return note;
                }

                public void setNote(String note) {
                    this.note = note;
                }

                public String getCss() {
                    return css;
                }

                public void setCss(String css) {
                    this.css = css;
                }

                public String getToo() {
                    return too;
                }

                public void setToo(String too) {
                    this.too = too;
                }

                public String getLrrq() {
                    return lrrq;
                }

                public void setLrrq(String lrrq) {
                    this.lrrq = lrrq;
                }

                public Object getTask_id() {
                    return task_id;
                }

                public void setTask_id(Object task_id) {
                    this.task_id = task_id;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public Object getFencheng() {
                    return fencheng;
                }

                public void setFencheng(Object fencheng) {
                    this.fencheng = fencheng;
                }
            }
        }
    }
}
