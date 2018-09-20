package com.coinwind.bifeng.ui.record.bean;

import java.util.List;

/**
 * 矿产记录的bean
 */
public class RecordBean {

    /**
     * code : 1000
     * msg : 请求成功！
     * state : true
     * data : {"ccLog":{"totalRow":66,"pageNumber":1,"firstPage":true,"lastPage":false,"totalPage":22,"pageSize":3,"list":[{"cc":8.42544,"build_date":"2018-09-17 03:00:00","note":null,"user_id":"1041637510574243840","build_time":1537124400000,"id":"1041657579417108480","create_date":"2018-09-17 19:58:15","m_tab":"ffffffff-f480-0bf5-0000-000062ca3591"},{"cc":8.4249,"build_date":"2018-09-17 04:00:00","note":null,"user_id":"1041637510574243840","build_time":1537128000000,"id":"1041657581375848448","create_date":"2018-09-17 19:58:15","m_tab":"ffffffff-f480-0bf5-0000-000062ca3591"},{"cc":8.42624,"build_date":"2018-09-17 01:30:00","note":null,"user_id":"1041637510574243840","build_time":1537119000000,"id":"1041657625860636672","create_date":"2018-09-17 19:58:26","m_tab":"ffffffff-f480-0bf5-0000-000062ca3591"}]},"state":true}
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
         * ccLog : {"totalRow":66,"pageNumber":1,"firstPage":true,"lastPage":false,"totalPage":22,"pageSize":3,"list":[{"cc":8.42544,"build_date":"2018-09-17 03:00:00","note":null,"user_id":"1041637510574243840","build_time":1537124400000,"id":"1041657579417108480","create_date":"2018-09-17 19:58:15","m_tab":"ffffffff-f480-0bf5-0000-000062ca3591"},{"cc":8.4249,"build_date":"2018-09-17 04:00:00","note":null,"user_id":"1041637510574243840","build_time":1537128000000,"id":"1041657581375848448","create_date":"2018-09-17 19:58:15","m_tab":"ffffffff-f480-0bf5-0000-000062ca3591"},{"cc":8.42624,"build_date":"2018-09-17 01:30:00","note":null,"user_id":"1041637510574243840","build_time":1537119000000,"id":"1041657625860636672","create_date":"2018-09-17 19:58:26","m_tab":"ffffffff-f480-0bf5-0000-000062ca3591"}]}
         * state : true
         */

        private CcLogBean ccLog;
        private boolean state;
        private int code;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public CcLogBean getCcLog() {
            return ccLog;
        }

        public void setCcLog(CcLogBean ccLog) {
            this.ccLog = ccLog;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public static class CcLogBean {
            /**
             * totalRow : 66
             * pageNumber : 1
             * firstPage : true
             * lastPage : false
             * totalPage : 22
             * pageSize : 3
             * list : [{"cc":8.42544,"build_date":"2018-09-17 03:00:00","note":null,"user_id":"1041637510574243840","build_time":1537124400000,"id":"1041657579417108480","create_date":"2018-09-17 19:58:15","m_tab":"ffffffff-f480-0bf5-0000-000062ca3591"},{"cc":8.4249,"build_date":"2018-09-17 04:00:00","note":null,"user_id":"1041637510574243840","build_time":1537128000000,"id":"1041657581375848448","create_date":"2018-09-17 19:58:15","m_tab":"ffffffff-f480-0bf5-0000-000062ca3591"},{"cc":8.42624,"build_date":"2018-09-17 01:30:00","note":null,"user_id":"1041637510574243840","build_time":1537119000000,"id":"1041657625860636672","create_date":"2018-09-17 19:58:26","m_tab":"ffffffff-f480-0bf5-0000-000062ca3591"}]
             */

            private int totalRow;
            private int pageNumber;
            private boolean firstPage;
            private boolean lastPage;
            private int totalPage;
            private int pageSize;
            private List<ListBean> list;
            private double allcc;

            public double getAllcc() {
                return allcc;
            }

            public void setAllcc(double allcc) {
                this.allcc = allcc;
            }

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
                 * cc : 8.42544
                 * build_date : 2018-09-17 03:00:00
                 * note : null
                 * user_id : 1041637510574243840
                 * build_time : 1537124400000
                 * id : 1041657579417108480
                 * create_date : 2018-09-17 19:58:15
                 * m_tab : ffffffff-f480-0bf5-0000-000062ca3591
                 */

                private double cc;
                private String build_date;
                private String note;
                private String user_id;
                private long build_time;
                private String id;
                private String create_date;
                private String m_tab;

                public double getCc() {
                    return cc;
                }

                public void setCc(double cc) {
                    this.cc = cc;
                }

                public String getBuild_date() {
                    return build_date;
                }

                public void setBuild_date(String build_date) {
                    this.build_date = build_date;
                }

                public String getNote() {
                    return note;
                }

                public void setNote(String note) {
                    this.note = note;
                }

                public String getUser_id() {
                    return user_id;
                }

                public void setUser_id(String user_id) {
                    this.user_id = user_id;
                }

                public long getBuild_time() {
                    return build_time;
                }

                public void setBuild_time(long build_time) {
                    this.build_time = build_time;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getCreate_date() {
                    return create_date;
                }

                public void setCreate_date(String create_date) {
                    this.create_date = create_date;
                }

                public String getM_tab() {
                    return m_tab;
                }

                public void setM_tab(String m_tab) {
                    this.m_tab = m_tab;
                }
            }
        }
    }
}
